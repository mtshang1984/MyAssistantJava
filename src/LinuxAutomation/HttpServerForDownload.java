package LinuxAutomation;

import SshConnection.SshConnection;

public class HttpServerForDownload extends HttpServer{

	public HttpServerForDownload() {
		// TODO Auto-generated constructor stub
		super();
		host =new Host("proxy.ijushan.com","root","ShMT0659","smt","ShMT0659",22); 
		useRoot = false;
		sshConnection = new SshConnection(host , useRoot);
	
		pathHome="/home/"+ host.username;
		pathWeb=pathHome+"/web";
		
		publicPathToShare=pathDrive+"/share";
		
		apache2ConfigPath = "/etc/apache2";
		apache2ConfigFile =apache2ConfigPath+"/apache2.conf";
		virtualHostShare = new VirtualHost(apache2ConfigPath, "share.proxy.ijushan.com", publicPathToShare,true);
		virtualHostDefault = new VirtualHost(apache2ConfigPath, "", virtualHostMain.documentRoot,true);
		virtualHostDefault.configFile=apache2ConfigPath+"/sites-available/000-default.conf";

	}

	/** 安装Apache2 */
	public void installApache2(){
		installPackageByAptget("apache2",true);
		disableVirtualHost(virtualHostShare);
		disableVirtualHost(virtualHostDefault);
		
		addVirtualHost(virtualHostShare, true);
		addVirtualHost(virtualHostDefault);
		
		addWebDirecotry(publicPathToShare);
		
		modifyConfigFile(apache2ConfigFile," ", "ServerName", "localhost", true);
		
		enableVirtualHost(virtualHostShare);
		enableVirtualHost(virtualHostDefault);
		restartService("apache2",true);
	}
}
