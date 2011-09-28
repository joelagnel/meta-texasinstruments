PR = "r1"

require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "beaglebone"
DEFAULT_PREFERENCE_beaglebone = "99"

SRC_URI = "git://github.com/joelagnel/u-boot.git;branch=bone-bringup \
          "
SRCREV = "9fb70a0afe46e15d977af072854f3b13de8830ce"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git"
