do_install_append() {
	mv ${D}${datadir}/uhd/images/usrp_e310_fpga.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_default.bit
}
