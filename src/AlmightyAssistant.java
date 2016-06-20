
import java.io.IOException;
import java.util.ArrayList;

import CommonEnum.SearchEngineEnum;
import LinuxAutomation.CameraMonitor;
import LinuxAutomation.Host;
import LinuxAutomation.HttpServer;
import LinuxAutomation.LinuxAutomation;
import LinuxAutomation.PrivateCloud;
import LinuxAutomation.ScientificBrowsing;
import LinuxAutomation.VpsServer;
import MindManager.DecisionMaker;
import MindManager.MindMap;
import OfficeAutomation.ExcelAutomation;
import OfficeAutomation.PowerPointAutomation;
import OfficeAutomation.WordAutomation;
import ReportGenerator.ParameterizeCFDPaper;
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import WindowsAutomation.WindowsAutomation;
import java.util.Properties;

import BussinessApplication.BussinessApplication;

public class AlmightyAssistant {

	public AlmightyAssistant() {
	}

	public void runToday() {

		String taskNumber;
		ArrayList<String> parameter = new ArrayList<String>();
		taskNumber = "1.1.1";
		taskNumber = "4.1.1";
		taskNumber = "6.2.1";
		excuteJob(taskNumber, parameter);
		
		
	}

	/**
	 * @param taskNumber
	 * @param parameter
	 */
	public void excuteJob(String taskNumber, ArrayList<String> parameter) {
		switch (taskNumber) {
		case "1.1.1": {
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.google, "二手 粮食 机械");
			Browser browser = new Browser();
			browser.openUrl("http://da.fengj.com/ggao-list-2-0-0-0-0-0-0-1.html");
			browser.openUrl("http://atomurl.net/torrent/");
			break;
		}
		case "2.1.1": {
			Browser browser = new Browser();
			browser.openUrl("http://subhd.com/");
			browser.openUrl("http://www.feijiu.net/");
			browser.openUrl("http://www.hxlyjx.com/zhuanrang/index.asp");
			break;
		}
		case "3.1": {
			// 购买冰箱和洗衣机
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.taobao, "京东e卡");
			searchEngine.browseSearchResults(SearchEngineEnum.jingdong, "电器");
			// Browser browser=new Browser();
			// browser.openUrl(stringUrl);
			break;
		}
		case "4.1.1": {
			/** 配置Shadowsocks服务器 */

			// Host vpsHost=new Host("proxy1.ijushan.com"
			// ,"root","ShMT0659","smt","ShMT0659",22);
			Host vpsHost = new Host("173.0.52.70", "root", "ShMT0659", "smt", "ShMT0659", 22);

			Host vpsSpeedHost = new Host("www.ijushan.com", "root", "ShMT0659", "phablet", "ShMT0659", 22);
			VpsServer vpsServer = new VpsServer(vpsHost);
			// VpsServer vpsServer = new VpsServer(vpsHost,true,"ShMT0659",22);
			vpsServer.initialize();
			// vpsServer.installAllSoftware();

			// vpsServer.installShadowsocksPythonServer();
			vpsServer.uninstallFinalSpeed();
			vpsServer.installFinalSpeed();
			vpsServer.close();

			BussinessApplication bussinessApplication = new BussinessApplication();
			bussinessApplication.addShadowsocksAccount(vpsHost, 443, "!abcd1234");
			bussinessApplication.addShadowsocksAccount(vpsHost, 444, "chenyuqing");
			bussinessApplication.addShadowsocksAccount(vpsHost, 446, "!abcd1234");
			bussinessApplication.addShadowsocksAccount(vpsHost, 448, "hongqing");

			// bussinessApplication.changeShadowsocksServer(vpsSpeedHost,vpsHost);
			// bussinessApplication.addSpeedShadowsocksAccount(vpsSpeedHost,"ssh",22,220);
			// bussinessApplication.addSpeedShadowsocksAccount(vpsSpeedHost,"my_ss",443,4430);
			// bussinessApplication.addSpeedShadowsocksAccount(vpsSpeedHost,"chenyuqing",444,4440);
			// bussinessApplication.addSpeedShadowsocksAccount(vpsSpeedHost,"my_ss_using",446,4460);

			// LinuxAutomation linuxAutomation=new LinuxAutomation(vpsHost
			// ,false);
			// linuxAutomation.initialize();
			// linuxAutomation.addScheduledTask("0 */1 * * * ", "smt", "sh " +
			// "/usr/share/finalspeed"+"/finalspeed_jar" +
			// "/restart.sh", "finalspeed", true);
			// linuxAutomation.close();
			break;
		}
		case "6.2.1": {
			ParameterizeCFDPaper parameterizeCFDPaper=new ParameterizeCFDPaper("D:\\00_yun_dir\\04_study\\03_paper\\01_my_paper\\06_参数化\\自动参数化数值模拟在燃烧室设计的应用20160617.docx");
			parameterizeCFDPaper.generatePaper();
			//			WordAutomation wordAutomation = new WordAutomation(true);
//			wordAutomation.openDocument("D:\\00_yun_dir\\04_study\\03_paper\\01_my_paper\\06_参数化\\自动参数化数值模拟在燃烧室设计的应用.doc");
//			wordAutomation.close();
			//D:\00_yun_dir\04_study\03_paper\01_my_paper\06_参数化
			break;
		}
		default: {
			WordAutomation wordAutomation = new WordAutomation(true);
			PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
			ExcelAutomation excelAutomation = new ExcelAutomation(true);
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();
			break;
		}
		}
	}
}
