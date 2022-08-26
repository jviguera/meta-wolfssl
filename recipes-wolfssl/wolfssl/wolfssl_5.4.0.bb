SUMMARY = "wolfSSL Lightweight Embedded SSL/TLS Library"
DESCRIPTION = "wolfSSL is a lightweight SSL/TLS library written in C and \
               optimized for embedded and RTOS environments. It can be up \
               to 20 times smaller than OpenSSL while still supporting \
               a full TLS client and server, up to TLS 1.3"
HOMEPAGE = "https://www.wolfssl.com/products/wolfssl"
BUGTRACKER = "https://github.com/wolfssl/wolfssl/issues"
SECTION = "libs"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "cyassl"
RPROVIDES_${PN} = "cyassl"
PROVIDES += "wolfssl"
RPROVIDES_${PN} = "wolfssl"

SRC_URI = "git://github.com/wolfssl/wolfssl.git;nobranch=1;protocol=https;rev=57aac1c50b45275c7a99eca32ad985998b292dc8"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure:prepend() {
    (cd ${S}; ./autogen.sh; cd -)
}

BBCLASSEXTEND += "native nativesdk"
