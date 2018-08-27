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

# We have to inherit from pythonnative if we do stuff with the system python.
# autotools-brokensep is the same as autotools but our build and src locations are the same since we cannot build away from our src.
inherit autotools-brokensep pkgconfig redhawk-softpkg

RH_DEPS_NAME="RFNoC_RH"

SOFTPKG_PREFIX .= "/${RH_DEPS_NAME}"
SOFTPKG_EPREFIX = "${SOFTPKG_PREFIX}/cpp_armv7l"

SRC_URI = "git://github.com/geontech/${RH_DEPS_NAME}.git;protocol=git;branch=develop \
        file://Clear_AMFLAGS.patch \
"

SRCREV = "99e67def66d00a2049eac1a069bf4ce796757995"

PR = "r0"

S = "${WORKDIR}/git/cpp_armv7l"

DEPENDS += "redhawk uhd"
RDEPENDS_${PN} += "redhawk uhd"

