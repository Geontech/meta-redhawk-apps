DESCRIPTION = "RFNoC Tune Filter Decimate Waveform"
SUMMARY = "Utilizes the FIR and decimate blocks"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=bfccfe952269fff2b407dd11f2f3083b"

inherit rfnoc-waveform

SRC_URI = "file://RFNoC_TFD_Waveform"

S = "${WORKDIR}"

RDEPENDS_${PN} += "tunefilterdecimate"
