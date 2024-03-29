package LinuxAutomation;


public class PrivateCloud extends LinuxAutomation{
	String pathDrive;
	String pathHome;
	String pathWeb;
	String pathOwncloudData;
	String pathToShare; 
	String shareName; 
	String publicPathToShare; 
	String publicShareName; 

	String publicPathAria2; 
	
	VirtualHost virtualHostMain;
	VirtualHost virtualHostShare;
	VirtualHost virtualHostLocal;
	MySqlServerDatabase mySqlServerDatabase;
	
	public PrivateCloud() {
		super();
		
		pathDrive="/mnt/usb";
		pathHome="/home/"+host.username;
		pathWeb="/home/smt/web";
		
		pathToShare=pathDrive;
		shareName="home";	
		
		publicPathToShare=pathDrive+"/share";
		publicShareName="public"; 

		publicPathAria2=publicPathToShare+"/04_aria2";

		pathOwncloudData=pathWeb+"/owncloud.ijushan.com/data";
		
		virtualHostMain = new VirtualHost("", "www.ijushan.com", pathWeb);
		virtualHostShare = new VirtualHost("", "share.ijushan.com", publicPathToShare,true);

		mySqlServerDatabase=new MySqlServerDatabase();
	}


	public PrivateCloud(Host host,String pathHome,String pathDrive,String pathWeb) {
		super(host,false);
//		pathDrive="/media/smt/yunpan";
//		pathHome="/home/"+username;
		this.pathHome=pathHome;
		this.pathDrive=pathDrive;
		
		pathToShare=pathDrive;
		shareName="home";	
		
		publicPathToShare=pathDrive+"/share";
		publicShareName="public"; 

		publicPathAria2=publicPathToShare+"/04_aria2";

		pathOwncloudData=pathWeb+"/owncloud.ijushan.com/data";
		
		virtualHostMain = new VirtualHost("", "www.ijushan.com", pathWeb);
		virtualHostShare = new VirtualHost("", "share.ijushan.com", publicPathToShare,true);
		
		mySqlServerDatabase=new MySqlServerDatabase("localhost","SmtOwncloudDataBase",host.username,host.password);
	}
	
	/**安装ftp服务器*/
	public void installFtp(){
		installPackageByAptget("vsftpd",true);
		String configFile="/etc/vsftpd.conf";
		makeDirectory(publicPathToShare,true);
		changeFileOwner(publicPathToShare, host.username, host.username,true);
		setFileFullPermission(publicPathToShare,true,true);
		changeFilePermission(publicPathToShare,"755",false,true);
		
		modifyConfigFile( configFile,  "listen",  "YES",true);
		modifyConfigFile( configFile,  "local_enable",  "YES",true);
		modifyConfigFile( configFile,  "write_enable",  "YES",true);


		modifyConfigFile( configFile,  "allow_writeable_chroot",  "YES",true);
		modifyConfigFile( configFile,  "chroot_local_user",  "NO",true);
		modifyConfigFile( configFile,  "local_umask", "0022",true);
		modifyConfigFile( configFile,  "file_open_mode", "0777",true);
		modifyConfigFile( configFile,  "listen_port",  "2121",true);
		modifyConfigFile( configFile,  "pasv_enable",  "YES",true);
		modifyConfigFile( configFile,  "pasv_min_port",  "10000",true);
		modifyConfigFile( configFile,  "pasv_max_port",  "10000",true);
		modifyConfigFile( configFile,  "anonymous_enable",  "YES",true);
		modifyConfigFile( configFile,  "anon_umask", "0000",true);
		modifyConfigFile( configFile,  "anon_root", publicPathToShare,true);
		modifyConfigFile( configFile,  "anon_upload_enable",  "YES",true);
		modifyConfigFile( configFile,  "anon_mkdir_write_enable",  "YES",true);
		modifyConfigFile( configFile,  "anon_world_readable_only",  "NO",true);
		modifyConfigFile( configFile,  "anon_other_write_enable",  "YES",true);
		
		modifyConfigFile( configFile,  "dirmessage_enable",  "YES",true);
		modifyConfigFile( configFile,  "xferlog_enable",  "YES",true);
		restartService("vsftpd",true);
		
	}
	/**卸载ftp服务器*/
	public void uninstallFtp(){
		stopService("vsftpd",true);
		uninstallPackageByAptget("vsftpd",true);		
	}
	
