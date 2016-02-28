package LinuxAutomation;


public class PrivateCloud extends LinuxAutomation{

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

		
		pathToShare="/home/"+username;
		shareName="home";	
		
		publicPathToShare="/home/"+username+"/share";
		publicShareName="public"; 

		publicPathAria2=publicPathToShare+"/04_aria2";

		pathOwncloudData=pathToShare+"/SmtOwncloudData";
		
		virtualHostMain = new VirtualHost("", "www.ijushan.com", "/home/smt/web");
		virtualHostShare = new VirtualHost("", "share.ijushan.com", publicPathToShare,true);

		mySqlServerDatabase=new MySqlServerDatabase();
	}


	public PrivateCloud(String hostname,int port, String rootUsername, String rootPassword, String username, String password) {
		super(hostname,port,false,rootUsername, rootPassword,  username,  password);
		pathToShare="/home/"+username;
		shareName="home";	
		
		publicPathToShare="/home/"+username+"/share";
		publicShareName="public"; 

		publicPathAria2=publicPathToShare+"/04_aria2";

		pathOwncloudData=pathToShare+"/SmtOwncloudData";
		
		virtualHostMain = new VirtualHost("", "www.ijushan.com", "/home/smt/web");
		virtualHostShare = new VirtualHost("", "share.ijushan.com", publicPathToShare,true);
		
		mySqlServerDatabase=new MySqlServerDatabase("localhost","SmtOwncloudDataBase",username,password);
	}
	
	/**安装ftp服务器*/
	public void installFtp(){
		installPackageByAptget("vsftpd",true);
		String configFile="/etc/vsftpd.conf";
		makeDirectory(publicPathToShare,true);
		changeFileOwner(publicPathToShare, username, username,true);
		setFileFullPermission(publicPathToShare,true,true);
		changeFilePermission(publicPathToShare,"755",false,true);
		
		modifyConfigFile( configFile,  "listen",  "YES",true);
		modifyConfigFile( configFile,  "local_enable",  "YES",true);
		modifyConfigFile( configFile,  "write_enable",  "YES",true);


		modifyConfigFile( configFile,  "allow_writeable_chroot",  "YES",true);
		modifyConfigFile( configFile,  "chroot_local_user",  "YES",true);
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
		runSshCommand("smbpasswd -a "+username,password+"\n"+password+"\n",true);
		addTextInFileEnd("["+shareName+"]", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	path = "+pathToShare, "/etc/samba/smb.conf",true);
		addTextInFileEnd("	writable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd(";	browsable = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd(";	available = yes", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	guest ok = no", "/etc/samba/smb.conf",true);
		addTextInFileEnd("	valid users = "+username, "/etc/samba/smb.conf",true);
		addTextInFileEnd("#", "/etc/samba/smb.conf",true);
						
		//配置公开共享目录
		makeDirectory(publicPathToShare,true);
		changeFileOwner(publicPathToShare, username, username,true);
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
		deleteFile("teamviewer_linux*.deb",true);
		downloadFile("http://download.teamviewer.com/download/teamviewer_i386.deb",true);
		runSshCommand("yes | dpkg -i teamviewer_linux*.deb && yes | apt-get -f install ",true);
	}
	/**卸载TeamViewer，后续需测试）*/
	public void uninstallTeamViewer(){
		uninstallPackageByDpkg("teamviewer_linux*.deb",true);
	}
	/**安装Aria2*/
	public void installAria2(){

		addAptRepository("ppa:t-tujikawa/ppa",true);
		installPackageByAptget("aria2",true);
		makeDirectory(publicPathAria2, username, username,true);	

		String configFilePath="/etc/aria2";
		String configFile=configFilePath+"/aria2.conf";
		String removeAria2Shell=configFilePath+"/remove_aria2_file.sh";
		makeDirectory(configFilePath,true);
		setFileFullPermission(configFilePath,true,true);		
		
		addTextInFileEnd("rm \"$3.aria2\"",removeAria2Shell,true);		
		setFileFullPermission(removeAria2Shell,true,true);		

		createEmptyFile(configFile,true);
		setFileFullPermission(configFile,true,true);
		//RPC
		modifyConfigFile( configFile,  "enable-rpc",  "true",true);
		modifyConfigFile( configFile,  "rpc-listen-all",  "true",true);
		modifyConfigFile( configFile,  "rpc-allow-origin-all",  "true",true);
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
		modifyConfigFile(configFile, "save-session-interval", "30",true);	
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
		
		killProgram("aria2c",1000,true);
		deleteFile(removeAria2Shell,true);
		deleteFile(configFile,true);
		cancelAutostart("service cron start",true);	
		deleteFile("/etc/cron.d/aria2",true);
		cancelAutostart("/usr/bin/aria2c",true);			
		uninstallPackageByAptget("aria2",true);
	}
	/**安装Yaaw*/
	public void installYaaw(){
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
		installPackageByAptget("mysql-server",true);
		runSshCommand( "mysql_secure_installation",true);
		runSshCommand("mysql_secure_installation",
				rootPassword+"\n"
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
		//installPackageByAptget("apache2",true);
		installPackageByAptget("php5 php5-mysql",true);
		installPackageByAptget("php5-gd php5-json php5-curl php5-intl php5-mcrypt php5-imagick",true);


		addMySqlServerDatabase(mySqlServerDatabase);

		downloadFile("http://download.opensuse.org/repositories/isv:ownCloud:community/xUbuntu_14.04/Release.key",true);
		addAptKey("Release.key",true);
		createEmptyFile("/etc/apt/sources.list.d/owncloud.list",true);
		addTextInFileEnd("deb http://download.opensuse.org/repositories/isv:/ownCloud:/community/xUbuntu_14.04/ /", "/etc/apt/sources.list.d/owncloud.list",true);

		installPackageByAptget("ownCloud",true);		
		
		makeDirectory(pathOwncloudData,"www-data","www-data",true);
		setFileFullPermission(pathOwncloudData,true,true);
		linkPath(publicPathToShare,pathOwncloudData+"/smt/files",true);
	}
	/**卸载OwnCloud*/
	public void uninstallOwnCloud(){
		uninstallPackageByAptget("ownCloud",true);
	}
	/**安装所有私有云相关软件*/
	public void installAllSoftware()	{
		installPackageByAptget("jq crudini", true);
		installTeamViewer();
		installFtp();
		installSamba();
		installAria2();
		installYaaw();
		installXware();
		installOwnCloud();

	}
	/**卸载所有私有云相关软件*/
	public void uninstallAllSoftware(){
		uninstallTeamViewer();
		uninstallFtp();
		uninstallSamba();
		uninstallAria2();
		uninstallYaaw();
		uninstallXware();
		uninstallOwnCloud();

	}
	public void addMySqlServerDatabase(MySqlServerDatabase mySqlServerDatabase){
//		runSshCommand("mysql -u root -p",
//				rootPassword+"\n"
//				+ "CREATE USER '"+mySqlServerDatabase.owncloudUserName+"'@'"+mySqlServerDatabase.hostname+"' IDENTIFIED BY '"+mySqlServerDatabase.owncloudUserPassword+"';\n"
//				+ "CREATE DATABASE "+mySqlServerDatabase.owncloudDatabase+";\n"
//				+ "GRANT ALL ON "+mySqlServerDatabase.owncloudDatabase+".* TO '"+mySqlServerDatabase.owncloudUserName+"'@'"+mySqlServerDatabase.hostname+"';\n"
//				+ "FLUSH PRIVILEGES;\n"
//				+ "exit\n",true);
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " -e\"" + "CREATE USER '"
				+ mySqlServerDatabase.owncloudUserName + "'@'" + mySqlServerDatabase.hostname + "' IDENTIFIED BY '"
				+ mySqlServerDatabase.owncloudUserPassword+"'\"",true);
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " -e\"" + "CREATE DATABASE "
				+ mySqlServerDatabase.owncloudDatabase+"\"",true);
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " -e\"" + "GRANT ALL ON "
				+ mySqlServerDatabase.owncloudDatabase + ".* TO '" + mySqlServerDatabase.owncloudUserName + "'@'"
				+ mySqlServerDatabase.hostname+"'\"",true);
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " -e\"" + "FLUSH PRIVILEGES\"",true);
	}
	/**测试代码*/
	public void test_code()
	{
//
//		String configFilePath="/etc/aria2";
//		String configFile=configFilePath+"/aria2.conf";
//		modifyConfigFile( configFile,  "enable-rpc",  "true",true);
//		uninstallFtp();
//		uninstallSamba();
//		installSamba();
//		installFtp();
		uninstallYaaw();
		uninstallAria2();
		installAria2();
		installYaaw();

	}

}
