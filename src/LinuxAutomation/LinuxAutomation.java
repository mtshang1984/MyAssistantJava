package LinuxAutomation;

import java.util.ArrayList;

import SshConnection.SshConnection;
import WindowsAutomation.WindowsAutomation;

public class LinuxAutomation {
	Host host;	
	String http_proxy;
	String https_proxy;
	SshConnection sshConnection;
	
	boolean useRoot;

	public LinuxAutomation() {
		host=new Host();
		// http_proxy = "192.168.1.101:1080";
		http_proxy = "http://192.168.1.107:1080";
		https_proxy = "https://192.168.1.107:1080";
		useRoot = false;
		sshConnection = new SshConnection(host, useRoot);
	}


	public LinuxAutomation(Host host, boolean useRoot) {
		this.host= (Host) host.clone();		
		this.useRoot = useRoot;
		http_proxy = "http://192.168.1.107:1080";
		https_proxy = "https://192.168.1.107:1080";
		sshConnection = new SshConnection(host, useRoot);
	}

	public LinuxAutomation(Host host, boolean useRoot, String http_proxy) {
		this.host= (Host) host.clone();		
		this.useRoot = useRoot;
		this.http_proxy = http_proxy;
		this.https_proxy = http_proxy.replace("http", "https");
		sshConnection = new SshConnection(host, useRoot);
	}
	
	public String getHttp_proxy() {
		return http_proxy;
	}

	public void setHttp_proxy(String http_proxy) {
		this.http_proxy = http_proxy;
	}



	/** 初始化 */
	public void initialize() {
		sshConnection.initialize();
	}

	/** 关闭 */
	public void close() {
		sshConnection.close();
	}

	/**
	 * 运行Ssh指令并显示输出
	 * 
	 * @return
	 */
	public ArrayList<String> runSshCommand(String stringCommand, boolean useSudo) {
		return sshConnection.runCommand(stringCommand, useSudo);
	}

	/** 运行Ssh指令并显示输出 */
	public ArrayList<String> runSshCommand(String stringCommand, boolean useProxy, boolean useSudo) {

		return runSshCommand(stringCommand, "", useProxy, useSudo);
		// if (useProxy && http_proxy.isEmpty() != true) {
		// return sshConnection.runCommand(
		// "export http_proxy=\"" + http_proxy + "\"" + "&&" + "export
		// https_proxy=\"" + https_proxy + "\"",
		// stringCommand, "", useSudo);
		// } else {
		// return sshConnection.runCommand(stringCommand, useSudo);
		// }

	}

	/** 运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runSshCommand(String stringCommand, String stringCommandPrompt, boolean useSudo) {
		return runSshCommand(stringCommand, stringCommandPrompt, false, useSudo);
	}

	/** 运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runSshCommand(String stringCommand, String stringCommandPrompt, boolean useProxy,
			boolean useSudo) {
		return runSshCommand(stringCommand, stringCommandPrompt, useProxy, true, true, useSudo);
		// if (useProxy && http_proxy.isEmpty() != true) {
		// if (useSudo) {
		// return sshConnection.runCommand("",
		// "http_proxy=\"" + http_proxy + "\" https_proxy=\"" + https_proxy +
		// "\" " + stringCommand,
		// stringCommandPrompt, useSudo);
		// } else {
		// return sshConnection.runCommand("export http_proxy=\"" + http_proxy +
		// "\"" + "&&"
		// + "export https_proxy=\"" + https_proxy + "\"", stringCommand,
		// stringCommandPrompt, useSudo);
		// }
		// } else {
		// return sshConnection.runCommand("", stringCommand,
		// stringCommandPrompt, useSudo);
		// }
	}

	/** 运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runSshCommand(String stringCommand, String stringCommandPrompt, boolean useProxy,
			boolean showOutput, boolean showError, boolean useSudo) {
		if (useProxy && http_proxy.isEmpty() != true) {
			if (useSudo) {
				return sshConnection.runCommand("",
						"http_proxy=\"" + http_proxy + "\" https_proxy=\"" + https_proxy + "\" " + stringCommand,
						stringCommandPrompt, showOutput, showError, useSudo);
			} else {
				return sshConnection
						.runCommand(
								"export http_proxy=\"" + http_proxy + "\"" + "&&" + "export https_proxy=\""
										+ https_proxy + "\"",
								stringCommand, stringCommandPrompt, showOutput, showError, useSudo);
			}
		} else {
			return sshConnection.runCommand("", stringCommand, stringCommandPrompt, showOutput, showError, useSudo);
		}

	}

	/** 运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runSshCommand(String preCommand, String stringCommand, String stringCommandPrompt,
			boolean useProxy, boolean showOutput, boolean showError, boolean useSudo) {
		if (useProxy && http_proxy.isEmpty() != true) {
			if (useSudo) {
				return sshConnection.runCommand(preCommand,
						"http_proxy=\"" + http_proxy + "\" https_proxy=\"" + https_proxy + "\" " + stringCommand,
						stringCommandPrompt, showOutput, showError, useSudo);
			} else {
				return sshConnection.runCommand(
						preCommand + "&&export http_proxy=\"" + http_proxy + "\"" + "&&" + "export https_proxy=\""
								+ https_proxy + "\"",
						stringCommand, stringCommandPrompt, showOutput, showError, useSudo);
			}
		} else {
			return sshConnection.runCommand("", stringCommand, stringCommandPrompt, showOutput, showError, useSudo);
		}

	}

	/** 更新apt安装源 */
	public void updateAptRepository(boolean useSudo) {
		runSshCommand("apt-get update ", useSudo);
	}
	/** 更新apt安装源 */
	public void updateAptRepository(boolean useProxy,boolean useSudo) {
		runSshCommand("apt-get update ",useProxy, useSudo);
	}

