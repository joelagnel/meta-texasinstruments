DESCRIPTION = "BeagleBone Getting Started Guide"

PR = "r3"

inherit allarch

LICENSE = "GPLv2+ && MIT && PD && others"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=603591dea023c3c75b48e07cb47ce639"

SRCREV = "f2f23b6031eeb21cef062dbfb035cc10f9603ff2"
SRC_URI = "git://github.com/jadonk/beaglebone-getting-started.git"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${datadir}/${PN}
	cp -a ${S}/* ${D}${datadir}/${PN}
}

FILES_${PN} += "${datadir}/${PN}"
