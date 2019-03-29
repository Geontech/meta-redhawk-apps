require uhd-firmware.inc

FILESEXTRAPATHS_prepend = "${THISDIR}/${BPN}-${PV}:"
SRC_URI_append_ettus-e300 = "\
    file://usrp_e310_fpga_RFNOC.bin \
    file://usrp_e310_fpga_RFNOC.bit \
    "

do_compile_append_ettus-e300 () {
    # The images pulled by the downloader for RFNoC have an incompatible
    # noc_shell version (2.x).  This task replaces the downloaded RFNoC
    # e310 images with ones built from source, which are correct for this
    # release of UHD.
    cp ${WORKDIR}/usrp_e310_fpga_RFNOC.* ${S_DL}
}

