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

DESCRIPTION = "REDHAWK Component with an RF-NoC implementation"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "CLOSED"

# NOTE: This recipe requires the USRP UHD driver and hardware installed
# which is provided by the meta-sdr layer which relies on meta-ettus.

DEPENDS = "redhawk-bulkio redhawk-core uhd liquid-dsp fftwf"
RDEPENDS_${PN} = "redhawk-bulkio redhawk-core uhd liquid-dsp fftwf"

RH_COMPONENT_NAME="TuneFilterDecimate"

SRC_URI = "git://github.com/geontech/${RH_COMPONENT_NAME}.git;protocol=git;branch=develop-2.0-RFNoC \
    file://Add_Missing_Files.patch \
    file://Clear_AMFLAGS.patch \
"

SRCREV = "3dbe34f48cc7ded9cb384c04646687af7bdc64c0"

PR = "r0" 

S = "${WORKDIR}/git/cpp_rfnoc"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the same as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig redhawk-device

EXTRA_OECONF += "--prefix=${SDRROOT}"
EXTRA_AUTORECONF += "-I ${STAGING_DIR}/${MACHINE}${OSSIEHOME}/share/aclocal/ossie"

FILES_${PN} += "${SDRROOT}/*"
INSANE_SKIP_${PN} += "dev-deps debug-files dev-so staticdev libdir"