	/** 增加apt安装源 */
	public void addAptRepository(String aptRepository, boolean useSudo) {
		runSshCommand("add-apt-repository " + aptRepository, useSudo);
		updateAptRepository(useSudo);
	}

	/** 增加apt安装源 */
	public void addAptKey(String aptKey, boolean useSudo) {
		runSshCommand("apt-key add - < " + aptKey, useSudo);
	}

	/** 通过apt-get安装包 */
	public void installPackageByAptget(String packageName, boolean useSudo) {
		// runSshCommand("apt-get --assume-yes install " + packageName );
		runSshCommand("apt-get -y install " + packageName, useSudo);
	}

	/** 通过apt-get安装包 */
	public void installPackageByAptget(String packageName, boolean useProxy, boolean useSudo) {
		// runSshCommand("apt-get --assume-yes install " + packageName ,
		// useProxy);
		runSshCommand("apt-get -y install " + packageName, useProxy, useSudo);
	}

	/** 通过Dpkg安装包 */
	public void installPackageByDpkg(String packageName, boolean useSudo) {
		runSshCommand("dpkg -i " + packageName + " && yes | apt-get -f install", "y", useSudo);
	}

	/** 通过Dpkg安装包 */
	public void installPackageByDpkg(String packageName, boolean useProxy, boolean useSudo) {
		runSshCommand("dpkg -i " + packageName + " && yes | apt-get -f install", "y", useProxy, useSudo);
	}

	/** 通过pip安装包 */
	public void installPackageByPip(String packageName, boolean useSudo) {
		runSshCommand("pip install " + packageName, "y", useSudo);

	}

	/** 通过pip安装包 */
	public void installPackageByPip(String packageName, boolean useProxy, boolean useSudo) {
		runSshCommand(" pip install " + packageName, "y", useProxy, useSudo);
	}

	/** 通过apt-get卸载包 */
	public void uninstallPackageByAptget(String packageName, boolean useSudo) {
		runSshCommand("apt-get --assume-yes --purge  remove " + packageName, useSudo);
	}

	/** 通过Dpkg卸载安装包 */
	public void uninstallPackageByDpkg(String packageName, boolean useSudo) {
		runSshCommand(" dpkg -r " + packageName, "y", useSudo);
	}

	/** 通过pip卸载包 */
	public void uninstallPackageByPip(String packageName, boolean useSudo) {
		runSshCommand("pip uninstall " + packageName, "y", useSudo);
	}

	/** 设置自动启动 */
	public void setAutostart(String command, boolean useSudo) {
		insertRowBeforeInFile("#", "exit 0", command, "/etc/rc.local", useSudo);
	}

	/** 设置自动启动 */
	public void setAutostart(String command, String screenOut, boolean useSudo) {
		insertRowBeforeInFile("#", "exit 0", "nohup  " + command + " >> " + screenOut + " 2>&1 &", "/etc/rc.local",
				useSudo);
	}

	/** 取消自动启动，限通过/etc/rc.local实现的自启动 */
	public void cancelAutostart(String command, boolean useSudo) {
		deleteRowInFile("#", command, "/etc/rc.local", useSudo);
	}

	/** 自动启动立即生效，限通过/etc/rc.local实现的自启动 */
	public void takeEffectAutostart(boolean useSudo) {
		excuteShell("/etc/rc.local", useSudo);
	}

	/** 设置登录后启动 */
	public void setRunAfterLogin(String command, boolean useSudo) {
		addTextInFileEnd(command, "/etc/profile", useSudo);
	}

	/** 设置登录后启动 */
	public void setRunAfterLogin(String command, String screenOut, boolean useSudo) {
		addTextInFileEnd("nohup  " + command + " >> " + screenOut + " 2>&1 & ", "/etc/profile", useSudo);
	}

	/** 取消登录后启动 */
	public void cancelRunAfterLogin(String command, boolean useSudo) {
		deleteRowInFile("#", command, "/etc/profile", useSudo);
	}


	/** 取消登录后启动 */
	public void sourceProfile(String profile) {
		runSshCommand("source "+profile,false);
	}

	/** 允许root ssh登录 */
	public void enableRootSsh(boolean useSudo) {
		modifyConfigFile("/etc/ssh/sshd_config", "", "PermitRootLogin", "yes", useSudo);
	}

	/** 禁止root ssh登录 */
	public void disableRootSsh(boolean useSudo) {
		modifyConfigFile("/etc/ssh/sshd_config", " ", "PermitRootLogin", "no", useSudo);
	}

