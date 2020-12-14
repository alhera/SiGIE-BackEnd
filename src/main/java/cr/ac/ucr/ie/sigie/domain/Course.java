package cr.ac.ucr.ie.sigie.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Course {

	private String courseId;
	private String name;
	private int credits;
	private int level;
	private boolean elective;
	private int hoursTheory;
	private int hoursPractice;
	private int hoursLab;
	private int hoursTheoryPractice;
	private List<CourseSyllabus> coursesSyllabus;
	private List<Course> requisites;
	private List<Course> corequisites;
	private List<Course> childrenElectiveCourses;
	private Area area;
	private List<Emphasis> emphasiss;

	
	public Course() {
		this.courseId = "";
		this.name = "";
		this.credits = 0;
		this.level = 0;
		this.elective = false;
		this.hoursTheory = 0;
		this.hoursPractice = 0;
		this.hoursLab = 0;
		this.hoursTheoryPractice = 0;
		this.coursesSyllabus = new ArrayList<>();
		this.requisites = new ArrayList<>();
		this.corequisites = new ArrayList<>();
		this.childrenElectiveCourses = new ArrayList<>();
		this.area = new Area();
		this.emphasiss = new ArrayList<>();
	}
	
	public Course(String courseId, String name, int credits, int level, boolean elective, int hoursTheory,
			int hoursPractice, int hoursLab, int hoursTheoryPractice, List<CourseSyllabus> coursesSyllabus,
			List<Course> requisites, List<Course> corequisites, List<Course> childrenElectiveCourses, Area area,
			List<Emphasis> emphasiss) {
		this.courseId = courseId;
		this.name = name;
		this.credits = credits;
		this.level = level;
		this.elective = elective;
		this.hoursTheory = hoursTheory;
		this.hoursPractice = hoursPractice;
		this.hoursLab = hoursLab;
		this.hoursTheoryPractice = hoursTheoryPractice;
		this.coursesSyllabus = coursesSyllabus;
		this.requisites = requisites;
		this.corequisites = corequisites;
		this.childrenElectiveCourses = childrenElectiveCourses;
		this.area = area;
		this.emphasiss = emphasiss;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isElective() {
		return elective;
	}

	public void setElective(boolean elective) {
		this.elective = elective;
	}

	public int getHoursTheory() {
		return hoursTheory;
	}

	public void setHoursTheory(int hoursTheory) {
		this.hoursTheory = hoursTheory;
	}

	public int getHoursPractice() {
		return hoursPractice;
	}

	public void setHoursPractice(int hoursPractice) {
		this.hoursPractice = hoursPractice;
	}

	public int getHoursLab() {
		return hoursLab;
	}

	public void setHoursLab(int hoursLab) {
		this.hoursLab = hoursLab;
	}

	public int getHoursTheoryPractice() {
		return hoursTheoryPractice;
	}

	public void setHoursTheoryPractice(int hoursTheoryPractice) {
		this.hoursTheoryPractice = hoursTheoryPractice;
	}

	public List<CourseSyllabus> getCoursesSyllabus() {
		return coursesSyllabus;
	}

	public void setCoursesSyllabus(List<CourseSyllabus> coursesSyllabus) {
		this.coursesSyllabus = coursesSyllabus;
	}

	public List<Course> getRequisites() {
		return requisites;
	}

	public void setRequisites(List<Course> requisites) {
		this.requisites = requisites;
	}

	public List<Course> getCorequisites() {
		return corequisites;
	}

	public void setCorequisites(List<Course> corequisites) {
		this.corequisites = corequisites;
	}

	public List<Course> getChildrenElectiveCourses() {
		return childrenElectiveCourses;
	}

	public void setChildrenElectiveCourses(List<Course> childrenElectiveCourses) {
		this.childrenElectiveCourses = childrenElectiveCourses;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Emphasis> getEmphasiss() {
		return emphasiss;
	}

	public void setEmphasiss(List<Emphasis> emphasiss) {
		this.emphasiss = emphasiss;
	}

	//Se encarga de setear su misma instancia con los datos de un resultset
	//obtenido por un query a base de datos
	public void setCourseResultSet(ResultSet rs) throws SQLException {
		this.courseId = rs.getString("c.course_id");
		this.name = rs.getString("c.name");
		this.level = rs.getInt("c.level");
		this.credits = rs.getInt("c.credits");
		this.elective = rs.getBoolean("c.elective");
		this.area.setAreaName(rs.getString("a.name"));
	}

	

}
