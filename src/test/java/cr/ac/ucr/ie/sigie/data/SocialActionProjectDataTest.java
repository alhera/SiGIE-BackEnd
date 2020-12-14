package cr.ac.ucr.ie.sigie.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.ie.sigie.domain.ExternalParticipant;
import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.Organization;
import cr.ac.ucr.ie.sigie.domain.ParticipationType;
import cr.ac.ucr.ie.sigie.domain.Professor;
import cr.ac.ucr.ie.sigie.domain.SocialActionProject;
import cr.ac.ucr.ie.sigie.domain.SocialActionReviewComment;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@SpringBootTest
public class SocialActionProjectDataTest {
	
	@Autowired
	private SocialActionProjectData socialActionProjectData;
	
	private static int projectCode = 5;
	
	@Autowired
	private static SocialActionProject socialActionProject;
	
	@Autowired
	private static SocialActionProject fixedSocialActionProject;
	
	@Autowired
	static SocialActionReviewComment socialActionProjectComment;
	
	
	@BeforeAll
	public static void init() throws SQLException{
		
		socialActionProject = getSocialActionProject();
		
		socialActionProjectComment = getReviewComment();
		
		fixedSocialActionProject = getFixSocialActionProject(projectCode);
		
	}
	
	@AfterAll
	public static void end() throws SQLException {
		
		int lastCode = SocialActionProjectData.getLastCode();
		
		SocialActionProjectData.deleteByCode(lastCode);
		
	}
	
	@Test
	public void find() throws SQLException {
		
		ArrayList<SocialActionProject> listProjets = socialActionProjectData.find();
		
		assertTrue(listProjets.size()>0);

	}
	
	@Test
	public void findById() throws SQLException {
		
		SocialActionProject project = socialActionProjectData.findByCode(projectCode);
		
		assertTrue(!project.getTitle().equals(""));
		
	}
	
	@Test
	public void publishRequest() throws SQLException {
		
		boolean wasSuccessfulProcess = socialActionProjectData.publishRequest(socialActionProject);
		
		assertTrue(wasSuccessfulProcess == true);
		
	}
	
	@Test
	public void approve() throws SQLException {
	
		boolean wasSuccessfulProcess = socialActionProjectData.approve(projectCode);
		
		assertTrue(wasSuccessfulProcess == true);
		
	}
	
	@Test
	public void refuse() throws SQLException {
		
		boolean wasProcessSuccsessful = 
				socialActionProjectData.refuse(socialActionProjectComment, projectCode);
		
		assertTrue(wasProcessSuccsessful == true);

	}
	
	@Test
	public void fixPublishRequest() throws SQLException {
		
		boolean wasProcessSuccsessful = 
				socialActionProjectData.fixProjectRequest(fixedSocialActionProject);
		
		assertTrue(wasProcessSuccsessful == true);

	}

	private static SocialActionProject getSocialActionProject(){
		
		String title = "*Test Data*";
		
		String description = "This row was inserted by Data Test Method";
		
		String startDate = "2020-08-25";
		
		String endDate = "2020-10-25";
		
		boolean active = false;
		
		boolean visible = false;
		
		Professor professor = new Professor("Julio", "Segura", "BAH", "julio@ucr.ac.cr");
		
		professor.setProfessorId(1);
		
		InternalSocialActionCollaborator internalCollaborator = 
			new InternalSocialActionCollaborator(
				new ParticipationType(1, "Contribución"),
				professor
			);
		
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = new ArrayList<>();
		internalCollaborators.add(internalCollaborator);
		
		ExternalSocialActionCollaborator externalCollaborator = 
				new ExternalSocialActionCollaborator(
					new ExternalParticipant(1, "Julio", "Segura", "BAH", "julio@ucr.ac.cr", new Organization(1, "AISEC", "Publicidad")),
					new ParticipationType(1, "Contribución")
				);
		
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = new ArrayList<>();
		externalCollaborators.add(externalCollaborator);
		
		UniversityBranch universityBranch = new UniversityBranch(1,"Informática");
		
		//----------------------------------------------------------------//
		
		SocialActionProject socialActionProject = new SocialActionProject();
		
		socialActionProject.setTitle(title);
		
		socialActionProject.setDescription(description);
		
		socialActionProject.setStartDate(startDate);
		
		socialActionProject.setEndDate(endDate);
		
		socialActionProject.setActive(active);
		
		socialActionProject.setVisible(visible);
		
		socialActionProject.setInternalCollaborators(internalCollaborators);
		
		socialActionProject.setExternalCollaborators(externalCollaborators);
		
		socialActionProject.setBranch(universityBranch);
		
		return socialActionProject;
		
	}

	private static SocialActionReviewComment getReviewComment() {
		
		String reviewComment = "Review Comment Test Data";
		
		String reviewCommentDate = "2020-09-20";
		
		SocialActionReviewComment socialActionProjectComentary = new SocialActionReviewComment();
		
		socialActionProjectComentary.setReviewComment(reviewComment);
		
		socialActionProjectComentary.setReviewCommentDate(reviewCommentDate);
		
		return socialActionProjectComentary;
		
	}
	
	private static SocialActionProject getFixSocialActionProject(int code){
		
		String title = "Test Data Fixed";
		
		String description = "This row was fixed by Data Test Method";
		
		String startDate = "2020-08-25";
		
		String endDate = "2020-10-25";
		
		boolean active = false;
		
		boolean visible = false;
		
		Professor professor = new Professor("Julio", "Segura", "BAH", "julio@ucr.ac.cr");
		
		professor.setProfessorId(1);
		
		InternalSocialActionCollaborator internalCollaborator = 
			new InternalSocialActionCollaborator(
				new ParticipationType(1, "Contribución"),
				professor
			);
		
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = new ArrayList<>();
		internalCollaborators.add(internalCollaborator);
		
		ExternalSocialActionCollaborator externalCollaborator = 
				new ExternalSocialActionCollaborator(
					new ExternalParticipant(1, "Julio", "Segura", "BAH", "julio@ucr.ac.cr", new Organization(1, "AISEC", "Publicidad")),
					new ParticipationType(1, "Contribución")
				);
		
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = new ArrayList<>();
		externalCollaborators.add(externalCollaborator);
		
		UniversityBranch universityBranch = new UniversityBranch(1,"Informática");
		
		//----------------------------------------------------------------//
		
		SocialActionProject socialActionProject = new SocialActionProject();
		
		socialActionProject.setCode(code);
		
		socialActionProject.setTitle(title);
		
		socialActionProject.setDescription(description);
		
		socialActionProject.setStartDate(startDate);
		
		socialActionProject.setEndDate(endDate);
		
		socialActionProject.setActive(active);
		
		socialActionProject.setVisible(visible);
		
		socialActionProject.setInternalCollaborators(internalCollaborators);
		
		socialActionProject.setExternalCollaborators(externalCollaborators);
		
		socialActionProject.setBranch(universityBranch);
		
		return socialActionProject;
		
	}

}
