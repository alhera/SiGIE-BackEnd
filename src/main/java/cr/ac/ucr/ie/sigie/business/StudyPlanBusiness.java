package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.StudyPlanData;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.StudyPlan;

@Service
public class StudyPlanBusiness {

	@Autowired
	private StudyPlanData studyPlanData;
	
	public StudyPlan createStudyPlan(StudyPlan studyPlan) {
		return studyPlanData.create(studyPlan);
	}
	
	public List<StudyPlan> findAllStudyPlan() {
		return studyPlanData.findAllStudyPlan();
	}
	
	public StudyPlan findStudyPlanByCode(String studyPlanCode) {
		return studyPlanData.findStudyPlanByCode(studyPlanCode);
	}
	
	
}
