package LinuxAutomation;

public class MySqlServerDatabase extends LinuxAutomation{
	String hostname;
	String owncloudDatabase;
	String owncloudUserName;
	String owncloudUserPassword;
	public MySqlServerDatabase() {
		hostname="localhost";
		owncloudDatabase="SmtOwnCloudDataBase";
		owncloudUserName=super.username;
		owncloudUserPassword=super.password;
		
	}
	public MySqlServerDatabase(String hostname, String owncloudDatabase, String owncloudUserName,
			String owncloudUserPassword) {
		super();
		this.hostname = hostname;
		this.owncloudDatabase = owncloudDatabase;
		this.owncloudUserName = owncloudUserName;
		this.owncloudUserPassword = owncloudUserPassword;
	}

}
