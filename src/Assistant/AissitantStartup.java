package Assistant;
import SearchEngine.SearchEngine;

public class AissitantStartup {

	public static void runToday() {


		System.out.println("2015.05.26.22:04 完成应用提交");
		/*
		SearchEngine.BrowseSearchResults("web", "只是简单的网页剪切、内容聚合器或者罗列链接");
		 * 
		 */
	}

	public static void runOldDay() {
		SearchEngine searchEngine=new SearchEngine();
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
	}
}
