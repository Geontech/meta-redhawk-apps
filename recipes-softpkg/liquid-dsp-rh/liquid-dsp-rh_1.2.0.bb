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

DESCRIPTION = "REDHAWK Shared Library for Liquid DSP"
HOMEPAGE = "http://www.liquidsdr.org"
LICENSE = "CLOSED"

# NOTE: This recipe requires the USRP UHD driver and hardware installed
# which is provided by the meta-sdr layer which relies on meta-ettus.

DEPENDS = ""
RDEPENDS_${PN} = "boost-filesystem boost-serialization boost-system boost-thread libfftwf omniorb redhawk util-linux-libuuid"

RH_DEPS_NAME="liquid-dsp-rh"

SRC_URI = "git://github.com/geontech/${RH_DEPS_NAME}.git;protocol=git;branch=develop \
    file://Add_Missing_Files.patch \
    file://Clear_AMFLAGS.patch \
    file://Force_Install_to_SDR.patch \
"

SRCREV = "b6ef07d547ecc6f06c1d8bd2bfe7d062746c3cde"

PR = "r0" 

S = "${WORKDIR}/git/cpp_armv7l"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the same as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative redhawk-entity redhawk-sysroot

PREFIX = "${SDRROOT}/dom/deps/liquid-dsp"
EXEC_PREFIX = "${PREFIX}/cpp_armv7l"
LIBDIR = "${EXEC_PREFIX}/lib"
INCLUDEDIR = "${EXEC_PREFIX}/include"

EXTRA_OECONF += "--prefix=${PREFIX} --exec-prefix=${EXEC_PREFIX} --libdir=${LIBDIR} --includedir=${INCLUDEDIR}"
EXTRA_AUTORECONF += "-I ${STAGING_DIR}/${MACHINE}${OSSIEHOME}/share/aclocal/ossie"

FILES_${PN} += "${SDRROOT}/*"
FILES_${PN}-dbg += "${SDRROOT}/dom/deps/liquid-dsp/cpp_armv7l/lib/.debug/*"
FILES_${PN}-dev += "${SDRROOT}/dom/deps/liquid-dsp/cpp_armv7l/lib/libliquid-dsp.so"
FILES_${PN}-staticdev += "${SDRROOT}/dom/deps/liquid-dsp/cpp_armv7l/lib/libliquid-dsp.a"
#INSANE_SKIP_${PN} += "debug-files dev-so staticdev libdir installed-vs-shipped"

