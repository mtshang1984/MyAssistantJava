package SshConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 实现通过ssh在运程linux系统上执行指令
 * 
 * @author smt
 *
 */
public class SshConnection {
	boolean useRoot;
	String rootUsername;
	String rootPassword;
	String hostname;
	String username;
	String password;
	int port;
	Connection connection;
	Session sessionForCommandWithPrompt;
	OutputStream outputStreamForCommandWithPrompt;

	public SshConnection() {
		useRoot = false;
		rootUsername = "root";
		rootPassword = "ShMT0659";
		hostname = "192.168.1.105";
		username = "root";
		password = "ShMT0659";
		port = 22;
		connection = new Connection(hostname, port);
		sessionForCommandWithPrompt = null;
		outputStreamForCommandWithPrompt = null;
	}

	public SshConnection(String hostname, int port, boolean useRoot, String username, String password) {
		this.useRoot = useRoot;
		this.hostname = hostname;
		if (useRoot) {
			this.rootUsername = username;
			this.rootPassword = password;
			this.username = "smt";
			this.password = "ShMT0659";
		} else {
			this.rootUsername = "root";
			this.rootPassword = "ShMT0659";
			this.username = username;
			this.password = password;
		}
		this.port = port;
		connection = new Connection(hostname, port);
		sessionForCommandWithPrompt = null;
		outputStreamForCommandWithPrompt = null;

	}

	public SshConnection(String hostname, int port, boolean useRoot, String rootUsername, String rootPassword,
			String username, String password) {
		this.useRoot = useRoot;
		this.hostname = hostname;
		this.rootUsername = rootUsername;
		this.rootPassword = rootPassword;
		this.username = username;
		this.password = password;
		this.port = port;
		connection = new Connection(hostname, port);
		sessionForCommandWithPrompt = null;
		outputStreamForCommandWithPrompt = null;

	}

