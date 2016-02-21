package LinuxAutomation;

import WindowsAutomation.WindowsAutomation;

/**
 * 实现科学上网
 * 
 * @author smt
 *
 */
public class VpsServer extends LinuxAutomation {
	// 配置shadowsocks
	String ss_server;
	String ss_server_port;
	String ss_local_address;
	String ss_local_port;
	String ss_password;
	String ss_timeout;
	String ss_method;
	String ss_fastOpen;
	String ss_worker;
	String ss_shadowsocksPath;

	String initialRootPassword;
	int initialSshPort;
	boolean isCleanVPS;
	
	String iptableRule;
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

	public VpsServer() {
		super();
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
		isCleanVPS = false;
		initialRootPassword = "";
		initialSshPort = 22;
		
		iptableRule="/etc/iptables.rules";
	}

	public VpsServer(String hostname, int port, String rootUsername, String rootPassword, String username,
			String password) {
		super(hostname, port, false, rootUsername, rootPassword, username, password);
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
		isCleanVPS = false;
		initialRootPassword = "";
		iptableRule="/etc/iptables.rules";
	}

	public VpsServer(String hostname, int port, boolean isCleanVPS, String initialRootPassword, int initialSshPort,
			String rootUsername, String rootPassword, String username, String password) {
		super(hostname, port, false, rootUsername, rootPassword, username, password);
		this.isCleanVPS = isCleanVPS;
		this.initialRootPassword = initialRootPassword;
		this.initialSshPort = initialSshPort;
		ss_server = "104.160.34.127";
		ss_server_port = "443";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
		iptableRule="/etc/iptables.rules";

	}

	/** 初始化 */
	public void initialize() {
		if (isCleanVPS) {
			WindowsAutomation windowAutomation=new WindowsAutomation();
			windowAutomation.sleep(60000);
			LinuxAutomation linuxAutomation ;
			linuxAutomation= new LinuxAutomation("proxy.ijushan.com", initialSshPort, true, rootUsername,
					initialRootPassword, username, password);
			linuxAutomation.initialize();
			linuxAutomation.changePassword(rootUsername, rootPassword, false);
			linuxAutomation.close();
//
//			windowAutomation.sleep(10000);
			
			linuxAutomation = new LinuxAutomation("proxy.ijushan.com", initialSshPort, true, rootUsername,
					rootPassword, username, password);
			linuxAutomation.initialize();
			linuxAutomation.modifySshPort(port,false);
			linuxAutomation.restartService("ssh",false);
			linuxAutomation.close();

//			windowAutomation.sleep(10000);

			linuxAutomation = new LinuxAutomation("proxy.ijushan.com", port, true, rootUsername,
					rootPassword, username, password);
			linuxAutomation.initialize();
			linuxAutomation.updateAptRepository(false);
			linuxAutomation.installPackageByAptget("sudo", false);
			linuxAutomation.addUserAndDirecotry(username, password, false);
			linuxAutomation.appendToGroup(username, "sudo", false);
			linuxAutomation.disableRootSsh(false);
			linuxAutomation.restartService("ssh",false);
			linuxAutomation.close();			

		}
		sshConnection.initialize();
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
	public void setIptable(){
		cleanIptables(true);
		//loopback
		allowLoopbackByIptables(true);
		//DNS
		addRuleInIptablesRuleChain("INPUT", "udp", false,true,"53", "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp", false,false,"53", "", "ACCEPT", true);
		//PING
		allowPingByIptables(true);
		
		//HTTP /HTTPS
//		addRuleInIptablesRuleChain("INPUT", "tcp",true, "80,"+ ss_server_port, "NEW,ESTABLISHED", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp",false,true, "80", "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp",false, false,"80", "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp",false, true, ss_server_port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp",false, false, ss_server_port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp",false, true, ss_server_port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp",false, false, ss_server_port, "", "ACCEPT", true);
		//SSH
//		addRuleInIptablesRuleChain("INPUT", "tcp", false, String.valueOf(port), "NEW,ESTABLISHED", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, true,String.valueOf(port), "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, false,String.valueOf(port), "", "ACCEPT", true);

		addRuleInIptablesRuleChain("INPUT", "all",false,true, "", "", "DROP", true);
//		setPolicyInIptablesRuleChain("INPUT", "ACCEPT", true);
//		setPolicyInIptablesRuleChain("OUTPUT", "ACCEPT", true);
//		setPolicyInIptablesRuleChain("FORWARD", "ACCEPT", true);		
//		logIptables(true);
//		preventDdosByIptables(true);
//		saveIptables(iptableRule,true);
		setIptablesAutoStart(iptableRule,true);
	}
	/**安装final speed*/
	public void installFinalSpeed() {
		deleteFile("install_fs.sh", true);
		downloadFile("http://fs.d1sm.net/finalspeed/install_fs.sh", true);
		changeFilePermission("install_fs.sh", "+x", false, true);
		runSshCommand("./install_fs.sh  2>&1 | tee install.log", true);
		setAutostart("sh /fs/start.sh",true);
		addScheduledTask("0 3 * * * ","root","sh  /fs/restart.sh", "finalspeed",true);
	}
	
	/** 安装科学上网所有软件 */
	public void installAllSoftware() {

		updateAptRepository(true);
		installPackageByAptget("python-gevent", true);
		installPackageByAptget("python-pip", true);
		installShadowsocksPythonServer();
		setIptable();
		installFinalSpeed();
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware() {
		uninstallShadowsocksPythonServer();
	}

	public void testCode() {
		addScheduledTask("0 3 * * * ","root","sh  /fs/restart.sh", "finalspeed",true);
	}

}