require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "(beagleboard)"

SRC_URI = "git://github.com/joelagnel/u-boot.git;branch=bone-bringup \
          "
SRCREV = "b9c1e88dff6d738c6595e0614bbcfd5b16367e29"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

# Hack machine for bone bringup
UBOOT_MACHINE = "am335x_evm_config"

S = "${WORKDIR}/git"
