
public class RequirementsAnalysis {
	protected RequirementList requirement_list;

	public RequirementList getRequirement_list() {
		return requirement_list;
	}

	public void setRequirement_list(RequirementList requirement_list) {
		this.requirement_list = requirement_list;
	}

	public RequirementsAnalysis() {
		super();
		requirement_list=new RequirementList();

	}

	//列举需求
	public void AnalysisRequirement(){
		LifeRequirement();
		StartupRequirement();
	}
	
	public void LifeRequirement(){
		requirement_list.addRequirement(new Requirement("1","过上好的生活",false));
		DecorationRequirement();
		EntertainmentRequirement();
		FosterKid();
		Shopping();
	}

	public void DecorationRequirement(){

		requirement_list.addRequirement(new Requirement("1.1","把新房装修好",false));
		requirement_list.addRequirement(new Requirement("1.1.1","选择好的装修公司",false));
		requirement_list.addRequirement(new Requirement("1.1.1.1","找出两个设计师的代表作",false));
		requirement_list.addRequirement(new Requirement("1.1.1","把握好装修预算",false));
	}

	public void EntertainmentRequirement(){

		requirement_list.addRequirement(new Requirement("1.2","获得足够的娱乐",false));
		requirement_list.addRequirement(new Requirement("1.2.1","阅读有意义的新闻",false));
		requirement_list.addRequirement(new Requirement("1.2.2","看一些好看的美剧",false));
	}

	public void FosterKid(){

		requirement_list.addRequirement(new Requirement("1.3","培养一个幸福的小孩",false));
	}

	public void Shopping(){

		requirement_list.addRequirement(new Requirement("1.4","准备好生活所需的东西",false));
		requirement_list.addRequirement(new Requirement("1.4.1","给老婆寻找合适的衣服",false));
		requirement_list.addRequirement(new Requirement("1.4.2","家庭日用品及时采购",false));
		requirement_list.addRequirement(new Requirement("1.4.2.1","给李玮妈妈买自拍杆",false));
		requirement_list.addRequirement(new Requirement("1.4.2.1.1","自拍杆具有铝合金材质，带变焦，待机时间长，蓝牙",false));
	}
	
	public void StartupRequirement(){
		requirement_list.addRequirement(new Requirement("2","开创自己的事业",false));
		SoftwareDevelopmentRequirement();
		DroneDevelopmentRequirement();
	}
	
	public  void SoftwareDevelopmentRequirement(){
		requirement_list.addRequirement(new Requirement("2.1","开发一块市场欢迎的软件",false));
		requirement_list.addRequirement(new Requirement("2.1.1","软件可以使人类更好生活和工作的",false));
		requirement_list.addRequirement(new Requirement("2.1.1.1","软件可以实现设计的自动化",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2","软件可以实现家庭生活的自动化",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.1","软件可以实现自动拍车牌",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.2","软件可以实现自动买火车票",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.3","软件可以实现自动购买日常用品",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.4","软件可以实现感兴趣的新闻的自动推送",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.5","软件可以实现天气的自动推送",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.6","软件可以实现合适约会对象的自动匹配",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.7","软件可以实现股市行情的自动预测和炒股",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.7","软件可以实现熟人之间信息的分享",false));
		requirement_list.addRequirement(new Requirement("2.1.1.3","软件可以实现信息的自动整合",false));
	}
	
	public  void DroneDevelopmentRequirement(){
		requirement_list.addRequirement(new Requirement("2.2","开发一型市场欢迎的无人机",false));
		requirement_list.addRequirement(new Requirement("2.2.1","完成好的无人机总体设计",false));
		requirement_list.addRequirement(new Requirement("2.2.2","完成好的无人机控制系统研制",false));
		requirement_list.addRequirement(new Requirement("2.2.3","完成好的无人机机械系统研制",false));
		requirement_list.addRequirement(new Requirement("2.2.4","完成好的市场营销",false));
	}


	public  void ShowRequirement(){
		requirement_list.PrintRequirement();;
	}

	
	//采用数组来描述需求
	//链接需求实现过程
	//验证需求
}
