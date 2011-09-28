PR = "r1"

require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "(beagleboard)"

SRC_URI = "git://github.com/joelagnel/u-boot.git;branch=bone-bringup \
          "
SRCREV = "9fb70a0afe46e15d977af072854f3b13de8830ce"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

# Hack machine for bone bringup
UBOOT_MACHINE = "am335x_evm_config"

S = "${WORKDIR}/git"
