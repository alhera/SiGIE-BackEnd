package cr.ac.ucr.ie.sigie.domain;

public class UniversityBranch {
	private int branchId;
	private String name;

	public UniversityBranch() {
	}

	public UniversityBranch(int branchId, String name) {
		this.branchId = branchId;
		this.name = name;
	}

	public int getBranchId() { 
		return branchId;
	}//*

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UniversityBranch [branchId=" + branchId + ", name=" + name + "]";
	}

}

