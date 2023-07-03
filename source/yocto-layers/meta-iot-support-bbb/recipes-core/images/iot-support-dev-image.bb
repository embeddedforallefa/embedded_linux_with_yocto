SUMMARY = "An image used for IoT Platform - development image"

IMAGE_INSTALL = ""
# - dev-pkgs            - development packages (headers, etc.) for all installed packages in the rootfs
# - dbg-pkgs            - debug symbol packages for all installed packages in the rootfs
# - debug-tweaks        - makes an image suitable for development, e.g. allowing passwordless root logins
# - package-management  - installs package management tools and preserves the package manager database
IMAGE_FEATURES = "dev-pkgs dbg-pkgs debug-tweaks package-management"

#default is en-us additional linguals will take extra space
IMAGE_LINGUAS = " "
IMAGE_FSTYPES = "tar.bz2 jffs2 wic wic.bmap"
inherit core-image

# refer beaglebone-yocto.conf for more machine specific features
