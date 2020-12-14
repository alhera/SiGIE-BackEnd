package cr.ac.ucr.ie.sigie.domain;

import java.util.Date;

public class Internship {

	private int internshipId;
	private Date startDate;
	private Date endDate;
	private String projectDescription;
	private String studentName;
	private String studentLastName;
	private String studentCarnet;
	private UniversityBranch universityBranch;
	private InternshipOrganization organization;
	private Professor professor;

	public Internship() {
		this.universityBranch = new UniversityBranch();
		this.organization = new InternshipOrganization();
		this.professor = new Professor();
	}

	public Internship(int internshipId, Date startDate, Date endDate, String projectDescription, String studentName,
			String studentLastName, String studentCarnet, UniversityBranch universityBranch,
			InternshipOrganization organization, Professor professor) {
		this.internshipId = internshipId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectDescription = projectDescription;
		this.studentName = studentName;
		this.studentLastName = studentLastName;
		this.studentCarnet = studentCarnet;
		this.universityBranch = universityBranch;
		this.organization = organization;
		this.professor = professor;
	}

	public int getInternshipId() {
		return internshipId;
	}

	public void setInternshipId(int internshipId) {
		this.internshipId = internshipId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentCarnet() {
		return studentCarnet;
	}

	public void setStudentCarnet(String studentCarnet) {
		this.studentCarnet = studentCarnet;
	}

	public UniversityBranch getUniversityBranch() {
		return universityBranch;
	}

	public void setUniversityBranch(UniversityBranch universityBranch) {
		this.universityBranch = universityBranch;
	}

	public InternshipOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(InternshipOrganization organization) {
		this.organization = organization;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
