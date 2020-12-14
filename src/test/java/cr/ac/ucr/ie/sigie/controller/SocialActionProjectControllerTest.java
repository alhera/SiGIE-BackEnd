package cr.ac.ucr.ie.sigie.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cr.ac.ucr.ie.sigie.business.SocialActionProjectBussiness;
import cr.ac.ucr.ie.sigie.data.SocialActionCollaboratorData;
import cr.ac.ucr.ie.sigie.data.SocialActionProjectData;
import cr.ac.ucr.ie.sigie.data.SocialActionReviewCommentData;
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
@RunWith(SpringRunner.class)
public class SocialActionProjectControllerTest {
	
	private String url = "http://localhost:8086/ie/api/social-action-project";
	
	private static int projectCode = 5;
	
	final private RestTemplate restTemplate = new RestTemplate();
	
	private String USERNAME = "alvaro.menamonge@ucr.ac.cr";
	
	private String PASSWORD = "amm";
	
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
	
	//@Test
	public void find() {
		
		ResponseEntity<SocialActionProject[]> result = 
				
				this.restTemplate.getForEntity(this.url+"/find", SocialActionProject[].class);
		
		assertTrue(result.getStatusCode() == HttpStatus.OK);
		
		List<SocialActionProject> listProjects = Arrays.asList(result.getBody());
		
		assertTrue(listProjects.size()>0);
		
	}
	
	//@Test
	public void find2() {

	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    
	    headers.setBasicAuth(this.USERNAME, this.PASSWORD);

	    // create request
	    HttpEntity request = new HttpEntity(headers);

	    // make a request
	    ResponseEntity<SocialActionProject[]> response = new RestTemplate().exchange(this.url+"/find", HttpMethod.POST, request, SocialActionProject[].class);

	    assertTrue(response.getStatusCode() == HttpStatus.OK);
		
		List<SocialActionProject> listProjects = Arrays.asList(response.getBody());
		
		assertTrue(listProjects.size()>0);

	}
	
	@Test
	public void find3() {
		
		String plainCreds = this.USERNAME+":"+this.PASSWORD;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<SocialActionProject[]> response = restTemplate.exchange(this.url+"/find", HttpMethod.GET, request, SocialActionProject[].class);

		
	}

	//@Test
	public void findById() {
		
		ResponseEntity<SocialActionProject> responseEntity = 
				this.restTemplate.getForEntity(this.url+"/findByCode?code="+this.projectCode, SocialActionProject.class);
		
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		
		SocialActionProject listProjects = responseEntity.getBody();
		
		assertTrue(!listProjects.getTitle().equals(""));
		
	}
	
	//@Test
	public void publishRequest() {
		
		boolean wasSuccessfulProcess = 
				this.restTemplate.postForObject(this.url+"/resquest-publication",socialActionProject, Boolean.class);
		
		assertTrue(wasSuccessfulProcess == true);
		
	}
	
	//@Test
	public void approve() {
	
		ResponseEntity<Boolean> responseEntity = 
				this.restTemplate.getForEntity(this.url+"/approve?code="+this.projectCode, Boolean.class);
		
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		
		boolean wasSuccessfulProcess = responseEntity.getBody();
		
		assertTrue(wasSuccessfulProcess == true);
		
	}

	//@Test
	public void refuse() {
			
		boolean wasProcessSuccsessful = 
				this.restTemplate.postForObject(this.url+"/refuse?code="+projectCode, socialActionProjectComment, Boolean.class);
		
		assertTrue(wasProcessSuccsessful == true);

	}
	
	//@Test
	public void fixPublishRequest() {
		
		boolean wasProcessSuccsessful = 
				this.restTemplate.postForObject(this.url+"/fix-request", fixedSocialActionProject, Boolean.class);
		
		assertTrue(wasProcessSuccsessful == true);

	}
	
	/*HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}*/

	private static SocialActionProject getSocialActionProject(){
		
		int code = 1;
		
		String title = "*Test Controller*";
		
		String description = "This row was inserted by Controller Test Method";
		
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
		
		socialActionProject = new SocialActionProject();
		
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
	
	private static SocialActionReviewComment getReviewComment() {
	
		String reviewComment = "Review Comment Test Controller";
		
		String reviewCommentDate = "2020-09-20";
		
		SocialActionReviewComment socialActionProjectComentary = new SocialActionReviewComment();
		
		socialActionProjectComentary.setReviewComment(reviewComment);
		
		socialActionProjectComentary.setReviewCommentDate(reviewCommentDate);
		
		return socialActionProjectComentary;
		
	}

	private static SocialActionProject getFixSocialActionProject(int code){
		
		String title = "Test Controller Fixed";
		
		String description = "This row was fixed by Controller Test Method";
		
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
