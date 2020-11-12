SUMMARY = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"

inherit allarch

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
DEPENDS += "uhd-native"

inherit python3native

PACKAGES = "${PN}"
FILES_${PN} = "${datadir}/uhd/images"

S = "${WORKDIR}"
S_DL = "${S}/dl"

do_configure[noexec] = "1"

UHD_FIRMWARE_TYPE = ""
UHD_FIRMWARE_TYPE_ettus-e300 = "e3xx_e310.*"
do_compile () {
    uhd_images_downloader -v -v -v -i ${S_DL} -t "${UHD_FIRMWARE_TYPE}"
}
do_compile[cleandirs] = "${S_DL}"

do_install() {
    install -d ${D}${datadir}/uhd/images
    install -m 0644 ${S_DL}/* ${D}${datadir}/uhd/images
}

do_install_append_ettus-e3xx-sg1 () {
    # Move the default e3x bit files
    # Also the RFNoC device doesn't like usrp_e310_fpga.bit existing (because it creates its own symlinks)
    # It's the same as the RFNOC one.
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_default.bit
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_default.bit
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_sg3.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_sg3_default.bit
}
