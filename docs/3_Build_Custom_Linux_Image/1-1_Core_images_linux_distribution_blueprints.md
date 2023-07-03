# Core Images — Linux Distribution Blueprints

The OpenEmbedded Core (OE Core) and other Yocto Project layers include several example images. These images offer root filesystem configurations for typical Linux OS stacks. They range from very basic images that just boot a device to a command-line prompt to images that include the X Window System (X11) server and a graphical user interface. These reference images are called the core images because the names of their respective recipes begin with core-image.

You can easily locate the recipes for the core images with the find command from within the installation directory of your build system.

```rb
veeresh@efa:~/dev/yocto_iot/poky$ find ./meta*/recipes*/images -name "*.bb" -print
```

You can look at the core images as Linux distribution blueprints from which you can derive your own distribution by extending them. All core image recipes inherit the core-image class, which itself inherits from image class.

poky/meta/classes/core-image.bbclass

All images set the IMAGE_INSTALL variable to specify what packages are to be installed into the root filesystem. IMAGE_INSTALL is a list of packages and package groups. Package groups are collections of packages. Defining package groups removes the need to potentially list
hundreds of single packages in the IMAGE_INSTALL variable.

Image recipes either explicitly set Image_INSTALL or extend its default value provided by the core-image class, which installs the two package groups packagegroup-core-boot and packagegroupbase-extended. The default creates a working root filesystem that boots to the
console.

Let’s have a closer look at the various core images:

**core-image-minimal**: This is the most basic image allowing a device to boot to a Linux command-line login. Login and command-line interpreter are provided by
BusyBox.

**core-image-minimal-initramfs**: This image is essentially the same as core-image-minimal but with a Linux kernel that includes a RAM-based initial root filesystem (initramfs).

**core-image-tiny-initramfs**: Tiny image capable of booting a device. The kernel includes the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the first 'init' program more efficiently. core-image-tiny-initramfs doesn't actually generate an image but rather generates boot and rootfs artifacts that can subsequently be picked up by external image generation tools such as wic.

**core-image-minimal-mtdutils**: Based on core-image-minimal, this image also includes user space tools to interact with the memory technology device
(MTD) subsystem in the Linux kernel to perform operations on flash memory devices.

**core-image-minimal-dev**: Based on core-image-minimal, this image also includes all the development packages (header files, etc.) for all the packages installed in the root filesystem. If deployed on the target together with a native target toolchain, it allows software development on the target. Together with a crosstoolchain, it can be used for software development on the development host.

**core-image-rt**: Based on core-image-minimal, this image target builds the Yocto Project real-time kernel and includes a test suite and tools for real-time
applications.

**core-image-rt-sdk**: In addition to core-image-rt, this image includes the system development kit (SDK) consisting of the development packages for all packages installed; development tools such as compilers, assemblers, and linkers; as
well as performance test tools and Linux kernel development packages. This image allows for software development on the target.

**core-image-minimal**: Essentially a core-image-minimal, this image also includes middle-ware and application packages to support a variety of hardware such as WiFi, Bluetooth, sound, and serial ports. The target device must include the
necessary hardware components, and the Linux kernel must provide the device drivers for them.

**core-image-full-cmdline**: This minimal image adds typical Linux command-line tools—bash, acl, attr, grep, sed, tar, and many more—to the root filesystem.

**core-image-x11**: This basic graphical image includes the X11 server and an X11 terminal application.

**core-image-sato**: This image provides X11 support that includes the OpenedHand Sato user experience for mobile devices. Besides the Sato screen manager, the image also provides several applications using the Sato theme, such as a terminal, editor, file manager, and several games.

**core-image-sato-dev**: This image is the same as core-image-sato but also includes the development packages for all packages installed in the root filesystem.

**core-image-sato-sdk**: In addition to core-image-sato-dev, this image includes development tools such as compilers, assemblers, and linkers as well as performance test tools and Linux kernel development packages.

**core-image-weston**: This image uses Weston instead of X11. Weston is a compositor that uses the Wayland protocol and implementation to exchange data with its clients. This image also includes a Wayland-capable terminal program.

**core-image-weston-sdk**: In addition to core-image-weston, this image includes the system development kit (SDK) consisting of the development packages for all packages installed; development tools such as compilers, assemblers, and linkers; as
well as performance test tools and Linux kernel development packages. This image allows for software development on the target.

