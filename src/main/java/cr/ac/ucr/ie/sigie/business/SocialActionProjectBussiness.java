package cr.ac.ucr.ie.sigie.business;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.SocialActionProjectData;
import cr.ac.ucr.ie.sigie.domain.SocialActionProject;
import cr.ac.ucr.ie.sigie.domain.SocialActionReviewComment;

@Service
public class SocialActionProjectBussiness {
	
	@Autowired
	private SocialActionProjectData socialActionProjectData;

	public ArrayList<SocialActionProject> find() {
		
		ArrayList<SocialActionProject> listProjects = null;
		try {
			listProjects = socialActionProjectData.find();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProjects;
		
	}

	public SocialActionProject findByCode(int code) {
	
		SocialActionProject socialActionProject = null;
		
		try {
			socialActionProject = socialActionProjectData.findByCode(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return socialActionProject;
	
	}

	public boolean approve(int code) {
		
		boolean wasSuccessfulProcess = false;
		
		try {
			wasSuccessfulProcess = socialActionProjectData.approve(code);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}

	public boolean refuse(SocialActionReviewComment socialActionReviewComment, int code) {
		
		
		boolean wasSuccessfulProcess = false;
		
		try {
			wasSuccessfulProcess = socialActionProjectData.refuse(socialActionReviewComment, code);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}

	public boolean publishRequest(SocialActionProject socialActionProject) {
		
		boolean wasSuccessfulProcess = false;
		
		try {
			wasSuccessfulProcess = socialActionProjectData.publishRequest(socialActionProject);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
	}

	public boolean fixRequest(SocialActionProject socialActionProject) {
		
		boolean wasSuccessfulProcess = false;
		
		try {
			wasSuccessfulProcess = socialActionProjectData.fixProjectRequest(socialActionProject);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}
	
	
}
