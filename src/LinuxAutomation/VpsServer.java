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

	public VpsServer() {
		super();
		ss_server = "216.189.154.82";
		ss_server_port = "446";
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

		iptableRule = "/etc/iptables.rules";
	}

	public VpsServer(Host host) {
		super(host, false);
		ss_server = "216.189.154.82";
		ss_server_port = "446";
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
		iptableRule = "/etc/iptables.rules";
	}

	public VpsServer(Host host, boolean isCleanVPS, String initialRootPassword, int initialSshPort) {
		super(host, false);
		this.isCleanVPS = isCleanVPS;
		this.initialRootPassword = initialRootPassword;
		this.initialSshPort = initialSshPort;
		ss_server = "216.189.154.82";
		ss_server_port = "446";
		ss_local_address = "0.0.0.0";
		ss_local_port = "1080";
		ss_password = "!abcd1234";
		ss_timeout = "600";
		ss_method = "aes-256-cfb";
		ss_fastOpen = "false";
		ss_worker = "1";
		ss_shadowsocksPath = "/etc/shadowsocks";
		iptableRule = "/etc/iptables.rules";

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

	/** 初始化 */
	public void initialize() {
		if (isCleanVPS) {
			WindowsAutomation windowAutomation = new WindowsAutomation();
			windowAutomation.sleep(60000);
			LinuxAutomation linuxAutomation;
			linuxAutomation = new LinuxAutomation(new Host(host.hostname, host.rootUsername, initialRootPassword,
					host.username, host.password, initialSshPort), true);
			linuxAutomation.initialize();
			linuxAutomation.changePassword(host.rootUsername, host.rootPassword, false);
			linuxAutomation.close();
			//
			// windowAutomation.sleep(10000);

			linuxAutomation = new LinuxAutomation(new Host(host.hostname, host.rootUsername, host.rootPassword,
					host.username, host.password, initialSshPort), true);

			linuxAutomation.initialize();
			linuxAutomation.modifySshPort(host.sshPort, false);
			linuxAutomation.restartService("ssh", false);
			linuxAutomation.close();

			// windowAutomation.sleep(10000);

			linuxAutomation = new LinuxAutomation(new Host(host.hostname, host.rootUsername, host.rootPassword,
					host.username, host.password, host.sshPort), true);
			linuxAutomation.initialize();
			linuxAutomation.updateAptRepository(false);
			linuxAutomation.installPackageByAptget("sudo", false);
			linuxAutomation.addUserAndDirecotry(host.username, host.password, false);
			linuxAutomation.appendToGroup(host.username, "sudo", false);
			linuxAutomation.disableRootSsh(false);
			linuxAutomation.restartService("ssh", false);
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
		addUser(Integer.parseInt(server_port), password);
//		addUser(446, password);

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

	public void setIptable() {
		// cleanIptables(true);
		// SSH
		addRuleInIptablesRuleChain("INPUT", "tcp", false, true, host.sshPort, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, false, host.sshPort, "", "ACCEPT", true);

		// loopback
		allowLoopbackByIptables(true);
		// DNS
		addRuleInIptablesRuleChain("INPUT", "udp", false, true, 53, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp", false, false,53, "", "ACCEPT", true);
		// PING
		allowPingByIptables(true);

		// HTTP /HTTPS
		// addRuleInIptablesRuleChain("INPUT", "tcp",true, "80,"+
		// ss_server_port, "NEW,ESTABLISHED", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, true, 80, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, false, 80, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "tcp", false, true, ss_server_port, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "tcp", false, false, ss_server_port, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "udp", false, true, ss_server_port, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "udp", false, false, ss_server_port, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "all", false, true, 0, "", "DROP", true);
		// setPolicyInIptablesRuleChain("INPUT", "ACCEPT", true);
		// setPolicyInIptablesRuleChain("OUTPUT", "ACCEPT", true);
		// setPolicyInIptablesRuleChain("FORWARD", "ACCEPT", true);
		// logIptables(true);
		// preventDdosByIptables(true);
		// saveIptables(iptableRule,true);
		setIptablesAutoStart(iptableRule, true);
	}

	/** 安装final speed */
	public void installFinalSpeed() {
		installPackageByAptget("cron", true);
		deleteFile("install_fs.sh", true);
		downloadFile("http://fs.d1sm.net/finalspeed/install_fs.sh", true);
		changeFilePermission("install_fs.sh", "+x", false, true);
		deleteRowInFile("server.log", "install_fs.sh", true);
		runSshCommand("./install_fs.sh  2>&1 | tee install.log", true);
//		runSshCommand("sh ./install_fs.sh  2>&1", true);
		setAutostart("sh /fs/start.sh", true);
//		addScheduledTask("0,15,30,45 * * * * ", "root", "sh  /fs/restart.sh", "finalspeed", true);
		addScheduledTask("0 3 * * * ", "root", "sh  /fs/restart.sh", "finalspeed", true);

		excuteShell("sh /fs/start.sh", true);
	}

	/** 卸载final speed */
	public void uninstallFinalSpeed() {
		excuteShell("/fs/stop.sh", true);
		cancelAutostart("sh /fs/start.sh", true);
		removeScheduledTask("0 3 * * * ", "root", "sh  /fs/restart.sh", "finalspeed", true);
		deleteFile("/fs/", true);
	}

	/** 安装科学上网所有软件 */
	public void installAllSoftware() {

		updateAptRepository(true);
		installPackageByAptget("jq crudini", true);

		installPackageByAptget("python-gevent", true);
		installPackageByAptget("python-pip", true);
		setIptable();
		installShadowsocksPythonServer();
		installFinalSpeed();
	}

	/** 卸载科学上网所有软件 */
	public void uninstallAllSoftware() {
		uninstallFinalSpeed();
		uninstallShadowsocksPythonServer();
	}

	public void addUser(int port, String password) {
//		 SSH
//		addRuleInIptablesRuleChain("INPUT", "tcp", false, true, String.valueOf(host.sshPort), "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "tcp", false, false, String.valueOf(host.sshPort), "", "ACCEPT", true);

		addRuleInIptablesRuleChain("INPUT", "tcp", false, true, port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "tcp", false, false, port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp", false, true, port, "", "ACCEPT", true);
		addRuleInIptablesRuleChain("INPUT", "udp", false, false, port, "", "ACCEPT", true);
//		addRuleInIptablesRuleChain("INPUT", "all", false, true, 0, "", "DROP", true);

		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		deleteJsonKey(shadowsocksConfigFile, ".server_port", true);
		deleteJsonKey(shadowsocksConfigFile, ".password", true);
		addValueToJsonKey(shadowsocksConfigFile, ".port_password", "\"port_password\"",
				"{\"" + port + "\":\"" + password + "\"}", true);
		runSshCommand("ssserver -c /etc/shadowsocks/config.json -d stop", true);
		excuteShell("/etc/rc.local", true);
	}
	/**删除用记*/
	public void delteUser(int port) {
		String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		delteValueInJsonKey(shadowsocksConfigFile, ".port_password", String.valueOf(port), true);
		runSshCommand("ssserver -c /etc/shadowsocks/config.json -d stop", true);
		excuteShell("/etc/rc.local", true);

		deleteRuleInIptablesRuleChain("INPUT", "tcp", false, true, port, "", "ACCEPT", true);
		deleteRuleInIptablesRuleChain("INPUT", "tcp", false, false, port, "", "ACCEPT", true);
		deleteRuleInIptablesRuleChain("INPUT", "udp", false, true, port, "", "ACCEPT", true);
		deleteRuleInIptablesRuleChain("INPUT", "udp", false, false, port, "", "ACCEPT", true);
	}
	/**安装pdnsd dns服务*/
	public void installPdnsd(){
		installPackageByAptget("pdnsd", true);
	}
	/**安装pdnsd dns服务*/
	public void installDnsmasq(){
		installPackageByAptget("pdnsd", true);
	}
	public void testCode() {

		updateAptRepository(true);
		installPackageByAptget("jq crudini", true);
		installPackageByAptget("python-gevent", true);
		installPackageByAptget("python-pip", true);
		installShadowsocksPythonServer();
		setIptable();
		installFinalSpeed();
		// addScheduledTask("0 3 * * * ","root","sh /fs/restart.sh",
		// "finalspeed",true);
		// addUser(443,"!abcd1234");
		// addUser(444,"chenyuqing");
		// addUser(445,"liwei");
		// removeScheduledTask("0 3 * * * ", "root","sh /fs/restart.sh",
		// "finalspeed",true) ;
		// removeScheduledTask("* */1 * * * ", "root","sh /fs/restart.sh",
		// "finalspeed",true) ;
		// addScheduledTask("0 3 * * * ", "root","sh /fs/restart.sh",
		// "finalspeed",true) ;
		// addScheduledTask("0 3 * * * ", "root","sh /fs/restart.sh",
		// "finalspeed",true) ;
//		removeScheduledTask("", "root", "sh  /fs/restart.sh", "finalspeed", true);
//		addScheduledTask("0 */1 * * * ", "root", "sh  /fs/restart.sh", "finalspeed", true);
		// String shadowsocksConfigFile = ss_shadowsocksPath + "/config.json";
		// deleteJsonKey(shadowsocksConfigFile, ".server_port", true);
		// deleteJsonKey(shadowsocksConfigFile, ".password", true);
		// addValueToJsonKey(shadowsocksConfigFile, ".port_password",
		// "\"port_password\"", "{\"443\":\"!abcd1234\"}", true);
		// addValueToJsonKey(shadowsocksConfigFile, ".port_password",
		// "\"port_password\"", "{\"444\":\"chenyuqing\"}", true);
	}

}