# Yocto Project Components

We will understand:

1. BitBake tool
2. Recipes
3. Classes
4. Configurations

## What is bitbake tool?

BitBake is a task scheduler that parses Python and the shell script mixed code.

The code parsed generates and runs tasks that may have a complex dependency chain, which is scheduled to allow a parallel execution and maximize the use of computational resources.

BitBake can be understood as a tool similar to GNU Make in some aspects.

To see a list of the options BitBake supports, use either of the following commands:

```rb
$ bitbake -h
$ bitbake --help
```

The most common usage for BitBake is bitbake recipename, where recipename is the name of the recipe you want to build (referred to as the “target”). The target often equates to the first part of a recipe’s filename (e.g. “foo” for a recipe named foo_1.3.0-r0.bb). So, to process the matchbox-desktop_1.2.3.bb recipe file, you might type the following:

```rb
$ bitbake matchbox-desktop
```

Several different versions of matchbox-desktop might exist. BitBake chooses the one selected by the distribution configuration.

BitBake also tries to execute any dependent tasks first. So for example, before building matchbox-desktop, BitBake would build a cross compiler and glibc if they had not already been built.

A useful BitBake option to consider is the -k or --continue option. This option instructs BitBake to try and continue processing the job as long as possible even after encountering an error. When an error occurs, the target that failed and those that depend on it cannot be remade. However, when you use this option other dependencies can still be processed.

## Recipes

Files that have the .bb suffix are “recipes” files. In general, a recipe contains information about a single piece of software. This information includes the location from which to download the unaltered source, any source patches to be applied to that source (if needed), which special configuration options to apply, how to compile the source files, and how to package the compiled output.

The term “package” is sometimes used to refer to recipes. However, since the word “package” is used for the packaged output from the OpenEmbedded build system (i.e. .ipk or .deb files), this document avoids using the term “package” when referring to recipes.

## Classes

Class files (.bbclass) contain information that is useful to share between recipes files. An example is the autotools class, which contains common settings for any application that Autotools uses.

## Configurations

The configuration files (.conf) define various configuration variables that govern the OpenEmbedded build process. These files fall into several areas that define machine configuration options, distribution configuration options, compiler tuning options, general common configuration options, and user configuration options in conf/local.conf, which is found in the Build Directory.
