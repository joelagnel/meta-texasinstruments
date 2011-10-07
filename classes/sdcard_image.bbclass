inherit image

IMAGE_FSTYPES_append = " sdimg"

IMAGE_CMD_sdimg () {
	SDIMG=${WORKDIR}/sd.img

	# cleanup loops
	for loop in $(/sbin/losetup -a | grep ${SDIMG}); do
		loop_dev=$(echo $loop|cut -d ":" -f 1)
		umount $loop_dev || true
		/sbin/losetup -d $loop_dev || true
	done

	dd if=/dev/zero of=${SDIMG} bs=$(echo '255 * 63 * 512' | bc) count=444
	LOOPDEV=$(/sbin/losetup -f)
	/sbin/losetup ${LOOPDEV} ${SDIMG}

	# Create partition table
	#dd if=/dev/zero of=${LOOPDEV} bs=1024 count=1024
	SIZE=$(/sbin/fdisk -l ${LOOPDEV} | grep Disk | grep bytes | awk '{print $5}')
	CYLINDERS=$(echo $SIZE/255/63/512 | bc)
	{
	echo ,9,0x0C,*
	echo ,,,-
	} | /sbin/sfdisk -D -H 255 -S 63 -C ${CYLINDERS} ${LOOPDEV}

	# Prepare loop devices for boot and filesystem partitions
	BOOT_OFFSET=32256
	FS_OFFSET_SECT=$(/sbin/fdisk -l -u $LOOPDEV 2>&1 | grep Linux | perl -p -i -e "s/\s+/ /"|cut -d " " -f 2)
	FS_OFFSET=$(echo "$FS_OFFSET_SECT * 512" | bc)
	FS_SIZE_BLOCKS=$(/sbin/fdisk -l -u $LOOPDEV 2>&1 | grep Linux | perl -p -i -e "s/\s+/ /g" \ 
	|cut -d " " -f 4 | cut -d "+" -f 1)
 
	LOOPDEV_BLOCKS=$(/sbin/fdisk -l -u $LOOPDEV 2>&1 | grep FAT | perl -p -i -e "s/\s+/ /g"|cut -d " " -f 5)
	LOOPDEV_BYTES=$(echo "$LOOPDEV_BLOCKS * 1024" | bc)

	LOOPDEV_BOOT=$(/sbin/losetup -f)
	/sbin/losetup ${LOOPDEV_BOOT} ${SDIMG} -o ${BOOT_OFFSET} 

	/sbin/mkfs.msdos ${LOOPDEV_BOOT} -n boot

	LOOPDEV_FS=$(/sbin/losetup -f)
	/sbin/losetup ${LOOPDEV_FS} ${SDIMG} -o ${FS_OFFSET}	

	# Prepare filesystem partition
	# Copy ubi used by flashing scripts
	if [ -e  ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ] ; then
		echo "Copying UBIFS image to file system"
		cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${IMAGE_ROOTFS}/boot/fs.ubi
	fi
	ROOTFS_SIZE="$(du -ks ${IMAGE_ROOTFS} | awk '{print 65536 + $1}')"
	genext2fs -b ${FS_SIZE_BLOCKS} -d ${IMAGE_ROOTFS} ${LOOPDEV_FS}
	/sbin/tune2fs -j ${LOOPDEV_FS}

	# Prepare boot partion. First mount the boot partition, and copy the boot loader and supporting files
	# from the root filesystem

	# sanity check fstab entry for boot partition mounting
	if [ "x$(cat /etc/fstab | grep $LOOPDEV_BOOT | grep ${WORKDIR}/tmp-mnt-boot | grep user || true)" = "x" ]; then
		echo "/etc/fstab entries need to be created with the user flag for $LOOPDEV_BOOT like:"
		echo "$LOOPDEV_BOOT ${WORKDIR}/tmp-mnt-boot msdos user 0 0"
		false
	fi

	mkdir -p ${WORKDIR}/tmp-mnt-boot
	mount $LOOPDEV_BOOT

	echo "Copying bootloaders into the boot partition"
	if [ -e ${IMAGE_ROOTFS}/boot/MLO ] ; then
		cp -v ${IMAGE_ROOTFS}/boot/MLO ${WORKDIR}/tmp-mnt-boot 
	else
		cp -v ${DEPLOY_DIR_IMAGE}/MLO ${WORKDIR}/tmp-mnt-boot
	fi

	# Check for u-boot SPL
	if [ -e u-boot-${MACHINE}.img ] ; then
		suffix=img
	else
		suffix=bin
	fi

	if [ -e ${IMAGE_ROOTFS}/boot/u-boot.$suffix ] ; then
		cp -v ${IMAGE_ROOTFS}/boot/{u-boot.$suffix,user.txt,uEnv.txt} ${WORKDIR}/tmp-mnt-boot || true
	else
		cp -v ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.$suffix ${WORKDIR}/tmp-mnt-boot 
	fi

	# cleanup
	umount ${LOOPDEV_BOOT}
	/sbin/losetup -d ${LOOPDEV}
	/sbin/losetup -d ${LOOPDEV_BOOT}
	/sbin/losetup -d ${LOOPDEV_FS}

	gzip -c ${WORKDIR}/sd.img > ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}-${PR}.img.gz
}
