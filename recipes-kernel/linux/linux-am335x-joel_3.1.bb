require linux.inc

DESCRIPTION = "Linux kernel for AM335x processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beaglebone"
DEFAULT_PREFERENCE = "-1"

SRCREV = "da6bf24fa53a44d7ee7298fa0a41c55b8f59f044"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "b+gitr${SRCREV}"

SRC_URI = "git://github.com/joelagnel/linux-omap-2.6.git;branch=kernel-3.1-psp \
file://0001-ARM-omap-am33xx-ADD-support-i2c1.patch \
file://0002-AM335X-Avoid-i2c-pin-mux-setup.patch \
file://0003-Consolidate-SRAM-support.patch \
file://0004-ARM-omap-am33xx-move-edma.h-to-common-place.patch \
file://0005-ARM-omap-am33xx-Make-Dummy-Regulator-default-selecte.patch \
file://0006-ARM-davinci-mcasp-Move-McASP-related-defines-to-comm.patch \
file://0007-ARM-davinci-mcasp-remove-unused-header-file.patch \
file://0008-ARM-davinci-mcasp-Support-new-McASP-IP-Variant.patch \
file://0009-ARM-omap-am33xx-ASoC-Add-support-for-am33xx-in-ASoC.patch \
file://0010-ARM-omap-am33xx-Register-McASP-Platform-on-AM335x-EV.patch \
file://0011-ARM-omap-am33xx-McASP-hookup-for-AM335x-EVM.patch \
file://0012-ARM-omap-am33xx-McASP-update-SRAM-varibale-for-am33x.patch \
file://0013-ARM-omap-am33xx-ASoC-Enable-Audio-support-for-AM335x.patch \
file://0014-AM335X-Enable-frame-buffer-and-backlight-driver-in-d.patch \
file://0015-AM335X-Enable-touchscreen-driver-in-default-am335x_e.patch \
file://0016-ARM-omap-hsmmc-use-clk_get_rate-instead-of-hard-codi.patch \
file://0017-ARM-omap-hsmmc-Support-new-HSMMC-IP-Variant.patch \
file://0018-ARM-omap-hsmmc-Enable-card-Insert-Removal-interrupt.patch \
file://0019-ARM-omap-am33xx-Register-HSMMC-Platform-on-AM335x-EV.patch \
file://0020-ARM-omap-am33xx-HSMMC-update-hwmod-data.patch \
file://0001-am335x-evm-hack-in-LED-support-for-beaglebone.patch \
file://0001-f_rndis-HACK-around-undefined-variables.patch \
           file://defconfig"

S = "${WORKDIR}/git"


