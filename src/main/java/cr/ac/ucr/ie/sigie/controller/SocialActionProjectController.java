package cr.ac.ucr.ie.sigie.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.SocialActionProjectBussiness;
import cr.ac.ucr.ie.sigie.domain.SocialActionProject;
import cr.ac.ucr.ie.sigie.domain.SocialActionReviewComment;

@RestController
@RequestMapping(value="/api/social-action-project")
@CrossOrigin(origins="*")
public class SocialActionProjectController {
	
	@Autowired
	private SocialActionProjectBussiness socialActionProjectBussinness;
	
	//---------------------------------------COORDINATOR METHODS---------------------------------------------//
	
	//This method shows all the social actions projects pending to approve them.
	@RequestMapping(method = RequestMethod.GET, value="/find")
	@ResponseBody
	public ResponseEntity<ArrayList<SocialActionProject>> find() {
		  
		ArrayList<SocialActionProject> listProjects = socialActionProjectBussinness.find();
		
		return new ResponseEntity<ArrayList<SocialActionProject>>(listProjects, HttpStatus.OK);
		  
	}
	
	//This method returns a social action project through its id.
	@RequestMapping(method = RequestMethod.GET, value="/findByCode")
	@ResponseBody
	public ResponseEntity<SocialActionProject> findByCode(@RequestParam int code) {
		  
		SocialActionProject socialActionProject = socialActionProjectBussinness.findByCode(code);
		
		return new ResponseEntity<SocialActionProject> (socialActionProject, HttpStatus.OK);
		  
	}
	
	
	
	//This method changes the status project to approve. 
	@RequestMapping(method = RequestMethod.GET, value="/approve")
	public ResponseEntity<Boolean> approve(@RequestParam int code) {
		
		boolean wasSuccessfulProcess = socialActionProjectBussinness.approve(code);

		return new ResponseEntity<Boolean> (wasSuccessfulProcess, HttpStatus.OK);
		
	}
	
	//This method changes the status project to refuse. 
	@RequestMapping(method = RequestMethod.POST, value="/refuse")
	public ResponseEntity<Boolean> refuse(@RequestBody SocialActionReviewComment socialActionReviewComment, @RequestParam int code) {
		
		boolean wasSuccessfulProcess = socialActionProjectBussinness.refuse(socialActionReviewComment, code);
		
		return new ResponseEntity<Boolean> (wasSuccessfulProcess, HttpStatus.OK);
		
	}
		
	
	//------------------------------------------PROFFESOR METHODS---------------------------------------------//
	
	
	//This method allows to professors request the publication of their social action projects.
	@RequestMapping(method = RequestMethod.POST, value="/resquest-publication")
	public ResponseEntity<Boolean> publishRequest(@RequestBody SocialActionProject socialActionProject) {
		
		//return projectBussiness.addSocialActionProject(socialActionProject);
		boolean wasSuccessfulProcess = socialActionProjectBussinness.publishRequest(socialActionProject);
		
		return new ResponseEntity<Boolean> (wasSuccessfulProcess, HttpStatus.OK);
	}
	
	
	//This method is to apply social action project corrections set by the coordinator
	@RequestMapping(method = RequestMethod.POST, value="/fix-request")
	public ResponseEntity<Boolean> fixRequest(@RequestBody SocialActionProject socialActionProject) {
		
		boolean wasSuccessfulProcess = socialActionProjectBussinness.fixRequest(socialActionProject);
		
		return new ResponseEntity<Boolean> (wasSuccessfulProcess, HttpStatus.OK);
		
	}
	

}
