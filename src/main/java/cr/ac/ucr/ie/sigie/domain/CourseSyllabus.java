package cr.ac.ucr.ie.sigie.domain;

import java.io.File;
import java.util.Date;


public class CourseSyllabus {

	private int courseSyllabusId;
	private int version;
	private boolean active;
	private File courseBaseSyllabus;
	private Date approbationDate;
	
	public CourseSyllabus() {
		this.courseSyllabusId = 0;
		this.version = 0;
		this.active = false;
		this.courseBaseSyllabus = new File("");
		this.approbationDate = new Date();
	}
	

	
	public CourseSyllabus(int courseSyllabusId, int version, boolean active, File courseBaseSyllabus,
			Date approbationDate) {
		this.courseSyllabusId = courseSyllabusId;
		this.version = version;
		this.active = active;
		this.courseBaseSyllabus = courseBaseSyllabus;
		this.approbationDate = approbationDate;
	}


	public int getCourseSyllabusId() {
		return courseSyllabusId;
	}

	public void setCourseSyllabusId(int courseSyllabusId) {
		this.courseSyllabusId = courseSyllabusId;
	}

	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public File getCourseBaseSyllabus() {
		return courseBaseSyllabus;
	}
	
	public void setCourseBaseSyllabus(File courseBaseSyllabus) {
		this.courseBaseSyllabus = courseBaseSyllabus;
	}
	
	public Date getApprobationDate() {
		return approbationDate;
	}
	
	public void setApprobationDate(Date approbationDate) {
		this.approbationDate = approbationDate;
	}

}
