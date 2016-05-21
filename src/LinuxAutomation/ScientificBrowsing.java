package LinuxAutomation;

/**
 * 实现科学上网
 * 
 * @author smt
 *
 */
public class ScientificBrowsing extends LinuxAutomation {
	String http_proxy;
	// 配置shadowsocks
	String ss_server;
	int ss_server_port;
	int ss_server_speed_port;
	String ss_local_address;
	int ss_local_port;
	String ss_password;
	int ss_timeout;
	String ss_method;
	String ss_fastOpen;
	String ss_worker;
	String ss_shadowsocksPath;

	public String getHttp_proxy() {
		return http_proxy;
	}

	public void setHttp_proxy(String http_proxy) {
		this.http_proxy = http_proxy;
	}

	public String getSs_server() {
		return ss_server;
	}

	public void setSs_server(String ss_server) {
		this.ss_server = ss_server;
	}

	public int getSs_server_port() {
		return ss_server_port;
	}

	public void setSs_server_port(int ss_server_port) {
		this.ss_server_port = ss_server_port;
	}

	public String getSs_local_address() {
		return ss_local_address;
	}

	public void setSs_local_address(String ss_local_address) {
		this.ss_local_address = ss_local_address;
	}

	public int getSs_local_port() {
		return ss_local_port;
	}

	public void setSs_local_port(int ss_local_port) {
		this.ss_local_port = ss_local_port;
	}

	public String getSs_password() {
		return ss_password;
	}

	public void setSs_password(String ss_password) {
		this.ss_password = ss_password;
	}

	public int getSs_timeout() {
		return ss_timeout;
	}

	public void setSs_timeout(int ss_timeout) {
		this.ss_timeout = ss_timeout;
	}

	public String getSs_method() {
		return ss_method;
	}

	public void setSs_method(String ss_method) {
		this.ss_method = ss_method;
	}

	public String getSs_fastOpen() {
		return ss_fastOpen;
	}

	public void setSs_fastOpen(String ss_fastOpen) {
		this.ss_fastOpen = ss_fastOpen;
	}

	public String getSs_worker() {
		return ss_worker;
	}

	public void setSs_worker(String ss_worker) {
		this.ss_worker = ss_worker;
	}

	public String getSs_shadowsocksPath() {
		return ss_shadowsocksPath;
	}

	public void setSs_shadowsocksPath(String ss_shadowsocksPath) {
		this.ss_shadowsocksPath = ss_shadowsocksPath;
	}

	public ScientificBrowsing() {
		super();
		ss_server = "216.189.154.82";
		ss_server_port = 446;
		ss_server_speed_port = 4460;
		ss_local_address = "0.0.0.0";
		ss_local_port = 1081;
		ss_password = "!abcd1234";
		ss_timeout = 600;
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
	}

	public ScientificBrowsing(Host host) {
		super(host, false);
		ss_server = "216.189.154.82";
		ss_server_port = 446;
		ss_server_speed_port = 4460;
		ss_local_address = "0.0.0.0";
		ss_local_port = 1081;
		ss_password = "!abcd1234";
		ss_timeout = 600;
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
	}

	public ScientificBrowsing(Host host, String http_proxy) {
		super(host, false, http_proxy);
		ss_server = "216.189.154.82";
		ss_server_port = 446;
		ss_server_speed_port = 4460;
		ss_local_address = "0.0.0.0";
		ss_local_port = 1081;
		ss_password = "!abcd1234";
		ss_timeout = 600;
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
	}

	/** 安装Python版的Shadowsocks Server */
	public void installShadowsocksPythonServer() {
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		installShadowsocksPythonServer("0.0.0.0", ss_server_port, ss_local_address, ss_local_port, ss_password,
				ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath, shadowsocksConfigFile);
	}

