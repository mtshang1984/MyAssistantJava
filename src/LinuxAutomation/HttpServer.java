package LinuxAutomation;

public class HttpServer extends LinuxAutomation {
	String apache2ConfigPath;
	String apache2ConfigFile;
	VirtualHost virtualHostMain;
	VirtualHost virtualHostPrivate;
	VirtualHost virtualHostShare;
	VirtualHost virtualHostData;
	VirtualHost virtualHostTest;	
	VirtualHost virtualHostDefault;
	String publicPathToShare; 

	public HttpServer() {
		super();
		publicPathToShare="/home/"+username+"/share";
		apache2ConfigPath = "/etc/apache2";
		apache2ConfigFile =apache2ConfigPath+"/apache2.conf";
		String fatherPath = "/home/smt/web";
		virtualHostMain = new VirtualHost(apache2ConfigPath, "www.ijushan.com", fatherPath);
		virtualHostPrivate = new VirtualHost(apache2ConfigPath, "my.ijushan.com", fatherPath);
		virtualHostShare = new VirtualHost(apache2ConfigPath, "share.ijushan.com", publicPathToShare,true);
		virtualHostData = new VirtualHost(apache2ConfigPath, "data.ijushan.com", fatherPath);
		virtualHostTest = new VirtualHost(apache2ConfigPath, "test.ijushan.com", fatherPath);
		virtualHostDefault = new VirtualHost(apache2ConfigPath, "", virtualHostMain.documentRoot,true);
		virtualHostDefault.configFile=apache2ConfigPath+"/sites-available/000-default.conf";
	}

	public HttpServer(String hostname,int port, String rootUsername, String rootPassword, String username, String password) {
		super(hostname, port,false,rootUsername, rootPassword, username, password);
	}

	/** 安装Apache2 */
	public void installApache2(){
		installPackageByAptget("apache2",true);
		disableVirtualHost(virtualHostMain);
		disableVirtualHost(virtualHostPrivate);
		disableVirtualHost(virtualHostShare);
		disableVirtualHost(virtualHostTest);
		disableVirtualHost(virtualHostData);
		disableVirtualHost(virtualHostDefault);
		
		addVirtualHost(virtualHostMain);
		addVirtualHost(virtualHostPrivate);
		addVirtualHost(virtualHostShare, true);
		addVirtualHost(virtualHostData);
		addVirtualHost(virtualHostTest);
		addVirtualHost(virtualHostDefault);
		
		addWebDirecotry(publicPathToShare);
		
		modifyConfigFile(apache2ConfigFile," ", "ServerName", "localhost", true);
		
		enableVirtualHost(virtualHostMain);
		enableVirtualHost(virtualHostPrivate);
		enableVirtualHost(virtualHostShare);
		enableVirtualHost(virtualHostData);
		enableVirtualHost(virtualHostTest);
		enableVirtualHost(virtualHostDefault);
		restartService("apache2",true);
	}
	/** 增加一个虚拟主机目录 */
	public void addWebDirecotry(String path){
		replaceMultiLineStringInFile("<Directory\\s"+path+">(.|\\n)*?</Directory>" , "", apache2ConfigFile, true);
		addTextInFileEnd("<Directory " + path + ">", apache2ConfigFile, true);
		addTextInFileEnd(" Options Indexes FollowSymLinks", apache2ConfigFile, true);
		addTextInFileEnd(" AllowOverride None", apache2ConfigFile, true);
		addTextInFileEnd(" Require all granted", apache2ConfigFile, true);
		addTextInFileEnd("</Directory>", apache2ConfigFile, true);
	}

	/** 增加一个虚拟主机 */
	public void addVirtualHost(VirtualHost virtualHost) {
		makeDirectory(virtualHost.documentRoot, true);
		changeFileOwner(virtualHost.documentRoot, username, username, true);

		deleteFile(virtualHost.configFile, true);
		addTextInFileEnd("<VirtualHost *:80>", virtualHost.configFile, true);
		addTextInFileEnd(" ServerAdmin" + " " + "mtshang1984@163.com", virtualHost.configFile, true);
		if(virtualHost.serverName.length()!=0){
			addTextInFileEnd(" ServerName" + " " + virtualHost.serverName, virtualHost.configFile, true);
		}
		addTextInFileEnd(" DocumentRoot" + " " + virtualHost.documentRoot, virtualHost.configFile, true);

		addTextInFileEnd(" <Directory />", virtualHost.configFile, true);
		addTextInFileEnd("  Options FollowSymLinks", virtualHost.configFile, true);
		addTextInFileEnd("  DirectoryIndex index.php index.html index.htm", virtualHost.configFile, true);
		addTextInFileEnd("  AllowOverride None", virtualHost.configFile, true);
		addTextInFileEnd(" </Directory>", virtualHost.configFile, true);

		addTextInFileEnd(" <Directory " + virtualHost.documentRoot + ">", virtualHost.configFile, true);
		addTextInFileEnd("  Options FollowSymLinks", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteEngine On", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteCond %{REQUEST_FILENAME} !-f", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteCond %{REQUEST_FILENAME} !-d", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteRule . index.php", virtualHost.configFile, true);
		addTextInFileEnd("  AllowOverride All", virtualHost.configFile, true);
		addTextInFileEnd("  Order allow,deny", virtualHost.configFile, true);
		addTextInFileEnd("  allow from all", virtualHost.configFile, true);
		addTextInFileEnd(" </Directory>", virtualHost.configFile, true);

		addTextInFileEnd(" ErrorLog ${APACHE_LOG_DIR}/error.log", virtualHost.configFile, true);
		addTextInFileEnd(" CustomLog ${APACHE_LOG_DIR}/access.log combined", virtualHost.configFile, true);
		addTextInFileEnd("</VirtualHost>", virtualHost.configFile, true);



	}

