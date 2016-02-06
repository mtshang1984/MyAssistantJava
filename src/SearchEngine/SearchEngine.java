package SearchEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import CommonEnum.SearchEngineEnum;

public class SearchEngine {

	// 根据关键词和所选用的搜索引擎生成网址
	public String getStringUrl(SearchEngineEnum searchEngineSelection,
			String keyword) {
		String stringUrl;
		switch (searchEngineSelection) {
		default:
			stringUrl = "https://www.google.com.hk/search?q=";
			break;
		// 网络搜索
		case google:
			stringUrl = "https://www.google.com.hk/search?q=";
			break;
		case bing:
			stringUrl = "http://global.bing.com/search?q=";
			break;
		case bingcn:
			stringUrl = "http://cn.bing.com/search?q=";
			break;
		case baidu:
			stringUrl = "http://www.baidu.com/s?ie=utf-8&wd=";
			break;
		case haosou:
			stringUrl = "http://www.haosou.com/s?ie=utf-8&q=";
			break;

		// 问答搜索
		case bingqa:
			stringUrl = "http://cn.bing.com/knows/search?est=3&q=";
			break;
		case baiduzhidao:
			stringUrl = "http://zhidao.baidu.com/search?word=";
			break;
		case baidujingyan:
			stringUrl = "http://jingyan.baidu.com/search?lm=11&word=";
			break;
		case zhihu:
			stringUrl = "http://www.zhihu.com/search?q=";
			break;
		case tianyawenda:
			stringUrl = "http://wenda.tianya.cn/search.jsp?q=";
			break;
		case haosouwenda:
			stringUrl = "http://wenda.haosou.com/search/?q=";
			break;
		case sogouwenwen:
			stringUrl = "http://wenwen.sogou.com/s/?w=";
			break;
		case guokewenda:
			stringUrl = "http://www.guokr.com/search/all/?wd=";
			break;

        // 图片搜索
        case googleimage:
            stringUrl = "https://www.google.com.hk/search?tbm=isch&q=";
            break;
        case bingimage:
            stringUrl = "http://cn.bing.com/images/search?q=";
            break;
        case bingcnimage:
            stringUrl = "http://cn.bing.com/images/search?q=";
            break;
        case baiduimage:
            stringUrl = "http://image.baidu.com/search/index?tn=baiduimage&ie=utf-8&word=";
            break;
        case haosouimage:
            stringUrl = "http://image.haosou.com/i?ie=utf-8&q=";
            break;
            
		// 健康搜索
		case _39jiankang:
			stringUrl = "http://so.39.net/ask.aspx?words=";
			break;

		// 旅游搜索
		case mafengwo:
			stringUrl = "http://www.mafengwo.cn/group/s.php?q=";
			break;

		// 百科搜索
		case wikipedia:
			stringUrl = "https://zh.wikipedia.org/w/index.php?search=";
			break;
		case bingwiki:
			stringUrl = "http://cn.bing.com/knows/search?est=2&q=";
			break;
		case baidubaike:
			stringUrl = "http://baike.baidu.com/search?enc=utf8&word=";
			break;
		case hudongbaike:
			stringUrl = "http://so.baike.com/doc/";
			break;
		case haosoubaike:
			stringUrl = "http://baike.haosou.com/search/?q=";
			break;

		// 社交搜索
		case twitter:
			stringUrl = "https://twitter.com/search?q=";
			break;
		case weibo:
			stringUrl = "http://s.weibo.com/weibo/";
			break;
		case googleplus:
			stringUrl = "https://plus.google.com/u/0/s/";
			break;

		// 社区            
		case libazhannei:
            stringUrl = "http://www.baidu.com/s?ie=utf-8&wd=";
            break;
        case kuandaishanzhannei:
            stringUrl = "http://www.baidu.com/s?ie=utf-8&wd=";
            break;
        case qijiazhannei:
            stringUrl = "http://www.baidu.com/s?ie=utf-8&wd=";
            break;
		case liba:
			stringUrl = "http://www.libaclub.com/facade.php?act=search&searchAction=submit&keyword=";
			break;
		case kuandaishan:
			stringUrl = "http://s.kdslife.com/search?q=";
			break;
		case qijia:
			stringUrl = "http://bbs.jia.com/f_57_0?key_words=";
			break;

		// pingjia
		case dianping:
			stringUrl = "http://www.dianping.com/search/keyword/1/0_";
			break;

		// 购物搜索
		case etao:
			stringUrl = "http://s.etao.com/search?sort=sale-desc&all_cities=on&qservice=s12&fseller=%BE%A9%B6%AB%C9%CC%B3%C7%2C%D1%C7%C2%ED%D1%B7%2C%CB%D5%C4%FE%D2%D7%B9%BA%2C1%BA%C5%B5%EA%2C%CC%EC%C3%A8%2C%B9%FA%C3%C0%D4%DA%CF%DF%2C%D0%C2%B5%B0%C9%CC%B3%C7%2C%D2%D7%D1%B8%CD%F8%2C%B5%B1%B5%B1%CD%F8%2C%D7%DF%D0%E3%CD%F8%2C%C8%A4%CC%EC%C2%F3%CD%F8%2C%BA%AB%B9%FAGmarket+%B9%D9%CD%F8%2C%B7%B2%BF%CD%B3%CF%C6%B7%2C%B8%DF%BA%E8%C9%CC%B3%C7%2C%C8%A4%CD%E6%CD%F8%2C%D3%C5%B9%BA%CD%F8%2C%CB%B3%B7%E1%D3%C5%D1%A1%2C%BA%C3%C0%D6%C2%F2%2C%BC%B4%C9%D0%CD%F8%2Cd1%D3%C5%C9%D0%CD%F8%2Cvjia%C9%CC%B3%C7%2C%CD%F2%B1%ED%CD%F8%2Cbabymarket%B9%D9%CD%F8%2C%C7%D7%C7%D7%B1%A6%B1%B4%C9%CC%B3%C7%2C%D5%E4%C6%B7%CD%F8&q=";
			break;
		case smzdm:
			stringUrl = "http://search.smzdm.com/?s=";
			break;
		case taobao:
			stringUrl = "http://s.taobao.com/search?psort=_lw_quantity&sort=sale-desc&q=";
			break;
		case jingdong:
			stringUrl = "http://search.jd.com/Search?enc=utf-8&psort=3&keyword=";
			break;
		case yihaodian:
			stringUrl = "http://search.yhd.com/c0-0/k";
			break;
		case dangdang:
			stringUrl = "http://search.dangdang.com/?sort_type=sort_sale_amt_desc&key=";
			break;
		case amazon:
			stringUrl = "http://www.amazon.com/s/keywords=";
			break;
		case amazoncn:
			stringUrl = "http://www.amazon.cn/s/sort=popularity-rank&keywords=";
			break;
		case suningyigou:
			stringUrl = "http://search.suning.com/";
			break;
		case guomeizaixian:
			stringUrl = "http://search.gome.com.cn/search?sort=10&question=";
			break;
		case weipinhui:
			stringUrl = "http://search.vip.com/search?sort=6&searchkw=";
			break;
		case lefeng:
			stringUrl = "http://search.lefeng.com/search/search?sos=sd&key=";
			break;

		// 学术搜索
		case googlescholar:
			stringUrl = "https://scholar.google.com.hk/scholar?q=";
			break;
		case bingacademic:
			stringUrl = "http://cn.bing.com/academic/search?q=";
			break;
		case baiduxueshu:
			stringUrl = "http://xueshu.baidu.com/s?ie=utf-8&wd=";
			break;

		// 下载搜索
		case cllj:
			stringUrl = "http://cililian.me/list/";
			break;
		case pansou:
			stringUrl = "http://www.pansou.com/s.php?wp=0&q=";
			break;
		case baiduyun:
			stringUrl = "http://so.baiduyun.me/search.php?wd=";
			break;
		case wangpansou:
			stringUrl = "http://www.wangpansou.cn/s.php?q=";
			break;
		case panyisou:
			stringUrl = "http://panyisou.com/?question/search/3/";
			break;
		case yisou:
			stringUrl = "http://www.yiso.me/search.php?wd=";
			break;
		case panduoduo:
			stringUrl = "http://www.panduoduo.net/s/name/";
			break;
		case panzz:
			stringUrl = "http://www.panzz.com/s/?kw=";
			break;
		case _360swp:
			stringUrl = "http://www.360swp.com/s.php?q=";
			break;
		}
		// 生成URL
		try {
			switch (searchEngineSelection) {
			default:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "UTF-8");
				break;
			case _39jiankang:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "GBK");
				break;
			case cllj:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "UTF-8")
						+ "/1.html";
				break;
			case yihaodian:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "UTF-8")
						+ "#page=1&sort=2";
				break;
			case suningyigou:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "UTF-8")
						+ "/cityId=9264";
				break;
			case googleplus:
				stringUrl = stringUrl + URLEncoder.encode(keyword, "UTF-8")
						+ "/posts";
				break;
            case libazhannei:
                stringUrl = stringUrl + URLEncoder.encode("site:liba.com "+keyword, "UTF-8");
                break;
            case kuandaishanzhannei:
                stringUrl = stringUrl + URLEncoder.encode("site:kdslife.com "+keyword, "UTF-8");
                break;
            case qijiazhannei:
                stringUrl = stringUrl + URLEncoder.encode("site:jia.com "+keyword, "UTF-8");
                break;
			}
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("Broken VM does not support UTF-8");
		}
		return stringUrl;
	}

	// 打开浏览器浏览单个搜索结果网页
	private void browseASearchResults(
			SearchEngineEnum searchEngineSelection, String keyword) {
		Browser browser=new Browser();
		String stringUrl;
		stringUrl = getStringUrl(searchEngineSelection, keyword);
		browser.openUrl(stringUrl);
	}

	// 打开浏览器浏览搜索结果网页
	public void browseSearchResults(
			SearchEngineEnum searchEngineSelection, String keyword) {

		Browser browser=new Browser();
		switch (searchEngineSelection) {
		default:
			browser.openUrl("");
			browseASearchResults(searchEngineSelection, keyword);
			break;
		case web:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.google, keyword);
			browseASearchResults(SearchEngineEnum.bing, keyword);
			// BrowseASearchResults(SearchEngineEnum.BingCN, Keyword);
			browseASearchResults(SearchEngineEnum.baidu, keyword);
			browseASearchResults(SearchEngineEnum.haosou, keyword);
			break;
		case wenda:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.bingqa, keyword);
			browseASearchResults(SearchEngineEnum.baiduzhidao, keyword);
			browseASearchResults(SearchEngineEnum.baidujingyan, keyword);
			browseASearchResults(SearchEngineEnum.zhihu, keyword);
			browseASearchResults(SearchEngineEnum.tianyawenda, keyword);
			browseASearchResults(SearchEngineEnum.haosouwenda, keyword);
			browseASearchResults(SearchEngineEnum.sogouwenwen, keyword);
			browseASearchResults(SearchEngineEnum.guokewenda, keyword);
			break;

		case image:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.googleimage, keyword);
			browseASearchResults(SearchEngineEnum.bingimage, keyword);
			// BrowseASearchResults(SearchEngineEnum.BingCN, Keyword);
			browseASearchResults(SearchEngineEnum.baiduimage, keyword);
			browseASearchResults(SearchEngineEnum.haosouimage, keyword);
			break;
		case scholar:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.googlescholar, keyword);
			browseASearchResults(SearchEngineEnum.bingacademic, keyword);
			browseASearchResults(SearchEngineEnum.baiduxueshu, keyword);
			break;

		case baike:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.wikipedia, keyword);
			browseASearchResults(SearchEngineEnum.bingwiki, keyword);
			browseASearchResults(SearchEngineEnum.baidubaike, keyword);
			browseASearchResults(SearchEngineEnum.hudongbaike, keyword);
			browseASearchResults(SearchEngineEnum.haosoubaike, keyword);
			break;

		case social:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.twitter, keyword);
			browseASearchResults(SearchEngineEnum.weibo, keyword);
			browseASearchResults(SearchEngineEnum.googleplus, keyword);
			break;

		case shopping:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.smzdm, keyword);
			browseASearchResults(SearchEngineEnum.etao, keyword);
			browseASearchResults(SearchEngineEnum.taobao, keyword);
			browseASearchResults(SearchEngineEnum.jingdong, keyword);
			browseASearchResults(SearchEngineEnum.yihaodian, keyword);
			browseASearchResults(SearchEngineEnum.dangdang, keyword);
			browseASearchResults(SearchEngineEnum.amazoncn, keyword);
			browseASearchResults(SearchEngineEnum.suningyigou, keyword);
			browseASearchResults(SearchEngineEnum.guomeizaixian, keyword);
			browseASearchResults(SearchEngineEnum.weipinhui, keyword);
			browseASearchResults(SearchEngineEnum.lefeng, keyword);
			browseASearchResults(SearchEngineEnum.amazon, keyword);
			break;
		case download:
			browser.openUrl("");
			//BrowseASearchResults(SearchEngineEnum.panzz, Keyword);
			browseASearchResults(SearchEngineEnum.wangpansou, keyword);
			browseASearchResults(SearchEngineEnum.baiduyun, keyword);
			browseASearchResults(SearchEngineEnum.yisou, keyword);
			browseASearchResults(SearchEngineEnum.cllj, keyword);
			// BrowseASearchResults(SearchEngineEnum.panduoduo, Keyword);
			// BrowseASearchResults(SearchEngineEnum.360swp, Keyword);
			// BrowseASearchResults(SearchEngineEnum.panyisou, Keyword);
			// BrowseASearchResults(SearchEngineEnum.PanSou, Keyword);
			break;

		case luntan:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.libazhannei, keyword);
			browseASearchResults(SearchEngineEnum.kuandaishanzhannei, keyword);
			browseASearchResults(SearchEngineEnum.qijiazhannei, keyword);
			browseASearchResults(SearchEngineEnum.liba, keyword);
			browseASearchResults(SearchEngineEnum.kuandaishan, keyword);
			browseASearchResults(SearchEngineEnum.qijia, keyword);
			break;

		case koubei:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.dianping, keyword);
			break;

		case lvyou:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum.mafengwo, keyword);
			break;

		case jiankang:
			browser.openUrl("");
			browseASearchResults(SearchEngineEnum._39jiankang, keyword);
			break;

		}

	}

	public void browseSearchResults(String searchEngineSelection,
			String keyword) {
		browseSearchResults(SearchEngineEnum.get(searchEngineSelection),
				keyword);
	}
}