	/** 安装samba服务 */
	public void installSamba(){
		installPackageByAptget("samba samba-common python-glade2 system-config-samba",true);
		//配置私有共享目录				
//		sshConnection.runCommand("smbpasswd -a "+username,password+"\n"+password+"\n",true);
		runSshCommand("smbpasswd -a "+host.username,host.password+"\n"+host.password+"\n",true);
		addTextInFileEnd("["+shareName+"]", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	path = "+pathToShare, "/etc/samba/smb.conf",true);
		addTextInFileEnd("	writable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd(";	browsable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd(";	available = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	guest ok = no", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	valid users = "+host.username, "/etc/samba/smb.conf",true);
		addTextInFileEnd("#", "/etc/samba/smb.conf",true);
						
		//配置公开共享目录
		makeDirectory(publicPathToShare,true);
		changeFileOwner(publicPathToShare, host.username, host.username,true);
		setFileFullPermission(publicPathToShare,true,true);
		changeFilePermission(publicPathToShare,"755",false,true);
		addTextInFileEnd("["+publicShareName+"]", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	comment = Guest access share", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	path = "+publicPathToShare, "/etc/samba/smb.conf",true);
		addTextInFileEnd("	writable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	browsable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	guest ok = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	create mask = 0777", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	force create mask = 0777", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	directory mask = 02777", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	force directory mask = 02777", "/etc/samba/smb.conf",true);
		addTextInFileEnd("#", "/etc/samba/smb.conf",true);
		
		restartService("smbd",true);
	}
	/** 卸载samba服务 */
	public void uninstallSamba(){
		stopService("smbd",true);
		replaceMultiLineStringInFile("\\["+publicShareName+"\\](.|\\n)*?#\\n", "", "/etc/samba/smb.conf",true);
		replaceMultiLineStringInFile("\\["+shareName+"\\](.|\\n)*?#\\n", "", "/etc/samba/smb.conf",true);
		uninstallPackageByAptget("samba samba-common system-config-samba",true);
	}
	/**安装非官方迅雷linux下载客户端，需安装完后手动进行设置*/
	public void installXware(){
		installPackageByAptget("git build-essential devscripts",true);
		installPackageByAptget("python3 pyqt5-dev-tools coffeescript chrpath python3-pyqt5 python3-pyqt5.qtwebkit python3-pyqt5.qtmultimedia python3-tk python3-pyinotify libcap2-bin findutils sed qtbase5-dev sip-dev pyqt5-dev",true);
		gitClone("git://github.com/Xinkai/XwareDesktop.git",true);
		runSshCommand("env QT_SELECT=5 make && make install ",true);
		
		runSshCommand("cd /root/XwareDesktop && dpkg-checkbuilddeps",true);
		installPackageByAptget("gcc-multilib debhelper",true);
		runSshCommand("cd /root/XwareDesktop && dpkg-buildpackage",true);
		installPackageByAptget("gdebi",true);
		runSshCommand("gdebi xware-desktop_*.deb","y",true);
		System.out.println("请打开网址 https://github.com/Xinkai/XwareDesktop/wiki/%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E 查看后续设置步骤");
	}
	/**卸载Xware，后续需测试）*/
	public void uninstallXware(){
		uninstallPackageByDpkg("xware-desktop_*.deb",true);
	}
	/**安装TeamViewer，后续需测试）*/
	public void installTeamViewer(){
		deleteFile("teamviewer_*.deb",true);
		downloadFile("http://download.teamviewer.com/download/teamviewer_i386.deb",true);
		runSshCommand("yes | dpkg -i teamviewer_*.deb && yes | apt-get -f install ",true);
	}
	/**卸载TeamViewer，后续需测试）*/
	public void uninstallTeamViewer(){
		uninstallPackageByDpkg("teamviewer_linux*.deb",true);
	}
	/**安装向日葵，后续需测试）*/
	public void installSunlogin(){
		deleteFile("sunlogin_remoteclient.tar.gz",true);
		downloadFile("http://sunlogin.oray.com/zh_CN/download/download?id=16","sunlogin_remoteclient.tar.gz",true);
		tar("sunlogin_remoteclient.tar.gz",true);
	}
	/**卸载向日葵，后续需测试）*/
	public void uninstallSunlogin(){
		uninstallPackageByDpkg("teamviewer_linux*.deb",true);
	}
	/**安装Aria2*/
	public void installAria2(){

		addAptRepository("ppa:t-tujikawa/ppa",true);
		installPackageByAptget("aria2",true);
		makeDirectory(publicPathAria2, host.username, host.username,true);	

		String configFilePath="/etc/aria2";
		String configFile=configFilePath+"/aria2.conf";
		makeDirectory(configFilePath,true);
		setFileFullPermission(configFilePath,true,true);	
		createEmptyFile(configFile,true);
		setFileFullPermission(configFile,true,true);	

		String removeAria2Shell=configFilePath+"/remove_aria2_file.sh";
		addTextInFileEnd("rm \"$3.aria2\"",removeAria2Shell,true);		
		setFileFullPermission(removeAria2Shell,true,true);		
		
		String sessionFile=configFilePath+"/aria2.session";
		createEmptyFile(sessionFile,true);
		setFileFullPermission(sessionFile,true,true);
		//RPC
		modifyConfigFile( configFile,  "enable-rpc",  "true",true);
		modifyConfigFile( configFile,  "rpc-listen-all",  "true",true);
		modifyConfigFile( configFile,  "rpc-listen-port",  "6800",true);
		modifyConfigFile( configFile,  "rpc-allow-origin-all",  "true",true);
		modifyConfigFile( configFile,  "rpc-secret",  host.password,true);		
		modifyConfigFile( configFile,  "event-poll",  "select",true);
		
		//速度
		modifyConfigFile(configFile, "max-concurrent-downloads", "5",true);
		modifyConfigFile(configFile, "max-connection-per-server", "10",true);
		modifyConfigFile(configFile, "min-split-size", "10M",true);
		modifyConfigFile(configFile, "split", "2",true);
		modifyConfigFile(configFile, "max-overall-download-limit", "0",true);
		modifyConfigFile(configFile, "max-download-limit", "0",true);
		modifyConfigFile(configFile, "max-overall-upload-limit", "100",true);
		modifyConfigFile(configFile, "max-upload-limit", "50",true);
		
		// 进度保存
		modifyConfigFile( configFile,  "continue",  "true",true);
		modifyConfigFile(configFile, "save-session", configFilePath + "/aria2.session",true);
		modifyConfigFile(configFile, "input-file", configFilePath + "/aria2.session",true);
		modifyConfigFile(configFile, "log", configFilePath + "/aria2.log",true);
		modifyConfigFile(configFile, "force-save", "true",true);
		modifyConfigFile(configFile, "save-session-interval", "10",true);	
		modifyConfigFile(configFile, "on-download-complete ", " " + configFilePath + "/remove_aria2_file.sh ",true);
		
		//BT
		modifyConfigFile(configFile, "follow-torrent", "true",true);
		modifyConfigFile(configFile, "enable-dht", "true",true);
		modifyConfigFile(configFile, "bt-enable-lpd", "true",true);
		modifyConfigFile(configFile, "enable-peer-exchange", "true",true);
		modifyConfigFile(configFile, "user-agent", "uTorrent/2210(25130)",true);
		modifyConfigFile(configFile, "peer-id-prefix", "UT2210-",true);
		modifyConfigFile(configFile, "seed-ratio", "0 ",true);
		modifyConfigFile(configFile, "bt-hash-check-seed", "true",true);
		modifyConfigFile(configFile, "bt-seed-unverified", "true",true);
		modifyConfigFile(configFile, "bt-save-metadata", "true",true);
	
		//磁盘相关
		modifyConfigFile( configFile,  "dir",  publicPathAria2,true);
		modifyConfigFile( configFile,  "file-allocation",  "prealloc",true);
		setAutostart("su smt -c \"/usr/bin/aria2c -D --conf-path="+configFile+"\"",true);
		
		setAutostart("service cron start",true);
		addTextInFileEnd("*/1 * * * * root chmod 777 -R "+publicPathAria2, "/etc/cron.d/aria2",true);
		takeEffectAutostart(true);
	}
	/**卸载Aria2*/
	public void uninstallAria2(){
		String configFilePath="/etc/aria2";
		String configFile=configFilePath+"/aria2.conf";
		String removeAria2Shell=configFilePath+"/remove_aria2_file.sh";
		String sessionFile=configFilePath+"/aria2.session";
		
		killProgram("aria2c",1000,true);
		
		deleteFile(sessionFile,true);
		deleteFile(removeAria2Shell,true);
		deleteFile(configFile,true);
		
//		cancelAutostart("service cron start",true);	
		deleteFile("/etc/cron.d/aria2",true);
		cancelAutostart("/usr/bin/aria2c",true);			
		uninstallPackageByAptget("aria2",true);
	}
	/**安装Yaaw*/
	public void installYaaw(){
		installPackageByAptget("git build-essential devscripts",true);
		gitClone("https://github.com/binux/yaaw.git",true);
		copyFile("yaaw", virtualHostMain.documentRoot+"/",true);
		copyFile("yaaw", virtualHostShare.documentRoot+"/",true);		
	}
	
	/**卸载Yaaw*/
	public void uninstallYaaw(){
		deleteFile("yaaw",true);
		deleteFile(virtualHostMain.documentRoot+"/yaaw",true);
		deleteFile( virtualHostShare.documentRoot+"/yaaw",true);		
	}
	/**安装MySql-Server*/
	public void installMySqlServer(){
//		installPackageByAptget("mysql-server",true);
		runSshCommand( "mysql_secure_installation",true);
		runSshCommand("mysql_secure_installation",
				host.rootPassword+"\n"
				+"n"+"\n"
				+"y"+"\n"
				+"y"+"\n"
				+"y"+"\n"
				+"y"+"\n",true);
		
	}
	/**卸载MySql-Server*/
	public void uninstallMySqlServer(){
		uninstallPackageByAptget("mysql-server",true);
		
	}
	/**安装OwnCloud*/
	public void installOwnCloud(){
		installPackageByAptget("apache2",true);
		installPackageByAptget("php php-mysql",true);
		installPackageByAptget("php-gd php-json php-curl php-intl php-mcrypt php-imagick",true);
		installPackageByAptget("php-zip php-xml php-mbstring",true);

		addMySqlServerDatabase(mySqlServerDatabase);

//		downloadFile("http://download.opensuse.org/repositories/isv:ownCloud:community/xUbuntu_16.04/Release.key",true);
		downloadFile("https://download.owncloud.org/download/repositories/stable/Ubuntu_16.04/Release.key",true);
		addAptKey("Release.key",true);
		createEmptyFile("/etc/apt/sources.list.d/owncloud.list",true);
//		addTextInFileEnd("deb http://download.opensuse.org/repositories/isv:/ownCloud:/community/xUbuntu_16.04/ /", "/etc/apt/sources.list.d/owncloud.list",true);
		addTextInFileEnd("deb http://download.owncloud.org/download/repositories/stable/Ubuntu_16.04/ /", "/etc/apt/sources.list.d/owncloud.list",true);
		
		updateAptRepository(true);
		installPackageByAptget("owncloud-files",true);		
		linkPath("/var/owncloud","/var/html/",true);
		
//		makeDirectory(pathOwncloudData,"www-data","www-data",true);
//		setFileFullPermission(pathOwncloudData,true,true);
//		linkPath(publicPathToShare,pathOwncloudData+"/smt/files",true);
	}
	/**卸载OwnCloud*/
	public void uninstallOwnCloud(){
		uninstallPackageByAptget("ownCloud",true);
		
	}
	/**安装所有私有云相关软件*/
	public void installAllSoftware()	{
		addMountInFstab("/dev/sda1","/mnt/usb","ext4","defaults","0","0",true);
		installPackageByAptget("jq crudini", true);
		installTeamViewer();
		installFtp();
		installSamba();
		installAria2();
		installYaaw();
//		installXware();
		installMySqlServer();
		//手动安装mysqlserver
		installOwnCloud();

	}
	/**卸载所有私有云相关软件*/
	public void uninstallAllSoftware(){
//		uninstallTeamViewer();
		uninstallFtp();
		uninstallSamba();
		uninstallAria2();
		uninstallYaaw();
		uninstallXware();
		uninstallMySqlServer();
		uninstallOwnCloud();

	}
	public void addMySqlServerDatabase(MySqlServerDatabase mySqlServerDatabase){

		runSshCommand("mysql -u" + host.rootUsername + " -p" + host.rootPassword + " -e\"" + "CREATE USER '"
				+ mySqlServerDatabase.userName + "'@'" + mySqlServerDatabase.hostname + "' IDENTIFIED BY '"
				+ mySqlServerDatabase.password+"'\"",true);
		//为用户创建一个数据库
		runSshCommand("mysql -u" + host.rootUsername + " -p" + host.rootPassword + " -e\"" + "CREATE DATABASE "
				+ mySqlServerDatabase.database+"\"",true);
		//授权用户拥有数据库的所有权限
		runSshCommand("mysql -u" + host.rootUsername + " -p" + host.rootPassword + " -e\"" + "GRANT ALL ON "
				+ mySqlServerDatabase.database + ".* TO '" + mySqlServerDatabase.userName + "'@'"
				+ mySqlServerDatabase.hostname+"'\"",true);
		//刷新系统权限表
		runSshCommand("mysql -u" + host.rootUsername + " -p" + host.rootPassword + " -e\"" + "FLUSH PRIVILEGES\"",true);
	}
	/**测试代码*/
	public void test_code()
	{
//		addMySqlServerDatabase(mySqlServerDatabase);
//		uninstallFtp();
//		uninstallSamba();
//		uninstallAria2();
//		uninstallYaaw();
//		
//		installFtp();
//		installSamba();
//		installAria2();
//		installYaaw();
		
//
//		unlinkPath("/home/smt/SmtOwncloudData/smt/files/share",true);
////
//		makeDirectory(pathOwncloudData,"www-data","www-data",true);
////		setFileFullPermission(pathOwncloudData,true,true);
//		linkPath(publicPathToShare,pathOwncloudData+"/smt/files",true);
	}

}
