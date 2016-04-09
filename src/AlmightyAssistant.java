
import java.io.IOException;
import java.util.ArrayList;

import CommonEnum.SearchEngineEnum;
import LinuxAutomation.CameraMonitor;
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
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import WindowsAutomation.WindowsAutomation;
import java.util.Properties;

import BussinessApplication.BussinessApplication;
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
		mindMap.addMindData("1.2", "论文", "1");
		mindMap.addMindData("1.3", "安装ubuntu系统", "1");
		mindMap.addMindData("2", "创业", "0");
		mindMap.addMindData("2.1", "自动配置一台服务器", "2");
//		mindMap.addMindData("2.1.1", "建立网络云盘", "2.1");
//		mindMap.addMindData("2.1.1.1", "查看建立网络云盘的方法", "2.1.1");
//		mindMap.addMindData("2.1.1.2", "执行建立owncloud云盘的命令", "2.1.1");
		mindMap.addMindData("2.1.2", "配置翻墙", "2.1");
//		mindMap.addMindData("2.1.2.1", "配置搬瓦工翻墙服务器", "2.1.2");
		mindMap.addMindData("2.1.2.2", "修复局域网代理服务器", "2.1.2");
		mindMap.addMindData("2.1.2.3", "建立新账号", "2.1.2");
		mindMap.addMindData("2.1.2.3.1", "配置json", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.2", "配置ini", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.3", "配置DOM4j", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.4", "配置snakeyaml", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.5", "shadowsocks账号管理", "2.1.2.3");
		mindMap.addMindData("2.1.2.4", "建立监控服务器", "2.1.2");
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
		mindMap.addMindData("3.3.3", "费用清单及质保联系人", "3.3");
		mindMap.addMindData("3.4", "娱乐", "3");
		mindMap.addMindData("3.4.3", "刷机", "3.4");
		mindMap.addMindData("3.4.4", "下载新电影", "3.4");
//		mindMap.addMindData("3.4.5", "爬山", "3.4");
//		mindMap.addMindData("3.4.6", "情人节活动", "3.4");
		mindMap.addMindData("3.5", "购物", "3");
//		mindMap.addMindData("3.5.3", "买酒", "3.5");
		mindMap.addMindData("3.6", "汽车", "3");
		mindMap.addMindData("3.6.1", "车子年审", "3.6");
		mindMap.addMindData("3.6.2", "车子解除抵押", "3.6");
		mindMap.addMindData("3.7", "房子", "3");
		mindMap.addMindData("3.7.1", "房产证", "3.6");
		// mindMap.showMindmap();

		String taskNumber;
		ArrayList<String> parameter = new ArrayList<String>();
		taskNumber = "1.2";
		taskNumber = "3.5.3";
		taskNumber = "2.1.2";
		taskNumber = "2.1.1.2";
		taskNumber = "2.1.1.1";
		taskNumber = "3.4.5";
		taskNumber = "2.1.3";
		taskNumber = "3.4.4";
		parameter.add("上海 爬山");

		taskNumber = "3.4.6";
		taskNumber = "1.1";
		taskNumber = "2.1.2.1";
		taskNumber = "3.7.1";
		taskNumber = "3.4.4";
		taskNumber = "2.1.4.1";
		taskNumber = "2.1.2.1";
		taskNumber = "2.1.2.2";
		taskNumber = "0.0";
		taskNumber = "3.4.3";

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
//			HttpServer httpServer = new HttpServer();
//			httpServer.initialize();
//			httpServer.test_code();
//			httpServer.close();
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

//			VpsServer vpsServer = new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
//			vpsServer.initialize();
//			vpsServer.testCode();
//			vpsServer.close();
//			BussinessApplication bussinessApplication = new BussinessApplication();
////			bussinessApplication.addShadowsocksAccount(444,"chenyuqing");
//			bussinessApplication.testCode();
//			ScientificBrowsing scientificBrowsing = new ScientificBrowsing();
//			scientificBrowsing.initialize();
//			scientificBrowsing.testCode();
//			scientificBrowsing.close();
			CameraMonitor cameraMonitor = new CameraMonitor();
			cameraMonitor.initialize();
			cameraMonitor.testCode();
			cameraMonitor.close();