	/** 安装Python版的Shadowsocks Server */
	public void installShadowsocksPythonServer(String server, int server_port, String local_address,
			int local_port, String password, int timeout, String method, String fastOpen, String worker,
			String shadowsocksPath, String shadowsocksConfigFile) {
		/** 安装 */
		// installPackageByPip("shadowsocks", true);
		installPackageByPip("shadowsocks", true);
		/** 配置 */
		deleteFile(shadowsocksConfigFile, true);
		makeDirectory(shadowsocksPath, true);

		// addTextInFileEnd("{", shadowsocksConfigFile, true);
		// addTextInFileEnd("\"server\":\"" + server + "\",",
		// shadowsocksConfigFile, true);
		// addTextInFileEnd("\"server_port\":\"" + server_port + "\",",
		// shadowsocksConfigFile, true);
		addTextInFileEnd("\"server\":\"" + server + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"server_port\":\"" + server_port + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"local_address\":\"" + local_address + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"local_port\":\"" + local_port + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"password\":\"" + password + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"timeout\":\"" + timeout + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"method\":\"" + method + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"fastOpen\":\"" + fastOpen + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"worker\":\"" + worker + "\" ", shadowsocksConfigFile, true);
		addTextInFileEnd("}", shadowsocksConfigFile, true);
		deleteRowInFile("#", "ssserver", "/etc/rc.local", true);
		insertRowBeforeInFile("#", "exit 0",
				"nohup /usr/local/bin/ssserver -c /etc/shadowsocks/config.json -d start> /etc/shadowsocks/log 2>&1 &",
				"/etc/rc.local", true);
		excuteShell("/etc/rc.local", true);

	}

	/** 卸载Python版的Shadowsocks Server */
	public void uninstallShadowsocksPythonServer() {
		runSshCommand("ssserver -c /etc/shadowsocks/config.json -d stop", true);
		uninstallPackageByPip("shadowsocks", true);
		deleteRowInFile("#", "ssserver", "/etc/rc.local", true);
		deleteFile("/etc/shadowsocks/config.json", true);

	}

	/** 安装haproxy */
	public void installHaproxy() {
		installPackageByAptget("haproxy", true);
		modifyConfigFile("/etc/default/haproxy", "=", "ENABLED", "1", true);

		String filename = "/etc/haproxy/haproxy.cfg";
		createEmptyFile(filename, true);
		addTextInFileEnd("global", filename, true);
		addTextInFileEnd("	ulimit-n 51200", filename, true);
		addTextInFileEnd("defaults", filename, true);
		addTextInFileEnd("	log global", filename, true);
		addTextInFileEnd("	mode tcp", filename, true);
		addTextInFileEnd("	option dontlognull", filename, true);
		addTextInFileEnd("	contimeout 10000", filename, true);
		addTextInFileEnd("	clitimeout 150000", filename, true);
		addTextInFileEnd("	srvtimeout 150000", filename, true);
		addTextInFileEnd("frontend ss-in", filename, true);
		addTextInFileEnd("	bind *:" + ss_server_port, filename, true);
		addTextInFileEnd("	default_backend ss-out", filename, true);
		addTextInFileEnd("backend ss-out", filename, true);
		addTextInFileEnd("	server server1 " + ss_server + ":" + ss_server_port + " maxconn 20480", filename, true);
		restartService("haproxy", true);
		setAutostart("service haproxy start", true);
	}

	/** 卸载haproxy */
	public void uninstallHaproxy() {
		stopService("haproxy", true);
		uninstallPackageByAptget("haproxy", true);
		deleteFile("/etc/default/haproxy", true);
		deleteFile("/etc/haproxy/haproxy.cfg", true);
		cancelAutostart("service haproxy start", true);
	}

