SUMMARY = "wolfCrypt as an Engine for OpenSSL"
DESCRIPTION = "wolfEngine is an OpenSSL engine backed by wolfSSL's wolfCrypt cryptography library."
HOMEPAGE = "https://github.com/wolfSSL/wolfEngine"
BUGTRACKER = "https://github.com/wolfSSL/wolfEngine/issues"
SECTION = "libs"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS += "openssl wolfssl"

SRC_URI = " \
    git://github.com/wolfssl/wolfEngine.git;nobranch=1;tag=v${PV}; \
    file://0001-Makefile.am-fix-build-on-an-external-directory.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += " \
    --with-openssl=${COMPONENTS_DIR}/${PACKAGE_ARCH}/openssl/usr \
    --with-wolfssl=${COMPONENTS_DIR}/${PACKAGE_ARCH}/wolfssl/usr \
    --enable-debug \
"

do_configure_prepend() {
    (cd ${S}; ./autogen.sh; cd -)
}

# Assign "libwolfengine.so" to normal (no -dev) package. This is
# needed for the engine to be loaded.
INSANE_SKIP_${PN} = "dev-so"
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libwolfengine.so"
