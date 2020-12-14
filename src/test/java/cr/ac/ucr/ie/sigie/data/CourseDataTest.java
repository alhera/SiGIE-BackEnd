package cr.ac.ucr.ie.sigie.data;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.business.CourseBusiness;
import cr.ac.ucr.ie.sigie.domain.Area;
import cr.ac.ucr.ie.sigie.domain.Course;

@SpringBootTest
@Transactional
class CourseDataTest {

	@Autowired
	CourseBusiness courseBusiness;
	
	@Autowired
	CourseData courseData;

	@Before
	@Test
	void SaveNewCourseTest() {

		Course course = fillObjects();

		String studyPlanCode = "ie2020ucr";
		try {
			courseBusiness.createCourse(course, studyPlanCode);
		}catch (Exception e) {
			
		}
		List<Course> courses = courseBusiness.findCoursesByStudyPlanCode(studyPlanCode);
		List<Course> courses2 = courseBusiness.findElectiveCoursesByStudyPlanCode(studyPlanCode);
		List<Course> courses3 = courseBusiness.findAllCourses();
	}

	@Test
	void findCoursesBasicDataByStudyPlanId() {
		ArrayList<Course> coursesList = (ArrayList<Course>) courseData.findCoursesBasicDataByStudyPlanId("ie2020ucr");
		
		Course course = coursesList.get(0);
		
		assertNotNull(coursesList);
		assertTrue(coursesList.size() > 0);
		assertNotNull(course);
		assertNotNull(course.getArea());
		assertNotEquals(course.getCourseId(),"");
		
	}
	
	private Course fillObjects() {
		// ----------------------------Area--------------------------
		// Fill Area
		Area area = new Area();
		area.setAreaId("123");
		area.setAreaName("Area1");

		// ---------------------------Requisite Course 1--------------
		// Fill Requisite Course
		Course requisiteCourse1 = new Course();
		requisiteCourse1.setArea(area);
		requisiteCourse1.setCourseId("R116");
		requisiteCourse1.setName("Requisite 1");
		requisiteCourse1.setLevel(1);
		requisiteCourse1.setCredits(4);
		requisiteCourse1.setHoursTheory(2);
		requisiteCourse1.setHoursPractice(2);
		requisiteCourse1.setHoursLab(0);
		requisiteCourse1.setHoursTheoryPractice(0);

		// ---------------------------Requisite Course 2--------------
		// Fill Requisite Course
		Course requisiteCourse2 = new Course();
		requisiteCourse2.setArea(area);
		requisiteCourse2.setCourseId("R226");
		requisiteCourse2.setName("Requisite 2");
		requisiteCourse2.setLevel(1);
		requisiteCourse2.setCredits(4);
		requisiteCourse2.setHoursTheory(2);
		requisiteCourse2.setHoursPractice(0);
		requisiteCourse2.setHoursLab(0);
		requisiteCourse2.setHoursTheoryPractice(0);

		// Fill list with Requisite Courses
		List<Course> requisiteCourses = new ArrayList();
		requisiteCourses.add(requisiteCourse1);
		requisiteCourses.add(requisiteCourse2);

		// ---------------------------Corequisite Course 1--------------
		// Fill Corequisite Course
		Course corequisiteCourse1 = new Course();
		corequisiteCourse1.setArea(area);
		corequisiteCourse1.setCourseId("C116");
		corequisiteCourse1.setName("Corequisite 1");
		corequisiteCourse1.setLevel(2);
		corequisiteCourse1.setCredits(2);
		corequisiteCourse1.setHoursTheory(4);
		corequisiteCourse1.setHoursPractice(0);
		corequisiteCourse1.setHoursLab(0);
		corequisiteCourse1.setHoursTheoryPractice(0);

		// ---------------------------Corequisite Course 2--------------
		// Fill Corequisite Course
		Course corequisiteCourse2 = new Course();
		corequisiteCourse2.setArea(area);
		corequisiteCourse2.setCourseId("C226");
		corequisiteCourse2.setName("Corequisite 2");
		corequisiteCourse2.setLevel(1);
		corequisiteCourse2.setCredits(4);
		corequisiteCourse2.setHoursTheory(2);
		corequisiteCourse2.setHoursPractice(0);
		corequisiteCourse2.setHoursLab(0);
		requisiteCourse2.setHoursTheoryPractice(0);

		// Fill list with Requisite Courses
		List<Course> corequisiteCourses = new ArrayList();
		corequisiteCourses.add(corequisiteCourse1);
		corequisiteCourses.add(corequisiteCourse2);

		// ---------------------------Elective Course --------------
		// Fill Elective Course
		Course electiveCourse1 = new Course();
		electiveCourse1.setArea(area);
		electiveCourse1.setCourseId("E116");
		electiveCourse1.setName("Elective Course 1");
		electiveCourse1.setLevel(1);
		electiveCourse1.setCredits(2);
		electiveCourse1.setHoursTheory(0);
		electiveCourse1.setHoursPractice(2);
		electiveCourse1.setHoursLab(0);
		electiveCourse1.setHoursTheoryPractice(0);

		// Course electiveCourse2 = new Course();
		// electiveCourse2.setArea(area);
		// electiveCourse2.setCourseId("E222");
		// electiveCourse2.setName("Elective Course 2");
		// electiveCourse2.setLevel(1);
		// electiveCourse2.setCredits(2);
		// electiveCourse2.setHoursTheory(0);
		// electiveCourse2.setHoursPractice(2);
		// electiveCourse2.setHoursLab(0);
		// electiveCourse2.setHoursTheoryPractice(0);

		// Fill list with Elective Courses
		List<Course> electiveCourses = new ArrayList();
		electiveCourses.add(electiveCourse1);

		Course parentCourse = new Course();
		parentCourse.setArea(area);
		parentCourse.setCourseId("IF5100");
		parentCourse.setName("Redes en los negocios");
		parentCourse.setLevel(1);
		parentCourse.setCredits(1);
		parentCourse.setElective(true);
		parentCourse.setHoursTheory(1);
		parentCourse.setHoursPractice(1);
		parentCourse.setHoursLab(1);
		parentCourse.setHoursTheoryPractice(1);
		parentCourse.setRequisites(requisiteCourses);
		parentCourse.setCorequisites(corequisiteCourses);
		parentCourse.setChildrenElectiveCourses(electiveCourses);

		return parentCourse;
	}

}
