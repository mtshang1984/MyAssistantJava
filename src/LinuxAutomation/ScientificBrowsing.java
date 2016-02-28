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
	String ss_server_port;
	String ss_server_speed_port;
	String ss_local_address;
	String ss_local_port;
	String ss_password;
	String ss_timeout;
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

	public String getSs_server_port() {
		return ss_server_port;
	}

	public void setSs_server_port(String ss_server_port) {
		this.ss_server_port = ss_server_port;
	}

	public String getSs_local_address() {
		return ss_local_address;
	}

	public void setSs_local_address(String ss_local_address) {
		this.ss_local_address = ss_local_address;
	}

	public String getSs_local_port() {
		return ss_local_port;
	}

	public void setSs_local_port(String ss_local_port) {
		this.ss_local_port = ss_local_port;
	}

	public String getSs_password() {
		return ss_password;
	}

	public void setSs_password(String ss_password) {
		this.ss_password = ss_password;
	}

	public String getSs_timeout() {
		return ss_timeout;
	}

	public void setSs_timeout(String ss_timeout) {
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
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_server_speed_port = "4430";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
	}

	public ScientificBrowsing(String hostname, int port, String rootUsername, String rootPassword, String username,
			String password) {
		super(hostname, port, false, rootUsername, rootPassword, username, password);
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_server_speed_port = "4430";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
	}

	public ScientificBrowsing(String hostname, int port, String rootUsername, String rootPassword, String username,
			String password, String http_proxy) {
		super(hostname, port, false, rootUsername, rootPassword, http_proxy);
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_server_speed_port = "4430";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
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
	public void installShadowsocksPythonServer(String server, String server_port, String local_address,
			String local_port, String password, String timeout, String method, String fastOpen, String worker,
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

	/** 安装Python版的Shadowsocks */
	public void installShadowsocksPythonClient() {
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		// installShadowsocksPythonClient(ss_server, ss_server_port,
		// ss_local_address, ss_local_port, ss_password,
		// ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath,
		// shadowsocksConfigFile);
		installShadowsocksPythonClient("127.0.0.1", ss_server_speed_port, ss_local_address, ss_local_port, ss_password,
				ss_timeout, ss_method, ss_fastOpen, ss_worker, ss_shadowsocksPath, shadowsocksConfigFile);
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
	public void installShadowsocksPythonClient(String server, String server_port, String local_address,
			String local_port, String password, String timeout, String method, String fastOpen, String worker,
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
		String Proxy = "SOCKS5 127.0.0.1:1080";
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
		String socks5proxy = "127.0.0.1:1080";
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
		String socks5proxy = "127.0.0.1:1080";
		installAutoProxy2Privoxy(socks5proxy);
	}

	/** 安装AutoProxy2Privoxy */
	public void installAutoProxy2Privoxy(String socks5proxy) {
		downloadFile("https://raw.githubusercontent.com/cckpg/autoproxy2privoxy/master/gfw.action", true, true);
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
		String proxy = "http 127.0.0.1 8118 \\nsocks5 127.0.0.1 1080";
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
	public void installFinalspeed() {
		downloadFile("https://github.com/zqhong/finalspeed/releases/download/v1.0/FinalSpeed_Client_CLI.zip", true,
				true);
		String finalspeedPath;
		finalspeedPath = "/usr/share/finalspeed";
		makeDirectory(finalspeedPath, true);
		changeFilePermission(finalspeedPath, "755", false, true);
		moveFile("FinalSpeed_Client_CLI.zip", finalspeedPath, true);
		unzip(finalspeedPath + "/FinalSpeed_Client_CLI.zip", finalspeedPath,true);

		String clientConfigFile;
		clientConfigFile = finalspeedPath + "/client_config.json";
		createEmptyFile(clientConfigFile, true);
		setFileFullPermission(clientConfigFile, false, true);
		addTextInFileEnd("{", clientConfigFile, true);
		addTextInFileEnd("	\"download_speed\":" + String.valueOf(30 * 1024 * 1024 / 8) + ",", clientConfigFile, true);
		addTextInFileEnd("	\"protocal\":" + "\"" + "udp" + "\"" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"server_address\":" + "\"" + ss_server + "\"" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"server_port\":" + "150" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"socks5_port\":" + "1083" + ",", clientConfigFile, true);
		addTextInFileEnd("	\"upload_speed\":" + String.valueOf(10 * 1024 * 1024 / 8), clientConfigFile, true);
		addTextInFileEnd("}", clientConfigFile, true);

		String portMapFile;
		portMapFile = finalspeedPath + "/port_map.json";
		createEmptyFile(portMapFile, true);
		setFileFullPermission(portMapFile, false, true);
		addTextInFileEnd("{", portMapFile, true);
		addTextInFileEnd("    \"map_list\": [", portMapFile, true);
		addTextInFileEnd("        {", portMapFile, true);
		addTextInFileEnd("	        \"dst_port\":" + ss_server_port + ",", portMapFile, true);
		addTextInFileEnd("	        \"listen_port\":" + ss_server_speed_port + ",", portMapFile, true);
		addTextInFileEnd("	        \"name\":" + "\"" + "ss" + "\"", portMapFile, true);
		addTextInFileEnd("        },", portMapFile, true);
		addTextInFileEnd("        {", portMapFile, true);
		addTextInFileEnd("	        \"dst_port\":" + port + ",", portMapFile, true);
		addTextInFileEnd("	        \"listen_port\":" + port+"0" + ",", portMapFile, true);
		addTextInFileEnd("	        \"name\":" + "\"" + "ssh" + "\"", portMapFile, true);
		addTextInFileEnd("        }", portMapFile, true);
		addTextInFileEnd("    ]", portMapFile, true);
		addTextInFileEnd("}", portMapFile, true);

		setAutostart("nohup java -jar " + finalspeedPath + "/client.jar> "+finalspeedPath+"/log 2>&1 &",
				"/etc/rc.local", true);
		excuteShell("/etc/rc.local", true);

	}

	/** 卸载Finalspeed */
	public void uninstallFinalspeed() {
		cancelAutostart("/client.jar", true);
	}

	/** 安装科学上网所有软件 */
	public void installAllSoftware() {
		installAllSoftware(true);
	}

	/** 安装科学上网所有软件 */
	public void installAllSoftware(boolean isClient) {
		updateAptRepository(true);
		installPackageByAptget("jq crudini", true);
		installPackageByAptget("python-gevent", true);
		installPackageByAptget("python-pip", true);
		if (isClient) {
			installShadowsocksPythonClient();
			// installShadowsocksQt5();
			installGenpac();
			installPrivoxy();
			installAutoProxy2Privoxy();
			installProxyChains();
			installHaproxy();
			installFinalspeed();
			installShadowsocksPythonClient(true);
		} else {
			installShadowsocksPythonServer();
		}
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware() {
		uninstallAllSoftware(true);
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware(boolean isClient) {
		if (isClient) {
			uninstallFinalspeed();
			uninstallHaproxy();
			uninstallAutoProxy2Privoxy();
			uninstallPrivoxy();
			uninstallGenpac();
			uninstallShadowsocksPythonClient();
			// uninstallShadowsocksQt5(true);
			uninstallProxyChains();
		} else {
			uninstallShadowsocksPythonServer();
		}
	}

	public void testCode() {
//		uninstallShadowsocksPythonClient();
//		uninstallFinalspeed();
//		installShadowsocksPythonClient(false);
//		installFinalspeed();
//		uninstallShadowsocksPythonClient();
//		installShadowsocksPythonClient(true);
//		installPackageByAptget("jq crudini", true);
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		addValueToJsonKey(shadowsocksConfigFile, ".port_password", "\"port_password\"", "{\"1080\":\"!abcd1234\"}", true);
	}
}