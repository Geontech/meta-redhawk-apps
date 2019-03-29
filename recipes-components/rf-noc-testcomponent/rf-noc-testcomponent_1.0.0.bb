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

DESCRIPTION = "REDHAWK Device for the RF-NoC Platforms"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "CLOSED"

inherit redhawk-component

NO_SPD_PATCH = "1"

DEPENDS = "bulkiointerfaces rf-noc-rh"
RDEPENDS_${PN} = "bulkiointerfaces rf-noc-rh"

RH_COMPONENT_NAME="RFNoC_TestComponent"

SRC_URI = "git://github.com/geontech/${RH_COMPONENT_NAME}.git;protocol=git;branch=develop \
    file://Add_Missing_Files.patch \
    file://Clear_AMFLAGS.patch \
"

SRCREV = "d13d0eebef691cec2d6eda86e2f76839a292be66"

PR = "r0" 

S = "${WORKDIR}/git/cpp_armv7l"

FILES_${PN} += "${SDRROOT}/*"

