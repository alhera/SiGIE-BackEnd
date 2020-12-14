package cr.ac.ucr.ie.sigie.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.SocialActionProject;
import cr.ac.ucr.ie.sigie.domain.SocialActionReviewComment;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@Repository
public class SocialActionProjectData {
	
	private String tableName = "social_action_project";
	
	private static DataSource dataSource;
	
	@Autowired
	private SocialActionReviewCommentData socialActionReviewCommentData;
	
	@Autowired
	private SocialActionCollaboratorData socialActionCollaboratorData;
	
	@Autowired
	private UniversityBranchData universityBranchData;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//---------------------------------------COORDINATOR METHODS--------------------------------------------//
	
	public ArrayList<SocialActionProject> find() throws SQLException {
		
		Connection  conn = dataSource.getConnection();
		
		String query = "SELECT * FROM "+tableName;

		SocialActionProject socialActionProject;
		
		ArrayList<SocialActionProject> listProjects = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				int code = rs.getInt("code");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				int branchId = rs.getInt("branch_id");
				String publicationStastus = rs.getString("publication_status");
				boolean active = rs.getBoolean("active");
				boolean visible = rs.getBoolean("visible");
				ArrayList<InternalSocialActionCollaborator> internalCollaborators = socialActionCollaboratorData.findInternalCollaborators(code);
				ArrayList<ExternalSocialActionCollaborator> externalCollaborators = socialActionCollaboratorData.findExternalCollaborators(code);
				UniversityBranch universityBranch = universityBranchData.findById(branchId);
				ArrayList<SocialActionReviewComment> listComments = socialActionReviewCommentData.getAllReviewCommentByCodeProject(code);
				
				socialActionProject = new SocialActionProject();
				
				socialActionProject.setCode(code);
				socialActionProject.setTitle(title);
				socialActionProject.setDescription(description);
				socialActionProject.setStartDate(startDate);
				socialActionProject.setEndDate(endDate);
				socialActionProject.setPublicationStatus(publicationStastus);
				socialActionProject.setActive(active);
				socialActionProject.setVisible(visible);
				socialActionProject.setInternalCollaborators(internalCollaborators);
				socialActionProject.setExternalCollaborators(externalCollaborators);
				socialActionProject.setBranch(universityBranch);
				socialActionProject.setListReviewComments(listComments);
				
				listProjects.add(socialActionProject);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProjects;
		
	}

	public SocialActionProject findByCode(int code) throws SQLException{

		Connection  conn = dataSource.getConnection();
		
		String query = "select * from "+ tableName +" where code = "+ code +";";
		
		
		
		SocialActionProject socialActionProject = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				String title = rs.getString("title");
				String description = rs.getString("description");
				String startDate = rs.getString("start_date");
				String endDate = rs.getString("end_date");
				int branchId = rs.getInt("branch_id");
				String publicationStastus = rs.getString("publication_status");
				boolean active = rs.getBoolean("active");
				boolean visible = rs.getBoolean("visible");
				ArrayList<InternalSocialActionCollaborator> internalCollaborators = socialActionCollaboratorData.findInternalCollaborators(code);
				ArrayList<ExternalSocialActionCollaborator> externalCollaborators = socialActionCollaboratorData.findExternalCollaborators(code);
				UniversityBranch universityBranch = universityBranchData.findById(branchId);
				ArrayList<SocialActionReviewComment> listComments = socialActionReviewCommentData.getAllReviewCommentByCodeProject(code);
				
				socialActionProject = new SocialActionProject();
				
				socialActionProject.setCode(code);
				socialActionProject.setTitle(title);
				socialActionProject.setDescription(description);
				socialActionProject.setStartDate(startDate);
				socialActionProject.setEndDate(endDate);
				socialActionProject.setPublicationStatus(publicationStastus);
				socialActionProject.setActive(active);
				socialActionProject.setVisible(visible);
				socialActionProject.setInternalCollaborators(internalCollaborators);
				socialActionProject.setExternalCollaborators(externalCollaborators);
				socialActionProject.setBranch(universityBranch);
				socialActionProject.setListReviewComments(listComments);
			
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return socialActionProject;
		
	}

