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

DESCRIPTION = "RF-NoC RedHawk Library"
HOMEPAGE = "http://www.geontech.com"
LICENSE = "CLOSED"

# NOTE: This recipe requires the USRP UHD driver and hardware installed
# which is provided by the meta-sdr layer which relies on meta-ettus.

DEPENDS = "redhawk-core uhd"
RDEPENDS_${PN} = "redhawk-core uhd"

RH_DEPS_NAME="RFNoC_RH"

SRC_URI = "git://github.com/geontech/${RH_DEPS_NAME}.git;protocol=git;branch=develop \
        file://Clear_AMFLAGS.patch \
"

SRCREV = "eba969fe41c621e200ef150009add26a700fd9d0"

PR = "r0"

S = "${WORKDIR}/git/"

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the same as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig pythonnative redhawk-cpp-lib

EXTRA_AUTORECONF += "-I ${STAGING_DIR}/${MACHINE}${OSSIEHOME}/share/aclocal/ossie"

PROVIDES += "${PN}-dev"

FILES_${PN} += "\
        ${libdir}/libRFNoC_RH.so \
        ${libdir}/pkgconfig \
"
FILES_${PN}-dev += "\
        ${includedir}/RFNoC_Component.h \
        ${includedir}/RFNoC_Persona.h \
        ${includedir}/RFNoC_Programmable.h \
        ${includedir}/RFNoC_Utils.h \
"

