# Need dev/deps link to dom/deps for persona shared libs.
do_install_append_class-target () {
    mkdir -p ${D}${SDRROOT}/dev
    ln -sf ${SDRROOT}/dom/deps ${D}${SDRROOT}/dev/deps
}

