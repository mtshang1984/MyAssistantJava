package Requirement;
public class Requirement {

	protected String requirementIndex;
	protected String requirementContent;
	protected boolean ifBeSatisfied;

	public Requirement(String requirementIndex, String requirementContent,
			boolean ifBeSatisfied) {
		super();
		this.requirementIndex = requirementIndex;
		this.requirementContent = requirementContent;
		this.ifBeSatisfied = ifBeSatisfied;
	}

	public String getRequirementIndex() {
		return requirementIndex;
	}

	public void setRequirementIndex(String requirementIndex) {
		this.requirementIndex = requirementIndex;
	}

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public boolean isIfBeSatisfied() {
		return ifBeSatisfied;
	}

	public void setIf_be_satisfied(boolean ifBeSatisfied) {
		this.ifBeSatisfied = ifBeSatisfied;
	}

	public void printRequirement() {
		String string_to_print = requirementIndex + "\t" + requirementContent;
		if(ifBeSatisfied)string_to_print+="\t 已满足";
		else string_to_print+="\t 未满足";
		System.out.println(string_to_print);
	}

}
