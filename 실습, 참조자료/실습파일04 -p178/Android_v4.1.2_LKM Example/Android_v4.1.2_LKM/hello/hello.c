#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/init.h>
#include <linux/syscalls.h>

int __init hello_init(void) {
	printk(KERN_INFO "hello_kernel_init\n");
	return 0;
}
void __exit hello_exit(void) {
	printk(KERN_INFO "hello_kernel_exit\n");
}

module_init(hello_init);
module_exit(hello_exit);
MODULE_AUTHOR("namdaehyeon");
MODULE_LICENSE("GPL");
