SUMMARY = "Universal Hardware Driver Firmware"
HOMEPAGE = "http://www.ettus.com"

PE = "1"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://share/uhd/images/LICENSE;md5=8255adf1069294c928e0e18b01a16282"

inherit allarch

SRC_URI = "http://files.ettus.com/binaries/images/uhd-images_${PV}.rfnoc-devel-238-g39a41476.zip"
SRC_URI[md5sum] = "ff2a1ab0e9ee277901cd0bdb9188708e"
SRC_URI[sha256sum] = "3677e46ef6bea0ffa8e0e7909c2ec6b967d2f40c38b8b97cef186664e48e6e10"

S = "${WORKDIR}/uhd-images_${PV}.rfnoc-devel-238-g39a41476"

do_install() {
    install -d ${D}${datadir}/uhd/images
    install -d ${D}${datadir}/uhd/images/bit
    install -d ${D}${datadir}/uhd/images/winusb_driver
    install -d ${D}${datadir}/uhd/images/winusb_driver/amd64
    install -d ${D}${datadir}/uhd/images/winusb_driver/x86
    install -m 0644 ${S}/share/uhd/images/bit/* ${D}${datadir}/uhd/images/bit
    install -m 0644 ${S}/share/uhd/images/winusb_driver/amd64/* ${D}${datadir}/uhd/images/winusb_driver/amd64
    install -m 0644 ${S}/share/uhd/images/winusb_driver/x86/* ${D}${datadir}/uhd/images/winusb_driver/x86
    install -m 0644 ${S}/share/uhd/images/winusb_driver/*.inf ${D}${datadir}/uhd/images/winusb_driver
    install -m 0644 ${S}/share/uhd/images/*.tag ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/LICENSE ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.hex ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.rbf ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.ihx ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.bin ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.bit ${D}${datadir}/uhd/images
    install -m 0644 ${S}/share/uhd/images/*.lvbitx ${D}${datadir}/uhd/images

    # Move the default e3x bit files
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_default.bit
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga_sg3.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_sg3_default.bit
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_default.bit
    mv ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_sg3.bit ${D}${datadir}/uhd/images/usrp_e310_fpga_RFNOC_sg3_default.bit
}

PACKAGES = "${PN}"
FILES_${PN} = "${datadir}/uhd/images"