	/** 安装Finalspeed */
	public void installFinalspeed(boolean useCommnadlineVersion) {
		
		
		//下载finalspeed客户端 
		// downloadFile("http://share.ijushan.com/03_software/02_tool/FinalSpeed_Client_CLI.zip",
		// true,
		// true);
		if (useCommnadlineVersion) {
//			downloadFile("http://share.ijushan.com/03_software/02_tool/FinalSpeed_Client_CLI.zip",true);
			downloadFile("http://share.ijushan.com/03_software/02_tool/finalspeed_jar.zip",true);
//			downloadFile("http://share.ijushan.com/03_software/02_tool/finalspeed.zip", false, true);
		} else {
			downloadFile("http://share.ijushan.com/03_software/02_tool/finalspeed_client1.0.zip", false, true);
		}
		//进行安装
		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";
		makeDirectory(finalspeedPath, true);
		changeFilePermission(finalspeedPath, "755", false, true);
		changeFileOwner(finalspeedPath, host.username, host.username, true);

		if (useCommnadlineVersion) {
//			moveFile("FinalSpeed_Client_CLI.zip", finalspeedPath, true);
//			unzip(finalspeedPath + "/FinalSpeed_Client_CLI.zip", finalspeedPath, true);
			moveFile("finalspeed_jar.zip", finalspeedPath, true);
			unzip(finalspeedPath + "/finalspeed_jar.zip", finalspeedPath, true);
//			moveFile("finalspeed.zip", finalspeedPath, true);
//			unzip(finalspeedPath + "/finalspeed.zip", finalspeedPath, true);
		} else {
			moveFile("finalspeed_client1.0.zip", finalspeedPath, true);
			unzip(finalspeedPath + "/finalspeed_client1.0.zip", finalspeedPath, true);
		}

		String prefix_filepath;
		if (useCommnadlineVersion) {
//			prefix_filepath = "";
			prefix_filepath = "/finalspeed_jar";
		} else {
			prefix_filepath = "/finalspeed_client";
		}
		//进行配置
		String clientConfigFile;
		clientConfigFile = finalspeedPath + prefix_filepath + "/client_config.json";
		createEmptyFile(clientConfigFile, true);
		setFileFullPermission(clientConfigFile, false, true);
		changeFileOwner(clientConfigFile, host.username, host.username, true);
		addTextInFileEnd("{", clientConfigFile, true);
		addTextInFileEnd("	\"download_speed\":" + String.valueOf(30 * 1024 * 1024 / 8) + ",", clientConfigFile, true);
		addTextInFileEnd("	\"protocal\":" + "\"" + "udp" + "\"" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"server_address\":" + "\"" + ss_server + "\"" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"server_port\":" + "150" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"socks5_port\":" + "1083" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"upload_speed\":" + String.valueOf(10 * 1024 * 1024 / 8), clientConfigFile, true);
		addTextInFileEnd("}", clientConfigFile, true);

		String portMapFile;
		portMapFile = finalspeedPath + prefix_filepath + "/port_map.json";
		createEmptyFile(portMapFile, true);
		setFileFullPermission(portMapFile, false, true);
		changeFileOwner(portMapFile, host.username, host.username, true);
		addTextInFileEnd("{", portMapFile, true);
		addTextInFileEnd("}", portMapFile, true);
		addPortMap("my_ss", ss_server_port, ss_server_speed_port, useCommnadlineVersion);
		addPortMap("ssh", 22, 220, useCommnadlineVersion);

		String logFile;
		logFile = finalspeedPath + prefix_filepath + "/log";
		createEmptyFile(logFile, true);
		setFileFullPermission(logFile, false, true);
		changeFileOwner(logFile, host.username, host.username, true);
		//设置启动脚本
		// setRunAfterLogin("/usr/bin/java -jar " + finalspeedPath +
		// prefix_filepath+"/finalspeed_client.jar",true);
		deleteFile(finalspeedPath + prefix_filepath + "/start.sh", true);
		addTextInFileEnd("cd " + finalspeedPath + prefix_filepath, finalspeedPath + prefix_filepath + "/start.sh",
				true);
		if (useCommnadlineVersion) {
//			addTextInFileEnd("nohup /usr/bin/java -jar " + finalspeedPath + prefix_filepath + "/client.jar  -b >>/usr/share/finalspeed/log 2>&1 &",
//					finalspeedPath + prefix_filepath + "/start.sh", true);
			addTextInFileEnd("nohup /usr/bin/java -jar " + finalspeedPath + prefix_filepath + "/finalspeed.jar -b >>"+finalspeedPath + prefix_filepath+"/log 2>&1 &",
					finalspeedPath + prefix_filepath + "/start.sh", true);
//			addTextInFileEnd("/usr/bin/java -jar " + finalspeedPath + prefix_filepath + "/finalspeed.jar -b",
//					finalspeedPath + prefix_filepath + "/start.sh", true);
		} else {
			addTextInFileEnd("/usr/bin/java -jar " + finalspeedPath + prefix_filepath + "/finalspeed_client.jar -b",
					finalspeedPath + prefix_filepath + "/start.sh", true);
		}
		
		changeFileOwner(finalspeedPath + prefix_filepath + "/start.sh", host.username, host.username, true);

		//设置停止脚本
		deleteFile(finalspeedPath + prefix_filepath + "/stop.sh", true);
		if (useCommnadlineVersion) {
//			addTextInFileEnd("ps -ef | grep client.jar  | grep -v grep | awk '{print $2}' | xargs kill -s 9",
//					finalspeedPath + prefix_filepath + "/stop.sh", true);
			addTextInFileEnd("ps -ef | grep finalspeed.jar  | grep -v grep | awk '{print $2}' | xargs kill -s 9",
					finalspeedPath + prefix_filepath + "/stop.sh", true);
		} else {
			addTextInFileEnd("ps -ef | grep finalspeed_client.jar | grep -v grep | awk '{print $2}' | xargs kill -s 9",
					finalspeedPath + prefix_filepath + "/stop.sh", true);
		}
		
		changeFileOwner(finalspeedPath + prefix_filepath + "/stop.sh", host.username, host.username, true);

		//设置重启脚本
		deleteFile(finalspeedPath + prefix_filepath + "/restart.sh", true);
		if (useCommnadlineVersion) {
			addTextInFileEnd("cd " + finalspeedPath + prefix_filepath, finalspeedPath + prefix_filepath + "/restart.sh",
					true);
//			addTextInFileEnd("ps -ef | grep client.jar  | grep -v grep | awk '{print $2}' | xargs kill -s 9",
//					finalspeedPath + prefix_filepath + "/restart.sh", true);
//			addTextInFileEnd("nohup /usr/bin/java -jar "+finalspeedPath + prefix_filepath +"/client.jar  >>"+finalspeedPath + prefix_filepath +"/log 2>&1 &",
//					finalspeedPath + prefix_filepath + "/restart.sh", true);
			addTextInFileEnd("ps -ef | grep finalspeed_client.jar  | grep -v grep | awk '{print $2}' | xargs kill -s 9",
					finalspeedPath + prefix_filepath + "/restart.sh", true);
			addTextInFileEnd("nohup /usr/bin/java -jar "+finalspeedPath + prefix_filepath +"/finalspeed_client.jar  >>"+finalspeedPath + prefix_filepath +"/log 2>&1 &",
					finalspeedPath + prefix_filepath + "/restart.sh", true);
		} else {
		}		
		changeFileOwner(finalspeedPath + prefix_filepath + "/restart.sh", host.username, host.username, true);

		//设置开机自启
		if (useCommnadlineVersion) {
			if (useCommnadlineVersion) {
				 addScheduledTask("0 */1 * * * ", "smt", "sh " + finalspeedPath +
						 "/restart.sh", "finalspeed", true);
//				setRunAfterLogin("su " + host.username + " -c \"nohup /usr/bin/java -jar " + finalspeedPath + prefix_filepath + "/client.jar -b >>"
//				+ finalspeedPath +  prefix_filepath + "/log 2>&1 &\"", true);
//				setRunAfterLogin("su " + host.username + " -c \"nohup /usr/bin/java -jar " + finalspeedPath +  prefix_filepath +"/finalspeed.jar   -b >>"
//						+ finalspeedPath +  prefix_filepath + "/log 2>&1 &\"", true);
//				setAutostart("su " + host.username + " -c \"nohup /usr/bin/java -jar " + finalspeedPath +  prefix_filepath +"/finalspeed.jar -b >>"
//						+ finalspeedPath +  prefix_filepath + "/log 2>&1 &\"", true);;
				setAutostart("su " + host.username + " -c \" sh "+finalspeedPath + prefix_filepath + "/start.sh\"", true);
			} else {
			}
			if (useCommnadlineVersion) {
//				excuteShell(finalspeedPath + prefix_filepath + "/start.sh", true);
				excuteShell("/etc/rc.local", true);
			} else {
				/** 后续通过gnome-session-properties设置finalspeed为开机自启 */
			}
//			sourceProfile("/etc/profile");
//			setRunAfterLogin("sh /fs/start.sh",true);
		} else {
		}


	}

