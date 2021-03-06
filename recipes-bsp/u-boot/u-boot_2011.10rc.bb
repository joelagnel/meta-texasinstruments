require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "(ti33x)"
DEFAULT_PREFERENCE_ti33x = "99"
PV = "2011.09+git"
PR = "r23"

# SPL build
UBOOT_BINARY = "u-boot.img"
UBOOT_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_SYMLINK = "u-boot-${MACHINE}.img"

SRC_URI = "git://arago-project.org/git/projects/u-boot-am33x.git;protocol=git;branch=int_am335xpsp_04.06.00.01-v2011.09-for-sdk-05.03.00.00 \
           file://2011.09git/0001-am335x_evm-only-do-in-kernel-dhcp-when-using-NFS-use.patch \
           file://2011.09git/0002-am335x_evm-boot-kernel-from-boot-in-ext2-3-filesyste.patch \
           file://2011.09git/0003-am335x_evm-set-bootdelay-to-1.patch \
           file://2011.09git/0004-am335x-evm-make-MMC-rootfs-RO-on-boot-so-fsck-works.patch \
           file://2011.09git/0005-am335x-Change-mmc_load_uimage-to-load-at-a-fixed-add.patch \
           file://2011.09git/0006-am335x-evm-Fix-bone-pmic-shut-down-over-USB-power.patch \
           file://2011.09git/0007-am335x_evm-switch-to-ext4.patch \
           file://2011.09git/0008-HACK-am335x-evm-turn-d-cache-on-globally-turn-it-off.patch \
          "

SRCREV = "f63b270e47f62f4d1a05b2001357e215966c6f5a"

LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

S = "${WORKDIR}/git"

do_deploy_append () {
    install ${S}/MLO ${DEPLOY_DIR_IMAGE}/MLO
}
