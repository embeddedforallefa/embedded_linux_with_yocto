# Bootloader

Bootloader is the part that starts the system and loads the operating system kernel.

In an embedded Linux system, the bootloader has two main jobs:

1. To initialize the system to a basic level and to load the kernel.
2. Load the kernel

## The boot sequence

The boot sequence is a complex, multi-stage procedure as the modern SOCs have complex non-linear addressable storage.

**Phase 1 – ROM code**:

The code that runs immediately after a reset or power-on has to be stored on-chip in the SoC; this is known as ROM code. It is
loaded into the chip when it is manufactured, and hence the ROM code is proprietary and cannot be replaced by an open source equivalent.

Usually, ROM code does not include code to initialize the memory controller, since DRAM configurations are highly device-specific, and so it can only use **Static Random Access Memory (SRAM)**, which does not require a memory controller. The ROM code is capable of loading a small chunk of code from one of several pre-programmed locations into the SRAM.

In SoCs, where the SRAM is not large enough to load a full bootloader such as U-Boot, there has to be an intermediate loader called the **secondary program loader (SPL)**.

At the end of the ROM code phase, the SPL is present in the SRAM and the ROM code jumps to the beginning of that code.

## Moving from the bootloader to a kernel

When the bootloader passes control to the kernel, it has to pass some basic information, which includes the following:

1. The machine number, which is used on PowerPC and ARM platforms without support for a device tree, to identify the type of the SoC.

2. Basic details of the hardware that's been detected so far, including (at the very least) the size and location of the physical RAM and the CPU's clock speed.

3. The kernel command line.

4. Optionally, the location and size of a device tree binary.

5. Optionally, the location and size of an initial RAM disk, called the initial RAM file system (initramfs).
