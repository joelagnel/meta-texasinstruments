require linux.inc

DESCRIPTION = "Linux kernel for AM335x processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beaglebone"

SRCREV = "90b625c03fe07ff4e285260172fb642a81d666ba"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "d+gitr${SRCREV}"

SRC_URI = "git://github.com/joelagnel/linux-omap-2.6.git;branch=bone-bringup \
           file://0001-board-am335x-evm-hack-in-gpio-led-support-for-beagle.patch \
           file://defconfig"

S = "${WORKDIR}/git"

# Perf in 2.6.38-psp doesn't build, so disable it
do_compile_perf() {
    :
}

do_install_perf() {
    :
}