	/** 添加finalSpeed加速的端口映射 */
	public void modifyServer(String server, boolean useCommnadlineVersion) {

		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";

		String prefix_filepath;
		if (useCommnadlineVersion) {
//			prefix_filepath = "";
			prefix_filepath = "/finalspeed_jar";
		} else {
			prefix_filepath = "/finalspeed_client";
		}

		String clientConfigFile;
		clientConfigFile = finalspeedPath + prefix_filepath + "/client_config.json";
		modifyJsonKey(clientConfigFile, ".server_address", "\""+server+"\"",true);
		
	}

	/** 添加finalSpeed加速的端口映射 */
	public void addPortMap(String name, int port, int mapPort, boolean useCommnadlineVersion) {

		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";

		String prefix_filepath;
		if (useCommnadlineVersion) {
//			prefix_filepath = "";
			prefix_filepath = "/finalspeed_jar";
		} else {
			prefix_filepath = "/finalspeed_client";
		}

		String portMapFile;
		portMapFile = finalspeedPath + prefix_filepath + "/port_map.json";
		addObjectToJsonKeyArray(portMapFile, ".map_list", "{\"name\":\"" + name + "\",\"dst_port\":"
				+ String.valueOf(port) + ",\"listen_port\":" + String.valueOf(mapPort) + "}", true);
	}

