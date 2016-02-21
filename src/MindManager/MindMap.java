package MindManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MindMap { 
	/**节点列表 */
	HashMap<String, Node> nodeList = new HashMap<String, Node>();

	/**根节点 */
	Node root = null;
 
	public void addNewMindMapData() {

		addMindData("0", "201602", "");
		addMindData("1", "工作", "0");
		addMindData("1.1", "培训材料制作", "1"); 
		addMindData("2", "创业", "0");
		addMindData("2.1", "自动配置一台网站", "2");
		addMindData("2.1.1", "建立网络云盘", "2.1");
		addMindData("2.1.1.1", "配置翻墙", "2.1.1");
		addMindData("2.1.1.2", "查看建立网络云盘的方法", "2.1.1");
		addMindData("2.1.1.3", "执行建立owncloud云盘的命令", "2.1.1");
		addMindData("3", "生活", "0");
		addMindData("3.1", "生育准备", "3");
		addMindData("3.1.1", "健身", "3.1");
		addMindData("3.2", "父母健康", "3");
		addMindData("3.2.1", "给妈看病买药", "3.1");
		addMindData("3.3", "装修", "3");
		addMindData("3.3.1", "墙纸", "3.3");
		addMindData("3.3.2", "窗帘", "3.3");
		addMindData("3.4", "娱乐", "3");
		addMindData("3.4.3", "刷机", "3.4");
		addMindData("3.4.4", "下载新电影", "3.4");
		addMindData("3.4.5", "下载新电影", "3.4");
		addMindData("3.5", "购物", "3");
		addMindData("3.5.3", "买酒", "3.5");
		
		
	}

 
	public void addMindData(String id, String text, String parentId) {
		/*
		 * HashMap<String, String> mind_data = new HashMap<String, String>();
		 * mind_data.put("id", id); mind_data.put("text", text);
		 * mind_data.put("parentId", parentId); dataList.add(mind_data);
		 */
		Node node = new Node();
		node.id = id;
		node.text = text;
		node.parentId = parentId;
		nodeList.put(node.id, node);

	}

	public void createMindmap() {
		
		for (Iterator it = nodeList.entrySet().iterator(); it.hasNext();) {
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == null || node.parentId.equals("")) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
	}

	public void showMindmap() {
		ForMatJSONStr formatJSONString=new ForMatJSONStr();
		//addNewMindMapData();
		createMindmap();

		// 对多叉树进行横向排序
		root.sortChildren();

		// 输出有序的树形结构的JSON字符串
		System.out.println(formatJSONString.format(root.toString()));

	}


}