	public boolean approve(int code) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		String query = "UPDATE "+tableName+" SET publication_status = '"+SocialActionProject.APPROVED+"', visible = true WHERE code = "+code+";";
		
		
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				wasSuccessfulProcess = true;
			}
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}

	public boolean refuse(SocialActionReviewComment socialActionReviewComment, int projecCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		String query = "UPDATE "+tableName+" SET publication_status = '"+SocialActionProject.NEED_TO_FIX+"' WHERE code = "+projecCode+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				
				wasSuccessfulProcess = socialActionReviewCommentData.addReviewComment(socialActionReviewComment, projecCode);
				
			}
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}
	
	//---------------------------------------PROFESSOR METHODS---------------------------------------------//
	
	public boolean publishRequest(SocialActionProject socialActionProject) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		String title = socialActionProject.getTitle();
		String description = socialActionProject.getDescription();
		String startDate = dateToString(socialActionProject.getStartDate());
		String endDate = dateToString(socialActionProject.getEndDate());
		UniversityBranch branch = socialActionProject.getBranch();
		//TODO no necesariamente active tiene que ser false
		boolean active = false;
		boolean visible = false;
		String publicationStatus = SocialActionProject.ON_REVIEW;
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = socialActionProject.getInternalCollaborators();
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = socialActionProject.getExternalCollaborators();
		
		String query = "insert into social_action_project(\r\n" + 
				"title, description, start_date, end_date, branch_id, active, visible, publication_status) \r\n" + 
				"values ("
				+ "'"+title+"',"
				+ "'"+description+"',"
				+ "'"+startDate+"', "
				+ "'"+endDate+"',"
				+ ""+branch.getBranchId()+","
				+ ""+active+","
				+ ""+visible+", "
				+ "'"+publicationStatus+"');";
		
		
		
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {

				wasSuccessfulProcess = socialActionCollaboratorData.addCollaborators(internalCollaborators, externalCollaborators);
				
			}
			
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}//method
	
	public boolean fixProjectRequest(SocialActionProject socialActionProject) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulConnection = false;
		
		int codeProject = socialActionProject.getCode();
		String title = socialActionProject.getTitle();
		String description = socialActionProject.getDescription();
		String startDate = dateToString(socialActionProject.getStartDate());
		String endDate = dateToString(socialActionProject.getEndDate());
		boolean active = socialActionProject.isActive();
		boolean visible = socialActionProject.isVisible();
		UniversityBranch branch = socialActionProject.getBranch();
		
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = socialActionProject.getInternalCollaborators();
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = socialActionProject.getExternalCollaborators();
		
		String query = "update social_action_project \r\n" + 
				"set title='"+title+"', description = '"+description+"', start_date = '"+startDate+"', "+
				"end_date = '"+endDate+"', branch_id = "+branch.getBranchId()+", publication_status = 'OnReview',\r\n" + 
				"active = "+active+", visible = "+visible+" where code = "+codeProject+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				
				wasSuccessfulConnection = true;
				
				//TODO falta actualizar la lista de los colaboradores
				
				//addInternalCollaborators(internalCollaborators, codeProject);
				
				//addExternalCollaborators(externalCollaborators, codeProject);
				
			}
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulConnection;
		
	}

	//----------------------------------------------UTIL METHODS-----------------------------------------------------------//
	
	private String dateToString(Date date) {
		
		String pattern = "yyyy-MM-dd";

		DateFormat df = new SimpleDateFormat(pattern);

		String dateAsString = df.format(date);

		return dateAsString;
		
	}
	
	public static int getLastCode() throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		int lastCode = -1;
		
		String query = "select max(code) as id from social_action_project;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				lastCode = rs.getInt("id");
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lastCode;
		
	}
	
	public static boolean deleteByCode(int code) throws SQLException {
		 
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		SocialActionReviewCommentData.deleteByCodeProject(code);
		
		SocialActionCollaboratorData.deleteCollaborators(code);
			
		String query = "DELETE FROM social_action_project where code = "+code+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {

				wasSuccessfulProcess = true;
				
			}
			
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}
	
	//-----------------------------------------------OTHER METHODS--------------------------------------------------------//

	
}
