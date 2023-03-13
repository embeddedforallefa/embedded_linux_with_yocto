# Add a software tool to your Yocto Linux image

I want to add openssh, python and pip for the minimal linux image.

## Get the Layer

From <https://layers.openembedded.org/layerindex/branch/master/layers/>

search ssh and you will get meta-shellhub.

clone it github repo.

git clone <https://github.com/shellhub-io/meta-shellhub.git>

copy this repo to poky/ directory.

## Update /build/conf/bblayers.conf

Append the directory path to BBLAYERS variable

Eg:

BBLAYERS ?= " \
  /home/veeresh/dev/yacto/poky/meta \
  /home/veeresh/dev/yacto/poky/meta-poky \
  /home/veeresh/dev/yacto/poky/meta-yocto-bsp \
  **/home/veeresh/dev/yacto/poky/meta-shellhub \\**
  "

## Add the layer

cd poky

source oe-init-build-env

bitbake-layers add-layer ../meta-shellhub

## Build the layer

bitbake shellhub-agent

## Building Your Image

bitbake core-image-minimal

### Simulate Your Image Using QEMU

runqemu qemuarm

### Flash the image to BBB and run

The image for the BBB will be present in tmp/deploy/images/beaglebone-yocto.

cd tmp/deploy/images/beaglebone-yocto

Make sure to delete all the partition of the sd card.

dd if=core-image-minimal-beaglebone-yocto.wic of=/dev/sdb bs=4M
