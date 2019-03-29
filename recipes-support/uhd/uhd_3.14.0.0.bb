require uhd-base.inc

# 3.14.0.0
UHD_BRANCH = "UHD-3.14"
SRCREV = "6875d061d8dd3cc59427638d35f76079beb34e70"

SRC_URI_append_class-target = "\
    file://0001-Convert-the-asm-s16-le-converters-to-NEON-intrinsics.patch \
    file://0002-Be-more-verbose-when-checking-for-python-import.patch \
    file://0003-Silence-ctrl_iface.cpp.patch \
    "

EXTRA_OECMAKE_append = "\
    -DUHD_RELEASE_MODE=release \
    -DENABLE_RFNOC=TRUE \
    -DRUNTIME_PYTHON_EXECUTABLE='/usr/bin/env python3' \
    "

FILES_${PN} += "${libdir}/${PYTHON_DIR}/site-packages"
FILES_${PN}-dbg += "${libdir}/${PYTHON_DIR}/site-packages/uhd/.debug"

do_install_append () {
    chrpath -d ${D}${libdir}/${PYTHON_DIR}/site-packages/uhd/libpyuhd.so
}
