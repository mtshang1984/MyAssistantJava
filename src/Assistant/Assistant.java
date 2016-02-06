package Assistant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import CommonEnum.SearchEngineEnum;
import MindMap.MindMap;
import OfficeAutomation.ExcelAutomation;
import OfficeAutomation.PowerPointAutomation;
import OfficeAutomation.WordAutomation;
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import SshConnection.TestSshConnection;
import WindowsAutomation.WindowsAutomation;

public class Assistant {

	public void runToday() {

		SearchEngine searchEngine=new SearchEngine();
		MindMap mindMap = new MindMap();
		WindowsAutomation windowsAutomation = new WindowsAutomation();
		WordAutomation wordAutomation = new WordAutomation(true);
		PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
		ExcelAutomation excelAutomation = new ExcelAutomation(true);
		// ExcelAutomation excel_automation=new ExcelAutomation(true);
		// TestSshConnection test_ssh_connection = new TestSshConnection();
		// test_ssh_connection.testSshConnection();
		mindMap.showMindmap();

		String taskNumber;
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		taskNumber = "1.2";
		taskNumber = "3.5.3";
		taskNumber = "3.4.4";

		/*
		 * try { task_number=buffered_reader.readLine(); } catch (IOException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		switch (taskNumber) {
		// 制作Ameco培训材料
		case "1.1":
			break;
		case "2.1.1":
			break;
		case "3.1.1":
			break;
		case "3.4.3":
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();

			windowsAutomation.runCommandWithShow("data");
			try {
				Process process=windowsAutomation.runCommand("ftp");
				windowsAutomation.putTextToProcess(process,"open 192.168.1.101 2121"+"\n");
				windowsAutomation.putTextToProcess(process,"smt"+"\n");
				windowsAutomation.putTextToProcess(process,"smt"+"\n");

				windowsAutomation.putTextToProcess(process,"dir"+"\n");
				windowsAutomation.putTextToProcess(process,"quit"+"\n").close();
				windowsAutomation.getProcessOutputText(process,"UTF8");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "3.4.4":
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();

			/*
			search_engine.BrowseSearchResults(SearchEngineEnum.google,"site:gaoqing.la 2016 1080p");
			search_engine.BrowseSearchResults(SearchEngineEnum.google,"site:dygang.com 2016 1080p");
			//search_engine.BrowseSearchResults(SearchEngineEnum.google,"site:hdscg.com 2016 1080p");
			search_engine.BrowseSearchResults(SearchEngineEnum.google,"site:cangyunge.com 2016 1080p");
			search_engine.BrowseSearchResults(SearchEngineEnum.download,"2016 1080p");*/
			
			searchEngine.browseSearchResults(SearchEngineEnum.wenda,"迅雷 边下载 边播放");
			break;
		case "3.5.3":
			searchEngine.browseSearchResults(SearchEngineEnum.shopping,"泸州老窖百年窖龄60年");
		default:
			break;
		}
	}

}
