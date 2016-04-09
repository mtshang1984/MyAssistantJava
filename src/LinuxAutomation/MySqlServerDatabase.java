package LinuxAutomation;

public class MySqlServerDatabase extends LinuxAutomation {
	String hostname;
	String database;
	String userName;
	String password;

	public MySqlServerDatabase() {
		hostname = "localhost";
		database = "SmtOwnCloudDataBase";
		userName = super.username;
		password = super.password;

	}
	public MySqlServerDatabase(String rootUsername,String rootPassword) {
		super();
		this.rootUsername = rootUsername;
		this.rootPassword = rootPassword;
	}
	public MySqlServerDatabase(String hostname, String database, String userName, String password) {
		super();
		this.hostname = hostname;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	public MySqlServerDatabase(String rootUsername,String rootPassword,String hostname, String database, String userName, String password) {
		super();
		this.rootUsername = rootUsername;
		this.rootPassword = rootPassword;
		this.hostname = hostname;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}
	/** 安装MySql-Server */
	public void installMySqlServer() {
		installPackageByAptget("mysql-server", true);
		runSshCommand("mysql_secure_installation", true);
		runSshCommand("mysql_secure_installation",
				rootPassword + "\n" + "n" + "\n" + "y" + "\n" + "y" + "\n" + "y" + "\n" + "y" + "\n", true);

	}

	/** 卸载MySql-Server */
	public void uninstallMySqlServer() {
		uninstallPackageByAptget("mysql-server", true);

	}

	/** 创建数据库 */
	public void addMySqlServerDatabase() {
		// 创建用户
		runMySqlCommand( rootUsername, rootPassword,"CREATE USER '" + userName + "'@'" + hostname + "' IDENTIFIED BY '" + password + "'", true);
		// 为用户创建一个数据库
		runMySqlCommand(rootUsername, rootPassword,"CREATE DATABASE " + database + "", true);
		// 授权用户拥有数据库的所有权限
		runMySqlCommand(rootUsername, rootPassword,"GRANT ALL ON " + database + ".* TO '" + userName + "'@'" + hostname + "'", true);
		// 刷新系统权限表
		runMySqlCommand(rootUsername, rootPassword,"FLUSH PRIVILEGES", true);
	}

	/** 创建数据库 */
	public void addMySqlServerDatabase(MySqlServerDatabase mySqlServerDatabase) {
		// 创建用户
		runMySqlCommand(rootUsername, rootPassword,"CREATE USER '" + mySqlServerDatabase.userName + "'@'" + mySqlServerDatabase.hostname
				+ "' IDENTIFIED BY '" + mySqlServerDatabase.password + "'", true);
		// 为用户创建一个数据库
		runMySqlCommand(rootUsername, rootPassword,"CREATE DATABASE " + mySqlServerDatabase.database + "", true);
		// 授权用户拥有数据库的所有权限
		runMySqlCommand(rootUsername, rootPassword,"GRANT ALL ON " + mySqlServerDatabase.database + ".* TO '" + mySqlServerDatabase.userName
				+ "'@'" + mySqlServerDatabase.hostname + "'", true);
		// 刷新系统权限表
		runMySqlCommand(rootUsername, rootPassword,"FLUSH PRIVILEGES", true);

	}

}
