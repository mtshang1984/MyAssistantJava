package Requirement;

public class RequirementsAnalysis {
	protected RequirementList requirementList;

	public RequirementList getRequirementlLst() {
		return requirementList;
	}

	public void setRequirementList(RequirementList requirementList) {
		this.requirementList = requirementList;
	}

	public RequirementsAnalysis() {
		super();
		requirementList=new RequirementList();

	}

	//列举需求
	public void analysisRequirement(){
		addLifeRequirement();
		addStartupRequirement();
	}
	
	public void addLifeRequirement(){
		requirementList.addRequirement(new Requirement("1","过上好的生活",false));
		addDecorationRequirement();
		addEntertainmentRequirement();
		addFosterKid();
		addShopping();
		addDocumentRequirement();
	}

	public void addDecorationRequirement(){

		requirementList.addRequirement(new Requirement("1.1","把新房装修好",false));
		requirementList.addRequirement(new Requirement("1.1.1","选择好的装修公司",false));
		requirementList.addRequirement(new Requirement("1.1.1.1","找出两个设计师的代表作",false));
		requirementList.addRequirement(new Requirement("1.1.1","把握好装修预算",false));
	}

	public void addEntertainmentRequirement(){

		requirementList.addRequirement(new Requirement("1.2","获得足够的娱乐",false));
		requirementList.addRequirement(new Requirement("1.2.1","阅读有意义的新闻",false));
		requirementList.addRequirement(new Requirement("1.2.2","看一些好看的美剧",false));
		requirementList.addRequirement(new Requirement("1.2.3","下载一些好看的电影",false));
	}

	public void addFosterKid(){

		requirementList.addRequirement(new Requirement("1.3","培养一个幸福的小孩",false));
		requirementList.addRequirement(new Requirement("1.3.1","搜索一下自然流产的原因",false));
		requirementList.addRequirement(new Requirement("1.3.2","搜索一下男女备孕",false));
	}

	public void addShopping(){

		requirementList.addRequirement(new Requirement("1.4","准备好生活所需的东西",false));
		requirementList.addRequirement(new Requirement("1.4.1","给老婆寻找合适的衣服",false));
		requirementList.addRequirement(new Requirement("1.4.2","家庭日用品及时采购",false));
		requirementList.addRequirement(new Requirement("1.4.2.1","给李玮妈妈买自拍杆",false));
		requirementList.addRequirement(new Requirement("1.4.2.1.1","自拍杆具有铝合金材质，带变焦，待机时间长，蓝牙",false));
	}

	public  void addDocumentRequirement(){
		requirementList.addRequirement(new Requirement("1.5","写论文和专利",false));
		requirementList.addRequirement(new Requirement("1.5.1","下载相关文献",false));
	}
	
	public void addStartupRequirement(){
		requirementList.addRequirement(new Requirement("2","开创自己的事业",false));
		addSoftwareDevelopmentRequirement();
		addDroneDevelopmentRequirement();
	}
	
	public  void addSoftwareDevelopmentRequirement(){
		requirementList.addRequirement(new Requirement("2.1","开发一块市场欢迎的软件",false));
		requirementList.addRequirement(new Requirement("2.1.1","软件可以使人类更好生活和工作的",false));
		requirementList.addRequirement(new Requirement("2.1.1.1","软件可以实现设计的自动化",false));
		requirementList.addRequirement(new Requirement("2.1.1.2","软件可以实现家庭生活的自动化",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.1","软件可以实现自动拍车牌",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.2","软件可以实现自动买火车票",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.3","软件可以实现自动购买日常用品",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.4","软件可以实现感兴趣的新闻的自动推送",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.5","软件可以实现天气的自动推送",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.6","软件可以实现合适约会对象的自动匹配",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.7","软件可以实现股市行情的自动预测和炒股",false));
		requirementList.addRequirement(new Requirement("2.1.1.2.7","软件可以实现熟人之间信息的分享",false));
		requirementList.addRequirement(new Requirement("2.1.1.3","软件可以实现信息的自动整合",false));
		requirementList.addRequirement(new Requirement("2.1.1.4","软件可以实现根据关键词显示相关信息链接",false));
		requirementList.addRequirement(new Requirement("2.1.1.5","软件界面较为友好",false));
		requirementList.addRequirement(new Requirement("2.1.1.5.1","action bar颜色",false));
		requirementList.addRequirement(new Requirement("2.1.1.5.1","修改搜索按钮图标",false));
		requirementList.addRequirement(new Requirement("2.1.1.6","避免侵权行为",false));
	}
	
	public  void addDroneDevelopmentRequirement(){
		requirementList.addRequirement(new Requirement("2.2","开发一型市场欢迎的无人机",false));
		requirementList.addRequirement(new Requirement("2.2.1","完成好的无人机总体设计",false));
		requirementList.addRequirement(new Requirement("2.2.2","完成好的无人机控制系统研制",false));
		requirementList.addRequirement(new Requirement("2.2.3","完成好的无人机机械系统研制",false));
		requirementList.addRequirement(new Requirement("2.2.4","完成好的市场营销",false));
	}



	public  void showRequirement(){
		requirementList.printRequirement();;
	}

	
	//采用数组来描述需求
	//链接需求实现过程
	//验证需求
}
