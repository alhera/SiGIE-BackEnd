package cr.ac.ucr.ie.sigie.domain;

public class ProfessorBranchSituation {

	private boolean active;
	private UniversityBranch branch;

	public ProfessorBranchSituation() {
		super();
		this.branch=new UniversityBranch();
	}

	public ProfessorBranchSituation(boolean active, UniversityBranch branch) {
		super();
		this.active = active;
		this.branch = branch;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UniversityBranch getBranch() {
		return branch;
	}

	public void setBranch(UniversityBranch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "ProfessorBranchSituation [active=" + active + ", branch=" + branch + "]";
	}

}
