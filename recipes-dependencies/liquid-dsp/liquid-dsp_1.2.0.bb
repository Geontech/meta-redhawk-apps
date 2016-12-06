#
# This file is protected by Copyright. Please refer to the COPYRIGHT file distributed 
# with this source distribution.
#
# This file is part of Geon Technology's meta-redhawk-apps.
#
# Geon Technology's meta-redhawk-apps is free software: you can redistribute it and/or 
# modify it under the terms of the GNU Lesser General Public License as published by 
# the Free Software Foundation, either version 3 of the License, or (at your option) 
# any later version.
#
# Geon Technology's meta-redhawk-apps is distributed in the hope that it will be useful, 
# but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
# FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
# details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with this program.  If not, see http://www.gnu.org/licenses/.
#

DESCRIPTION = "Liquid DSP Library"
HOMEPAGE = "http://www.liquidsdr.org"
LICENSE = "CLOSED"

BBCLASSEXTEND += "native"

# NOTE: This recipe requires the USRP UHD driver and hardware installed
# which is provided by the meta-sdr layer which relies on meta-ettus.

DEPENDS = "liquid-dsp-native"
RDEPENDS_${PN} = ""

RH_DEPS_NAME="liquid-dsp"

SRC_URI = "git://github.com/jgaeddert/${RH_DEPS_NAME}.git;protocol=git;tag=v1.2.0 \
    file://Hard_Float.patch \
    file://Run_Native_Gentab.patch \
"

SRC_URI_class-native = "git://github.com/jgaeddert/${RH_DEPS_NAME}.git;protocol=git;tag=v1.2.0 \
"

PR = "r0" 

S = "${WORKDIR}/git/"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the same as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative

EXTRA_OECONF = "--prefix=${STAGING_DIR}/${MACHINE}/usr/ --exec-prefix=${STAGING_DIR}/${MACHINE}/usr/"
EXTRA_OECONF_class-native = "--prefix=${STAGING_DIR_NATIVE}/usr/ --exec-prefix=${STAGING_DIR_NATIVE}/usr/"

FILES_${PN} += "/usr/*"
FILES_${PN}-dev += "/usr/lib/libliquid.so"
FILES_${PN}-staticdev += "/usr/lib/libliquid.a"
#INSANE_SKIP_${PN} += "debug-files dev-so staticdev libdir installed-vs-shipped"

do_compile() {
    export TOOLBINDIR=${STAGING_BINDIR_NATIVE}
    oe_runmake
}

do_install_class-native() {
    cp -r ${S}/src/fec/gentab/reverse_byte_gentab ${STAGING_BINDIR_NATIVE}
    cp -r ${S}/src/utility/gentab/count_ones_gentab ${STAGING_BINDIR_NATIVE}
}