	/** 添加finalSpeed加速的端口映射 */
	public void removePortMap(String name, boolean useCommnadlineVersion) {
		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";

		String prefix_filepath;
		if (useCommnadlineVersion) {
//			prefix_filepath = "";
			prefix_filepath = "/finalspeed_jar";
		} else {
			prefix_filepath = "/finalspeed_client";
		}

		String portMapFile;
		portMapFile = finalspeedPath + prefix_filepath + "/port_map.json";

		deleteObjectInJsonKeyArray(portMapFile, ".map_list", ".name==\"" + name + "\"", true);
	}

	/** 卸载Finalspeed */
	public void uninstallFinalspeed(boolean useCommnadlineVersion) {
		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";
		String prefix_filepath;
		if (useCommnadlineVersion) {
//			prefix_filepath = "";
			prefix_filepath = "/finalspeed_jar";
		} else {
			prefix_filepath = "/finalspeed_client";
		}
		excuteShell(finalspeedPath + prefix_filepath + "/stop.sh", true);
		deleteFile(finalspeedPath, true);
//		cancelRunAfterLogin("client.jar", true);
//		cancelAutostart("finalspeed.jar", true);
		cancelAutostart("finalspeed", true);
//		cancelRunAfterLogin("finalspeed.jar",true);
//		sourceProfile("/etc/profile");
		
		if (useCommnadlineVersion) {
//			removeScheduledTask("0 3 * * * ", "smt", "sh " + finalspeedPath + "/restart.sh", "finalspeed", true);
		} else {
		}
	}

	/** 安装Python版的Shadowsocks */
	public void installShadowsocksPythonClient() {
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		installShadowsocksPythonClient(true);
//		installShadowsocksPythonClient("127.0.0.1", ss_server_speed_port, ss_local_address, ss_local_port, ss_password,
//				ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath, shadowsocksConfigFile);
	}

	/** 安装Python版的Shadowsocks */
	public void installShadowsocksPythonClient(boolean useFinalspeed) {
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		if (useFinalspeed) {
			installShadowsocksPythonClient("127.0.0.1", ss_server_speed_port, ss_local_address, ss_local_port,
					ss_password, ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath,
					shadowsocksConfigFile);
		} else {
			installShadowsocksPythonClient(ss_server, ss_server_port, ss_local_address, ss_local_port, ss_password,
					ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath, shadowsocksConfigFile);
		}
	}

