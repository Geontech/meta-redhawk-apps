require recipes-support/uhd/uhd.inc

UHD_BRANCH ?= "master"
SRC_URI = "git://github.com/EttusResearch/uhd.git;branch=${UHD_BRANCH}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=8255adf1069294c928e0e18b01a16282"

BBCLASSEXTEND = "native"

DEPENDS_class-native = "git-native python3-mako-native boost-native \
    ncurses-native python3-requests-native python3-numpy-native \
    python3-urllib3-native python3-chardet-native python3-certifi-native \
    python3-idna-native "
RDEPENDS_${PN}_class-native = "python3-requests"

# No need to make for targets
PACKAGECONFIG_class-native = ""

S = "${WORKDIR}/git/host"
