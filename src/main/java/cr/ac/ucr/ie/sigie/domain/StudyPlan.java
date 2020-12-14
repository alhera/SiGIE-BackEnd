package cr.ac.ucr.ie.sigie.domain;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyPlan {
	
	private String studyPlanCode;
	private String careerName;
	private int approvalYear;
	private File studyPlanDocument;
	private boolean active;
	private File ucrApprovalDocument;
	private String careerCode;
	private int levelsQuantity;
	private int durationInYears;
	private String careerPlanDescription;
	private String studyPlanDescription;
	private List<Area> areas;
	private List<Course> courses;
	private List<Emphasis> emphasiss;
	private Degree degree;
	
	public StudyPlan() {
		super();
		this.studyPlanCode = "";
		this.careerName = "";
		this.approvalYear = 0;
		this.levelsQuantity = 0;
		this.studyPlanDocument = new File("");
		this.active = false;
		this.ucrApprovalDocument = new File("");
		this.careerCode = "";
		this.durationInYears = 0;
		this.careerPlanDescription = "";
		this.studyPlanDescription = "";
		this.areas = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.emphasiss = new ArrayList<>();
		this.degree = new Degree();
	}
	
	public StudyPlan(String studyPlanCode, String careerName) {
		this.studyPlanCode = studyPlanCode;
		this.careerName = careerName;
		this.approvalYear = 0;
		this.studyPlanDocument = null;
		this.active = false;
		this.levelsQuantity = 0;
		this.ucrApprovalDocument = null;
		this.careerCode = "";
		this.durationInYears = 0;
		this.careerPlanDescription = "";
		this.studyPlanDescription = "";
		this.areas = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.emphasiss = new ArrayList<>();
		this.degree = new Degree();
	}
	
	public StudyPlan(String studyPlanCode, String careerName, int approvalYear, File studyPlanDocument, boolean active,
			File ucrApprovalDocument, String careerCode, int levelsQuantity, int durationInYears, String careerPlanDescription,
			String studyPlanDescription, List<Area> areas, List<Course> courses, List<Emphasis> emphasiss,
			Degree degree) {
		super();
		this.studyPlanCode = studyPlanCode;
		this.careerName = careerName;
		this.approvalYear = approvalYear;
		this.studyPlanDocument = studyPlanDocument;
		this.active = active;
		this.levelsQuantity = levelsQuantity;
		this.ucrApprovalDocument = ucrApprovalDocument;
		this.careerCode = careerCode;
		this.durationInYears = durationInYears;
		this.careerPlanDescription = careerPlanDescription;
		this.studyPlanDescription = studyPlanDescription;
		this.areas = areas;
		this.courses = courses;
		this.emphasiss = emphasiss;
		this.degree = degree;
	}
	

	public String getStudyPlanCode() {
		return studyPlanCode;
	}

	public void setStudyPlanCode(String studyPlanCode) {
		this.studyPlanCode = studyPlanCode;
	}

	public String getCareerName() {
		return careerName;
	}

	public void setCareerName(String careerName) {
		this.careerName = careerName;
	}

	public int getApprovalYear() {
		return approvalYear;
	}

	public void setApprovalYear(int approvalYear) {
		this.approvalYear = approvalYear;
	}

	public File getStudyPlanDocument() {
		return studyPlanDocument;
	}

	public void setStudyPlanDocument(File studyPlanDocument) {
		this.studyPlanDocument = studyPlanDocument;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public int getLevelsQuantity() {
		return levelsQuantity;
	}

	public void setLevelsQuantity(int levelsQuantity) {
		this.levelsQuantity = levelsQuantity;
	}

	public File getUcrApprovalDocument() {
		return ucrApprovalDocument;
	}

	public void setUcrApprovalDocument(File ucrApprovalDocument) {
		this.ucrApprovalDocument = ucrApprovalDocument;
	}

	public String getCareerCode() {
		return careerCode;
	}

	public void setCareerCode(String careerCode) {
		this.careerCode = careerCode;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	public String getCareerPlanDescription() {
		return careerPlanDescription;
	}

	public void setCareerPlanDescription(String careerPlanDescription) {
		this.careerPlanDescription = careerPlanDescription;
	}

	public String getStudyPlanDescription() {
		return studyPlanDescription;
	}

	public void setStudyPlanDescription(String studyPlanDescription) {
		this.studyPlanDescription = studyPlanDescription;
	}


	public List<Area> getAreas() {
		return areas;
	}


	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	public List<Emphasis> getEmphasiss() {
		return emphasiss;
	}


	public void setEmphasiss(List<Emphasis> emphasiss) {
		this.emphasiss = emphasiss;
	}


	public Degree getDegree() {
		return degree;
	}


	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	//Se encarga de setear su misma instancia con los datos de un resultset
	//obtenido por un query a base de datos
	public void setStudyPlanResultSet(ResultSet rs) throws SQLException {
		this.careerName = rs.getString("career_name");
		this.approvalYear = rs.getInt("approval_year");
		
		this.active = rs.getInt("is_active") == 1;
		this.careerCode = rs.getString("career_code");
		this.levelsQuantity = rs.getInt("levels_quantity");
		this.studyPlanDescription = rs.getString("study_plan_description");
		
		//convierte el varbinary a un file
		//To be implemented
		//this.studyPlanDocument = new File(rs.getString("study_plan_document"));
		//this.ucrApprovalDocument = new File(rs.getString("study_plan_document"));
	}
	
	
}