//			SearchEngine searchEngine=new SearchEngine();
//			searchEngine.browseSearchResults(SearchEngineEnum.download, "共享文件夹管理");
			
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
			scientificBrowsing.uninstallAllSoftware();
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
			
			VpsServer vpsServer = new VpsServer("proxy.ijushan.com",22 ,true,"ShMT0659",22,"root","ShMT0659","smt","ShMT0659");
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
			scientificBrowsing.uninstallAllSoftware(true);
			scientificBrowsing.installAllSoftware(true);
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
		case "2.1.4.1": {
			String download;
			download="vmware workstation pro 12.1 keygen";
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.download, download);
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
			LinuxAutomation linuxAutomation_start=new LinuxAutomation("www.ijushan.com",22 ,false,"smt","ShMT0659");
			linuxAutomation_start.initialize();
//			linuxAutomation_start.deletFile();
			linuxAutomation_start.downloadFileByAria2("https://download.chainfire.eu/363/CF-Root1/CF-Auto-Root-hammerhead-hammerhead-nexus5.zip?retrieve_file=1","/mnt/usb/share/03_software/05_rom/nexus5","CF-Auto-Root-hammerhead-hammerhead-nexus5.zip",false);
//			linuxAutomation_start.downloadFileByAria2("https://download.chainfire.eu/347/CF-Root1/CF-Auto-Root-flo-razor-nexus7.zip?retrieve_file=1","/mnt/usb/share/03_software/05_rom/nexus7","CF-Auto-Root-flo-razor-nexus7.zip",false);
			
			linuxAutomation_start.close();
			WindowsAutomation windowsAutomation = new WindowsAutomation();
//			windowsAutomation.BrowseFile("https://developers.google.com/android/nexus/images#hammerhead");
//			windowsAutomation.runCommand("cd /d Z:\\03_software\\05_rom",true);
//			
//			WindowsAutomation windowsAutomation = new WindowsAutomation();
//			try {
				//https://download.chainfire.eu/363/CF-Root1/CF-Auto-Root-hammerhead-hammerhead-nexus5.zip?retrieve_file=1

//				windowsAutomation.BrowseFile("https://download.chainfire.eu/363/CF-Root1/CF-Auto-Root-hammerhead-hammerhead-nexus5.zip?retrieve_file=1");
//				
//				windowsAutomation.putCommandToProcess(process, "smt" + "\n");
//				windowsAutomation.putCommandToProcess(process, "smt" + "\n");
//
//				windowsAutomation.putCommandToProcess(process, "dir" + "\n");
//				windowsAutomation.putCommandToProcess(process, "quit" + "\n").close();
//				windowsAutomation.showProcessOutput(process, "UTF8");

//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			break;
		}
		case "3.4.4": {
			String movie;
//			movie="星球大战7 3d 1080p";
//			 movie="태양의후예 E07";
//			 movie="太阳的后裔 torrent";
//			 movie="太阳的后裔";
//			 movie="singal 韩 1080";
//			movie="House of Cards S04 2160p";
			movie="疯狂动物城 1080p";
			SearchEngine searchEngine = new SearchEngine();
			// searchEngine.browseSearchResults(SearchEngineEnum.wenda, "迅雷 边下载
			// 边播放");
			
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:gaoqing.la 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:dygang.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:hdscg.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "site:cangyunge.com 2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.download, "2016 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.google, "星球大战7 3d 1080p");
//			searchEngine.browseSearchResults(SearchEngineEnum.download, "星球大战7 3d 1080p");
			searchEngine.browseSearchResults(SearchEngineEnum.download, movie);
			
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

		case "3.7.1": {
			SearchEngine searchEngine = new SearchEngine();
			searchEngine.browseSearchResults(SearchEngineEnum.luntan, "上海 房产证");
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

		decisionMaker.addDecisionData("1", "事情优先级", "0");
		decisionMaker.addDecisionData("1.1", "洗碗", "0");
		decisionMaker.addDecisionData("1.2", "整理装修清单", "0");
		decisionMaker.addDecisionData("2", "java程序与网页结合", "0");

		// decisionMaker.showDecision();

	}
}
