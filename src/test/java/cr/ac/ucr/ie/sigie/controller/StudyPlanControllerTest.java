package cr.ac.ucr.ie.sigie.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cr.ac.ucr.ie.sigie.business.StudyPlanBusiness;
import cr.ac.ucr.ie.sigie.data.StudyPlanData;
import cr.ac.ucr.ie.sigie.domain.Area;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.Emphasis;
import cr.ac.ucr.ie.sigie.domain.StudyPlan;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudyPlanControllerTest {

	private final String ROOT_URL = "http://127.0.0.1:8086/ie/api/studyplan";
	final private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	StudyPlanBusiness studyPlanBusiness;
	
	@Before
	void init() {
		//Asegura que la base tenga datos
		try {
			studyPlanBusiness.createStudyPlan(fillStudyPlan());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	@Test
	void findAllStudyPlan(){
		List<StudyPlan> studyPlanList = Arrays.asList(
				restTemplate.getForObject(ROOT_URL + "/", StudyPlan[].class));
		String testStudyPlanCode = studyPlanList.get(0).getStudyPlanCode();
		
		assertNotNull(studyPlanList);
		assertTrue(studyPlanList.size() > 0);

		assertNotEquals(studyPlanList.get(0).getCareerName(),"");
		assertNotEquals(testStudyPlanCode ,"");

		StudyPlan studyPlan = restTemplate.getForObject(ROOT_URL + "/" + testStudyPlanCode, StudyPlan.class);
		ArrayList<Course> courses = (ArrayList<Course>) studyPlan.getCourses(); 
		
		assertNotNull(studyPlan);
		assertNotEquals(studyPlan.getCareerCode(),"");
		assertTrue(courses.size() > 0);
		assertNotEquals(courses.get(0).getName(),"");

		assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->{
			restTemplate.getForObject(ROOT_URL + "/Something" , StudyPlan.class);
		});
	}

	
	
	@After
	void onFinish() {
		
	}
	
	private StudyPlan fillStudyPlan() throws Exception{
		// ----------------------------Area--------------------------
		// Fill Area
		Area area = new Area();
		area.setAreaId("129");
		area.setAreaName("Area1");
		List<Area> areas = new ArrayList();
		areas.add(area);

		// ----------------------------Emphasis--------------------------
		Emphasis emphasis = new Emphasis();
		emphasis.setEmphasisId("459");
		emphasis.setEmphasisName("gestion de proyectos");
		List<Emphasis> emphasiss = new ArrayList();
		emphasiss.add(emphasis);
		

		// ----------------------------Course-----------------------------
		// ---------------------------Course 1--------------
		// Fill Requisite Course
		Course requisiteCourse1 = new Course();
		requisiteCourse1.setArea(area);
		requisiteCourse1.setCourseId("R119");
		requisiteCourse1.setName("Requisite 1");
		requisiteCourse1.setLevel(1);
		requisiteCourse1.setCredits(4);
		requisiteCourse1.setHoursTheory(2);
		requisiteCourse1.setHoursPractice(2);
		requisiteCourse1.setHoursLab(0);
		requisiteCourse1.setHoursTheoryPractice(0);

		// ---------------------------Course 2--------------
		// Fill Requisite Course
		Course requisiteCourse2 = new Course();
		requisiteCourse2.setArea(area);
		requisiteCourse2.setCourseId("R229");
		requisiteCourse2.setName("Requisite 2");
		requisiteCourse2.setLevel(1);
		requisiteCourse2.setCredits(4);
		requisiteCourse2.setHoursTheory(2);
		requisiteCourse2.setHoursPractice(0);
		requisiteCourse2.setHoursLab(0);
		requisiteCourse2.setHoursTheoryPractice(0);

		// ---------------------------Course 3--------------
		// Fill Corequisite Course
		Course course3 = new Course();
		course3.setArea(area);
		course3.setCourseId("C119");
		course3.setName("Corequisite 1");
		course3.setLevel(2);
		course3.setCredits(2);
		course3.setHoursTheory(4);
		course3.setHoursPractice(0);
		course3.setHoursLab(0);
		course3.setHoursTheoryPractice(0);

		// ---------------------------Course 4--------------
		// Fill Corequisite Course
		Course course4 = new Course();
		course4.setArea(area);
		course4.setCourseId("C229");
		course4.setName("Corequisite 2");
		course4.setLevel(1);
		course4.setCredits(4);
		course4.setHoursTheory(2);
		course4.setHoursPractice(0);
		course4.setHoursLab(0);
		requisiteCourse2.setHoursTheoryPractice(0);
		
		List<Course> courses = new ArrayList();
		courses.add(requisiteCourse1);
		courses.add(requisiteCourse2);
		courses.add(course3);
		courses.add(course4);

		//-------------------StudyPlan----------------------
		StudyPlan studyPlan = new StudyPlan();
		studyPlan.setStudyPlanCode("ie2021ucr");
		studyPlan.setCareerName("Informatica Empresarial");
		studyPlan.setApprovalYear(2024);
		studyPlan.setActive(true);
		File testAproval = new File("src/test/java/cr/ac/ucr/ie/sigie/data/aproval.txt");
		studyPlan.setUcrApprovalDocument(testAproval);
		File testdoc = new File("src/test/java/cr/ac/ucr/ie/sigie/data/StudyPlan.txt");
		studyPlan.setStudyPlanDocument(testdoc);
		studyPlan.setCareerCode("ie3209");
		
		studyPlan.setEmphasiss(emphasiss);
		studyPlan.setAreas(areas);
		studyPlan.setCourses(courses);

		return studyPlan;
	}

}
