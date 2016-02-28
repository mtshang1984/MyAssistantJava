
import java.io.IOException;
import java.util.ArrayList;

import CommonEnum.SearchEngineEnum;
import LinuxAutomation.HttpServer;
import LinuxAutomation.PrivateCloud;
import LinuxAutomation.ScientificBrowsing;
import LinuxAutomation.VpsServer;
import MindManager.DecisionMaker;
import MindManager.MindMap;
import OfficeAutomation.ExcelAutomation;
import OfficeAutomation.PowerPointAutomation;
import OfficeAutomation.WordAutomation;
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import WindowsAutomation.WindowsAutomation;
import java.util.Properties;
public class AlmightyAssistant {

	MindMap mindMap;
	DecisionMaker decisionMaker;

	public AlmightyAssistant() {
		mindMap = new MindMap();
		decisionMaker = new DecisionMaker();
	}

	public void runToday() {
		makeDecision();
		// 创建脑图
		mindMap.addMindData("0", "201602", "");
		mindMap.addMindData("1", "工作", "0");
		mindMap.addMindData("1.1", "培训材料制作", "1");
		mindMap.addMindData("2", "创业", "0");
		mindMap.addMindData("2.1", "自动配置一台服务器", "2");
//		mindMap.addMindData("2.1.1", "建立网络云盘", "2.1");
//		mindMap.addMindData("2.1.1.1", "查看建立网络云盘的方法", "2.1.1");
//		mindMap.addMindData("2.1.1.2", "执行建立owncloud云盘的命令", "2.1.1");
		mindMap.addMindData("2.1.2", "配置翻墙", "2.1");
//		mindMap.addMindData("2.1.2.1", "配置搬瓦工翻墙服务器", "2.1.2");
//		mindMap.addMindData("2.1.2.2", "修复局域网代理服务器", "2.1.2");
		mindMap.addMindData("2.1.2.3", "建立新账号", "2.1.2");
		mindMap.addMindData("2.1.2.3.1", "配置json", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.2", "配置ini", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.3", "配置DOM4j", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.4", "配置snakeyaml", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.5", "shadowsocks账号管理", "2.1.2.3");
//		mindMap.addMindData("2.1.3", "配置Appahce2服务器", "2.1");
		mindMap.addMindData("2.1.3.1", "配置https加密", "2.1");
		mindMap.addMindData("2.1.4", "配置windows服务器", "2.1");
		mindMap.addMindData("2.1.4.1", "配置windows服务器", "2.1.4");
		mindMap.addMindData("3", "生活", "0");
		mindMap.addMindData("3.1", "生育准备", "3");
		mindMap.addMindData("3.1.1", "健身", "3.1");
		mindMap.addMindData("3.2", "父母健康", "3");
//		mindMap.addMindData("3.2.1", "给妈看病买药", "3.1");
		mindMap.addMindData("3.3", "装修", "3");
		mindMap.addMindData("3.3.1", "墙纸", "3.3");
		mindMap.addMindData("3.3.2", "窗帘", "3.3");
		mindMap.addMindData("3.4", "娱乐", "3");
		mindMap.addMindData("3.4.3", "刷机", "3.4");
		mindMap.addMindData("3.4.4", "下载新电影", "3.4");
		mindMap.addMindData("3.4.5", "爬山", "3.4");
//		mindMap.addMindData("3.4.6", "情人节活动", "3.4");
		mindMap.addMindData("3.5", "购物", "3");
//		mindMap.addMindData("3.5.3", "买酒", "3.5");
		mindMap.addMindData("3.6", "汽车", "3");
		mindMap.addMindData("3.6.1", "车子年审", "3.6");
		// mindMap.showMindmap();

		String taskNumber;
		ArrayList<String> parameter = new ArrayList<String>();
		taskNumber = "1.2";
		taskNumber = "3.5.3";
		taskNumber = "2.1.2";
		taskNumber = "2.1.1.2";
		taskNumber = "2.1.1.1";
		taskNumber = "3.4.4";
		taskNumber = "3.4.5";
		taskNumber = "2.1.3";
		taskNumber = "3.4.4";
		parameter.add("上海 爬山");

		taskNumber = "3.4.6";
		taskNumber = "1.1";
		taskNumber = "2.1.2.2";
		taskNumber = "2.1.2.1";
		taskNumber = "0.0";

		excuteJob(taskNumber, parameter);

	}

