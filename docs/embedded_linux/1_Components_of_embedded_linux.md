# What are the components of Embedded Linux?

Here we will try to understand components of Embedded Linux:

1. Toolchain.
2. Bootloader.
3. Kernel.
4. Root filesystem.

There is also fifth component called applications, the collection of programs specific to your embedded application.

## Toolchain

The toolchain is the first element of embedded Linux and the starting point of your project. You will use it to compile all the code that will run on your device. The choices you make at this early stage will have a profound impact on the final outcome.

Your toolchain should remain constant throughout the project. In other words, once you have chosen your toolchain, it is important to stick with it. Changing compilers and development libraries in an inconsistent way during a project will lead to subtle bugs. That being said, it is still best to update your toolchain when security flaws or bugs are found.

A standard GNU toolchain consists of three main components:

**Binutils**: A set of binary utilities including the assembler and the linker. It is available at http://gnu.org/software/binutils.

**GNU Compiler Collection (GCC)**: These are the compilers for C and other languages, which, depending on the version of GCC, include C++, Objective-C, Objective-C++, Java, Fortran, Ada, and Go. They all use a common backend that
produces assembler code, which is fed to the GNU assembler. It is available at http://gcc.gnu.org/.

**C library**: A standardized application program interface (API) based on the POSIX specification, which is the main interface to the operating system kernel for applications.

### Types of toolchains

There are two types of toolchain:

**Native**: This toolchain runs on the same type of system (sometimes the same actual system) as the programs it generates. This is the usual case for desktops and servers, and it is becoming popular on certain classes of embedded devices. The BBB running Debian for ARM, for example, has self-hosted native compilers.

**Cross**: This toolchain runs on a different type of system than the target, allowing the development to be done on a fast desktop PC and then loaded onto the embedded target for testing.

As we would be using Yocto Project, it provides the toolchain needed for our development which is cross toolchain.

## Bootloader

Bootloader is the part that starts the system and loads the operating system kernel.

In an embedded Linux system, the bootloader has two main jobs:

1. To initialize the system to a basic level and to load the kernel.
2. Load the kernel

### The boot sequence

The boot sequence is a complex, multi-stage procedure as the modern SOCs have complex non-linear addressable storage.

**Phase 1 – ROM code**:

The code that runs immediately after a reset or power-on has to be stored on-chip in the SoC; this is known as ROM code. It is
loaded into the chip when it is manufactured, and hence the ROM code is proprietary and cannot be replaced by an open source equivalent. 

Usually, ROM code does not include code to initialize the memory controller, since DRAM configurations are highly device-specific, and so it can only use **Static Random Access Memory (SRAM)**, which does not require a memory controller. The ROM code is capable of loading a small chunk of code from one of several pre-programmed locations into the SRAM.

In SoCs, where the SRAM is not large enough to load a full bootloader such as U-Boot, there has to be an intermediate loader called the **secondary program loader (SPL)**.

At the end of the ROM code phase, the SPL is present in the SRAM and the ROM code jumps to the beginning of that code.