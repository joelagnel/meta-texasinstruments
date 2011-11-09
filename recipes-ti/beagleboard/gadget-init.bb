DESCRIPTION = "Units to initialize usb gadgets"

PR = "r4"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

COMPATIBLE_MACHINE = "(ti33x)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://storage-gadget-init.service \
           file://network-gadget-init.service \
           file://udhcpd.service \
           file://udhcpd.conf \
           file://99-hokey-pokey.rules \
           file://hokey-pokey.sh \
           file://bone-gmass-eject.rules \
           file://g-storage-reinsert.sh \
           file://g-ether-insert.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system
	for i in ${WORKDIR}/*.service ; do
		install -m 0644 $i ${D}${base_libdir}/systemd/system
		ln -sf ../$(basename $i) ${D}${base_libdir}/systemd/system/basic.target.wants/
	done

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/*.rules ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/*.conf ${D}${sysconfdir}

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}
}

FILES_${PN} = "${base_libdir}/systemd ${sysconfdir} ${bindir}"
