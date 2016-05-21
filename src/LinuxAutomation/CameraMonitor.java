package LinuxAutomation;

public class CameraMonitor extends LinuxAutomation {
	MySqlServerDatabase mySqlServerDatabase;
	public CameraMonitor() {
		// TODO Auto-generated constructor stub
		mySqlServerDatabase=new MySqlServerDatabase();
	}


	/** 安装Motion*/
	public void installMotion() { 
		installPackageByAptget("motion", true);
//		modifyConfigFile("/etc/motion/motion.conf"," ", "daemon", "off", true);
		modifyConfigFile("/etc/motion/motion.conf"," ", "output_normal ", "off", true);
		modifyConfigFile("/etc/motion/motion.conf"," ", "webcam_port", "8881", true);

		modifyConfigFile("/etc/motion/motion.conf"," ", "webcam_localhost", "off", true);
		modifyConfigFile("/etc/motion/motion.conf"," ", "width ", "1280", true);
		modifyConfigFile("/etc/motion/motion.conf"," ", "height", "720", true);
		setAutostart("motion", true);
		takeEffectAutostart(true);
	}

	/** 卸载Motion*/
	public void uninstallMotion() { 
		killProgram("motion", true);
		cancelAutostart("motion", true);
		uninstallPackageByAptget("motion", true);
	}
 
	/** 安装ZoneMinder*/
	public void installZoneMinder() { 
		addAptRepository("ppa:mc3man/trusty-media", true);
		installPackageByAptget(" ffmpeg libarchive-tar-perl libarchive-zip-perl libdate-manip-perl libdevice-serialport-perl", true);	
		installPackageByAptget(" libjpeg62 libmime-perl libstdc++6 libwww-perl zlib1g", true);	
		
		
		addAptRepository("ppa:iconnor/zoneminder", true);
		updateAptRepository(true); 
		installPackageByAptget("zoneminder", true);		
		runMySqlBatch(host.rootUsername, host.rootPassword,"/usr/share/zoneminder/db/zm_create.sql",  true);
		runMySqlCommand(host.rootUsername, host.rootPassword,"grant select,insert,update,delete,create,alter,index,lock tables on zm.* to 'zmuser'@localhost identified by 'zmpass';",  true);
		runSshCommand("a2enconf zoneminder", true);
		runSshCommand("a2enmod rewrite", true);
		runSshCommand("a2enmod cgi", true);
		insertRowAfterInFile("        Starting $prog", "sleep 10", "/etc/init.d/zoneminder", true);
		changeFileOwner("/etc/zm/zm.conf", "www-data", "www-data", true);
		restartService("apache2",true);
		runSshCommand("/etc/init.d/zoneminder restart",true);
		runSshCommand("adduser www-data video",true);
//		在浏览器中打ZM，在 Options->Path 修改 PATH_ZMS 为/zm/cgi-bin/nph-zms 
//		runSshCommand("/etc/init.d/zoneminder restart",true);
	}

	/** 卸载ZoneMinder*/
	public void uninstallZoneMinder() { 
		
	}
	/** 安装科学上网所有软件 */
	public void installAllSoftware() { 
	}
 
	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware() { 
	}



	public void testCode() {
//		installZoneMinder();
//		installMotion();
		
//		runMySqlBatch(rootUsername, rootPassword,"/usr/share/zoneminder/db/zm_create.sql",  true);
//		runMySqlCommand(rootUsername, rootPassword,"grant select,insert,update,delete,create,alter,index,lock tables on zm.* to 'zmuser'@localhost identified by 'zmpass';",  true);
//		runSshCommand("a2enconf zoneminder", true);
//		runSshCommand("a2enmod rewrite", true);
//		runSshCommand("a2enmod cgi", true);
//		insertRowAfterInFile("        Starting $prog", "sleep 10", "/etc/init.d/zoneminder", true);
//		changeFileOwner("/etc/zm/zm.conf", "www-data", "www-data", true);
//		restartService("apache2",true);
//		runSshCommand("/etc/init.d/zoneminder restart",true);
		runSshCommand("adduser www-data video",true);
//		在浏览器中打ZM，在 Options->Path 修改 PATH_ZMS 为/zm/cgi-bin/nph-zms 

//		runSshCommand("/etc/init.d/zoneminder restart",true);
		
	}
}