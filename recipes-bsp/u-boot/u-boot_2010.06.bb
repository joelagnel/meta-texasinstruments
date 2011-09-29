PR = "r3"

require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "beaglebone"
DEFAULT_PREFERENCE_beaglebone = "99"

SRC_URI = "git://github.com/joelagnel/u-boot.git;branch=bone-bringup \
          "
SRCREV = "81d39e0c2a88bfee58c8b7a3f8457869490cbba6"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git"