	/** 打开连接 */
	public void initialize() {
		try {
			connection.connect();

			boolean isAuthenticated;
			if (useRoot) {
				isAuthenticated = connection.authenticateWithPassword(rootUsername, rootPassword);
			} else {
				isAuthenticated = connection.authenticateWithPassword(username, password);
			}
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 关闭连接 */
	public void close() {
		if (connection != null)
			connection.close();
	}

	/** 打开会话 */
	public Session openSession() {
		try {
			Session session = connection.openSession();
			return session;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 关闭会话 */
	public void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}

	/** 在会话中运行命令 */
	public void runCommandInSession(Session session, String preCommand, String stringCommand, boolean useSudo) {
		runCommandInSession(session, preCommand, stringCommand, "", useSudo);
	}

	/** 在会话中运行带提示的命令 */
	public void runCommandInSession(Session session, String preCommand, String stringCommand,
			String stringCommandPrompt, boolean useSudo) {
		try {
			// InputStream session_error = session.getStderr();
			OutputStream outputStreamOfSessionInput = session.getStdin();
			if (useSudo) {
				if (preCommand.isEmpty()) {
					session.execCommand("sudo -S " + stringCommand);
				} else {
					session.execCommand(preCommand + " && sudo -S " + stringCommand);
				}
				// session.execCommand("sudo "+stringCommand);
				if (stringCommandPrompt.isEmpty()) {
					outputStreamOfSessionInput.write((rootPassword + "\n").getBytes());
				} else {
					outputStreamOfSessionInput.write((rootPassword + "\n" + stringCommandPrompt + "\n").getBytes());
				}
			} else {
				session.execCommand(stringCommand);
				outputStreamOfSessionInput.write((stringCommandPrompt + "\n").getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 输出ssh显示结果 */
	public String showSessionOutput(Session session) {
		StringBuffer stringBufferSessionOutput = new StringBuffer();
		if (session == null) {
			return stringBufferSessionOutput.toString();
		}
		try {
			BufferedReader bufferReaderOfSessionOutput = new BufferedReader(
					new InputStreamReader(new StreamGobbler(session.getStdout())));
			String stringSessionOutput;
			while ((stringSessionOutput = bufferReaderOfSessionOutput.readLine()) != null) {
				System.out.println(stringSessionOutput);
				stringBufferSessionOutput.append(stringSessionOutput + "\n");
			}
			bufferReaderOfSessionOutput.close();
			return stringBufferSessionOutput.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBufferSessionOutput.toString();
	}

	/** 输出ssh显示结果，同时选择输出编码 */
	public String showSessionOutput(Session session, String characterSet) {
		StringBuffer stringBufferSessionOutput = new StringBuffer();
		if (session == null) {
			return stringBufferSessionOutput.toString();
		}
		try {
			BufferedReader bufferReaderOfSessionOutput = new BufferedReader(
					new InputStreamReader(new StreamGobbler(session.getStdout()), characterSet));
			String stringSessionOutput;
			while ((stringSessionOutput = bufferReaderOfSessionOutput.readLine()) != null) {
				System.out.println(stringSessionOutput);
				stringBufferSessionOutput.append(stringSessionOutput + "\n");
			}
			bufferReaderOfSessionOutput.close();
			return stringBufferSessionOutput.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBufferSessionOutput.toString();
	}

	/** 输出ssh提示结果 */
	public String showSessionError(Session session) {
		StringBuffer stringBufferSessionError = new StringBuffer();
		if (session == null) {
			return stringBufferSessionError.toString();
		}
		try {
			BufferedReader bufferReaderOfSessionError = new BufferedReader(
					new InputStreamReader(new StreamGobbler(session.getStderr())));
			String stringSessionError;
			while ((stringSessionError = bufferReaderOfSessionError.readLine()) != null) {
				System.out.println(stringSessionError);
				stringBufferSessionError.append(stringSessionError + "\n");
			}
			bufferReaderOfSessionError.close();
			return stringBufferSessionError.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBufferSessionError.toString();
	}

	/** 输出ssh提示结果，同时选择输出编码 */
	public String showSessionError(Session session, String characterSet) {
		StringBuffer stringBufferSessionError = new StringBuffer();
		if (session == null) {
			return stringBufferSessionError.toString();
		}
		try {
			BufferedReader bufferReaderOfSessionError = new BufferedReader(
					new InputStreamReader(new StreamGobbler(session.getStderr()), characterSet));
			String stringSessionError;
			while ((stringSessionError = bufferReaderOfSessionError.readLine()) != null) {
				System.out.println(stringSessionError);
				stringBufferSessionError.append(stringSessionError + "\n");
			}
			bufferReaderOfSessionError.close();
			return stringBufferSessionError.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBufferSessionError.toString();
	}

	/**
	 * 运行Ssh指令并显示输出
	 * 
	 * @return
	 */
	public ArrayList<String> runCommand(String stringCommand, boolean useSudo) {
		return runCommand("", stringCommand, "",useSudo);
	}

	/** 运行Ssh指令并显示输出 */
	// public ArrayList<String> runCommand(String stringCommand, boolean
	// showOutput, boolean showError, boolean useSudo) {
	// Session session = openSession();
	// runCommandInSession(session,"",stringCommand,useSudo);
	// ArrayList<String> stringBufferArray =new ArrayList<String> ();
	// if (showOutput) {
	// stringBufferArray.add(showSessionOutput(session));
	// }
	// if (showError) {
	// stringBufferArray.add(showSessionError(session));
	// }
	// closeSession(session);
	// return stringBufferArray;
	// }

	/** 运行Ssh带提示的指令并显示输出 */
	// public ArrayList<String> runCommand(String stringCommand, String
	// stringCommandPrompt, boolean useSudo) {
	// return runCommand(stringCommand,stringCommandPrompt,true, true, useSudo);
	// }
	/** 运行Ssh带提示的指令并显示输出 */
	// public ArrayList<String> runCommand(String stringCommand, String
	// stringCommandPrompt,boolean showOutput, boolean showError,boolean
	// useSudo) {
	// return runCommand( "", stringCommand, stringCommandPrompt, showOutput,
	// showError, useSudo);
	// Session session = openSession();
	// runCommandInSession(session,"",stringCommand,
	// stringCommandPrompt,useSudo);
	// ArrayList<String> stringBufferArray =new ArrayList<String> ();
	// if (showOutput) {
	// stringBufferArray.add(showSessionOutput(session));
	// }
	// if (showError) {
	// stringBufferArray.add(showSessionError(session));
	// }
	// closeSession(session);
	// return stringBufferArray;
	// }

//	/** 运行前置命令后运行Ssh指令并显示输出*/
//	public ArrayList<String> runCommand(String preCommand, String stringCommand, boolean useSudo) {
//		return runCommand(preCommand, stringCommand, "", useSudo);
//	}

	/** 运行前置命令后运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runCommand(String preCommand, String stringCommand, String stringCommandPrompt,
			boolean useSudo) {
		return runCommand(preCommand, stringCommand, stringCommandPrompt, true, true, useSudo);
	}

	/** 运行前置命令后运行Ssh带提示的指令并显示输出 */
	public ArrayList<String> runCommand(String preCommand, String stringCommand, String stringCommandPrompt,
			boolean showOutput, boolean showError, boolean useSudo) {
		Session session = openSession();
		runCommandInSession(session, preCommand, stringCommand, stringCommandPrompt, useSudo);
		ArrayList<String> stringBufferArray = new ArrayList<String>();
		if (showOutput) {
			stringBufferArray.add(showSessionOutput(session));
		}
		if (showError) {
			stringBufferArray.add(showSessionError(session));
		}
		closeSession(session);
		return stringBufferArray;
	}

	/** 测试代码 */
	public void test_code() {

	}

}
