package AlmightyAssistantBackup;

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
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import WindowsAutomation.WindowsAutomation;
import java.util.Properties;

import BussinessApplication.BussinessApplication;
public class AlmightyAssistant201604 {

	MindMap mindMap;
	DecisionMaker decisionMaker;
public AlmightyAssistant201604() {
		mindMap = new MindMap();
		decisionMaker = new DecisionMaker();
	}		

	public void runToday() {
		makeDecision();
		makeMindMap();

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
		taskNumber = "2.2.1";
		taskNumber = "2.2.1";
		taskNumber = "2.1.2.1";//搭建翻墙VPS
		taskNumber = "2.2.2";//增加翻墙账号
		taskNumber="2.1.2.2.1";//建立DNS中转服务器
		taskNumber = "2.1.2.5.4";//备份翻墙服务器
		taskNumber = "2.1.2.5";//平板翻墙
		taskNumber = "2.1.2.5.3";//备份翻墙服务器
		excuteJob(taskNumber, parameter);
	}
	public void makeDecision(){

		decisionMaker.addDecisionData("1", "事情优先级", "0");
		decisionMaker.addDecisionData("1.1", "20160409", "0");
		decisionMaker.addDecisionData("1.1.1", "整理装修清单", "0");
		decisionMaker.addDecisionData("1.1.2", "洗碗", "0");;
		decisionMaker.addDecisionData("1.2", "20160423", "0");
//		decisionMaker.addDecisionData("1.2.1", "VPS上建一个DNS服务器", "0");
//		decisionMaker.addDecisionData("1.2.2", "中转服务器上用dnsmasq进行分流", "0");
		decisionMaker.addDecisionData("1.2.3", "wifi中转", "0");
//		decisionMaker.addDecisionData("1.2.3", "透明代理", "0");
//		decisionMaker.addDecisionData("1.2.4", "VPN代理", "0");
		decisionMaker.addDecisionData("1.3", "20160425", "0");
		decisionMaker.addDecisionData("1.3.1", "透明代理", "0");
		decisionMaker.addDecisionData("1.3.1.1", "安装hostapd", "0");
		decisionMaker.addDecisionData("1.3.1.2", "安装chinadns,dnsmasq进行dhcp和dns解析", "0");
		decisionMaker.addDecisionData("1.3.1.3", "安装shadowsocks-libev进行翻墙", "0");
		decisionMaker.addDecisionData("1.3.1.4", "配置iptables", "0");
		decisionMaker.addDecisionData("1.3.1.5", "安装privoxy进行代理调度", "0");
		decisionMaker.addDecisionData("1.3.2", "建网站", "0");
		decisionMaker.addDecisionData("1.4", "20160502", "0");
		decisionMaker.addDecisionData("1.4.1", "发布出售消息", "0");
		decisionMaker.addDecisionData("1.4.2", "联系二手回收", "0");
		
		
		decisionMaker.addDecisionData("2", "java程序与网页结合", "0");
		
		decisionMaker.addDecisionData("3", "树莓派是否有必要买", "0");
		decisionMaker.addDecisionData("3.1", "nexus7 2013是否能够实现树莓派的翻墙功能","0");
		decisionMaker.addDecisionData("3.1.1", "nexus 7 2013是否能够安装ubuntu 14.04桌面版,不能","0");
		decisionMaker.addDecisionData("3.1.2", "ubuntu 14.04 touch是否可以运行linux程序,带图形界面的不行，命令行版本的可以","0");
		decisionMaker.addDecisionData("3.1.2.1", "是否可以开机自启动,是","0");
		decisionMaker.addDecisionData("3.1.2.2", "是否使用openssh server","0");
		
		
		decisionMaker.addDecisionData("4", "任务难度超出预期，无法按时完成怎么办", "0");
		decisionMaker.addDecisionData("4.1", "详细说明情况，困难较大，尽力了却无法完成", "0");
		decisionMaker.addDecisionData("4.2", "分清主次，保持良好的精神状态,为完成其它事情做好准备","0");
		
		// decisionMaker.showDecision();

	}
	public void makeMindMap() {

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
		mindMap.addMindData("2.1.2.1", "配置翻墙服务器", "2.1.2");
		mindMap.addMindData("2.1.2.2.1", "设置DNS中转服务器", "2.1.2");
		mindMap.addMindData("2.1.2.2.2", "建一个DNS服务器", "2.1.2");
		mindMap.addMindData("2.1.2.2.3", "透明代理", "2.1.2");
		mindMap.addMindData("2.1.2.2.4", "VPN代理", "2.1.2");
		mindMap.addMindData("2.1.2.2", "修复局域网代理服务器", "2.1.2");
		mindMap.addMindData("2.1.2.3", "建立新账号", "2.1.2");
		mindMap.addMindData("2.1.2.3.1", "配置json", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.2", "配置ini", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.3", "配置DOM4j", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.4", "配置snakeyaml", "2.1.2.3");
		mindMap.addMindData("2.1.2.3.5", "shadowsocks账号管理", "2.1.2.3");
		mindMap.addMindData("2.1.2.4", "建立监控服务器", "2.1.2");
		mindMap.addMindData("2.1.2.5", "建立ubuntu翻墙服务器", "2.1.2");
		mindMap.addMindData("2.1.2.5.1", "建立翻墙代理服务器", "2.1.2");
		mindMap.addMindData("2.1.2.5.2", "设置局域网代理", "2.1.2");
		mindMap.addMindData("2.1.2.5.3", "设置透明代理", "2.1.2");
		mindMap.addMindData("2.1.2.5.4", "建立备份翻墙代理服务器", "2.1.2");
//		mindMap.addMindData("2.1.3", "配置Appahce2服务器", "2.1");
		mindMap.addMindData("2.1.3.1", "配置https加密", "2.1");
		mindMap.addMindData("2.1.4", "配置windows服务器", "2.1");
		mindMap.addMindData("2.1.4.1", "配置windows服务器", "2.1.4");
		mindMap.addMindData("2.2", "卖翻墙账号", "2");
		mindMap.addMindData("2.2.1", "给陈毓卿增加账号", "2.2");
		mindMap.addMindData("2.2.2", "给洪钦增加账号", "2.2");
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
			
			VpsServer vpsServer = new VpsServer(new Host("proxy.ijushan.com" ,"root","ShMT0659","smt","ShMT0659",22));
			vpsServer.initialize();
			vpsServer.setIptable();
//			vpsServer.uninstallAllSoftware();
//			vpsServer.installAllSoftware();
//			vpsServer.uninstallFinalSpeed();
//			vpsServer.installFinalSpeed();
//			vpsServer.testCode();
			vpsServer.close();
			break;
		}
		case "2.1.2.2.1":{

			/** 在平板上配置Shadowsocks客户端 */
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing(new Host("www.ijushan.com" ,"root","ShMT0659","phablet","ShMT0659",22));
			scientificBrowsing.initialize();
			scientificBrowsing.addDnsForward();
			scientificBrowsing.close();
			break;
		}
		case "2.1.2.3.5":{
			break;
		}
		case "2.1.2.2": {
			/** 配置Shadowsocks客户端 */
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing(new Host("www.ijushan.com" ,"root","ShMT0659","smt","ShMT0659",22));
			scientificBrowsing.initialize();
			scientificBrowsing.installAllSoftware();
			scientificBrowsing.close();
			break;
		}
		case "2.1.2.5": {
			/** 在平板上配置Shadowsocks客户端 */
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing(new Host("www.ijushan.com" ,"root","ShMT0659","phablet","ShMT0659",22));
			scientificBrowsing.initialize();

//			scientificBrowsing.runSshCommand("mount -o remount,rw /",  true);
//			scientificBrowsing.installPackageByAptget("openjdk-8-jdk",true,  true);
//			scientificBrowsing.installPackageByAptget("libpcap-dev",true, true);
			scientificBrowsing.uninstallAllSoftware();
			scientificBrowsing.installAllSoftware();
//			scientificBrowsing.uninstallHaproxy();
//			scientificBrowsing.installHaproxy();
//			scientificBrowsing.uninstallFinalspeed(true);
//			scientificBrowsing.installFinalspeed(true);
			scientificBrowsing.close();
			break;
		}
		case "2.1.2.5.3": {
			/** 在透明代理 */
//
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing(new Host("www.ijushan.com" ,"root","ShMT0659","phablet","ShMT0659",22));
			scientificBrowsing.initialize();
			scientificBrowsing.testCode();
			scientificBrowsing.close();
			break;
		}
		case "2.1.2.5.4": {
			/** 在备份翻墙服务器 */
//
			ScientificBrowsing scientificBrowsing = new ScientificBrowsing(new Host("192.168.1.105" ,"root","ShMT0659","smt","ShMT0659",22));
			scientificBrowsing.initialize();
			scientificBrowsing.uninstallAllSoftware();
			scientificBrowsing.installAllSoftware();
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
		case "2.2.1": {
			BussinessApplication bussinessApplication = new BussinessApplication();
			Host vpsHost=new Host("proxy1.ijushan.com" ,"root","ShMT0659","smt","ShMT0659",22);
			bussinessApplication.addShadowsocksAccount(vpsHost,446,"!abcd1234");
			bussinessApplication.addShadowsocksAccount(vpsHost,444,"chenyuqing");
			break;
		}
		case "2.2.2": {

//			VpsServer vpsServer = new VpsServer("proxy.ijushan.com",22,"root","ShMT0659","smt","ShMT0659");
//			vpsServer.initialize();
//			vpsServer.setIptable();
//			vpsServer.installFinalSpeed();
//			vpsServer.close();
//			
			BussinessApplication bussinessApplication = new BussinessApplication();
			Host vpsHost=new Host("proxy1.ijushan.com" ,"root","ShMT0659","smt","ShMT0659",22);
//			bussinessApplication.addShadowsocksAccount(443,"!abcd1234");
//			bussinessApplication.addShadowsocksAccount(444,"chenyuqing");
//			bussinessApplication.addShadowsocksAccount(446,"!abcd1234");
//			bussinessApplication.addShadowsocksAccount(448,"hongqing");

			bussinessApplication.addSpeedShadowsocksAccount(vpsHost,"ssh",22,220);
			bussinessApplication.addSpeedShadowsocksAccount(vpsHost,"my_ss",443,4430);
			bussinessApplication.addSpeedShadowsocksAccount(vpsHost,"chenyuqing",444,4440);
			bussinessApplication.addSpeedShadowsocksAccount(vpsHost,"my_ss_using",446,4460);
//			bussinessApplication.deleteShadowsocksAccount(445);
//			bussinessApplication.deleteShadowsocksAccount(448);
//			bussinessApplication.addShadowsocksAccount(446,"hongqin");
//			bussinessApplication.addShadowsocksAccount(448,"hongqing");
//			bussinessApplication.deleteShadowsocksAccount(446);
//			bussinessApplication.deleteShadowsocksAccount(448);
//			bussinessApplication.addShadowsocksAccount(448,"zhuying");
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
			LinuxAutomation linuxAutomation_start=new LinuxAutomation(new Host("www.ijushan.com" ,"root","ShMT0659","smt","ShMT0659",22),false);
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
}
