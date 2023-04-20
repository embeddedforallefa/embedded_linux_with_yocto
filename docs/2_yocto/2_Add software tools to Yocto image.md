# Add software tools to your Yocto Linux image

I want to add:

1. openssh
2. python and
3. pip

for the minimal linux image which we have.

PS: Here I am using yocto release - Release 4.0 (kirkstone)

## Add following tools to the append list in local.conf

```rb
IMAGE_INSTALL:append = " openssh \
                         python3 \
                         python3-pip"
```

These are already part of the meta. So here we are just appending these tools so that they will installed as part of our image.

## Building Your Image

```rb
cd poky
source oe-init-build-env
bitbake core-image-minimal
```

### Simulate Your Image Using QEMU

```rb
runqemu qemuarm
```

### Flash the image to BBB and run

The image for the BBB will be present in tmp/deploy/images/beaglebone-yocto.

```rb
cd tmp/deploy/images/beaglebone-yocto
```

Make sure to delete all the partition of the sd card.

```rb
dd if=core-image-minimal-beaglebone-yocto.wic of=/dev/sdb bs=4M
```