	/** 增加一个虚拟主机 */
	public void addVirtualHost(VirtualHost virtualHost, boolean publicIndex) {
		makeDirectory(virtualHost.documentRoot, true);
		changeFileOwner(virtualHost.documentRoot, username, username, true);

		deleteFile(virtualHost.configFile, true);
		addTextInFileEnd("<VirtualHost *:80>", virtualHost.configFile, true);
		addTextInFileEnd(" ServerAdmin" + " " + "mtshang1984@163.com", virtualHost.configFile, true);
		if(virtualHost.serverName.length()!=0){
			addTextInFileEnd(" ServerName" + " " + virtualHost.serverName, virtualHost.configFile, true);
		}
		addTextInFileEnd(" DocumentRoot" + " " + virtualHost.documentRoot, virtualHost.configFile, true);

		addTextInFileEnd(" <Directory />", virtualHost.configFile, true);
		addTextInFileEnd("  Options FollowSymLinks", virtualHost.configFile, true);
		addTextInFileEnd("  DirectoryIndex index.php index.html index.htm", virtualHost.configFile, true);
		addTextInFileEnd("  AllowOverride None", virtualHost.configFile, true);
		addTextInFileEnd(" </Directory>", virtualHost.configFile, true); 
		
		addTextInFileEnd(" <Directory " + virtualHost.documentRoot + ">", virtualHost.configFile, true);
		if (publicIndex) {
			addTextInFileEnd("  Options Indexes FollowSymLinks MultiViews", virtualHost.configFile, true);
		} else {
			addTextInFileEnd("  Options FollowSymLinks", virtualHost.configFile, true);
		}
		addTextInFileEnd("  RewriteEngine On", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteCond %{REQUEST_FILENAME} !-f", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteCond %{REQUEST_FILENAME} !-d", virtualHost.configFile, true);
		addTextInFileEnd("  RewriteRule . index.php", virtualHost.configFile, true);
		addTextInFileEnd("  AllowOverride All", virtualHost.configFile, true);
		addTextInFileEnd("  Order allow,deny", virtualHost.configFile, true);
		addTextInFileEnd("  allow from all", virtualHost.configFile, true);
		addTextInFileEnd(" </Directory>", virtualHost.configFile, true);
		
		addTextInFileEnd(" ErrorLog ${APACHE_LOG_DIR}/error.log", virtualHost.configFile, true);
		addTextInFileEnd(" CustomLog ${APACHE_LOG_DIR}/access.log combined", virtualHost.configFile, true);
		addTextInFileEnd("</VirtualHost>", virtualHost.configFile, true);

	}

	/** 删除一个虚拟主机 */
	public void removeVirtualHost(VirtualHost virtualHost) {
		deleteFile(virtualHost.configFile, true);
	}

	/** 上线一个虚拟主机 */
	public void enableVirtualHost(VirtualHost virtualHost) {
		runSshCommand("a2ensite " + virtualHost.configFile.replaceAll("/.*/", ""), true);

	}

	/** 下线一个虚拟主机 */
	public void disableVirtualHost(VirtualHost virtualHost) {
		runSshCommand("a2dissite  " + virtualHost.configFile.replaceAll("/.*/", ""), true);
	}

	/** 卸载Apache2 */
	public void uninstallApache2( ) {
		uninstallPackageByAptget("apache2",true);
	}

	/** 安装Perl支持 */
	public void installPerlSupprot( ) {
		installPackageByAptget("libapache2-mod-perl2",true);
	}

	/** 卸载Perl支持 */
	public void uninstallPerlSupprot( ) {
		uninstallPackageByAptget("libapache2-mod-perl2",true);
	}

	/** 安装Python支持 */
	public void installPythonSupprot( ) {
		installPackageByAptget("libapache2-mod-python",true);
	}

	/** 卸载Python支持 */
	public void uninstallPythonSupprot( ) {
		uninstallPackageByAptget("libapache2-mod-python",true);
	}

	/** 安装Php支持 */
	public void installPhpSupprot( ) {
		installPackageByAptget("libapache2-mod-php5 php5 php-pear php5-xcache",true);
	}

	/** 卸载Php支持 */
	public void uninstallPhpSupprot( ) {
		uninstallPackageByAptget("libapache2-mod-php5 php5 php-pear php5-xcache",true);
	}

	/** 安装Http服务器所有软件 */
	public void installAllSoftware( ) {
		installApache2( );
		installPerlSupprot( );
		installPhpSupprot( );
		installPythonSupprot( );
	}

	/** 卸载Http服务器所有软件 */
	public void uninstallAllSoftware( ) {
		uninstallApache2();
		uninstallPerlSupprot();
		uninstallPhpSupprot();
		uninstallPythonSupprot();

	}

	/** 测试代码 */
	public void test_code( ) {
	}
}
