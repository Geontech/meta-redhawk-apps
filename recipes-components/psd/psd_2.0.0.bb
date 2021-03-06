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

inherit redhawk-component

NO_SPD_PATCH = "1"

DEPENDS = "bulkiointerfaces rf-noc-rh"
RDEPENDS_${PN} = "bulkiointerfaces rf-noc-rh"

RH_COMPONENT_NAME="psd"

SRC_URI = "git://github.com/geontech/${RH_COMPONENT_NAME}.git;protocol=git;branch=develop-2.0-RFNoC \
    file://Add_Missing_Files.patch \
    file://Clear_AMFLAGS.patch \
"

SRCREV = "a5daac0bf668a6e3a4a99da71a28b8fb21b5f50f"

PR = "r0" 

S = "${WORKDIR}/git/cpp_rfnoc"

FILES_${PN} += "${SDRROOT}/*"

