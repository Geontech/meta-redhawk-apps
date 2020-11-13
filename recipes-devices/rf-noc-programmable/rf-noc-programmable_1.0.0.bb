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

NO_SPD_PATCH = "1"

DEPENDS = "frontendinterfaces rf-noc-rh"
RDEPENDS_${PN} = "frontendinterfaces rf-noc-rh"

PACKAGE_BEFORE_PN += "${PN}-init"
RDEPENDS_${PN} = "${PN}"

RH_DEVICE_NAME="RFNoC_ProgrammableDevice"
RH_NODE_NAME="DevMgr-RFNoC_ettus-e3xx-sg1"

SRC_URI = "\
    git://github.com/geontech/${RH_DEVICE_NAME}.git;protocol=git;branch=develop \
    file://DeviceManager.dcd.xml \
    file://rf-noc-programmable.init.d \
"

SRCREV = "b76330bfa089e8cc60a4c02296dc1ebe92fa9611"

PR = "r0" 

S = "${WORKDIR}/git/cpp_armv7l"

FILES_${PN} += "${SDRROOT}/*"

inherit update-rc.d
RFNOC_NODE_INITD_NAME="node-${RH_NODE_NAME}"
INITSCRIPT_PACKAGES = "${PN}-init"
INITSCRIPT_NAME_${PN}-init = "${RFNOC_NODE_INITD_NAME}"
INITSCRIPT_PARAMS_${PN}-init = "start 99 2 3 4 5 . stop 01 0 1 6 ."
FILES_${PN}-init = "${sysconfdir}/init.d/${RFNOC_NODE_INITD_NAME}"

do_compile_append () {
    cp ${WORKDIR}/rf-noc-programmable.init.d ${B}/${RFNOC_NODE_INITD_NAME}
    sed -i "s|NODE_NAME|${RH_NODE_NAME}|g"   ${B}/${RFNOC_NODE_INITD_NAME}
    sed -i "s|SDRROOT_PATH|${SDRROOT}|g"     ${B}/${RFNOC_NODE_INITD_NAME}
    sed -i "s|OSSIEHOME_PATH|${OSSIEHOME}|g" ${B}/${RFNOC_NODE_INITD_NAME}
    sed -i "s|DOMAIN_NAME|REDHAWK_DEV|g"     ${B}/${RFNOC_NODE_INITD_NAME}
}

# Install the template node
do_install_append () {
    install -Dm 755 ${S}/../nodeconfig.py ${D}${SDRROOT}/dev/devices/${RH_DEVICE_NAME}/nodeconfig.py
    install -Dm 644 ${WORKDIR}/DeviceManager.dcd.xml ${D}${SDRROOT}/dev/nodes/DevMgr-RFNoC_${MACHINE}/DeviceManager.dcd.xml
    install -Dm 755 ${B}/${RFNOC_NODE_INITD_NAME} ${D}/${sysconfdir}/init.d/${RFNOC_NODE_INITD_NAME}
}
