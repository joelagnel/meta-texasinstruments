DESCRIPTION = "BeagleBone tester scripts"
HOMEPAGE = "http://beagleboard.org/support"

SRC_URI = "git://github.com/joelagnel/validation-scripts.git;protocol=git \
"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://gpl.txt;md5=5b122a36d0f6dc55279a0ebc69f3c60b"

SRCREV = "e9b6378ae1be1d63626e4c177167cd192aaee166"
S = "${WORKDIR}/git"

inherit update-rc.d
INITSCRIPT_NAME = "bone-tester/test-init.sh"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 ."

do_install() {
  install -d ${D}/${sysconfdir}/init.d/
  install -d ${D}/${sysconfdir}/init.d/bone-tester/
  install -m 0755 ${S}/bone-tester/test-init.sh ${D}/${sysconfdir}/init.d/bone-tester/test-init.sh
  install -d ${D}/${sysconfdir}/init.d/bone-tester/component/
  for i in ${S}/bone-tester/component/*; do
    install -m 0755 ${i} ${D}/${sysconfdir}/init.d/bone-tester/component/
  done
}