	/** 设置ssh端口 */
	public void modifySshPort(int port, boolean useSudo) {
		modifyConfigFile("/etc/ssh/sshd_config", "", "Port", String.valueOf(port), useSudo);
	}

	/** 修改用户主目录 */
	public void changeUserHomeDirecotry(String username, String path, boolean useSudo) {
		runSshCommand("usermod -d " + path + " ftp", useSudo);
	}

	/** 创建用户及目录 */
	public void addUserAndDirecotry(String username, boolean useSudo) {
		runSshCommand("useradd -d /home/" + username + " -m " + username, useSudo);
	}

	/** 创建用户及目录 */
	public void addUserAndDirecotry(String username, String password, boolean useSudo) {
		addUserAndDirecotry(username, useSudo);
		changePassword(username, password, useSudo);
		// runSshCommand("adduser
		// "+username,password+"\n"+password+"\n\n\n\n\n\n\n\n\n\n\n",useSudo);
		// changePassword(username,password,useSudo);
	}

	/** 删除用户及目录 */
	public void deleteUserAndDirecotry(String username, boolean useSudo) {
		runSshCommand("userdel -r " + username, useSudo);
	}

	/** 将用户加入某个组 */
	public void appendToGroup(String username, String groupname, boolean useSudo) {
		runSshCommand("usermod -a -G " + groupname + " " + username, useSudo);

	}

	/** 软链接目录 */
	public void linkPath(String pathToLink, String targerPath, boolean useSudo) {
		runSshCommand("ln -s " + pathToLink + " " + targerPath, useSudo);

	}

	/** 移除软链接目录 */
	public void unlinkPath(String targerPath, boolean useSudo) {
		runSshCommand("unlink " + targerPath, useSudo);

	}

	/** 修改密码 */
	public void changePassword(String username, String password, boolean useSudo) {
		runSshCommand("passwd " + username, password + "\n" + password + "\n", useSudo);
	}

	/** 在文件中替换匹配的多行字符串 */
	public void replaceMultiLineStringInFile(String stringToBeReplace, String stringForReplace, String filename,
			boolean useSudo) {
		stringToBeReplace = stringToBeReplace.replace("/", "\\/");
		stringForReplace = stringForReplace.replace("/", "\\/");
		runSshCommand("perl -i -0 -p -e 's/" + stringToBeReplace + "/" + stringForReplace + "/g' " + filename, useSudo);
	}

	/** 在转义特殊字符串 */
	public String escapeSpecialString(String string) {
		String resultString;
		resultString = string;
		resultString = resultString.replace("/", "\\/");
		resultString = resultString.replace("*", "\\*");
		resultString = resultString.replace("+", "\\+");
		resultString = resultString.replace("?", "\\?");
		resultString = resultString.replace("$", "\\$");
		return resultString;
	}

	/** 在文件中替换字符串 */
	public void replaceStringInFile(String stringToBeReplace, String stringForReplace, String filename,
			boolean useSudo) {
		stringToBeReplace = escapeSpecialString(stringToBeReplace);
		stringForReplace = escapeSpecialString(stringForReplace);
		runSshCommand("sed -i.bak 's/" + stringToBeReplace + "/" + stringForReplace + "/g' " + filename, useSudo);
	}

	/** 在文件中替换字符串，字符前不包含某字符 */
	public void replaceStringInFile(String charaterNotInclude, String stringToBeReplace, String stringForReplace,
			String filename, boolean useSudo) {
		// stringToBeReplace = stringToBeReplace.replace("/", "\\/");
		// stringForReplace = stringForReplace.replace("/", "\\/");
		stringToBeReplace = escapeSpecialString(stringToBeReplace);
		stringForReplace = escapeSpecialString(stringForReplace);
		runSshCommand("sed -i.bak  's/\\(^[^" + charaterNotInclude + "]*\\)" + stringToBeReplace + "/\\1"
				+ stringForReplace + "/g' " + filename, useSudo);
	}

	/** 在文件中删除一行 */
	public void deleteRowInFile(String stringMatch, String filename, boolean useSudo) {
		// stringMatch = stringMatch.replace("/", "\\/");
		stringMatch = escapeSpecialString(stringMatch);
		runSshCommand("sed -i.bak  '/" + stringMatch + "/d' " + filename, useSudo);
	}

	/** 在文件中删除一行,字符前不包含某字符 */
	public void deleteRowInFile(String charaterNotInclude, String stringMatch, String filename, boolean useSudo) {
		// stringMatch = stringMatch.replace("/", "\\/");
		stringMatch = escapeSpecialString(stringMatch);
		runSshCommand("sed -i.bak  '/^[^" + charaterNotInclude + "]*" + stringMatch + "/d' " + filename, useSudo);
	}

	/** 在文件中匹配的字符串所在行前插入一行 */
	public void insertRowBeforeInFile(String stringMatch, String stringForInsert, String filename, boolean useSudo) {
		// stringMatch = stringMatch.replace("/", "\\/");
		// stringForInsert = stringForInsert.replace("/", "\\/");
		stringMatch = escapeSpecialString(stringMatch);
		stringForInsert = escapeSpecialString(stringForInsert);
		runSshCommand("sed -i.bak  '/" + stringMatch + "/i" + stringForInsert + "' " + filename, useSudo);
	}

