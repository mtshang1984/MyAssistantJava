package Assistant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CommonEnum.SearchEngineEnum;
import MindMap.MindMap;
import OfficeAutomation.ExcelAutomation;
import OfficeAutomation.PowerPointAutomation;
import OfficeAutomation.WordAutomation;
import SearchEngine.Browser;
import SearchEngine.SearchEngine;
import WindowsAutomation.WindowsAutomation;

public class AssistantBackup {

	MindMap mindMap = new MindMap();
	WindowsAutomation windowsAutomation = new WindowsAutomation();
	WordAutomation wordAutomation = new WordAutomation(true);
	PowerPointAutomation powerPointAutomation = new PowerPointAutomation(true);
	ExcelAutomation excelAutomation = new ExcelAutomation(true);

	String taskNumber;
	Browser browser=new Browser();
	public void runDay20160203() {

		SearchEngine searchEngine=new SearchEngine();
		switch (taskNumber) {
		// 制作Ameco培训材料
		case "1.1":
			break;
		case "2.1":
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
				windowsAutomation.putCommandToProcess(process,"open 192.168.1.101 2121"+"\n");
				windowsAutomation.putCommandToProcess(process,"smt"+"\n");
				windowsAutomation.putCommandToProcess(process,"smt"+"\n");

				windowsAutomation.putCommandToProcess(process,"dir"+"\n");
				windowsAutomation.putCommandToProcess(process,"quit"+"\n").close();
				windowsAutomation.showProcessOutput(process,"UTF8");

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	public void runDay20160130() {


		SearchEngine searchEngine=new SearchEngine();
		switch (taskNumber) {
		// 制作Ameco培训材料
		case "1.1":
			break;
		case "2.1":
			break;
		case "3.1.1":
			break;
		case "3.4.1":
			wordAutomation.close();
			powerPointAutomation.close();
			excelAutomation.close();
			searchEngine.browseSearchResults(SearchEngineEnum.taobao, "上海 杜莎夫人");

			break;
		default:
			break;
		}

		/*
		System.out.println("2015.11.6  完成应用提交");
		search_engine.BrowseSearchResults("web", "只是简单的网页剪切、内容聚合器或者罗列链接");
		 * 
		 */
	}

	public  void runOldDay() {
		Browser browser=new Browser();
		SearchEngine searchEngine=new SearchEngine();
		System.out.println("2015.05.26.22:04 完成应用提交");
		// search_engine.BrowseSearchResults("web", "品店网上超市");
		searchEngine.browseSearchResults("wenda", "一键应用分发");
		searchEngine.browseSearchResults("web", "material design 怎么用");
		System.out.println("2015.05.24.10:30 下载材料设计语言的素材");
		System.out.println("2015.05.24.21:00 基于材料设计语言 开发应用");
		searchEngine.browseSearchResults("web", "ANDROID STUDIO GITHUB");
		searchEngine.browseSearchResults("web", "ANDROID 中国 应用 收费");
		searchEngine.browseSearchResults("download", "aide");
		searchEngine.browseSearchResults("web", "网盘搜索");
		searchEngine.browseSearchResults("wenda", "android SDK 下载慢");
		searchEngine.browseSearchResults("shopping", "发布");
		searchEngine.browseSearchResults("shopping", "谷歌开发者 帐号");
		searchEngine.browseSearchResults("wenda", "谷歌开开发者 帐号 收费");
		/*
		 * search_engine.BrowseSearchResults("web", "eclipse package 含义");
		 */

		/*
		 * 
		 * search_engine.BrowseSearchResults(SearchEngineEnum.luntan, "电视背景 设计");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.shopping, "玩具 船");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web, "lbe 后台自启");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.wenda, "手机 通讯录 整理"
		 * ); search_engine.BrowseSearchResults(SearchEngineEnum.download,
		 * "MindManager");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web,
		 * "Mindjet maps dropbox 同步");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web,
		 * "shadow socks gae 结合");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web, "polymer 例子");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.download,
		 * "office 2013");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.download,
		 * "Illustrator");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.download,
		 * "allway sync 15");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web, "办公 笔记本");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.image, "厨房 黑白灰");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.web, "厨房 地砖");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.luntan, "瓷砖 类型");
		 * search_engine.BrowseSearchResults(SearchEngineEnum.luntan, "瓷砖 能强")
		 * search_engine.BrowseSearchResults(SearchEngineEnum.luntan, "瓷砖");
		 * search_engine.BrowseSearchResults("zhuangxiu", "网上 早餐");
		 * search_engine.BrowseSearchResults("web", "网上 早餐");
		 * search_engine.BrowseSearchResults("wenda", "vmware xp 游戏 黑边");
		 * search_engine.BrowseSearchResults("download",
		 * "cn windows 10 enterprise x64 ");
		 * search_engine.BrowseSearchResults("download",
		 * "cn_windows_10_enterprise_x64_dvd_6846957");
		 * search_engine.BrowseSearchResults("wenda", "win 10 企业版 激活");
		 * 
		 * search_engine.BrowseSearchResults("web", "site:liba.com 花洒 汉斯格雅");
		 * search_engine.BrowseSearchResults("social", "柚子装修");
		 * search_engine.BrowseSearchResults("download", "autocad 2015");
		 * 
		 * search_engine.BrowseSearchResults("shopping", "施耐德 强电箱 ");
		 * search_engine.BrowseSearchResults("web", "创饰 梦开 site:liba.com");
		 * search_engine.BrowseSearchResults("WEB", "嘉怡水岸  断桥 卧室 窗户");
		 * search_engine.BrowseSearchResults("WEB", "高层  断桥 SITE:LIBA.COM");
		 * search_engine.BrowseSearchResults("web", "凤铝 罗普斯金 site:liba.com");
		 * search_engine.BrowseSearchResults("web", "尤阿姨  封阳台 site:liba.com");
		 * search_engine.BrowseSearchResults("wenda", "前端 开发 工具");
		 * search_engine.BrowseSearchResults("wenda", "网页 开发 软件");
		 * search_engine.BrowseSearchResults("web", "site:liba.com 洗碗机");
		 * search_engine.BrowseSearchResults("web", "SITE:LIBA.COM 采购清单");
		 * search_engine.BrowseSearchResults("web", " 弱电箱 光大 正泰 信基伟业");
		 * 
		 * search_engine.BrowseSearchResults("wenda", "净水 超滤 反渗透");
		 * search_engine.BrowseSearchResults("shopping", "MEDI 弹力袜 长筒");
		 * search_engine.BrowseSearchResults("zhuangxiu", "柚子装修");
		 * search_engine.BrowseSearchResults("web", "c++ boost 数学 书");
		 * 
		 * search_engine.BrowseSearchResults("download", "数值模拟");
		 * search_engine.BrowseSearchResults("web", "亦帆 孙石锁 site:liba.com");
		 * search_engine.BrowseSearchResults("web", "现代简约 装修日记 site:liba.com");
		 * search_engine.BrowseSearchResults("zhuangxiu", "日记");
		 * search_engine.BrowseSearchResults("web", "日记 site:liba.com");
		 * search_engine.BrowseSearchResults("web", "亦帆  现代简约 site:liba.com");
		 * search_engine.BrowseSearchResults("zhuangxiu", "亦帆 施工");
		 * search_engine.BrowseSearchResults("web", "亦帆 鸿鹄 site:liba.com");
		 * search_engine.BrowseSearchResults("web", "google keep 同步出错");
		 * search_engine.BrowseSearchResults("wenda", "互联网 主材包");
		 * search_engine.BrowseSearchResults("shopping", "小熊 电蒸锅");
		 * search_engine.BrowseSearchResults("social", "柚子装修");
		 * search_engine.BrowseSearchResults("web", "csr 蓝牙 搜索不到");
		 * search_engine.BrowseSearchResults("download",
		 * "enterprise architect 12 crack");
		 * search_engine.BrowseSearchResults("wenda", "极三路由");
		 * search_engine.BrowseSearchResults("download", "闪电侠");
		 * search_engine.BrowseSearchResults("download", "权力的游戏");
		 * search_engine.BrowseSearchResults("WEB", "github oschina");
		 * search_engine.BrowseSearchResults("scholar", "燃烧室");
		 * 
		 * search_engine.BrowseSearchResults("scholar", "自动参数化CFD");
		 * System.out.println("2015.05.24.9:33 洗碗"); System.out.println(
		 * "2015.05.24.10:30 京东采购"); System.out.println("2015.05.24.10:30 1号店采购"
		 * ); System.out.println("2015.05.24.11:00 京东采购"); System.out.println(
		 * "2015.05.24.13:00 休息睡觉"); System.out.println("2015.05.24.16:00 出去买菜"
		 * ); System.out.println("2015.05.24.16:00 京东扫帚"); System.out.println(
		 * "2015.05.28.22:07 搜索相关文献");
		 * search_engine.BrowseSearchResults("scholar",
		 * "Automatic Parametric combustor CFD");
		 * search_engine.BrowseSearchResults("wenda", "香港 转运 iphone 6");
		 * search_engine.BrowseSearchResults("shopping", "微力达 扫把");
		 * search_engine.BrowseSearchResults("shopping", "扫把");
		 * search_engine.BrowseSearchResults("wenda", "扫地 选购");
		 * search_engine.BrowseSearchResults("wenda", "扫把 选购");
		 * search_engine.BrowseSearchResults("wenda", "查拉图斯特拉如是说 翻译");
		 * search_engine.BrowseSearchResults("web", "尼采 语言");
		 * search_engine.BrowseSearchResults("web", "尼采 希特勒");
		 * search_engine.BrowseSearchResults("web", "nexus 5 LMY48b  root ");
		 * search_engine.BrowseSearchResults("web", "支付宝 生活 缴费"); public static
		 * void Today() { search_engine.BrowseSearchResults("wenda",
		 * "查拉图斯特拉如是说 翻译"); search_engine.BrowseSearchResults("web", "尼采 语言");
		 * search_engine.BrowseSearchResults("web", "尼采 希特勒");
		 * search_engine.BrowseSearchResults("web", "nexus 5 LMY48b  root ");
		 * search_engine.BrowseSearchResults("web", "支付宝 生活 缴费");
		 * search_engine.BrowseSearchResults("web", "购物 推荐");
		 * search_engine.BrowseSearchResults("web", "购物 推荐");
		 * search_engine.BrowseSearchResults("shopping", "自拍杆");
		 * search_engine.BrowseSearchResults("wenda", "备孕");
		 * search_engine.BrowseSearchResults("wenda", "自然流产");
		 * search_engine.BrowseSearchResults("web",
		 * "ubuntu 14.04 gitolite 配置Git项目");
		 * search_engine.BrowseSearchResults("download", "2015 1080p");
		 * search_engine.BrowseSearchResults("web", "ubuntu gitolite 使用") ; /*
		 * 
		 * search_engine.BrowseSearchResults("web",
		 * "ubuntu 14.04 gitolite 配置Git项目");
		 * search_engine.BrowseSearchResults("download", "2015 1080p");
		 * search_engine.BrowseSearchResults("web", "ubuntu gitolite 使用") ;
		 * search_engine.BrowseSearchResults("download", "2015 1080p"); /*
		 * 
		 * search_engine.BrowseSearchResults("wenda", "歌唱比赛  选歌 ");
		 * search_engine.BrowseSearchResults("web", "校园 比赛 歌曲");
		 * search_engine.BrowseSearchResults("social", "校园 比赛 歌曲");
		 */
		searchEngine.browseSearchResults("social", "自拍杆 铝 蓝牙");
		/*
		 * 
		 * search_engine.BrowseSearchResults("wenda", "自拍杆 选购");
		 * search_engine.BrowseSearchResults("shopping", "自拍杆");
		 */
		browser.openUrl(
				"https://www.google.com.hk/search?newwindow=1&safe=strict&tbs=simg:CAES1AEa0QELEKjU2AQaAggCDAsQsIynCBpgCl4IAxIohQ2VFZkVgg2QGIQN5gqIGP4MlBWwIY02jjbWKpM2lDbbIog2giCSNhowUpBCI3eNWsot0rxK6TJjbSWR6HzbKj8ZINyLzSYlyZNM9VqMthdJ85gu48YBYFBPDAsQjq7-CBoKCggIARIETXEzwQwLEJ3twQkaQQoGCgRyb29tCg0KC2xpdmluZyByb29tCgcKBXlhY2h0ChAKDnBhc3NlbmdlciBzaGlwCg0KC2NvbmRvbWluaXVtDA&tbm=isch&sa=X&ei=_iRSVd7mJeLfmgXXpoBw&ved=0CEIQsw4");

		searchEngine.browseSearchResults("social", "五凹  site:liba.com");
		searchEngine.browseSearchResults("web", "亦帆  现代简约 site:liba.com");
		searchEngine.browseSearchResults("social", "亦帆 简约");
		searchEngine.browseSearchResults("social", "廖县华");
		searchEngine.browseSearchResults("social", "岳正");
		searchEngine.browseSearchResults("web", "魔力 几何");
		searchEngine.browseSearchResults("web", "鸿鹄 小宝 site:liba.com");
		searchEngine.browseSearchResults("social", "HH 小宝");
		searchEngine.browseSearchResults("social", "微信 消息 推送");
		// search_engine.BrowseSearchResults("baidu", "装修 设计师 谈什么");

		searchEngine.browseSearchResults("web", "自动发微信");
		searchEngine.browseSearchResults("social", "网易云音乐");
		searchEngine.browseSearchResults("social", "鸿鹄 设计 清单");
		searchEngine.browseSearchResults("web", "qqex 鞋子 渠道");
		searchEngine.browseSearchResults("shopping", "应用 赚钱");
		/*
		 * search_engine.BrowseSearchResults("wenda", "装修  设计师  谈什么");
		 * search_engine.BrowseSearchResults("social", "华为 P8");
		 * search_engine.BrowseSearchResults("social", "装修预算");
		 * search_engine.BrowseSearchResults("wenda", "eclipse 文件分类");
		 * search_engine.BrowseSearchResults("web", "品店网上超市");
		 */
		/*
		 * search_engine.BrowseSearchResults("social", "美剧");
		 * search_engine.BrowseSearchResults("download", "权力的游戏");
		 * search_engine.BrowseSearchResults("web", "allway sync 15 激活");
		 * search_engine.BrowseSearchResults("download", "allway sync");
		 * search_engine.BrowseSearchResults("wenda", "iphone 6 海淘");
		 */

		searchEngine.browseSearchResults("social", "allway sync 15 激活");
		/*
		 * search_engine.BrowseSearchResults("web", "allway sync 15 激活");
		 * search_engine.BrowseSearchResults("download", "allway sync");
		 * search_engine.BrowseSearchResults("wenda", "iphone 6 海淘");
		 */
		searchEngine.browseSearchResults("wenda", "iphone 6 海淘");
		/*
		 * 
		 * search_engine.BrowseSearchResults("wenda", "c++  容器 java ");
		 * search_engine.BrowseSearchResults("download", "人工智能");
		 */

		searchEngine.browseSearchResults("scholar", "英国 45型");
		/*
		 * search_engine.BrowseSearchResults("web", "英国 45型");
		 * search_engine.BrowseSearchResults("social", "英国 45型");
		 * search_engine.BrowseSearchResults("social", "降息 房贷 ");
		 * search_engine.BrowseSearchResults("download", "2015 mkv ");
		 * search_engine.BrowseSearchResults("web", "综合电力推进");
		 * search_engine.BrowseSearchResults("social", "综合电力推进");
		 * search_engine.BrowseSearchResults("social", "鸿鹄 设计");
		 * search_engine.BrowseSearchResults("social", "鸿鹄 小宝 / 汪伟伦"); //推荐
		 * search_engine.BrowseSearchResults("social", "鸿鹄 大强"); 现代简约 作品一般
		 * search_engine.BrowseSearchResults("social", "鸿鹄 雪燕"); 现代简约 作品一般
		 * search_engine.BrowseSearchResults("social", "鸿鹄 吴沛"); 文艺青年，未有耀眼作品
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "亿陶");
		 * search_engine.BrowseSearchResults("social", "亿陶 纪");
		 * search_engine.BrowseSearchResults("social", "亿陶 台微");
		 * 
		 * search_engine.BrowseSearchResults("social", "亦帆 设计");
		 * search_engine.BrowseSearchResults("social", "亦帆 岳正"); 做的套数比较多
		 * search_engine.BrowseSearchResults("social", "亦帆 彭全"); 作品网上较少。
		 * search_engine.BrowseSearchResults("social", "亦帆 黄小林");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "ID城市空间"); //设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "80年代");//设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "润秦"); //水电不错，设计一般
		 * search_engine.BrowseSearchResults("social", "1917 设计"); //贵
		 * search_engine.BrowseSearchResults("social", "上海映象"); //贵
		 * search_engine.BrowseSearchResults("social", "瀚高"); //贵
		 */
		searchEngine.browseSearchResults("social", "联系 鸿鹄");
		/*
		 * search_engine.BrowseSearchResults("social", "鸿鹄 设计");
		 * search_engine.BrowseSearchResults("social", "鸿鹄 小宝 / 汪伟伦"); //推荐
		 * search_engine.BrowseSearchResults("social", "鸿鹄 大强"); 现代简约 作品一般
		 * search_engine.BrowseSearchResults("social", "鸿鹄 雪燕"); 现代简约 作品一般
		 * search_engine.BrowseSearchResults("social", "鸿鹄 吴沛"); 文艺青年，未有耀眼作品
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "亿陶");
		 * search_engine.BrowseSearchResults("social", "亿陶 纪");
		 * search_engine.BrowseSearchResults("social", "亿陶 台微");
		 * 
		 * search_engine.BrowseSearchResults("social", "亦帆 设计");
		 * search_engine.BrowseSearchResults("social", "亦帆 岳正"); 做的套数比较多
		 * search_engine.BrowseSearchResults("social", "亦帆 彭全"); 作品网上较少。
		 * search_engine.BrowseSearchResults("social", "亦帆 黄小林");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "ID城市空间"); //设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "80年代");//设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "润秦"); //水电不错，设计一般
		 * search_engine.BrowseSearchResults("social", "1917 设计"); //贵
		 * search_engine.BrowseSearchResults("social", "上海映象"); //贵
		 * search_engine.BrowseSearchResults("social", "瀚高"); //贵
		 */

		searchEngine.browseSearchResults("social", "腾洋");

		/*
		 * search_engine.BrowseSearchResults("social", "鸿鹄 设计");
		 * search_engine.BrowseSearchResults("social", "亦帆 设计");
		 * search_engine.BrowseSearchResults("social", "亿陶");
		 * 
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "ID城市空间"); //设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "80年代");//设计不错，工程一般
		 * search_engine.BrowseSearchResults("social", "1917 设计"); //贵
		 * search_engine.BrowseSearchResults("social", "上海映象"); //贵
		 * search_engine.BrowseSearchResults("social", "瀚高"); //贵
		 * 
		 * 
		 * search_engine.BrowseSearchResults("social", "胤IN DESIGN");
		 * search_engine.BrowseSearchResults("social", "至纯 设计");
		 * search_engine.BrowseSearchResults("social", "卓观 设计");
		 * search_engine.BrowseSearchResults("social", "同济居家");
		 * search_engine.BrowseSearchResults("social", "乐益");
		 * search_engine.BrowseSearchResults("social", "润秦");
		 * search_engine.BrowseSearchResults("social", "乾龙");
		 * search_engine.BrowseSearchResults("social", "境远");
		 * search_engine.BrowseSearchResults("social", "朗域");
		 * search_engine.BrowseSearchResults("social", "紫业");
		 * search_engine.BrowseSearchResults("social", "绿通");
		 * search_engine.BrowseSearchResults("social", "腾洋");
		 */

		searchEngine.browseSearchResults("Web", "鸿鹄  site:liba.com");
		/*
		 * 
		 * search_engine.BrowseSearchResults("Web", "乐杰 site:liba.com");
		 * search_engine.BrowseSearchResults("Web","网盘 搜索");
		 * search_engine.BrowseSearchResults ("Web","乐杰 site:liba.com");
		 * SearchEngine .BrowseSearchResults("Download" ,"侠盗猎车手4");
		 * search_engine.BrowseSearchResults( "Download","侠盗车 4");
		 * search_engine.BrowseSearchResults("Web","GTA 4 下载");
		 */
		searchEngine.browseSearchResults("Web", "java main 参数 -");
		/*
		 * 
		 * search_engine.BrowseSearchResults("Web" ,"非工作目录 错误: 找不到或无法加载主类");
		 * search_engine.BrowseSearchResults("Web","eclipse utf8");
		 * search_engine.BrowseSearchResults("Web" ,"java 启动 Linux命令");
		 * search_engine.BrowseSearchResults("Web","java 启动 linux shell");
		 * SearchEngine .BrowseSearchResults("Web","java class windows Linux 版本"
		 * ); search_engine.BrowseSearchResults("Web" ,"java 启动前台程序");
		 * search_engine.BrowseSearchResults("Web","eclipse java 中文字体");
		 * SearchEngine .BrowseSearchResults("Web",
		 * "ubuntu 14 apt-get oracle jdk");
		 * search_engine.BrowseSearchResults("Web" ,"java 文件读写");
		 */
	}
}
