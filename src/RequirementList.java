import java.util.ArrayList;

public class RequirementList {

	protected ArrayList<Requirement> Requirement_List;

	public RequirementList() {
		super();
		Requirement_List = new ArrayList<Requirement>();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Requirement> getRequirement_List() {
		return Requirement_List;
	}

	public void setRequirement_List(ArrayList<Requirement> requirement_List) {
		Requirement_List = requirement_List;
	}

	public void addRequirement(Requirement requirement) {
		Requirement_List.add(requirement);
	}

	public void PrintRequirement() {
		for(int i = 0;i < Requirement_List.size(); i ++){
			   Requirement_List.get(i).PrintRequirement();

			}

	}
}
