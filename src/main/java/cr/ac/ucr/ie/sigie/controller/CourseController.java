package cr.ac.ucr.ie.sigie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.CourseBusiness;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.Emphasis;



@RestController
@RequestMapping(value="/api/course", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*") 
public class CourseController {
	@Autowired
	private CourseBusiness courseBusiness;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> create(@RequestBody  @Valid Course course, @Valid String studyPlanCourse){
		Course savedCourse = courseBusiness.createCourse(course, studyPlanCourse);
		return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/view/allCourses")
	public ResponseEntity<List<Course>> listAllCourses(){
		List<Course> courses = courseBusiness.findAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@GetMapping(path = "/view/byStudyPlanCode/{id}")
	public ResponseEntity<List<Course>> findCoursesByStudyPlanCode(@PathVariable("id") String studyPlanCode){
		List<Course> courses = courseBusiness.findCoursesByStudyPlanCode(studyPlanCode);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping(path = "/view/electiveByStudyPlanCode/{id}")
	public ResponseEntity<List<Course>> findElectiveCoursesByStudyPlanCode(@PathVariable("id") String studyPlanCode){
		List<Course> courses = courseBusiness.findElectiveCoursesByStudyPlanCode(studyPlanCode);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
}