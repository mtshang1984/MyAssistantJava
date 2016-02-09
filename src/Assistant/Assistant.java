package Assistant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import CommonEnum.SearchEngineEnum;
import LinuxAutomation.PrivateCloud;
import LinuxAutomation.ScientificBrowsing;
import MindMap.MindMap;
import OfficeAutomation.ExcelAutomation;
import OfficeAutomation.PowerPointAutomation;
import OfficeAutomation.WordAutomation;
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import SshConnection.SshConnection;
import SshConnection.TestSshConnection;
import WindowsAutomation.WindowsAutomation;
import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class Assistant {

	public void runToday() {

		MindMap mindMap = new MindMap();
		//mindMap.showMindmap();
		// ExcelAutomation excel_automation=new ExcelAutomation(true);
		// TestSshConnection test_ssh_connection = new TestSshConnection();
		// test_ssh_connection.testSshConnection();

		String taskNumber;
		taskNumber = "1.2";
		taskNumber = "3.5.3";
		taskNumber = "3.4.4";
		taskNumber = "2.1.1";
		taskNumber = "2.1.1.2";
		taskNumber = "0.0";
		taskNumber = "2.1.1.1";

		switch (taskNumber) {
		// 制作Ameco培训材料
		case "0.0":{
			SshConnection sshConnection = new SshConnection("192.168.1.105", "root", "ShMT0659");
			sshConnection.openConnection();

			String server = "104.160.34.127";
			String server_port = "443";
			String local_address = "127.0.0.1";
			String local_port = "1080";
			String password = "UNuJUtXhZu";
			String timeout = "600";
			String method = "aes-256-cfb";
			String fastOpen = "false";
			String worker = "1"; 
			String shadowsocksPath="/etc/shadowsocks";
			String shadowsocksConfigFile=shadowsocksPath+"/config.json";

			sshConnection.runCommandWithShow("mkdir -p "+shadowsocksPath + "&& rm "+shadowsocksConfigFile+ "\n");
			sshConnection.runCommandWithShow("echo '{' >> "+shadowsocksConfigFile + "&& echo '\"server\":\"" + server
					+ "\",' >>  "+shadowsocksConfigFile + "&& echo '\"server_port\":\"" + server_port
					+ "\",' >>"+shadowsocksConfigFile + "&& echo '\"local_address\":\"" + local_address
					+ "\",' >> "+shadowsocksConfigFile + "&& echo '\"local_port\":\"" + local_port
					+ "\",' >>  "+shadowsocksConfigFile + "&& echo '\"password\":\"" + password
					+ "\",' >>"+shadowsocksConfigFile + "&& echo '\"timeout\":\"" + timeout
					+ "\",' >> "+shadowsocksConfigFile + "&& echo '\"method\":\"" + method
					+ "\",' >> "+shadowsocksConfigFile + "&& echo '\"fastOpen\":\"" + fastOpen
					+ "\",' >> "+shadowsocksConfigFile + "&& echo '\"worker\":\"" + worker
					+ "\" ' >> "+shadowsocksConfigFile + "&& echo '}' >> "+shadowsocksConfigFile + "\n");

			sshConnection.runCommandWithShow(
					"sed -i '/sslocal/d' /etc/rc.local \n");

			sshConnection.runCommandWithShow(
					"sed -i '/^[^#]*exit 0/inohup /usr/local/bin/sslocal -c /etc/shadowsocks/config.json > /etc/shadowsocks/log 2>&1 &' /etc/rc.local \n");
			//sshConnection.runCommandWithShow(
			//		"sed  '13 anohup /usr/local/bin/sslocal -c /etc/shadowsocks/config.json &' -i /etc/rc.local \n");
			sshConnection.runCommandWithShow("sh /etc/rc.local \n");

			sshConnection.closeConnection();
		}
		case "1.1":
			break;
		case "2.1.1.1": {
			ScientificBrowsing scientificBrowsing=new ScientificBrowsing();
			//scientificBrowsing.uninstallAllSoftware();
			scientificBrowsing.installAllSoftware();

		}
			break;
		case "2.1.1.2": {
			/** 查看家庭私有云建立方法 */
			/*
			Browser browser = new Browser();
			browser.openUrl("http://blog.csdn.net/ljmwork/article/details/8629831");
			browser.openUrl("http://www.bkjia.com/Linux/967931.html");
			browser.openUrl("https://www.howtoforge.com/how-to-install-owncloud-7-on-ubuntu-14.04");
			*/
			PrivateCloud privateCloud =new PrivateCloud();
			privateCloud.installOwnCloud();
		}
			break;
		case "2.1.1.3": {
			/** 执行家庭私有云建立命令 */
			SshConnection sshConnection = new SshConnection("192.168.1.105", "root", "ShMT0659");
			sshConnection.openConnection();
			sshConnection.runCommandWithShow("date");
			sshConnection.runCommandWithShow("apt-get update");
			sshConnection.closeConnection();
		}
			break;
		case "3.1.1": {
			WordAutomation wordAutomation = new WordAutomation(true);
			PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
			ExcelAutomation excelAutomation = new ExcelAutomation(true);
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();
		}
			break;
		case "3.4.3": {
			WindowsAutomation windowsAutomation = new WindowsAutomation();
			windowsAutomation.runCommandWithShow("data");
			try {
				Process process = windowsAutomation.runCommand("ftp");
				windowsAutomation.putCommandToProcess(process, "open 192.168.1.101 2121" + "\n");
				windowsAutomation.putCommandToProcess(process, "smt" + "\n");
				windowsAutomation.putCommandToProcess(process, "smt" + "\n");

				windowsAutomation.putCommandToProcess(process, "dir" + "\n");
				windowsAutomation.putCommandToProcess(process, "quit" + "\n").close();
				windowsAutomation.showProcessOutput(process, "UTF8");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "3.4.4": {

			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.wenda, "迅雷 边下载 边播放");
			/*
			 * search_engine.BrowseSearchResults(SearchEngineEnum.google,
			 * "site:gaoqing.la 2016 1080p");
			 * search_engine.BrowseSearchResults(SearchEngineEnum.google,
			 * "site:dygang.com 2016 1080p");
			 * //search_engine.BrowseSearchResults(SearchEngineEnum.google,
			 * "site:hdscg.com 2016 1080p");
			 * search_engine.BrowseSearchResults(SearchEngineEnum.google,
			 * "site:cangyunge.com 2016 1080p");
			 * search_engine.BrowseSearchResults(SearchEngineEnum.download,
			 * "2016 1080p");
			 */
		}
			break;
		case "3.5.3": {
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.shopping, "泸州老窖百年窖龄60年");
		}
		default:
			break;
		}
	}

}
