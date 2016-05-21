package LinuxAutomation;

import SshConnection.SshConnection;

public class Host  implements Cloneable{


	public String hostname;
	public String rootUsername;
	public String rootPassword;
	public String username;
	public String password;
	public int sshPort;

	public Host() {
		// TODO Auto-generated constructor stub
		hostname = "192.168.1.105";
		rootUsername = "root";
		rootPassword = "ShMT0659";
		username = "smt";
		password = "ShMT0659";
		sshPort = 22;
	}
	/**
	 * @param hostname
	 * @param rootUsername
	 * @param rootPassword
	 * @param username
	 * @param password
	 * @param sshPort
	 */
	public Host(String hostname, String rootUsername, String rootPassword, String username, String password,
			int sshPort) {
		super();
		this.hostname = hostname;
		this.rootUsername = rootUsername;
		this.rootPassword = rootPassword;
		this.username = username;
		this.password = password;
		this.sshPort = sshPort;
	}
	@Override  
    public Object clone(){  
    	Host o = null;  
        try{  
            o = (Host)super.clone();  
        }catch(CloneNotSupportedException e){  
            e.printStackTrace();  
        }  
        return o;  
    }  
	
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getRootUsername() {
		return rootUsername;
	}
	public void setRootUsername(String rootUsername) {
		this.rootUsername = rootUsername;
	}
	public String getRootPassword() {
		return rootPassword;
	}
	public void setRootPassword(String rootPassword) {
		this.rootPassword = rootPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSshPort() {
		return sshPort;
	}
	public void setSshPort(int sshPort) {
		this.sshPort = sshPort;
	}

}