	/** 在文件中匹配的字符串所在行前插入一行,字符前不包含某字符 */
	public void insertRowBeforeInFile(String charaterNotInclude, String stringMatch, String stringForInsert,
			String filename, boolean useSudo) {
		stringMatch = escapeSpecialString(stringMatch);
		stringForInsert = escapeSpecialString(stringForInsert);
		runSshCommand("sed -i.bak  '/^[^" + charaterNotInclude + "]*" + stringMatch + "/i" + stringForInsert + "' "
				+ filename, useSudo);
	}

	/** 在文件中匹配的字符串所在行后插入一行 */
	public void insertRowAfterInFile(String stringMatch, String stringForInsert, String filename, boolean useSudo) {
		stringMatch = escapeSpecialString(stringMatch);
		stringForInsert = escapeSpecialString(stringForInsert);
		runSshCommand("sed -i.bak '/" + stringMatch + "/a" + stringForInsert + "' " + filename, useSudo);
	}

	/** 在文件中匹配的字符串所在行后插入一行,字符前不包含某字符 */
	public void insertRowAfterInFile(String charaterNotInclude, String stringMatch, String stringForInsert,
			String filename, boolean useSudo) {
		stringMatch = escapeSpecialString(stringMatch);
		stringForInsert = escapeSpecialString(stringForInsert);
		runSshCommand("sed -i.bak  '/^[^" + charaterNotInclude + "]*" + stringMatch + "/a" + stringForInsert + "' "
				+ filename, useSudo);
	}

	/** 在文件最后插入内容 */
	public void addTextInFileEnd(String stringForAdd, String filename, boolean useSudo) {
		stringForAdd = stringForAdd.replace("\\", "\\0134");
		stringForAdd = stringForAdd.replace("\"", "\\0042");
		stringForAdd = stringForAdd.replace("$", "\\0044");
		if (useSudo) {
			filename = filename.replace("\"", "");
			runSshCommand("bash -c \"echo -e \\\"" + stringForAdd + "\\\" >> " + filename + "\"", useSudo);
		} else {
			runSshCommand("echo -e \"" + stringForAdd + "\" >> " + filename, useSudo);
		}
	}

	/** 运行shell */
	public void excuteShell(String shell, boolean useSudo) {
		runSshCommand("sh " + shell, useSudo);
	}

	/** 运行shell */
	public void excuteShell(String shell, String username, boolean useSudo) {
		runSshCommand("sudo su " + username + " -c " + shell, useSudo);
	}

	/** 删除文件 */
	public void deleteFile(String filename, boolean useSudo) {
		runSshCommand("rm -rf " + filename, useSudo);
	}

	/** 创建文件夹 */
	public void makeDirectory(String path, boolean useSudo) {
		runSshCommand("mkdir -p " + path, useSudo);
	}

	/** 创建文件夹 */
	public void makeDirectory(String path, String username, String groupname, boolean useSudo) {
		runSshCommand("mkdir -p " + path, useSudo);
		runSshCommand("chown " + username + ":" + groupname + " " + path, useSudo);
	}

	/** 杀掉某一进程 */
	public void killProgram(String program, boolean useSudo) {
		runSshCommand("pkill  " + program, useSudo);
	}

	/** 杀掉某一进程，并等待xx秒 */
	public void killProgram(String program, long milliseconds, boolean useSudo) {
		runSshCommand("pkill  " + program, useSudo);
		WindowsAutomation windowsAutomation = new WindowsAutomation();
		windowsAutomation.sleep(milliseconds);
	}

	/** 启动服务 */
	public void startService(String serviceName, boolean useSudo) {
		runSshCommand("service " + serviceName + " start", useSudo);
	}

	/** 停止服务 */
	public void stopService(String serviceName, boolean useSudo) {
		runSshCommand("service " + serviceName + " stop", useSudo);
	}

	/** 重启服务 */
	public void restartService(String serviceName, boolean useSudo) {
		runSshCommand("service " + serviceName + " restart", useSudo);
	}

	/** 下载文件 */
	public void downloadFile(String url, boolean useSudo) {
		runSshCommand("wget -N " + url, useSudo);
	}

	/** 使用代理下载文件 */
	public void downloadFile(String url, boolean useProxy, boolean useSudo) {
		runSshCommand("wget -N " + url, useProxy, useSudo);
	}

	/** 下载文件并重命名 */
	public void downloadFile(String url, String filename, boolean useSudo) {
		runSshCommand("wget -N " + url + " -O " + filename, useSudo);
	}

	/** 使用代理下载文件并重命名 */
	public void downloadFile(String url, String filename, boolean useProxy, boolean useSudo) {
		runSshCommand("wget -N " + url + " -O " + filename, useProxy, useSudo);
	}

	/** 使用aria2c下载文件 */
	public void downloadFileByAria2(String url, boolean useSudo) {
		runSshCommand("aria2c " + url, useSudo);
	}
	
