package cr.ac.ucr.ie.sigie.business;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.SocialActionCollaboratorData;
import cr.ac.ucr.ie.sigie.domain.ExternalParticipant;
import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.Professor;

@Service
public class SocialActionCollaboratorBussiness {
	
	@Autowired
	SocialActionCollaboratorData data;
	
	public ArrayList<InternalSocialActionCollaborator> findInternalCollaborators() {
		
		ArrayList<InternalSocialActionCollaborator> listInternalCollaborators = null;
		try {
			listInternalCollaborators = data.findInternalCollaborators();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listInternalCollaborators;
		
	}
	
	public ArrayList<ExternalSocialActionCollaborator> findExternalCollaborators() {
		
		ArrayList<ExternalSocialActionCollaborator> listExternalCollaborators = null;
		try {
			listExternalCollaborators = data.findExternalCollaborators();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listExternalCollaborators;
		
	}
	
	//TODO delete this method
	/*public ArrayList<Professor> findProfessors() {
		
		ArrayList<Professor> listExternalCollaborators = null;
		try {
			listExternalCollaborators = data.getAllProfessors();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listExternalCollaborators;
		
	}*/
	
	
	//TODO delete this method
	public ArrayList<ExternalParticipant> findExternalParticipants() {
		
		ArrayList<ExternalParticipant> listExternalCollaborators = null;
		try {
			listExternalCollaborators = data.getAllExternalParticipants();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listExternalCollaborators;
		
	}
	

}
