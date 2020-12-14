package cr.ac.ucr.ie.sigie.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.ProfessorBusiness;
import cr.ac.ucr.ie.sigie.business.SocialActionCollaboratorBussiness;
import cr.ac.ucr.ie.sigie.domain.ExternalParticipant;
import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.Professor;
import cr.ac.ucr.ie.sigie.domain.SocialActionProject;

@RestController
@RequestMapping(value="/api/social-action-collaborator", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
public class SocialActionCollaboratorController {
	
	@Autowired
	SocialActionCollaboratorBussiness collaboratorBussiness;
	
	@Autowired
	ProfessorBusiness professorBussiness;
	
	
	@RequestMapping(method = RequestMethod.GET, value="/findInternal")
	@ResponseBody
	public ResponseEntity<ArrayList<InternalSocialActionCollaborator>> findInternalCollaborators() {
		  
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = collaboratorBussiness.findInternalCollaborators();
		
		return new ResponseEntity<ArrayList<InternalSocialActionCollaborator>>(internalCollaborators, HttpStatus.OK);
		  
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/findExternal")
	@ResponseBody
	public ResponseEntity<ArrayList<ExternalSocialActionCollaborator>> findExternalCollaborators() {
		  
		ArrayList<ExternalSocialActionCollaborator> internalCollaborators = collaboratorBussiness.findExternalCollaborators();
		
		return new ResponseEntity<ArrayList<ExternalSocialActionCollaborator>>(internalCollaborators, HttpStatus.OK);
		  
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/professors")
	@ResponseBody
	public ResponseEntity<ArrayList<Professor>> findProfessors() {
		  
		ArrayList<Professor> professors = new ArrayList<Professor>(professorBussiness.getProfessors(null));
		
		return new ResponseEntity<ArrayList<Professor>>(professors, HttpStatus.OK);
		  
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/external_participants")
	@ResponseBody
	public ResponseEntity<ArrayList<ExternalParticipant>> findExternalParticipants() {
		  
		ArrayList<ExternalParticipant> externalParticipants = collaboratorBussiness.findExternalParticipants();
		
		return new ResponseEntity<ArrayList<ExternalParticipant>>(externalParticipants, HttpStatus.OK);
		  
	}
	

}