	/** 使用aria2下载文件并重命名 */
	public void downloadFileByAria2(String url, String filename, boolean useSudo) {
		runSshCommand("aria2c "+ " -o " + filename +" "+ url, useSudo);
	}

	/** 使用aria2下载文件并重命名 */
	public void downloadFileByAria2(String url, String path,String filename, boolean useSudo) {
		runSshCommand("aria2c "+ " -d " + path+ " -o " + filename +" "+ url, useSudo);
	}
	/** 移动文件 */
	public void moveFile(String fileToMove, String targetFile, boolean useSudo) {
		runSshCommand("mv -f " + fileToMove + " " + targetFile, useSudo);
	}

	/** 复制文件 */
	public void copyFile(String fileToCopy, String targetFile, boolean useSudo) {
		runSshCommand("cp -rf " + fileToCopy + " " + targetFile, useSudo);
	}

	/** 更改文件所有人 */
	public void changeFileOwner(String filename, String username, String groupname, boolean useSudo) {
		runSshCommand("chown " + username + ":" + groupname + " " + filename, useSudo);
	}

	/** 更改文件所有人 */
	public void gitClone(String url, boolean useSudo) {
		runSshCommand("git clone " + url, useSudo);
	}

	/** 创建空文件 */
	public void createEmptyFile(String filename, boolean useSudo) {
		deleteFile(filename, useSudo);
		runSshCommand("touch " + filename, useSudo);
	}

	/** 创建空文件 */
	public void createEmptyFile(String filename, String username, String groupname, boolean useSudo) {
		deleteFile(filename, useSudo);
		moveFile(createTempFile(useSudo), filename, useSudo);
		changeFileOwner(filename, username, groupname, useSudo);
	}

	/** 创建临时文件 */
	public String createTempFile(boolean useSudo) {
		ArrayList<String> stringArrayReturn = runSshCommand("mktemp", useSudo);
		return (stringArrayReturn.get(0).replace("\n", ""));
	}

	/** 设置文件权限 */
	public void setFileFullPermission(String filename, boolean forAllFile, boolean useSudo) {
		String permission = "777";
		changeFilePermission(filename, permission, forAllFile, useSudo);

	}

	/** 设置文件的权限为所有人拥有全部权限 */
	public void changeFilePermission(String filename, String permission, boolean forAllFile, boolean useSudo) {
		if (forAllFile) {
			runSshCommand("chmod " + permission + " -R " + filename, useSudo);
		} else {
			runSshCommand("chmod " + permission + " " + filename, useSudo);
		}

	}

	/** 修改配置文件 */
	public void modifyConfigFile(String filename, String key, String value, boolean useSudo) {
		modifyConfigFile(filename, "=", key, value, useSudo);
		// String valueWithReplace=value.replace("/", "\\/");
		// String tempShell=createTempFile(useSudo);
		// addTextInFileEnd("if grep -q '^#*\\s*"+key+"\\s*=.*' "+filename+";
		// then", tempShell, useSudo);
		// addTextInFileEnd(" sed -i.bak
		// 's/^#*\\s*\\("+key+"\\s*=\\).*/\\1"+valueWithReplace+"/'
		// "+filename+";", tempShell, useSudo);
		// addTextInFileEnd("else", tempShell, useSudo);
		// addTextInFileEnd(" echo '"+key+"="+value+"' >> "+filename+" ;",
		// tempShell, useSudo);
		// addTextInFileEnd("fi", tempShell, useSudo);
		// runSshCommand("if grep -q '^#*\\s*"+key+"\\s*=.*' "+filename+"; then"
		// + " sed -i.bak
		// 's/^#*\\s*\\("+key+"\\s*=\\).*/\\1"+valueWithReplace+"/'
		// "+filename+";"
		// + " else"
		// + " echo '"+key+"="+value+"' >> "+filename+" ;"
		// + " fi",useSudo);
	}

	/** 修改配置文件 */
	public void modifyConfigFile(String filename, String split, String key, String value, boolean useSudo) {
		String valueWithReplace = value.replace("/", "\\/");
		String tempShell = createTempFile(useSudo);
		addTextInFileEnd("if grep -q '^#*\\s*" + key + "\\s*" + split + ".*' " + filename + "; then", tempShell,
				useSudo);
		addTextInFileEnd(" sed -i.bak 's/^#*\\s*\\(" + key + "\\s*" + split + "\\).*/\\1" + valueWithReplace + "/' "
				+ filename + ";", tempShell, useSudo);
		addTextInFileEnd("else", tempShell, useSudo);
		addTextInFileEnd(" echo '" + key + split + value + "' >> " + filename + " ;", tempShell, useSudo);
		addTextInFileEnd("fi", tempShell, useSudo);
		excuteShell(tempShell, useSudo);
		deleteFile(tempShell, useSudo);
		//
		// runSshCommand("if grep -q '^#*\\s*"+key+"\\s*"+split+".*'
		// "+filename+"; then"
		// + " sed -i.bak
		// 's/^#*\\s*\\("+key+"\\s*"+split+"\\).*/\\1"+valueWitReplace+"/'
		// "+filename+";"
		// + " else"
		// + " echo '"+key+split+value+"' >> "+filename+" ;"
		// + " fi",useSudo);
	}

