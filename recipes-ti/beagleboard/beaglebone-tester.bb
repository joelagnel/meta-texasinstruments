DESCRIPTION = "BeagleBone tester scripts"
HOMEPAGE = "http://beagleboard.org/support"

PR="r2"

SRC_URI = "git://github.com/joelagnel/validation-scripts.git;protocol=git \
"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://gpl.txt;md5=5b122a36d0f6dc55279a0ebc69f3c60b"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

do_install() {
  install -d ${D}/lib/systemd/system/
  install -d ${D}/var/lib/bone-tester/component/
  install -m 0755 ${S}/bone-tester/bone-tester.service ${D}/lib/systemd/system/bone-tester.service
  install -m 0755 ${S}/bone-tester/init.sh ${D}/${sysconfdir}/var/lib/bone-tester/init.sh
  for i in ${S}/bone-tester/component/* ; do
    install -m 0755 ${i} ${D}/var/lib/bone-tester/component/
  done
}