**core-image-multilib-example**: This image is an example of the support of multiple libraries, typically 32-bit support on an otherwise 64-bit system. The image is based on a core image and adds the desired multilib packages to IMAGE_INSTALL.

**core-image-testcontroller**: A test controller image to be deployed on a target useful for testing other images using the OEQA runtime tests.

**core-image-testcontroller-initramfs**: This image is essentially the same as core-image-testcontroller but with a Linux kernel that includes a RAM-based initial root filesystem (initramfs).

**core-image-ptest-all**: Includes ptest packages.

**core-image-ptest-fast**: Includes ptest packages with fast execution times to allow for more automated QA.

**core-image-kernel-dev**: A development image that builds the kernel and packages that are sensitive to kernel updates and version changes

**build-appliance-image_15.0.0**: An image containing the build system that you can boot and run using either VirtualBox, VMware Player or VMware Workstation.

**oe-selftest-image**: An image used during oe-selftest tests.

**wic-image-minimal**: An example of partitioned image.

You can easily extend them by adding packages and package groups to IMAGE_INSTALL. Images can only be extended, not shrunk. To build an image with less functionality, you have to start from a smaller core image and add only the packages you need. There is no simple way to remove packages. The majority of them are added through package groups, and you would need to split up the package group if you do not want to install a package included with it. Of course, if you are removing a package, you also have to remove any other packages that depend on it.

## Extending a Core Image through Local Configuration

The simplest method for adding packages and package groups to images is to add IMAGE_INSTALL to the conf/local.conf file of your build environment:

```rb
IMAGE_INSTALL:append = "\<package\> \<package group\>"
```

To extend an image, you have to append your packages and packages group to the variable. You may wonder why we use the explicit \_append operator instead of the += or .+ operators. Using the _append operator unconditionally appends the specified value to the IMAGE_INSTALL variable after all recipes and configuration files have been processed. Image recipes commonly explicitly set the IMAGE_INSTALL variable using the = or ?= operators, which may happen after BitBake processed the settings in conf/local.conf.

```rb
IMAGE_INSTALL:append = " openssh \
                         python3 \
                         python3-pip"
```

Using IMAGE_INSTALL also affects core images, that is, images that inherit from the core-image class, as well as images that inherit directly from the image class. For
convenience purposes, the core-image class defines the variable CORE_IMAGE_EXTRA_INSTALL. All packages and package groups added to this variable are appended to IMAGE_INSTALL by the class.

```rb
CORE_IMAGE_EXTRA_INSTALL = " openssh \
                         python3 \
                         python3-pip"
```

This adds these packages to all images that inherit from core-image. Images that inherit directly from image are not affected. Using CORE_IMAGE_EXTRA_INSTALL is a safer and easier method for core images than appending directly to IMAGE_INSTALL.

## Extending a Core Image with a Recipe

Adding packages and package groups to CORE_IMAGE_EXTRA_INSTALL and IMAGE_INSTALL and in conf/local.conf may be straightforward and quick, but doing so makes a project hard to maintain and complicates reuse. A better way is to extend a predefined image through a recipe. Below is a simple recipe that extends core-image-minimal.

```rb
SUMMARY = "recipe to add needed IoT tools"
DESCRIPTION = "A console image with hardware support for our IoT device recipes"
require recipes-core/images/core-image-minimal.bb
IMAGE_INSTALL += "openssh python3 python3-pip coreutils timedatectl"
IMAGE_FEATURES = "dev-pkgs"
LICENSE = "MIT"
```

It is best to have a custom layer to add this recipe instead of adding to existing meta-core layers.

### Create and Add a layer

Let's create a layer called "meta-iot-support-bbb" where we will add all the required tools to this layer. Using below commands we create and add the layer.

```rb
\$ bitbake-layers create-layer ../meta-iot-support-bbb
\$ bitbake-layers add-layer ../meta-iot-support-bbb
\$ bitbake-layers show-layers
```

Add the above recipe(iot_tools_0.1.bb) to extend the core-image-minimal.

Build the project to get Linux Image with the rquired tools you have added in the recipe.

```rb
bitbake core-image-minimal
```