	/** 注释配置文件 */
	public void toggleConfigFile(String filename, String key, boolean useSudo) {

		String tempShell = createTempFile(useSudo);
		addTextInFileEnd("if grep -q '^#*\\s*" + key + "\\s*=.*' " + filename + "; then", tempShell, useSudo);
		addTextInFileEnd(" sed -i.bak 's/\\(^\\s*" + key + "\\s*=.*\\)/#\\1/' " + filename + ";", tempShell, useSudo);
		addTextInFileEnd("fi", tempShell, useSudo);
		excuteShell(tempShell, useSudo);
		deleteFile(tempShell, useSudo);
		//
		// runSshCommand("if grep -q '^#*\\s*"+key+"\\s*=.*' "+filename+"; then"
		// + " sed -i.bak 's/\\(^\\s*"+key+"\\s*=.*\\)/#\\1/' "+filename+";"
		// + " fi",useSudo);
	}

	/** 清除防火墙规则 */
	public void cleanIptables(boolean useSudo) {
		setPolicyInIptablesRuleChain("INPUT", "ACCEPT", true);
		setPolicyInIptablesRuleChain("OUTPUT", "ACCEPT", true);
		setPolicyInIptablesRuleChain("FORWARD", "ACCEPT", true);
		runSshCommand("iptables -F  ", useSudo);
		runSshCommand("iptables -X  ", useSudo);
	}
	
	/**
	 * 设置iptables规则链的默认策略
	 * 
	 * @param ruleChain
	 *            INPUT,OUTPUT,FORWARD
	 * @param policy
	 *            ACCEPT,DROP,REJECT,LOG
	 */
	public void setPolicyInIptablesRuleChain(String ruleChain, String policy, boolean useSudo) {
		runSshCommand("iptables -P " + ruleChain + " " + policy, useSudo);
	}

	/**
	 * 在iptables规则链添加一条规则
	 * 
	 * @param ruleChain
	 *            INPUT,OUTPUT,FORWARD
	 * @param protocol
	 *            all, tcp, udp, udplite, icmp, icmpv6, esp, ah, sctp, mh
	 * @param ctstate
	 *            all NEW,RELATED,ESTABLISHED,INVALID
	 * @param policy
	 *            ACCEPT,DROP,REJECT,LOG
	 */
	public void addRuleInIptablesRuleChain(String ruleChain, String protocol, boolean isMultiport, boolean isdport,
			int port, String ctstate, String policy, boolean useSudo) {
		if(port!=22){
			for (int i = 0; i <= 1; i++) {
				deleteRuleInIptablesRuleChain(ruleChain, protocol, isMultiport, isdport, port, ctstate, policy, useSudo);
			}				
		}
		String command = "iptables -A " + ruleChain;
		if (protocol.isEmpty() == false) {
			command += " -p " + protocol;
		}
		if (port!=0) {
			if (isMultiport) {
				command += " -m multiport ";
			}
			if (isdport) {
				command += " --dport ";
			} else {
				command += " --sport ";
			}
			command += port;
		}
		if (ctstate.isEmpty() == false) {
			command += " -m conntrack --ctstate " + ctstate;
		}
		if (policy.isEmpty() == false) {
			command += " -j " + policy;
		} else {
			return;
		}
		runSshCommand(command, useSudo);

	}

	public void deleteRuleInIptablesRuleChain(String ruleChain, String protocol, boolean isMultiport, boolean isdport,
			int port, String ctstate, String policy, boolean useSudo) {
		if (port==22)
			return;
		String command = "iptables -D " + ruleChain;
		if (protocol.isEmpty() == false) {
			command += " -p " + protocol;
		}
		if (port!=0) {
			if (isMultiport) {
				command += " -m multiport ";
			}
			if (isdport) {
				command += " --dport ";
			} else {
				command += " --sport ";
			}
			command += port;
		}
		if (ctstate.isEmpty() == false) {
			command += " -m conntrack --ctstate " + ctstate;
		}
		if (policy.isEmpty() == false) {
			command += " -j " + policy;
		} else {
			return;
		}
		runSshCommand(command, useSudo);

	}

	/**iptables 实现nat*/
	public void addNatInIptables(String protocol, boolean isMultiport, boolean isdport,
			int port, String policy, boolean useSudo)	{
		for (int i = 0; i <= 1; i++) {
			deleteNatInIptables( protocol, isMultiport, isdport, port, policy, useSudo);
		}
		String command = "iptables -t nat -A PREROUTING";
		if (protocol.isEmpty() == false) {
			command += " -p " + protocol;
		}
		if (port!=0) {
			if (isMultiport) {
				command += " -m multiport ";
			}
			if (isdport) {
				command += " --dport ";
			} else {
				command += " --sport ";
			}
			command += port;
		}
		if (policy.isEmpty() == false) {
			command += " -j " + policy;
		} else {
			return;
		}
		runSshCommand(command, useSudo);
		
	}

