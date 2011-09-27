require linux.inc

DESCRIPTION = "Linux kernel for AM335x processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beagleboard"

SRCREV = "76635cff726904c693a820032eea6e0c07a2f1d3"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a+gitr${SRCREV}"

SRC_URI = "git://github.com/joelagnel/linux-omap-2.6.git;branch=bone-bringup \
           file://defconfig"

S = "${WORKDIR}/git"

# Perf in 2.6.38-psp doesn't build, so disable it
do_compile_perf() {
    :
}

do_install_perf() {
    :
}

