SUMMARY = "recipe to add needed IoT tools"
DESCRIPTION = "A console image with hardware support for our IoT device recipes"
require recipes-core/images/iot-support-dev-image.bb
IMAGE_INSTALL += "openssh python3 python3-pip coreutils timedatectl"
LICENSE = "MIT"