	/**iptables 实现nat*/
	public void deleteNatInIptables(String protocol, boolean isMultiport, boolean isdport,
			int port, String policy, boolean useSudo)	{
	
		String command = "iptables -t nat -D PREROUTING";
		if (protocol.isEmpty() == false) {
			command += " -p " + protocol;
		}
		if (port!=0) {
			if (isMultiport) {
				command += " -m multiport ";
			}
			if (isdport) {
				command += " --dport ";
			} else {
				command += " --sport ";
			}
			command += port;
		}
		if (policy.isEmpty() == false) {
			command += " -j " + policy;
		} else {
			return;
		}
		runSshCommand(command, useSudo);
	}
	/** 记允许被ping */
	public void allowLoopbackByIptables(boolean useSudo) {
		runSshCommand("iptables -A INPUT  -i lo -j ACCEPT", useSudo);
//		 runSshCommand("iptables -A INPUT -p icmp --icmp-type echo-reply -j
//		 ACCEPT" , useSudo);
	}

	/** 允许被ping */
	public void allowPingByIptables(boolean useSudo) {
		runSshCommand("iptables -A OUTPUT -p icmp --icmp-type echo-request -j ACCEPT", useSudo);
		runSshCommand("iptables -A INPUT -p icmp --icmp-type echo-reply -j  ACCEPT" , useSudo);
	}

	/** 记录被拒绝的访问 */
	public void logIptables(boolean useSudo) {
		runSshCommand(
				"iptables -A INPUT -m limit --limit 5/min -j LOG --log-prefix \"iptables denied: \" --log-level 7",
				useSudo);
		// runSshCommand("iptables -A OUTPUT -m limit --limit 5/min -j LOG
		// --log-prefix \"iptables denied: \" --log-level 7" , useSudo);
	}

	/** 通过iptables防止DDos攻击 */
	public void preventDdosByIptables(boolean useSudo) {
		runSshCommand("iptables -A INPUT  -p tcp --dport 80 -m limit --limit 25/minute --limit-burst 100 -j ACCEPT",
				useSudo);

	}

	/** 保存iptables */
	public void saveIptables(String filename, boolean useSudo) {
		if (useSudo) {
			filename = filename.replace("\"", "");
			runSshCommand("bash -c \"iptables-save > " + filename + "\"", useSudo);
		} else {
			runSshCommand("iptables-save > " + filename, useSudo);
		}
	}

	/** 恢复iptables */
	public void restoreIptables(String filename, boolean useSudo) {
		if (useSudo) {
			filename = filename.replace("\"", "");
			runSshCommand("bash -c \"iptables-restore < " + filename + "\"", useSudo);
		} else {
			runSshCommand("iptables-restore < " + filename, useSudo);
		}
	}

	/** 使iptables启动时生效 */
	public void setIptablesAutoStart(String filename, boolean useSudo) {
		String iptablesScript;
		iptablesScript = "/etc/network/if-pre-up.d/iptables";
		createEmptyFile(iptablesScript, useSudo);
		changeFilePermission(iptablesScript, "+x", false, useSudo);
		addTextInFileEnd("#!/bin/sh", iptablesScript, useSudo);
		addTextInFileEnd("iptables-restore < " + filename, iptablesScript, useSudo);
		addTextInFileEnd("exit 0" + filename, iptablesScript, useSudo);
	}

	/** 添加计划任务 */
	public void addScheduledTask(String Schedule, String username, String command, String taskname, boolean useSudo) {

		addTextInFileEnd(Schedule + " " + username + " " + command, "/etc/cron.d/" + taskname, useSudo);
		changeFilePermission("/etc/cron.d/" + taskname, "+x", false, useSudo);
	}

	/** 移除计划任务 */
	public void removeScheduledTask(String Schedule, String username, String command, String taskname,
			boolean useSudo) {
		deleteRowInFile(Schedule + " " + username + " " + command, "/etc/cron.d/" + taskname, useSudo);
	}

	/** 解压缩zip文件 */
	public void unzip(String zipFilename, boolean useSudo) {
		runSshCommand("unzip -o " + zipFilename, useSudo);
	}

	/** 解压缩zip文件 */
	public void unzip(String zipFilename, String targetPath, boolean useSudo) {
		runSshCommand("unzip -o " + zipFilename + " -d " + targetPath, useSudo);
	}

	/** 压缩zip文件 */
	public void zip(String zipFilename, String filepath, boolean useSudo) {
		runSshCommand("zip " + zipFilename + " " + filepath, useSudo);
	}

	/** 解压缩tgz文件 */
	public void tar(String tgzFilename, boolean useSudo) {
		runSshCommand("tar -zxvf " + tgzFilename, useSudo);
	}