	/** 安装 */
	public void installShadowsocksPythonClient(String server, int server_port, String local_address,
			int local_port, String password, int timeout, String method, String fastOpen, String worker,
			String shadowsocksPath, String shadowsocksConfigFile) {
		/** 安装 */
		// installPackageByPip("shadowsocks", true);
		installPackageByPip("shadowsocks", true);
		/** 配置 */
		deleteFile(shadowsocksConfigFile, true);
		makeDirectory(shadowsocksPath, true);

		createEmptyFile(shadowsocksConfigFile, true);
		setFileFullPermission(shadowsocksConfigFile, false, true);
		addTextInFileEnd("{", shadowsocksConfigFile, true);
		addTextInFileEnd("\"server\":\"" + server + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"server_port\":\"" + server_port + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"local_address\":\"" + local_address + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"local_port\":\"" + local_port + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"password\":\"" + password + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"timeout\":\"" + timeout + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"method\":\"" + method + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"fastOpen\":\"" + fastOpen + "\",", shadowsocksConfigFile, true);
		addTextInFileEnd("\"worker\":\"" + worker + "\" ", shadowsocksConfigFile, true);
		addTextInFileEnd("}", shadowsocksConfigFile, true);
		deleteRowInFile("#", "sslocal", "/etc/rc.local", true);
		insertRowBeforeInFile("#", "exit 0",
				"nohup /usr/local/bin/sslocal -c /etc/shadowsocks/config.json -d start> /etc/shadowsocks/log 2>&1 &",
				"/etc/rc.local", true);
		excuteShell("/etc/rc.local", true);

	}

	/** 卸载Python版的Shadowsocks */
	public void uninstallShadowsocksPythonClient() {
		runSshCommand("sslocal -c /etc/shadowsocks/config.json -d stop", true);
		uninstallPackageByPip("shadowsocks", true);
		deleteRowInFile("#", "sslocal", "/etc/rc.local", true);
		deleteFile("/etc/shadowsocks/config.json", true);

	}

	/** 安装带图形界面的Shadowsocks */
	public void installShadowsocksQt5() {
		// 安装shadowsocks-qt5
		// installPackageByAptget("shadowsocks-qt5",true);
		installPackageByAptget("shadowsocks-qt5", true);
	}

	/** 卸载带图形界面的Shadowsocks */
	public void uninstallShadowsocksQt5() {
		uninstallPackageByAptget("shadowsocks-qt5", true);
		runSshCommand("rm /etc/shadowsocks/config.json", true);
	}

	/** 安装Genpac */
	public void installGenpac() {
		String Proxy = "SOCKS5 127.0.0.1:"+ss_local_port;
		installGenpac(Proxy);
	}

	/** 安装Genpac */
	public void installGenpac(String Proxy) {
		installPackageByPip(" --upgrade genpac", true);
		runSshCommand(
				" genpac -p \"" + Proxy + "\" --gfwlist-proxy=\"" + Proxy
						+ "\" --output=\"/etc/autoproxy.pac\" --gfwlist-url=\"https://autoproxy-gfwlist.googlecode.com/svn/trunk/gfwlist.txt\" --user-rule-from=\"user-rules.txt\" \n",
				true);
	}

	/** 卸载Genpac */
	public void uninstallGenpac() {
		uninstallPackageByPip("genpac", true);
		deleteFile("/etc/autoproxy.pac", true);
	}

	/** 安装Privoxy */
	public void installPrivoxy() {
		String socks5proxy = "127.0.0.1:"+ss_local_port;
		installPrivoxy(socks5proxy);
	}

	/** 安装Privoxy */
	public void installPrivoxy(String socks5proxy) {
		installPackageByAptget("privoxy", true);
		// 配置Privoxy
		addTextInFileEnd("forward-socks5 / " + socks5proxy + " .", "/etc/privoxy/config", true);
		replaceStringInFile("#", "listen-address  localhost:8118", "listen-address 0.0.0.0:8118", "/etc/privoxy/config",
				true);
		restartService("privoxy", true);
	}

	/** 卸载Privoxy */
	public void uninstallPrivoxy() {
		stopService("privoxy", true);
		// runSshCommand("service privoxy stop",true);
		uninstallPackageByAptget("privoxy", true);
		deleteFile("/etc/privoxy", true);
		// runSshCommand("rm -rf /etc/privoxy",true);
	}

	/** 安装AutoProxy2Privoxy */
	public void installAutoProxy2Privoxy() {
		String socks5proxy = "127.0.0.1:"+ss_local_port;
		installAutoProxy2Privoxy(socks5proxy);
	}

