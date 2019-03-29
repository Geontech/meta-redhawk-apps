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

DEPENDS = "bulkiointerfaces liquid-dsp fftw rf-noc-rh uhd"
RDEPENDS_${PN} = "bulkiointerfaces liquid-dsp libfftwf rf-noc-rh uhd"

RH_COMPONENT_NAME="TuneFilterDecimate"

SRC_URI = "git://github.com/geontech/${RH_COMPONENT_NAME}.git;protocol=git;branch=develop-2.0-RFNoC \
    file://0001-softpkg-deps.patch \
"

SRCREV = "39c6ef80c52e70b53b34f4755c5de3c7e234fc0d"

PR = "r0" 

S = "${WORKDIR}/git/cpp_rfnoc"

FILES_${PN} += "${SDRROOT}/*"