	/**
	 * @param taskNumber
	 * @param parameter
	 */
	public void excuteJob(String taskNumber, ArrayList<String> parameter) {
		switch (taskNumber) {
		// 制作Ameco培训材料
		case "0.0": {
//
//			PrivateCloud privateCloud = new PrivateCloud();
//			privateCloud.initialize();
//			privateCloud.test_code();
//			privateCloud.close();
//			String filename="/home/smt/test.out";
//			LinuxAutomation linuxAutomation=new LinuxAutomation("www.ijushan.com",22,false,"smt","ShMT0659");
//			linuxAutomation.initialize();
//			linuxAutomation.createEmptyFile(filename, false);
//			linuxAutomation.addTextInFileEnd("\"'\\",filename , true);
//			linuxAutomation.addTextInFileEnd("\"'\\",filename , false);
//			linuxAutomation.close();
//
//			VpsServer vpsServer = new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
//			vpsServer.initialize();
//			vpsServer.testCode();
////			vpsServer.close();
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing();
			scientificBrowsing.initialize();
			scientificBrowsing.testCode();
			scientificBrowsing.close();
			break;
		}
		case "1.1":{
			WordAutomation wordAutomation = new WordAutomation(true);
			PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
			ExcelAutomation excelAutomation = new ExcelAutomation(true);
			powerPointAutomation.openPresentation("D:\\00_yun_dir\\04_study\\02_training\\05_ameco\\Ameco培训总结.ppt");
			WindowsAutomation windowsAutomation = new WindowsAutomation();
			windowsAutomation.sleep(10000);;
			wordAutomation.close();
			excelAutomation.close();
			powerPointAutomation.close();

			break;
		}
		case "2.1.2": {
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing();
			scientificBrowsing.initialize();
			scientificBrowsing.installAllSoftware();
			scientificBrowsing.close();
			break;
		}
		case "2.1.1.1": {
			/** 查看家庭私有云建立方法 */
			Browser browser = new Browser();
			browser.openUrl("http://blog.csdn.net/ljmwork/article/details/8629831");
			browser.openUrl("http://www.bkjia.com/Linux/967931.html");
			browser.openUrl("https://www.howtoforge.com/how-to-install-owncloud-7-on-ubuntu-14.04");
			break;
		}
		case "2.1.1.2": {
			/** 执行家庭私有云建立命令 */
			PrivateCloud privateCloud = new PrivateCloud();
			privateCloud.initialize();
			privateCloud.installAllSoftware();
			privateCloud.close();
			break;
		}
		case "2.1.2.1": {
			/** 配置Shadowsocks服务器 */
//			LinuxAutomation linuxAutomation_start=new LinuxAutomation("proxy.ijushan.com",26123 ,true,"root","3oi7RqSFAloA","smt","ShMT0659");
//			linuxAutomation_start.initialize();
//			linuxAutomation_start.changePassword("root", "ShMT0659", false);
//			linuxAutomation_start.close();
			
//			LinuxAutomation linuxAutomation=new LinuxAutomation("proxy.ijushan.com",26123 ,true,"root","ShMT0659","smt","ShMT0659");
//			linuxAutomation.initialize();
//			linuxAutomation.updateAptRepository(false);
//			linuxAutomation.installPackageByAptget("sudo", false);
//			linuxAutomation.addUserAndDirecotry("smt","ShMT0659", false);
//			linuxAutomation.appendToGroup("smt", "sudo", false);
//			linuxAutomation.close();
			
			VpsServer vpsServer = new VpsServer("proxy.ijushan.com",22 ,true,"NenRZUynYkQw",27868,"root","ShMT0659","smt","ShMT0659");
			vpsServer.initialize();
			vpsServer.installAllSoftware();
			vpsServer.close();
			break;
		}
		case "2.1.2.3.5":{
			break;
		}
		case "2.1.2.2": {
			/** 配置Shadowsocks客户端 */
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing("www.ijushan.com",22,"root","ShMT0659","smt","ShMT0659");
			scientificBrowsing.initialize();
			scientificBrowsing.installAllSoftware(false);
			scientificBrowsing.close();
			break;
		}
		case "2.1.3": {
			/** 执行家庭私有云建立命令 */
			HttpServer httpServer = new HttpServer();
			httpServer.initialize();
			httpServer.installAllSoftware();
			httpServer.close();
			break;
		}
		case "3.1.1": {
			WordAutomation wordAutomation = new WordAutomation(true);
			PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
			ExcelAutomation excelAutomation = new ExcelAutomation(true);
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();
			break;
		}
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
				e.printStackTrace();
			}
			break;
		}
		case "3.4.4": {
			String movie="星球大战7 3d 1080p";
			SearchEngine searchEngine = new SearchEngine();
			// searchEngine.browseSearchResults(SearchEngineEnum.wenda, "迅雷 边下载
			// 边播放");
			
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:gaoqing.la 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:dygang.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:hdscg.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:cangyunge.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.download, "2016 1080p");
			searchEngine.browseSearchResults(SearchEngineEnum.google, "星球大战7 3d 1080p");
			searchEngine.browseSearchResults(SearchEngineEnum.download, "星球大战7 3d 1080p");
			
			break;
		}
		case "3.4.5": {
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.web, parameter.get(0));
			searchEngine.browseSearchResults(SearchEngineEnum.wenda, parameter.get(0));
			searchEngine.browseSearchResults(SearchEngineEnum.social, parameter.get(0));
			searchEngine.browseSearchResults(SearchEngineEnum.luntan, parameter.get(0));
			break;
		}
		case "3.4.6": {
			String search;
			search="奉贤 好玩";
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.web, search);
			searchEngine.browseSearchResults(SearchEngineEnum.wenda, search);
			searchEngine.browseSearchResults(SearchEngineEnum.social, search);
			searchEngine.browseSearchResults(SearchEngineEnum.luntan, search);
			break;
		}
		case "3.5.3": {
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.shopping, "泸州老窖百年窖龄60年");
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
	public void makeDecision(){
 
		decisionMaker.addDecisionData("0", "做什么", "");
		decisionMaker.addDecisionData("1", "拜年", "0");
		decisionMaker.addDecisionData("1.1", "爸爸，三姑，大姐，二姐", "1");
		decisionMaker.addDecisionData("2", "出去玩", "0");
		decisionMaker.addDecisionData("3", "session是放在SshConnection类中，还是不放在其中", "0");
		decisionMaker.addDecisionData("3.1", "如果session是放在SshConnection类中，如何处理存在多个session的情况", "3");
		decisionMaker.addDecisionData("3.2", "如果session不放在SshConnection类中，如何提供根据Connection创建session的方法", "3");
		decisionMaker.addDecisionData("3.2.1",
				"在SshConnection类中，提供openSession和closeSession的方法，返回值为Session类型的对象。由于只要有对象的引用存在，对象就不会被收回，返回对象是可行的方法",
				"3.1");
		decisionMaker.addDecisionData("0", "做什么", "");
		// decisionMaker.showDecision();

	}
}
