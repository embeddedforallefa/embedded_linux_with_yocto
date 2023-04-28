# Generate SDK for application development

A software development kit (SDK) is a set of tools and files used to develop and debug.

Software Development Kit(SDK) is necessary if you want to develop applications on your development host for the target device.

This method of compiling application is called cross-compilation.

## Generate SKD using Yocto

It is very straightforward to generate SDK using Yocto project. We just have to pass "-c populate_sdk" parameter to the bitbake.

```rb
bitbake core-image-minimal -c populate_sdk
```

In the preceding command we are generating SDK for "core-image-minimal" image.

You will find the SDK generated in directory build/tmp/deploy/sdk.

## Install generated SDK

To install the SDK run the shell script in the sdk directory as below:

```rd
veeresh@elp:~/dev/yacto/poky/build/tmp/deploy/sdk$ ./poky-glibc-x86_64-core-image-minimal-cortexa8hf-neon-beaglebone-yocto-toolchain-4.0.6.sh
Poky (Yocto Project Reference Distro) SDK installer version 4.0.6
=================================================================

Enter target directory for SDK (default: /opt/poky/4.0.6): 
You are about to install the SDK to "/opt/poky/4.0.6". Proceed [Y/n]? y
[sudo] password for veeresh: 
Extracting SDK...............................................................................done
Setting it up...done
SDK has been successfully set up and is ready to be used.
Each time you wish to use the SDK in a new shell session, you need to source the environment setup script e.g.
 $ . /opt/poky/4.0.6/environment-setup-cortexa8hf-neon-poky-linux-gnueabi
```

## Use SDK to build Hello world and execute on the target

The SDK is installed in /opt/poky/4.0.6/.

First thing is to source the environment setup script in our terminal.

```rb
source /opt/poky/4.0.6/environment-setup-cortexa8hf-neon-poky-linux-gnueabi
```

Now the SDK environment is ready for our development.

Check the compiler version with $CC

```rd
echo $CC
arm-poky-linux-gnueabi-gcc -mfpu=neon -mfloat-abi=hard -mcpu=cortex-a8 -fstack-protector-strong -O2 -D_FORTIFY_SOURCE=2 -Wformat -Wformat-security -Werror=format-security --sysroot=/opt/poky/4.0.6/sysroots/cortexa8hf-neon-poky-linux-gnueabi
```

Create hello.c file with the below code:

```rb
#include <stdio.h>

int main(void)
{
    printf("Hello World! \n");
    return 0;
}
```

Compile the code

```rd
$CC hello.c -o hello
```

copy the hello binary to the target. I use scp for copy.

```rd
scp -r hello root@10.42.0.17:~/
```

Execute the hello binary on the target.

```rd
./hello
```
