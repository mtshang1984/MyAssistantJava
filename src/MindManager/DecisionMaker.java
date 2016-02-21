package MindManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DecisionMaker {

	/** 节点列表 */
	HashMap<String, Node> nodeList = new HashMap<String, Node>();

	/** 根节点 */
	Node root = null;

	public DecisionMaker() {
		// TODO Auto-generated constructor stub
	}

	public void addNewDecisionData() {

		addDecisionData("0", "做什么", "");
		addDecisionData("1", "拜年", "0");
		addDecisionData("1.1", "爸爸，三姑，大姐，二姐", "1");
		addDecisionData("2", "出去玩", "0");
		addDecisionData("3", "session是放在SshConnection类中，还是不放在其中", "0");
		addDecisionData("3.1", "如果session是放在SshConnection类中，如何处理存在多个session的情况", "3");
		addDecisionData("3.2", "如果session不放在SshConnection类中，如何提供根据Connection创建session的方法", "3");
		addDecisionData("3.2.1", "在SshConnection类中，提供openSession和closeSession的方法，返回值为Session类型的对象。由于只要有对象的引用存在，对象就不会被收回，返回对象是可行的方法", "3.1");


	}

	public void addDecisionData(String id, String text, String parentId) {
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

	public void createDecision() {

		for (Iterator it = nodeList.entrySet().iterator(); it.hasNext();) {
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == null || node.parentId.equals("")) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
	}

	public void showDecision() {
		ForMatJSONStr formatJSONString = new ForMatJSONStr();
		addNewDecisionData();
		createDecision();

		// 对多叉树进行横向排序
		root.sortChildren();

		// 输出有序的树形结构的JSON字符串
		System.out.println(formatJSONString.format(root.toString()));

	}

}
