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

inherit redhawk-device

DEPENDS = "frontendinterfaces rf-noc-rh"
RDEPENDS_${PN} = "frontendinterfaces rf-noc-rh"

RH_DEVICE_NAME="RFNoC_ProgrammableDevice"

SRC_URI = "\
    git://github.com/geontech/${RH_DEVICE_NAME}.git;protocol=git;branch=develop \
    file://DeviceManager.dcd.xml \
"

SRCREV = "b76330bfa089e8cc60a4c02296dc1ebe92fa9611"

PR = "r0" 

S = "${WORKDIR}/git/cpp_armv7l"

FILES_${PN} += "${SDRROOT}/*"

# Install the template node
do_install_append () {
    install -Dm 755 ${S}/../nodeconfig.py ${D}${SDRROOT}/dev/devices/${RH_DEVICE_NAME}/nodeconfig.py
    install -Dm 644 ${WORKDIR}/DeviceManager.dcd.xml ${D}${SDRROOT}/dev/nodes/DevMgr-RFNoC_${MACHINE}/DeviceManager.dcd.xml
}
