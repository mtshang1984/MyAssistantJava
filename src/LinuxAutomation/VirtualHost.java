package LinuxAutomation;

public class VirtualHost {


	String apache2ConfigPath;
	String configFile;
	
	String serverName;
	String documentRoot;	
	
	public VirtualHost() {
		apache2ConfigPath="/etc/apache2";
		configFile=apache2ConfigPath+"/sites-available/"+serverName+".conf";		
		serverName="www.ijushan.com";
		documentRoot="/home/smt/web/"+serverName;
	}
	
	/**
	 * @param configPath
	 * @param serverName
	 */
	public VirtualHost(String configPath, String serverName,String fatherPath) {
		super();
		this.apache2ConfigPath = configPath;
		this.serverName = serverName;		
		configFile=apache2ConfigPath+"/sites-available/"+serverName+".conf";	 
		this.documentRoot=fatherPath+"/"+serverName;
	}
	public VirtualHost(String configPath, String serverName,String path,boolean useDocumentRoot) {
		super();
		this.apache2ConfigPath = configPath;
		this.serverName = serverName;		
		configFile=apache2ConfigPath+"/sites-available/"+serverName+".conf";	 
		if(useDocumentRoot){
			this.documentRoot=path;
		}
		else{
			this.documentRoot=path+"/"+serverName;}
	}

	public void setApache2ConfigPath(String apache2ConfigPath) {
		this.apache2ConfigPath = apache2ConfigPath;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}


}
