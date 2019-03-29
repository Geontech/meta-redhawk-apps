require uhd-firmware.inc

FILESEXTRAPATHS_prepend = "${THISDIR}/${BPN}-${PV}:"
SRC_URI_append_ettus-e3xx-sg1 = "\
    file://usrp_e310_fpga_RFNOC.bin \
    file://usrp_e310_fpga_RFNOC.bit \
    "

do_compile_append_ettus-e3xx-sg1 () {
    # The images pulled by the downloader for RFNoC have an incompatible
    # noc_shell version (2.x).  This task replaces the downloaded RFNoC
    # e310 images with ones built from source, which are correct for this
    # release of UHD.
    cp ${WORKDIR}/usrp_e310_fpga_RFNOC.* ${S_DL}
}

do_install_append_ettus-e3xx-sg1 () {
    # the "idle" image, even when made from source, will fail to load because
    # the ad9361 block is expecting status 8 and returns 0.
    rm ${D}${datadir}/uhd/images/usrp_e3xx_fpga_idle*
    cp ${D}${datadir}/uhd/images/usrp_e310_fpga_default.bit \
        ${D}${datadir}/uhd/images/usrp_e3xx_fpga_idle.bit
}
