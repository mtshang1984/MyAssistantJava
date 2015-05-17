
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

	//�о�����
	public void AnalysisRequirement(){
		LifeRequirement();
		StartupRequirement();
	}
	
	public void LifeRequirement(){
		requirement_list.addRequirement(new Requirement("1","���Ϻõ�����",false));
		DecorationRequirement();
		EntertainmentRequirement();
		FosterKid();
		Shopping();
	}

	public void DecorationRequirement(){

		requirement_list.addRequirement(new Requirement("1.1","���·�װ�޺�",false));
		requirement_list.addRequirement(new Requirement("1.1.1","ѡ��õ�װ�޹�˾",false));
		requirement_list.addRequirement(new Requirement("1.1.1.1","�ҳ��������ʦ�Ĵ�����",false));
		requirement_list.addRequirement(new Requirement("1.1.1","���պ�װ��Ԥ��",false));
	}

	public void EntertainmentRequirement(){

		requirement_list.addRequirement(new Requirement("1.2","����㹻������",false));
		requirement_list.addRequirement(new Requirement("1.2.1","�Ķ������������",false));
		requirement_list.addRequirement(new Requirement("1.2.2","��һЩ�ÿ�������",false));
	}

	public void FosterKid(){

		requirement_list.addRequirement(new Requirement("1.3","����һ���Ҹ���С��",false));
	}

	public void Shopping(){

		requirement_list.addRequirement(new Requirement("1.4","׼������������Ķ���",false));
		requirement_list.addRequirement(new Requirement("1.4.1","������Ѱ�Һ��ʵ��·�",false));
		requirement_list.addRequirement(new Requirement("1.4.2","��ͥ����Ʒ��ʱ�ɹ�",false));
		requirement_list.addRequirement(new Requirement("1.4.2.1","���������������ĸ�",false));
		requirement_list.addRequirement(new Requirement("1.4.2.1.1","���ĸ˾������Ͻ���ʣ����佹������ʱ�䳤������",false));
	}
	
	public void StartupRequirement(){
		requirement_list.addRequirement(new Requirement("2","�����Լ�����ҵ",false));
		SoftwareDevelopmentRequirement();
		DroneDevelopmentRequirement();
	}
	
	public  void SoftwareDevelopmentRequirement(){
		requirement_list.addRequirement(new Requirement("2.1","����һ���г���ӭ�����",false));
		requirement_list.addRequirement(new Requirement("2.1.1","�������ʹ�����������͹�����",false));
		requirement_list.addRequirement(new Requirement("2.1.1.1","�������ʵ����Ƶ��Զ���",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2","�������ʵ�ּ�ͥ������Զ���",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.1","�������ʵ���Զ��ĳ���",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.2","�������ʵ���Զ����Ʊ",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.3","�������ʵ���Զ������ճ���Ʒ",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.4","�������ʵ�ָ���Ȥ�����ŵ��Զ�����",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.5","�������ʵ���������Զ�����",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.6","�������ʵ�ֺ���Լ�������Զ�ƥ��",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.7","�������ʵ�ֹ���������Զ�Ԥ��ͳ���",false));
		requirement_list.addRequirement(new Requirement("2.1.1.2.7","�������ʵ������֮����Ϣ�ķ���",false));
		requirement_list.addRequirement(new Requirement("2.1.1.3","�������ʵ����Ϣ���Զ�����",false));
	}
	
	public  void DroneDevelopmentRequirement(){
		requirement_list.addRequirement(new Requirement("2.2","����һ���г���ӭ�����˻�",false));
		requirement_list.addRequirement(new Requirement("2.2.1","��ɺõ����˻��������",false));
		requirement_list.addRequirement(new Requirement("2.2.2","��ɺõ����˻�����ϵͳ����",false));
		requirement_list.addRequirement(new Requirement("2.2.3","��ɺõ����˻���еϵͳ����",false));
		requirement_list.addRequirement(new Requirement("2.2.4","��ɺõ��г�Ӫ��",false));
	}


	public  void ShowRequirement(){
		requirement_list.PrintRequirement();;
	}

	
	//������������������
	//��������ʵ�ֹ���
	//��֤����
}
