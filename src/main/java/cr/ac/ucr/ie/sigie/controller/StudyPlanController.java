package cr.ac.ucr.ie.sigie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.StudyPlanBusiness;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.StudyPlan;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/studyplan")
public class StudyPlanController {

	@Autowired
	private StudyPlanBusiness studyPlanBusiness;
	
	//FEA-1 Dilan y Ariel
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudyPlan> create(@RequestBody  @Valid StudyPlan studyPlan){
		StudyPlan insertedStudyPlan = studyPlanBusiness.createStudyPlan(studyPlan);
		return new ResponseEntity<>(insertedStudyPlan, HttpStatus.CREATED);
	}
	
	//FEA-2 Albin y Josue
	//Trae nombre y code de los planes de estudio
	//para ser implentado en el menu
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<StudyPlan>> findAllStudyPlan() {
		return new ResponseEntity<>(studyPlanBusiness.findAllStudyPlan() ,HttpStatus.OK);
	}
	
	//Trae los datos de plan de estudios por medio de su codigo
	//Ademas de los cursos con sus respectivos requisitos y corequisitos 
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StudyPlan> findStudyPlanByCode(@PathVariable("id") String studyPlanCode) {
		return new ResponseEntity<>(studyPlanBusiness.findStudyPlanByCode(studyPlanCode),
			       HttpStatus.OK);
	}
	

}