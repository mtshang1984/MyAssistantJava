package CommonEnum;

import java.util.HashMap;
import java.util.Map;

public enum SearchEngineEnum {

	web, google, bing, bingcn, baidu, haosou,

	wenda, bingqa, baiduzhidao, baidujingyan, zhihu, tianyawenda, haosouwenda, sogouwenwen, guokewenda,

	image,googleimage,bingimage,bingcnimage,baiduimage,haosouimage,
        
	scholar, googlescholar, bingacademic, baiduxueshu,

	baike, wikipedia, bingwiki, baidubaike, hudongbaike, haosoubaike,

	social, twitter, weibo, googleplus, 

	shopping, etao, smzdm, taobao, jingdong, yihaodian, dangdang, amazon, amazoncn, suningyigou, guomeizaixian, weipinhui, lefeng,

	download, cllj, pansou, baiduyun, wangpansou, panyisou, yisou, panduoduo, panzz, _360swp,

	luntan, libazhannei,kuandaishanzhannei,qijiazhannei,kuandaishan,liba, qijia,

	koubei, dianping,

	lvyou, mafengwo,

	jiankang, _39jiankang;

	public static final class BootstrapSingleton {
		// Reverse-lookup map for getting a day from an abbreviation
		public static final Map<String, SearchEngineEnum> SearchEngineMap = new HashMap<String, SearchEngineEnum>();
	}

	SearchEngineEnum() {
		BootstrapSingleton.SearchEngineMap.put(toString(), this);
	}
	public static SearchEngineEnum get(String search_engine_string) {
		SearchEngineEnum result;
		result=BootstrapSingleton.SearchEngineMap.get(search_engine_string);
		if(result!=null)        return result;
		else  return web;
    }
}
