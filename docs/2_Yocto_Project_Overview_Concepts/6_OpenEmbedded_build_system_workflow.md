# The OpenEmbedded Build System Workflow

The OpenEmbedded Build System uses a “workflow” to accomplish image and SDK generation. The following figure overviews that workflow:

![Embedded Linux Devices](../pictures/YP-flow-diagram.png)

Following is a brief summary of the “workflow”:

1. Developers specify architecture, policies, patches and configuration details.
2. The build system fetches and downloads the source code from the specified location. The build system supports standard methods such as tarballs or source code repositories systems such as Git.
3. Once source code is downloaded, the build system extracts the sources into a local work area where patches are applied and common steps for configuring and compiling the software are run.
4. The build system then installs the software into a temporary staging area where the binary package format you select (DEB, RPM, or IPK) is used to roll up the software.
5. Different QA and sanity checks run throughout entire build process.
6. After the binaries are created, the build system generates a binary package feed that is used to create the final root file image.
7. The build system generates the file system image and a customized Extensible SDK (eSDK) for application development in parallel.

In general, the build’s workflow consists of several functional areas:

User Configuration: metadata you can use to control the build process.

Metadata Layers: Various layers that provide software, machine, and distro metadata.

Source Files: Upstream releases, local projects, and SCMs.

Build System: Processes under the control of BitBake. This block expands on how BitBake fetches source, applies patches, completes compilation, analyzes output for package generation, creates and tests packages, generates images, and generates cross-development tools.

Package Feeds: Directories containing output packages (RPM, DEB or IPK), which are subsequently used in the construction of an image or Software Development Kit (SDK), produced by the build system. These feeds can also be copied and shared using a web server or other means to facilitate extending or updating existing images on devices at runtime if runtime package management is enabled.

Images: Images produced by the workflow.

Application Development SDK: Cross-development tools that are produced along with an image or separately with BitBake.