	/** 删除JSON文件键值 */
	public void deleteJsonKey(String filename, String key, boolean useSudo) {
		// runSshCommand("bash -c \"echo -e \\\"" + "\\\" >> " + filename +
		// "\"", useSudo);
		String tempFilename = filename + ".tmp";
		runSshCommand("bash -c \"jq " + "'del(" + key + ")'" + " " + filename + " > " + tempFilename + "\"", useSudo);
		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 修改JSON文件键值 */
	public void modifyJsonKey(String filename, String key, String value, boolean useSudo) {
		value = value.replace("\\", "\\\\");
		value = value.replace("\"", "\\\"");
		value = value.replace("$", "\\$");
		value = value.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		runSshCommand("set +H",
				"bash -c \"jq " + "'" + key + "=" + value + "' " + filename + " > " + tempFilename + "\"", "", false,
				true, true, useSudo);

		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 增加JSON文件键和值 */
	// public void addJsonKey(String filename, String key, String name, String
	// value, boolean useSudo) {
	// runSshCommand("jq " + "'select(" + key + "==null)|.+={" + name + "=" +
	// value + "}'" + " " + filename, useSudo);
	// }

	/** 增加JSON文件键和值 */
	public void addValueToJsonKey(String filename, String key, String name, String value, boolean useSudo) {
		name = name.replace("\\", "\\\\");
		name = name.replace("\"", "\\\"");
		name = name.replace("$", "\\$");
		name = name.replace("`", "\\`");
		value = value.replace("\\", "\\\\");
		value = value.replace("\"", "\\\"");
		value = value.replace("$", "\\$");
		value = value.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		// runSshCommand(preCommand, stringCommand, stringCommandPrompt,
		// useProxy, showOutput, showError, useSudo)
		runSshCommand("set +H",
				"bash -c \"jq " + "' if " + key + "==null then .+={" + name + ":" + value + "} else " + key + "+="
						+ value + " end" + "' " + filename + " > " + tempFilename + "\"",
				"", false, true, true, useSudo);
		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 删除JSON文件键的值 */
	public void delteValueInJsonKey(String filename, String key, String name, boolean useSudo) {
		name = name.replace("\\", "\\\\");
		name = name.replace("\"", "\\\"");
		name = name.replace("$", "\\$");
		name = name.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		// runSshCommand(preCommand, stringCommand, stringCommandPrompt,
		// useProxy, showOutput, showError, useSudo)
		runSshCommand("set +H",
				"bash -c \"jq " + "' del(" + key + "[\\\"" + name + "\\\"])' " + filename + " > " + tempFilename + "\"",
				"", false, true, true, useSudo);
		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 增加JSON文件键数组中值 */
	public void addObjectToJsonKeyArray(String filename, String key, String object, boolean useSudo) {
		object = object.replace("\\", "\\\\");
		object = object.replace("\"", "\\\"");
		object = object.replace("$", "\\$");
		object = object.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		// runSshCommand(preCommand, stringCommand, stringCommandPrompt,
		// useProxy, showOutput, showError, useSudo)
		runSshCommand("set +H",
				"bash -c \"jq " + "' " + key + "+=[" + object + "] ' " + filename + " > " + tempFilename + "\"", "",
				false, true, true, useSudo);

		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 删除JSON文件键值 */
	public void deleteObjectInJsonKeyArray(String filename, String key, String condition, boolean useSudo) {
		condition = condition.replace("\\", "\\\\");
		condition = condition.replace("\"", "\\\"");
		condition = condition.replace("$", "\\$");
		condition = condition.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		runSshCommand("bash -c \"jq " + "'del(" + key + "[]|select (" + condition + "))'" + " " + filename + " > "
				+ tempFilename + "\"", useSudo);

		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 修改JSON文件键值 */
	public void modifyObjectInJsonKeyArray(String filename, String key, String condition, String object,
			boolean useSudo) {
		condition = condition.replace("\\", "\\\\");
		condition = condition.replace("\"", "\\\"");
		condition = condition.replace("$", "\\$");
		condition = condition.replace("`", "\\`");
		object = object.replace("\\", "\\\\");
		object = object.replace("\"", "\\\"");
		object = object.replace("$", "\\$");
		object = object.replace("`", "\\`");
		String tempFilename = filename + ".tmp";
		runSshCommand("set +H",
				"bash -c \"jq " + "'" + key + "[]|condition=" + object + "' " + filename + " > " + tempFilename + "\"",
				"", false, true, true, useSudo);

		moveFile(filename, filename + ".bak", useSudo);
		moveFile(tempFilename, filename, useSudo);
	}

	/** 增加移动硬盘启动挂载 */
	public void addMountInFstab(String fileSystem, String mountPoint, String type, String option, String dump,
			String pass, boolean useSudo) {
		addTextInFileEnd(fileSystem + "\t" + mountPoint + "\t" + type + "\t" + option + "\t" + dump + "\t" + pass,
				"/etc/fstab", useSudo);
	}

	/**
	 * 运行mysql脚本
	 * 
	 * @param mysqlFilename
	 * @param mysqlRootUsername
	 * @param mysqlRootPassword
	 * @param useSudo
	 */
	public void runMySqlBatch(String rootUsername,String rootPassword,String mysqlFilename, boolean useSudo) {
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " < " + mysqlFilename, useSudo);
	}

	/**
	 * 运行mysql命令
	 * 
	 * @param command
	 * @param mysqlRootUsername
	 * @param mysqlRootPassword
	 * @param useSudo
	 */
	public void runMySqlCommand(String rootUsername,String rootPassword,String command, boolean useSudo) {
		runSshCommand("mysql -u" + rootUsername + " -p" + rootPassword + " -e\"" + command + "\"", useSudo);
	}

}
