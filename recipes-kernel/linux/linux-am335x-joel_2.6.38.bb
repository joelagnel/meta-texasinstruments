require linux.inc

DESCRIPTION = "Linux kernel for AM335x processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beaglebone"

SRCREV = "be0eb88dcb70a31842023755e2a0ededa16e03ad"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "g+gitr${SRCREV}"

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

