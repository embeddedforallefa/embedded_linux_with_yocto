# Building Open Source Software Packages

If you have built open source software packages for a Linux host system before, you may have noticed that the workflow follows a specific pattern. Some of the steps of this workflow you execute yourself, whereas others are typically carried out through some sort of automation such as Make or other source-to-binary build systems.

1. Fetch: Obtain the source code.
2. Extract: Unpack the source code.
3. Patch: Apply patches for bug fixes and added functionality.
4. Configure: Prepare the build process according to the environment.
5. Build: Compile and link.
6. Install: Copy binaries and auxiliary files to their target directories.
7. Package: Bundle binaries and auxiliary files for installation on other systems.

## Fetch

Typically, open source projects have a download area from where the source code together with instructions,
documentation, and other information can be downloaded in the form of an archive, which commonly is also compressed.

Of course, each open source project has its own URL to access its website, file servers, and download areas. Some projects may also provide access to released versions and development branches of their source code from a source
control management (SCM) system such as Git, Subversion, and more.

Commonly, sources obtained from remote locations such as download sites or repositories may be supplemented with patches and auxiliary files that are stored on a local filesystem.

## Extract

After the source code is downloaded, it must be unpacked and copied from its download location to an area where you are going to build it. Typically, open source packages are
wrapped into archives, most commonly into compressed tar archives, but CPIO and other formats that serialize multiple files into a single archive are also in use. The most frequently used compression formats are GZIP and BZIP, but some projects utilize other compression schemes. 

## Patch

Patching is the process of incrementally modifying the source code by adding, deleting,and changing the source files.

There are various reasons why source code could require patching before building:

1. applying bug and security fixes
2. adding functionality
3. providing configuration information
4. making adjustments for cross-compiling, and so forth.

Applying a patch can be as simple as copying a file into the directory structure of the source code.

Commonly, patches are applied using the patch utility, which takes a patch file as input that has been created with the diff utility. Diff compares an original file with a modified file and creates a differential file that includes not only the changes but also metadata such
as the name and path of the file and the exact location of the modifications and a context.

## Configure

Providing a software package in source code form serves, among others, the purpose that users can build the software themselves for a wide range of target systems. With variety comes diversity requiring the build environment for the software package to be configured
appropriately for the target system. 

Accurate configuration is particularly important for
cross-build environments where the CPU architecture of the build host is different from the CPU architecture of the target system.

Many software packages now use the GNU build system, also known as Autotools, for configuration. Autotools is a suite of tools aimed at making source code software
packages portable to many UNIX-like systems.

Autotools creates a configure script from a series of input files that characterize a particular source code body. Through a series of processing steps, configure creates a makefile specifically for the target system.

## Build

The vast majority of software packages utilize Make to build binaries such as executable program files and libraries as well as auxiliary files from source code. Some software packages may use other utilities, such as CMake or qmake, for software packages using the Qt graphical libraries.

## Install

The install step copies binaries, libraries, documentation, configuration, and other files to
the correct locations in the target’s filesystem.

Program files are typically installed in /usr/bin, for user programs, and /usr/sbin, for system administration programs.

Libraries are copied to /usr/lib and application-specific subdirectories inside /usr/lib.

Configuration files are commonly installed to /etc.

The install utility can also set file ownership and permissions while copying the files.

## Package

Packaging is the process of bundling the software, binaries, and auxiliary files into a single archive file for distribution and direct installation on a target system.

Packaging can be as simple as a compressed tar archive that the user then extracts on the target system.

For convenience and usability, most software packages bundle their files for use with an installer or package management system.

Linux systems commonly rely on a package management system that is part of the distribution rather than using self-contained installation packages. The advantages are that the package manager, as the only instance, maintains the software database on the system and that the software packages are smaller in size because they do not need to contain the installation software. However, the maintainers for each Linux distribution decide on its
package management system, which requires software packages to be packaged multiple times for different target systems.

The most commonly used package management systems for Linux distributions are:

1. RPM Package Manager (RPM; originally Red Hat Package Manager).
2. dpkg, Debian’s package management program.
3. Itsy Package Management System (ipkg) has gained popularity for embedded devices. Ipkg is a lightweight system that resembles dpkg.
4. opkg, which was forked from ipkg by the Openmoko project. Opkg is written in C, it is actively maintained by the Yocto Project and used by OpenEmbedded
and many other projects.

Install and package are not necessarily sequential steps. And they are also optional. If you are building a software package for local use only and not for redistribution, there is no need to package the software. 

If you are a package maintainer and create packages for
redistribution, you may not need to perform the step to install the software package on your build system.

The steps outlined here are essentially the same whether you are building a software package natively or are performing a cross-build. However, you must consider many
intricacies when setting up and configuring the build environment and building the package for a cross-build.
