package LinuxAutomation;

public class UbuntuTouchAutomation extends LinuxAutomation{

	public UbuntuTouchAutomation() {
		// TODO Auto-generated constructor stub
		host =new Host("192.168.1.152","root","ShMT0659","phablet","ShMT0659",22); 
		useRoot = false;
	}
	/**开启sshServer*/
	public void enableSshServer(){
		//adb shell android-gadget-service enable ssh
		//adb shell sudo rm /etc/init/ssh.override
		//sudo mount -o remount,rw /
//		resize-root-partition
//		#!/bin/sh
//
//		# This script resizes the root partition of the phone, be careful.
//
//		set -xe
//
//		# needed for https://bugs.launchpad.net/ubuntu/+source/gparted/+bug/373409
//		echo "Resizing your root partition, this may take some time..."
//		echo "When done, it will REBOOT the device, make sure you're not losing any work!"
//
//		cd /userdata
//		#create new disk image
//		dd bs=1M count=4000 if=/dev/zero of=system2.img
//		#this may take a while, put the kettle on
//
//		#mount it as a loopback device
//		losetup -f --show system2.img
//		#copy from existing loopback to current one
//		dd if=/dev/loop0 of=/dev/loop2
//
//
//		# then resize the file system on our new device. This may give a warning, accept it
//		e2fsck -f /dev/loop2 || true
//		resize2fs -f /dev/loop2
//
//		#now swap over our new image with the old one
//		mv system.img system.old
//		mv system2.img system.img
//
//		reboot
//
//		#enjoy your new / space
//		df -h
//		#clear up old files
//		rm /userdata/system.old
	}
	
}
