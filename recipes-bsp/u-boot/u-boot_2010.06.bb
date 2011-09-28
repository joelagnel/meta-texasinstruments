PR = "r2"

require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "beaglebone"
DEFAULT_PREFERENCE_beaglebone = "99"

SRC_URI = "git://github.com/joelagnel/u-boot.git;branch=bone-bringup \
          "
SRCREV = "5387a71d905ea3bccd8bc4c9719e9292e96ed9d0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git"
