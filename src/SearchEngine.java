import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchEngine {

	// 根据关键词和所选用的搜索引擎生成网址
	public static String GetString_url(String search_egine_selection,
			String Keyword) {
		String string_url;
		switch (search_egine_selection.toLowerCase()) {
		default:
			string_url = "https://www.google.com.hk/search?q=";
			break;
		// 网络搜索
		case "google":
			string_url = "https://www.google.com.hk/search?q=";
			break;
		case "bing":
			string_url = "http://global.bing.com/search?q=";
			break;
		case "bingcn":
			string_url = "http://cn.bing.com/search?q=";
			break;
		case "baidu":
			string_url = "http://www.baidu.com/s?ie=utf-8&wd=";
			break;
		case "haosou":
			string_url = "http://www.haosou.com/s?ie=utf-8&q=";
			break;

		// 问答搜索
		case "bingqa":
			string_url = "http://cn.bing.com/knows/search?est=3&q=";
			break;
		case "baiduzhidao":
			string_url = "http://zhidao.baidu.com/search?word=";
			break;
		case "baidujingyan":
			string_url = "http://jingyan.baidu.com/search?lm=11&word=";
			break;
		case "zhihu":
			string_url = "http://www.zhihu.com/search?q=";
			break;
		case "tianyawenda":
			string_url = "http://wenda.tianya.cn/search.jsp?q=";
			break;
		case "haosouwenda":
			string_url = "http://wenda.haosou.com/search/?q=";
			break;
		case "sogouwenwen":
			string_url = "http://wenwen.sogou.com/s/?w=";
			break;
		case "guokewenda":
			string_url = "http://www.guokr.com/search/all/?wd=";
			break;

		// 健康搜索
		case "39jiankang":
			string_url = "http://so.39.net/ask.aspx?words=";
			break;

		// 旅游搜索
		case "mafengwowenda":
			string_url = "http://www.mafengwo.cn/group/s.php?q=";
			break;

		// 百科搜索
		case "wikipedia":
			string_url = "https://zh.wikipedia.org/w/index.php?search=";
			break;
		case "bingwiki":
			string_url = "http://cn.bing.com/knows/search?est=2&q=";
			break;
		case "baidubaike":
			string_url = "http://baike.baidu.com/search?enc=utf8&word=";
			break;
		case "hudongbaike":
			string_url = "http://so.baike.com/doc/";
			break;
		case "haosoubaike":
			string_url = "http://baike.haosou.com/search/?q=";
			break;

		// 社交搜索
		case "twitter":
			string_url = "https://twitter.com/search?q=";
			break;
		case "weibo":
			string_url = "http://s.weibo.com/weibo/";
			break;
		case "googleplus":
			string_url = "https://plus.google.com/u/0/s/";
			break;
		case "kuandaishan":
			string_url = "http://s.kdslife.com/search?q=";
			break;
		//装修
		case "liba":
			string_url = "http://www.libaclub.com/facade.php?act=search&searchAction=submit&keyword=";
			break;
		case "qijia":
			string_url = "http://bbs.jia.com/f_57_0?key_words=";
			break;
			
		//pingjia	
		case "dianping":
			string_url = "http://www.dianping.com/search/keyword/1/0_";
			break;
			
		// 购物搜索
		case "amazon":
			string_url = "http://www.amazon.com/s/keywords=";
			break;
		case "amazoncn":
			string_url = "http://www.amazon.cn/s/sort=popularity-rank&keywords=";
			break;
		case "taobao":
			string_url = "http://s.taobao.com/search?psort=_lw_quantity&sort=sale-desc&q=";
			break;
		case "jingdong":
			string_url = "http://search.jd.com/Search?enc=utf-8&psort=3&keyword=";
			break;
		case "smzdm":
			string_url = "http://search.smzdm.com/?s=";
			break;

		// 学术搜索
		case "googlescholar":
			string_url = "https://scholar.google.com.hk/scholar?q=";
			break;
		case "bingacademic":
			string_url = "http://cn.bing.com/academic/search?q=";
			break;
		case "baiduxueshu":
			string_url = "http://xueshu.baidu.com/s?ie=utf-8&wd=";
			break;

		// 下载搜索
		case "cllj":
			string_url = "http://cililian.me/list/";
			break;
		case "pansou":
			string_url = "http://www.pansou.com/s.php?wp=0&q=";
			break;
		case "baiduyun":
			string_url = "http://so.baiduyun.me/search.php?wd=";
			break;
		case "wangpansou":
			string_url = "http://www.wangpansou.cn/s.php?q=";
			break;
		case "panyisou":
			string_url = "http://panyisou.com/?question/search/3/";
			break;
		case "yisou":
			string_url = "http://www.yiso.me/search.php?wd=";
			break;
		case "panduoduo":
			string_url = "http://www.panduoduo.net/s/name/";
			break;
		case "panzz":
			string_url = "http://www.panzz.com/s/?kw=";
			break;
		case "swp360":
			string_url = "http://www.360swp.com/s.php?q=";
			break;
		}
		//生成URL
		try {
			switch (search_egine_selection.toLowerCase()) {
			default:
				string_url = string_url + URLEncoder.encode(Keyword, "UTF-8");
				break;
			case "39jiankang":
				string_url = string_url + URLEncoder.encode(Keyword, "GBK");
				break;
			case "cllj":
				string_url = string_url + URLEncoder.encode(Keyword, "UTF-8")
						+ "/1.html";
				break;
			case "googleplus":
				string_url = string_url + URLEncoder.encode(Keyword, "UTF-8")
						+ "/posts";
				break;
			}
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("Broken VM does not support UTF-8");
		}
		return string_url;
	}

	// 打开浏览器浏览单个搜索结果网页
	private static void BrowseASearchResults(String search_egine_selection,
			String Keyword) {
		String string_url;
		string_url = GetString_url(search_egine_selection, Keyword);
		Browser.OpenUrl(string_url);
	}

	// 打开浏览器浏览搜索结果网页
	public static void BrowseSearchResults(String search_egine_selection,
			String Keyword) {

		switch (search_egine_selection.toLowerCase()) {
		default:
			Browser.OpenUrl("");
			BrowseASearchResults(search_egine_selection, Keyword);
			break;
		case "web":
			Browser.OpenUrl("");
			BrowseASearchResults("Google", Keyword);
			BrowseASearchResults("Bing", Keyword);
			// BrowseASearchResults("BingCN", Keyword);
			BrowseASearchResults("Baidu", Keyword);
			BrowseASearchResults("Haosou", Keyword);
			break;
		case "wenda":
			Browser.OpenUrl("");
			BrowseASearchResults("BingQA", Keyword);
			BrowseASearchResults("Baiduzhidao", Keyword);
			BrowseASearchResults("baidujingyan", Keyword);
			BrowseASearchResults("Zhihu", Keyword);
			BrowseASearchResults("tianyawenda", Keyword);
			BrowseASearchResults("haosouwenda", Keyword);
			BrowseASearchResults("sogouwenwen", Keyword);
			BrowseASearchResults("guokewenda", Keyword);
			break;

		case "jiankang":
			Browser.OpenUrl("");
			BrowseASearchResults("39jiankang", Keyword);
			break;

		case "lvyou":
			Browser.OpenUrl("");
			BrowseASearchResults("mafengwowenda", Keyword);
			break;

		case "baike":
			Browser.OpenUrl("");
			BrowseASearchResults("wikipedia", Keyword);
			BrowseASearchResults("BingWiki", Keyword);
			BrowseASearchResults("baidubaike", Keyword);
			BrowseASearchResults("hudongbaike", Keyword);
			BrowseASearchResults("haosoubaike", Keyword);
			break;

		case "social":
			Browser.OpenUrl("");
			BrowseASearchResults("Twitter", Keyword);
			BrowseASearchResults("Weibo", Keyword);
			BrowseASearchResults("GooglePlus", Keyword);
			BrowseASearchResults("KuanDaiShan", Keyword);
			break;

		case "zhuangxiu":
			Browser.OpenUrl("");
			BrowseASearchResults("Liba", Keyword);
			BrowseASearchResults("Qijia", Keyword);
			break;

		case "koubei":
			Browser.OpenUrl("");
			BrowseASearchResults("Dianping", Keyword);
			break;
			
		case "shopping":
			Browser.OpenUrl("");
			BrowseASearchResults("Taobao", Keyword);
			BrowseASearchResults("Jingdong", Keyword);
			BrowseASearchResults("SMZDM", Keyword);
			BrowseASearchResults("Amazon", Keyword);
			BrowseASearchResults("AmazonCN", Keyword);
			break;
		case "download":
			Browser.OpenUrl("");
			BrowseASearchResults("panzz", Keyword);
			BrowseASearchResults("wangpansou", Keyword);
			BrowseASearchResults("baiduyun", Keyword);
			BrowseASearchResults("yisou", Keyword);
			BrowseASearchResults("CLLJ", Keyword);
			//BrowseASearchResults("panduoduo", Keyword);
			//BrowseASearchResults("SWP360", Keyword);
			//BrowseASearchResults("panyisou", Keyword);
			//BrowseASearchResults("PanSou", Keyword);
			break;
		case "scholar":
			Browser.OpenUrl("");
			BrowseASearchResults("googlescholar", Keyword);
			BrowseASearchResults("bingacademic", Keyword);
			BrowseASearchResults("baiduxueshu", Keyword);
			break;
		}
	}
}
