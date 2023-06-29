# Features

Features provide a mechanism for working out which packages should be included in the generated images. Distributions can select which features they want to support through the DISTRO_FEATURES variable, which is set or appended to in a distribution’s configuration file such as poky.conf, poky-tiny.conf, poky-lsb.conf and so forth. Machine features are set in the MACHINE_FEATURES variable, which is set in the machine configuration file and specifies the hardware features for a given machine.

These two variables combine to work out which kernel modules, utilities, and other packages to include. A given distribution can support a selected subset of features so some machine features might not be included if the distribution itself does not support them.

One method you can use to determine which recipes are checking to see if a particular feature is contained or not is to grep through the Metadata for the feature. Here is an example that discovers the recipes whose build is potentially changed based on a given feature:

```rb
cd poky
git grep 'contains.*MACHINE_FEATURES.*feature'
```

## Machine Features

Machine features is controlled by MACHINE_FEATURES variable.

Features do not have a one-to-one correspondence to packages, and they can go beyond simply controlling the installation of a package or packages. Sometimes a feature can influence how certain recipes are built. For example, a feature might determine whether a particular configure option is specified within the do_configure task for a particular recipe.

Some examples are:

* **acpi**: Hardware has ACPI (x86/x86_64 only)
* **alsa**: Hardware has ALSA audio drivers
* **apm**: Hardware uses APM (or APM emulation)
* **bluetooth**: Hardware has integrated BT
* **efi**: Support for booting through EFI
* **ext2**: Hardware HDD or Microdrive
* **keyboard**: Hardware has a keyboard
* **numa**: Hardware has non-uniform memory access
* **pcbios**: Support for booting through BIOS
* **pci**: Hardware has a PCI bus
* **pcmcia**: Hardware has PCMCIA or CompactFlash sockets
* **phone**: Mobile phone (voice) support
* **qvga**: Machine has a QVGA (320x240) display
* **rtc**: Machine has a Real-Time Clock
* **screen**: Hardware has a screen
* **serial**: Hardware has serial support (usually RS232)
* **touchscreen**: Hardware has a touchscreen
* **usbgadget**: Hardware is USB gadget device capable
* **usbhost**: Hardware is USB Host capable
* **vfat**: FAT file system support
* **wifi**: Hardware has integrated WiFi

## Distro Features

Distro features is controlled by DISTRO_FEATURES variable.

Features do not have a one-to-one correspondence to packages, and they can go beyond simply controlling the installation of a package or packages. In most cases, the presence or absence of a feature translates to the appropriate option supplied to the configure script during the do_configure task for the recipes that optionally support the feature.

Some examples are:

* **alsa**: Include ALSA support (OSS compatibility kernel modules installed if available).
* **bluetooth**: Include bluetooth support (integrated BT only).
* **ext2**: Include tools for supporting for devices with internal HDD/Microdrive for storing files (instead of Flash only devices).
* **ipsec**: Include IPSec support.
* **ipv6**: Include IPv6 support.
* **nfs**: Include NFS client support (for mounting NFS exports on device).

## Image Features

Image features provide certain functionality that you can add to your target images. This can be additional packages to be installed, modification of configuration files, and more.

For example, the 'dev-pkgs' image feature adds the development packages, which typically include headers and other files required for development, for all packages installed in the root filesystem. Using this image feature is a convenient way to enable a target image for development without having to explicitly specify the development packages in the IMAGE_INSTALL variable. For deployment, you can then simply
remove the dev-pkgs image feature.

Installation of image features is controlled by the two variables:

1. IMAGE_FEATURES: this is used in image recipes to define the required set of image features.
2. EXTRA_IMAGE_FEATURES: This is typically used in the conf/local.conf file to define additional image features that, of course, then affect all images built with that build environment.

The content of EXTRA_IMAGE_FEATURES is simply added to
IMAGE_FEATURES by the meta/conf/bitbake.conf configuration file.

The following are  some image features are available for all images:

* **allow-empty-password**: Allows Dropbear and OpenSSH to accept root logins and logins from accounts having an empty password string.
* **dbg-pkgs**: Installs debug symbol packages for all packages installed in a given image.
* **debug-tweaks**: Makes an image suitable for development (e.g. allows root logins without passwords and enables post-installation logging). See the ‘allow-empty-password’, ‘empty-root-password’, and ‘post-install-logging’ features in this list for additional information.
* **dev-pkgs**: Installs development packages (headers and extra library links) for all packages installed in a given image.
* **empty-root-password**: Sets the root password to an empty string, which allows logins with a blank password.

For more information refer yocto documentaion: <https://docs.yoctoproject.org/4.0.10/ref-manual/features.html>
