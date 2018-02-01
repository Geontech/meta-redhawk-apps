PV = "3.10.1.1"

FILESEXTRAPATHS_append := ":${THISDIR}/files"

SRC_URI += "file://fix_block_id.patch \
            file://fix_log.patch \
            "

SRCREV = "89427e8cbb35c435e67f60efec5c56e26867c60c"