	/** 安装AutoProxy2Privoxy */
	public void installAutoProxy2Privoxy(String socks5proxy) {
		downloadFile("https://raw.githubusercontent.com/cckpg/autoproxy2privoxy/master/gfw.action", false, true);
		moveFile("gfw.action", "/etc/privoxy/", true);
		replaceStringInFile("#", "127.0.0.1:7127", socks5proxy, "/etc/privoxy/gfw.action", true);
		deleteRowInFile("#", "actionsfile gfw.action", "/etc/privoxy/config", true);
		addTextInFileEnd("actionsfile gfw.action", "/etc/privoxy/config", true);
		restartService("privoxy", true);
	}

	public void uninstallAutoProxy2Privoxy() {
		runSshCommand("sed -i '/actionsfile gfw.action/d' /etc/privoxy/config \n", true);
		runSshCommand("rm /etc/privoxy/gfw.action \n", true);
	}

	/** 安装ProxyChain */
	public void installProxyChains() {
		String proxy = "http 127.0.0.1 8118 \\nsocks5 127.0.0.1 "+ss_local_port;
		installProxyChains(proxy);
	}

	public void installProxyChains(String proxy) {
		installPackageByAptget("proxychains", true);
		replaceStringInFile("#", "strict_chain", "#strict_chain", "/etc/proxychains.conf", true);
		replaceStringInFile("#random_chain", "random_chain", "/etc/proxychains.conf", true);
		replaceStringInFile("#", "socks4 \t127.0.0.1 9050", proxy, "/etc/proxychains.conf", true);
	}

	public void uninstallProxyChains() {
		uninstallPackageByAptget("proxychains", true);
	}

	/** 安装科学上网所有软件 */
	public void installAllSoftware() {
		installAllSoftware(true, true);
	}

	/**安装透明代理*/
	public void installTransparentProxy(){
		
	}
	/** 安装科学上网所有软件 */
	public void installAllSoftware(boolean isClient, boolean useCommnadlineVersion) {
		updateAptRepository(true);
		installPackageByAptget("jq crudini", true);
		installPackageByAptget("python-gevent", true);
		installPackageByAptget("python-pip", true);
		installPackageByAptget("default-jdk", true);
		if (isClient) {
			installHaproxy();
			installFinalspeed(useCommnadlineVersion);
			installShadowsocksPythonClient();
			// installShadowsocksQt5();
			installGenpac();
			installPrivoxy();
			installAutoProxy2Privoxy();
			installProxyChains();
			// installShadowsocksPythonClient(true);
		} else {
			installShadowsocksPythonServer();
		}
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware() {
		uninstallAllSoftware(true, true);
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware(boolean isClient, boolean useCommnadlineVersion) {
		if (isClient) {
			uninstallAutoProxy2Privoxy();
			uninstallPrivoxy();
			uninstallGenpac();
			uninstallProxyChains();
			uninstallShadowsocksPythonClient();
// 			uninstallShadowsocksQt5(true);
			uninstallFinalspeed(useCommnadlineVersion);
			uninstallHaproxy();
		} else {
			uninstallShadowsocksPythonServer();
		}
	}
	/** 卸载科学上网所有软件 */
	public void addDnsForward(){
		addNatInIptables("udp", false, true,
				53, "DNAT --to-destination "+ss_server, true);
	}

	public void testCode() {
//		installPackageByAptget("ethtool", true);
//		installPackageByAptget("hostapd dnsmasq", true);
		String driver = "nl80211";
		String filename = "/etc/hostapd/hostapd.conf";
		createEmptyFile(filename, true);
		addTextInFileEnd("interface=wlan0", filename, true);
		addTextInFileEnd("driver="+driver, filename, true);
		addTextInFileEnd("ssid=smt-transparent", filename, true);
		addTextInFileEnd("channel=6", filename, true);
		addTextInFileEnd("hw_mode=g", filename, true);
		addTextInFileEnd("ignore_broadcast_ssid=0", filename, true);
		addTextInFileEnd("auth_algs=1", filename, true);
		addTextInFileEnd("wpa=3", filename, true);
		addTextInFileEnd("wpa_passphrase=!abcd1234", filename, true);
		addTextInFileEnd("wpa_key_mgmt=WPA-PSK", filename, true);
		addTextInFileEnd("wpa_pairwise=TKIP", filename, true);
		addTextInFileEnd("rsn_pairwise=CCMP", filename, true);
		
		
//		restartService("haproxy", true);
//		setAutostart("service haproxy start", true);
	}
}