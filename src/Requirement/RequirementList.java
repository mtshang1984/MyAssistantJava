package Requirement;
import java.util.ArrayList;

public class RequirementList {

	protected ArrayList<Requirement> requirementList;

	public RequirementList() {
		super();
		requirementList = new ArrayList<Requirement>();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Requirement> getRequirementList() {
		return requirementList;
	}

	public void setRequirementList(ArrayList<Requirement> requirementList) {
		this.requirementList = requirementList;
	}

	public void addRequirement(Requirement requirement) {
		requirementList.add(requirement);
	}

	public void printRequirement() {
		for(int i = 0;i < requirementList.size(); i ++){
			   requirementList.get(i).printRequirement();

			}

	}
}
