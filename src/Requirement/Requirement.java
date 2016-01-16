package Requirement;
public class Requirement {

	protected String requirement_index;
	protected String requirement_content;
	protected boolean if_be_satisfied;

	public Requirement(String requirement_index, String requirement_content,
			boolean if_be_satisfied) {
		super();
		this.requirement_index = requirement_index;
		this.requirement_content = requirement_content;
		this.if_be_satisfied = if_be_satisfied;
	}

	public String getRequirement_index() {
		return requirement_index;
	}

	public void setRequirement_index(String requirement_index) {
		this.requirement_index = requirement_index;
	}

	public String getRequirement_content() {
		return requirement_content;
	}

	public void setRequirement_content(String requirement_content) {
		this.requirement_content = requirement_content;
	}

	public boolean isIf_be_satisfied() {
		return if_be_satisfied;
	}

	public void setIf_be_satisfied(boolean if_be_satisfied) {
		this.if_be_satisfied = if_be_satisfied;
	}

	public void PrintRequirement() {
		String string_to_print = requirement_index + "\t" + requirement_content;
		if(if_be_satisfied)string_to_print+="\t 已满足";
		else string_to_print+="\t 未满足";
		System.out.println(string_to_print);
	}

}
