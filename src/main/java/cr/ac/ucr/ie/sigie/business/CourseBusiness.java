package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.CourseData;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.Emphasis;

@Service
public class CourseBusiness {
	
	@Autowired
	private CourseData courseData;
	
	public Course createCourse(Course course, String studyPlanCourse) {
		return courseData.create(course, studyPlanCourse);
	}
	
	public List<Emphasis> findAllEmphasis (){
		return courseData.findAllEmphasis();
	}
	
	public List<Course> findAllCourses() {
		return courseData.findAllCourses();
	}
	
	public List<Course> findCoursesByStudyPlanCode(String studyPlanCode) {
		return courseData.findCoursesByStudyPlanCode(studyPlanCode);
	} 
	
	public List<Course> findElectiveCoursesByStudyPlanCode(String studyPlanCode) {
		return courseData.findElectiveCoursesByStudyPlanCode(studyPlanCode);
	}
}
