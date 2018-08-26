require recipes-support/uhd/uhd.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=8255adf1069294c928e0e18b01a16282"

PV = "4.0.0"

SRC_URI = "git://github.com/EttusResearch/uhd.git;branch=rfnoc-devel \
          "

inherit pkgconfig

SRCREV = "eec24d7b0442616fdbe9adf6b426959677e67f92"

S = "${WORKDIR}/git/host"

EXTRA_OECMAKE += "-DENABLE_RFNOC=TRUE"
