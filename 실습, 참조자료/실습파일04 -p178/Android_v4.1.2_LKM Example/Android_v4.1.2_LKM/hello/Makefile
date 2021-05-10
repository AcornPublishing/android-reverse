obj-m := hello.o 
KERNELDIR := /home/namdaehyeon/SHV-Kernel/
TOOLCPATH:= /home/namdaehyeon/arm-eabi-4.4.3/bin
PWD := $(shell pwd)

all:
	$(MAKE)	ARCH=arm 					\
	EXTRA_CFLAGS=-Wno-error 				\
	CROSS_COMPILE=$(TOOLCPATH)/arm-eabi- -C $(KERNELDIR) 	\
	M=$(PWD) modules
clean: 
	rm -rf *.mod.* *.o *.ko .*.ko.* .*.mod.o.* .*.o.* modules.order \
		Module.symvers .tmp_versions
