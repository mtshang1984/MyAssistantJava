package WindowsAutomation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WindowsAutomation {

	public WindowsAutomation() {
		// TODO Auto-generated constructor stub
	}

	public void BrowseFile(String filePath) {
		try {
			Process process = Runtime.getRuntime().exec("explorer.exe " + filePath);
			showProcessOutputText(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Process runExe(String stringCommand) {
		try {
			Process process = Runtime.getRuntime().exec(stringCommand);
			return process;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Process runExeWithShow(String stringCommand) {
		Process process =runExe(stringCommand);
		showProcessOutputText(process);
		return process;
	}

	public Process runExe(String stringCommand, String[] envp, File dir) {
		try {
			Process process = Runtime.getRuntime().exec(stringCommand, envp, dir);
			return process;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Process runExeWithShow(String stringCommand, String[] envp, File dir) {
		Process process =runExe(stringCommand,envp,dir);
		showProcessOutputText(process);
		return process;
	}

	public Process runCommand(String stringCommand) {
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /C " + stringCommand);
			return process;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Process runCommandWithShow(String stringCommand) {
		Process process =runCommand(stringCommand);
		showProcessOutputText(process);
		return process;
	}
	public Process runCommand(String stringCommand, String[] envp, File dir) {
		try {
			Process process = Runtime.getRuntime().exec("cmd.exe /C " + stringCommand, envp, dir);
			return process;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Process runCommandWithShow(String stringCommand, String[] envp, File dir) {
		Process process =runCommand(stringCommand,envp,dir);
		showProcessOutputText(process);
		return process;
	}
	public void showProcessOutputText(Process process) {
		if(process==null)return;
		try {
			BufferedReader stringBufferProcessOutputText = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String stringLine;
			while ((stringLine = stringBufferProcessOutputText.readLine()) != null) {
				System.out.println(stringLine);
			}
			stringBufferProcessOutputText.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showProcessOutput(Process process,String characterSet) {
		if(process==null)return;
		try {
			BufferedReader stringBufferProcessOutput = new BufferedReader(
					new InputStreamReader(process.getInputStream(),characterSet));
			String stringLine;
			while ((stringLine = stringBufferProcessOutput.readLine()) != null) {
				System.out.println(stringLine);
			}
			stringBufferProcessOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BufferedWriter putCommandToProcess(Process process, String stringCommand) {
		if(process==null)return null;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(
	                new OutputStreamWriter(process.getOutputStream()));
			
			bufferedWriter.write(stringCommand);
			bufferedWriter.flush();
			return bufferedWriter;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void sleep(long milliseconds){

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
