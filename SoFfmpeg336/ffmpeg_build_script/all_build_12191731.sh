#!/bin/bash
# 环境: ununtu 14 + ndk-r14b + ffmpeg 3.3.6
export NDK_HOME=/home/zzg/programs/android-ndk-r14b
export PLATFORM_VERSION=android-14
function build
{
	echo "start build ffmpeg for $ARCH"
	./configure --target-os=linux \
	--prefix=$PREFIX --arch=$ARCH \
	--disable-doc \
	--enable-shared \
	--disable-static \
	--disable-yasm \
	--disable-asm \
	--disable-symver \
	--enable-gpl \
	--disable-ffmpeg \
	--disable-ffplay \
	--disable-ffprobe \
	--disable-ffserver \
	--cross-prefix=$CROSS_COMPILE \
	--enable-cross-compile \
	--sysroot=$SYSROOT \
	--enable-small \
	--extra-cflags="-Os -fpic $ADDI_CFLAGS" \
	--extra-ldflags="$ADDI_LDFLAGS" \
	$ADDITIONAL_CONFIGURE_FLAG
	make clean
	make -j4
	make install
	echo "build ffmpeg for $ARCH finished"
}

#arm
ARCH=arm
CPU=armv7-a
PREFIX=$(pwd)/android/$ARCH
TOOLCHAIN=$NDK_HOME/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64
CROSS_COMPILE=$TOOLCHAIN/bin/arm-linux-androideabi-
ADDI_CFLAGS="-marm"
SYSROOT=$NDK_HOME/platforms/$PLATFORM_VERSION/arch-$ARCH/
build

#x86
ARCH=x86
CPU=x86
PREFIX=$(pwd)/android/$ARCH
TOOLCHAIN=$NDK_HOME/toolchains/x86-4.9/prebuilt/linux-x86_64
CROSS_COMPILE=$TOOLCHAIN/bin/i686-linux-android-
ADDI_CFLAGS="-march=i686 -mtune=intel -mssse3 -mfpmath=sse -m32"
SYSROOT=$NDK_HOME/platforms/$PLATFORM_VERSION/arch-$ARCH/
build

