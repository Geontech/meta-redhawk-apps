DESCRIPTION = "RFNoC PSD Waveform"
SUMMARY = "Utilizes the FFT block configured for magnitude output"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=bfccfe952269fff2b407dd11f2f3083b"

inherit rfnoc-waveform

SRC_URI = "file://RFNoC_PSD_Waveform"

S = "${WORKDIR}"

RDEPENDS_${PN} += "psd"
