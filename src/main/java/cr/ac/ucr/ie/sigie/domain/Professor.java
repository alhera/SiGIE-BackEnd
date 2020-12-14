package cr.ac.ucr.ie.sigie.domain;

import java.util.LinkedList;
import java.util.List;

public class Professor {

	private int professorId;
	private String name;
	private String lastName;
	private String institutionalMail;
	private String academicDegreeAcronym;
	private List<ProfessorBranchSituation> branchSituations;
	private List<AcademicDegree> academicDegrees;
	private List<TopicOfInterest> topicsOfInterest;

	public Professor() {
		super();
	}

	public Professor(String name, String lastName, String academicDegreeAcronym, String institutionalMail) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.institutionalMail = institutionalMail;
		this.academicDegreeAcronym = academicDegreeAcronym;
		this.branchSituations = new LinkedList<ProfessorBranchSituation>();
		this.academicDegrees = new LinkedList<AcademicDegree>();
		this.topicsOfInterest = new LinkedList<TopicOfInterest>();
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInstitutionalMail() {
		return institutionalMail;
	}

	public void setInstitutionalMail(String institutionalMail) {
		this.institutionalMail = institutionalMail;
	}

	public String getAcademicDegreeAcronym() {
		return academicDegreeAcronym;
	}

	public void setAcademicDegreeAcronym(String academicDegreeAcronym) {
		this.academicDegreeAcronym = academicDegreeAcronym;
	}

	public List<ProfessorBranchSituation> getBranchSituations() {
		return branchSituations;
	}

	public void setBranchSituations(List<ProfessorBranchSituation> branchSituations) {
		this.branchSituations = branchSituations;
	}

	public List<TopicOfInterest> getTopicsOfInterest() {
		return topicsOfInterest;
	}

	public void setTopicsOfInterest(List<TopicOfInterest> topicsOfInterest) {
		this.topicsOfInterest = topicsOfInterest;
	}

	public List<AcademicDegree> getAcademicDegrees() {
		return academicDegrees;
	}

	public void setAcademicDegrees(List<AcademicDegree> academicDegrees) {
		this.academicDegrees = academicDegrees;
	}


	@Override
	public String toString() {
		return "Professor [professorId=" + professorId + ", name=" + name + ", lastName=" + lastName
				+ ", institutionalMail=" + institutionalMail + ", academicDegreeAcronym=" + academicDegreeAcronym
				+ ", branchSituations=" + branchSituations + ", academicDegrees=" + academicDegrees
				+ ", topicsOfInterest=" + "]";
	}

}
