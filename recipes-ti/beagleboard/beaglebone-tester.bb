DESCRIPTION = "BeagleBone tester scripts"
HOMEPAGE = "http://beagleboard.org/support"

SRC_URI = "git://github.com/joelagnel/validation-scripts.git;protocol=git \
"

RDEPENDS_${PN}="kernel-module-g-zero \
                kernel-module-g-file-storage \
                kernel-module-smsc95xx"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://gpl.txt;md5=5b122a36d0f6dc55279a0ebc69f3c60b"

SRCREV = "1b0b8d45cf0718a1768503e8f7616aff6fc3ca03"
S = "${WORKDIR}/git"

FILES_${PN} += "/lib/systemd/system/bone-tester.service \
		/lib/systemd/system/multi-user.target.wants/bone-tester.service \
		/boot/uEnv.txt \
"

do_install() {
  install -d ${D}/var/lib/bone-tester/component/data/
  install -d ${D}/var/lib/bone-tester/init-scripts/
  install -d ${D}/var/lib/bone-tester/lib/
  install -d ${D}/lib/systemd/system/multi-user.target.wants/
  install -d ${D}/boot/
  install -m 0755 ${S}/bone-tester/init-scripts/uEnv.txt ${D}/boot/uEnv.txt

  # systemd configuration
  ln -s ../bone-tester.service ${D}/lib/systemd/system/multi-user.target.wants/bone-tester.service
  install -m 0755 ${S}/bone-tester/init-scripts/bone-tester.service ${D}/lib/systemd/system/bone-tester.service
  install -m 0755 ${S}/bone-tester/init-scripts/init.sh ${D}/var/lib/bone-tester/init-scripts/init.sh

  for i in $(find ${S}/bone-tester/component/ -maxdepth 1 -type f) ; do
    install -m 0755 ${i} ${D}/var/lib/bone-tester/component/
  done
  for i in ${S}/bone-tester/component/data/* ; do
    install -m 0755 ${i} ${D}/var/lib/bone-tester/component/data/
  done
  for i in ${S}/bone-tester/lib/* ; do
    install -m 0755 ${i} ${D}/var/lib/bone-tester/lib/
  done
}
