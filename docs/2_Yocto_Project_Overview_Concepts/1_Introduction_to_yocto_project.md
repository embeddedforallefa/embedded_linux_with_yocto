# Introduction to Yocto Project

## What is the Yocto Project?

<https://www.yoctoproject.org/>

The Yocto Project (YP) is an open source collaboration project that helps developers create custom Linux-based systems regardless of the hardware architecture.

The project provides a flexible set of tools and a space where embedded developers worldwide can share technologies, software stacks, configurations, and best practices that can be used to create tailored Linux images for embedded and IoT devices, or anywhere a customized Linux OS is needed.

The Yocto project provides a reference build system for embedded Linux, called **Poky**, which has the **BitBake** and **OpenEmbedded-Core (OE-Core)** projects at its base.

The purpose of Poky is to build the components needed for an embedded Linux product, namely:

1. A bootloader image
2. A Linux kernel image
3. A root filesystem image
4. Toolchains and **software development kits (SDKs)** for application development

With these, the Yocto project covers the needs of both system and application developers. When the Yocto project is used as an integration environment for bootloaders, the Linux kernel, and user space applications, we refer to it as system development.

For application development, the Yocto project builds SDKs that enable the development of applications independently of the Yocto build system.

The Yocto project makes a new release every six months. The latest LTS release is Yocto 4.0.6 Kirkstone, and all the examples in this course refer to the 4.0.6 release.

## Features

## Challenges

## Why Yocto?
