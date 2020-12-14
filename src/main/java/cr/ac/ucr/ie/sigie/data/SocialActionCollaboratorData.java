package cr.ac.ucr.ie.sigie.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.ExternalParticipant;
import cr.ac.ucr.ie.sigie.domain.ExternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.InternalSocialActionCollaborator;
import cr.ac.ucr.ie.sigie.domain.Organization;
import cr.ac.ucr.ie.sigie.domain.ParticipationType;
import cr.ac.ucr.ie.sigie.domain.Professor;

@Repository
public class SocialActionCollaboratorData {
	
	private static DataSource dataSource;
	
	@Autowired
	ProfessorData professorData;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<InternalSocialActionCollaborator> findInternalCollaborators(int projectCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from internal_social_action_collaborator where project_code = "+projectCode+";";
		
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			InternalSocialActionCollaborator internalCollaborator;
			
			while(rs.next()) {
				
				int professorID = rs.getInt("professor_id");
				int participationTypeID = rs.getInt("participation_type_id");

				internalCollaborator = new InternalSocialActionCollaborator();
				
				Professor professor = professorData.getProfessorByID(professorID);
				ParticipationType participationType = getParticipationType(participationTypeID);

				
				internalCollaborator.setParticipationType(participationType);
				internalCollaborator.setProfessor(professor);
				
				internalCollaborators.add(internalCollaborator);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return internalCollaborators;
		
	}
	
	public ArrayList<ExternalSocialActionCollaborator> findExternalCollaborators(int projectCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from external_social_action_collaborator where project_code = "+projectCode+";";
		
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			ExternalSocialActionCollaborator externalCollaborator;
			
			while(rs.next()) {
				
				int externalParticipantId = rs.getInt("external_participant_id");
				int participationTypeID = rs.getInt("participation_type_id");
				
				externalCollaborator = new ExternalSocialActionCollaborator();
				
				ExternalParticipant externalParticipant = getExternalParticipantByID(externalParticipantId);
				ParticipationType participationType = getParticipationType(participationTypeID);
				
				externalCollaborator.setExternalParticipant(externalParticipant);
				externalCollaborator.setParticipationtype(participationType);
				
				externalCollaborators.add(externalCollaborator);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return externalCollaborators;
		
	}
	
	public boolean addCollaborators(ArrayList<InternalSocialActionCollaborator> internalCollaborators, 
			ArrayList<ExternalSocialActionCollaborator> externalCollaborators) throws SQLException{
		
		boolean wasSuccessfulProcess = false;
		
		int codeProject = SocialActionProjectData.getLastCode();
		
		boolean x = addInternalCollaborators(internalCollaborators, codeProject);
		
		boolean y = addExternalCollaborators(externalCollaborators, codeProject);
		
		if(x && y) {
			wasSuccessfulProcess = true;
		}
		
		return wasSuccessfulProcess;
		
	}

	public static boolean deleteCollaborators(int codeProject) throws SQLException {
		
		boolean wasSuccessfulProcess = false;
		
		boolean x = deleteExternalParticipantsByCodeProject(codeProject);
		
		boolean y = deleteInternalParticipantsByCodeProject(codeProject);
		
		if(x && y) {
			wasSuccessfulProcess = true;
		}
		
		return wasSuccessfulProcess;
		
	}
	
	public ArrayList<InternalSocialActionCollaborator> findInternalCollaborators() throws SQLException {
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from internal_social_action_collaborator;";
		
		ArrayList<InternalSocialActionCollaborator> internalCollaborators = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			InternalSocialActionCollaborator internalCollaborator;
			
			while(rs.next()) {
				
				int professorID = rs.getInt("professor_id");
				int participationTypeID = rs.getInt("participation_type_id");

				internalCollaborator = new InternalSocialActionCollaborator();
				
				Professor professor = professorData.getProfessorByID(professorID);
				ParticipationType participationType = getParticipationType(participationTypeID);

				
				internalCollaborator.setParticipationType(participationType);
				internalCollaborator.setProfessor(professor);
				
				internalCollaborators.add(internalCollaborator);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return internalCollaborators;
		
	}
	
	public ArrayList<ExternalSocialActionCollaborator> findExternalCollaborators() throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from external_social_action_collaborator;";
		
		ArrayList<ExternalSocialActionCollaborator> externalCollaborators = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			ExternalSocialActionCollaborator externalCollaborator;
			
			while(rs.next()) {
				
				int externalParticipantId = rs.getInt("external_participant_id");
				int participationTypeID = rs.getInt("participation_type_id");
				
				externalCollaborator = new ExternalSocialActionCollaborator();
				
				ExternalParticipant externalParticipant = getExternalParticipantByID(externalParticipantId);
				ParticipationType participationType = getParticipationType(participationTypeID);
				
				externalCollaborator.setExternalParticipant(externalParticipant);
				externalCollaborator.setParticipationtype(participationType);
				
				externalCollaborators.add(externalCollaborator);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return externalCollaborators;
		
	}
	
	//-----------------------------------------------PRIVATE METHODS--------------------------------------------------------//
	
	private boolean addInternalCollaborators(ArrayList<InternalSocialActionCollaborator> internalCollaborators, int projectCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		for (InternalSocialActionCollaborator internalSocialActionCollaborator : internalCollaborators) {
			
			int professorID = internalSocialActionCollaborator.getProfessor().getProfessorId();
			
			int participationTypeID = internalSocialActionCollaborator.getParticipationType().getIdParticipation();
			
			String query = "insert into internal_social_action_collaborator(\r\n" + 
					"professor_id, project_code, participation_type_id) \r\n" + 
					"values ("+professorID+","+projectCode+","+participationTypeID+");";
			
			
			
			try {
				
				Statement stmt = conn.createStatement();
				
				int rs = stmt.executeUpdate(query);
				
				if(rs != 0) {
					
					wasSuccessfulProcess = true;
					
				}
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}// end foreach
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}//end method
	
	private boolean addExternalCollaborators(ArrayList<ExternalSocialActionCollaborator> externalCollaborators, int projectCode) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulProcess = false;
		
		for (ExternalSocialActionCollaborator externalSocialActionCollaborator : externalCollaborators) {
			
			int externalParticipantID = externalSocialActionCollaborator.getExternalParticipant().getExternalParticipantId();
			
			int participationTypeID = externalSocialActionCollaborator.getParticipationType().getIdParticipation();
			
			String query = "insert into external_social_action_collaborator(\r\n" + 
					"external_participant_id, project_code, participation_type_id) \r\n" + 
					"values ("+externalParticipantID+","+projectCode+","+participationTypeID+");";
			
			
			
			try {
				
				Statement stmt = conn.createStatement();
				
				int rs = stmt.executeUpdate(query);
				
				if(rs != 0) {

					wasSuccessfulProcess = true;
					
				}
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}// end foreach
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulProcess;
		
	}//end method

	//-----------------------------------------------OTHER METHODS--------------------------------------------------------//
	
	//TODO delete
	/*private Professor getProfessorByID(int idProfessor) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from professor where professor_id = "+ idProfessor +";";
		
		
		
		Professor professor = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String lastName = rs.getString("last_name");
				String institutionalEmail = rs.getString("institutional_mail");
				String academicDegree = rs.getString("academic_degree_acronym");
				
				professor = new Professor();
				
				professor.setProfessorId(idProfessor);
				professor.setName(name);
				professor.setLastName(lastName);
				professor.setInstitutionalMail(institutionalEmail);
				professor.setAcademicDegreeAcronym(academicDegree);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return professor;
		
	}*/
	
	//TODO delete this method
	/*public ArrayList<Professor> getAllProfessors() throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from professor;";
		
		
		
		ArrayList<Professor> list = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				int id = rs.getInt("professor_id");
				String name = rs.getString("name");
				String lastName = rs.getString("last_name");
				String institutionalEmail = rs.getString("institutional_mail");
				String academicDegree = rs.getString("academic_degree_acronym");
				
				Professor professor = new Professor();
				
				professor.setProfessorId(id);
				professor.setName(name);
				professor.setLastName(lastName);
				professor.setInstitutionalMail(institutionalEmail);
				professor.setAcademicDegreeAcronym(academicDegree);
				
				list.add(professor);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}*/
	
	private ParticipationType getParticipationType(int idParticipationType) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from participation_type where participation_type_id = "+ idParticipationType +";";
		
		
		
		ParticipationType participationType = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				String description = rs.getString("description");
				
				participationType = new ParticipationType(idParticipationType, description);
				
				/*participationType.setIdParticipation(idParticipationType);
				participationType.setDescription(description);*/
						
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participationType;
		
	}
	
	private Organization getOrganizationByID(int organizationID) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from organization where organization_id = "+ organizationID +";";
		
		
		
		Organization organization = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String organizationDepartment = rs.getString("organization_department");
				
				organization = new Organization();
				
				organization.setOrganizationName(name);
				organization.setOrganizationId(organizationID);
				organization.setOrganizationDepartment(organizationDepartment);
							
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return organization;
		
	}

	private ExternalParticipant getExternalParticipantByID(int externalParticipantID) throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from external_participant where external_participant_id = "+ externalParticipantID +";";
		
		ExternalParticipant externalParticipant = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String academicDegree = rs.getString("academic_degree");
				int organizationID = rs.getInt("organization_id");
				
				externalParticipant = new ExternalParticipant();
				Organization organization = getOrganizationByID(organizationID);
				
				externalParticipant.setExternalParticipantId(externalParticipantID);
				externalParticipant.setName(name);
				externalParticipant.setLastName(lastName);
				externalParticipant.setEmail(email);
				externalParticipant.setAcademicDegree(academicDegree);
				externalParticipant.setOrganization(organization);
				
						
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return externalParticipant;
		
	}
	
	//TODO delete this method
	public ArrayList<ExternalParticipant> getAllExternalParticipants() throws SQLException{
		
		Connection  conn = dataSource.getConnection();
		
		String query = "select * from external_participant;";
		
		ArrayList<ExternalParticipant> externalParticipants = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				int id = rs.getInt("external_participant_id");
				String name = rs.getString("name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String academicDegree = rs.getString("academic_degree");
				int organizationID = rs.getInt("organization_id");
				
				ExternalParticipant externalParticipant = new ExternalParticipant();
				Organization organization = getOrganizationByID(organizationID);
				
				externalParticipant.setExternalParticipantId(id);
				externalParticipant.setName(name);
				externalParticipant.setLastName(lastName);
				externalParticipant.setEmail(email);
				externalParticipant.setAcademicDegree(academicDegree);
				externalParticipant.setOrganization(organization);
				
				externalParticipants.add(externalParticipant);
						
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return externalParticipants;
		
	}
	
	private static boolean deleteExternalParticipantsByCodeProject(int codeProject) throws SQLException {
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulConnection = false;

		String query = "delete from external_social_action_collaborator where project_code = "+codeProject+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				
				wasSuccessfulConnection = true;
				
			}
			
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulConnection;
		
	}
	
	private static boolean deleteInternalParticipantsByCodeProject(int codeProject) throws SQLException {
		
		Connection  conn = dataSource.getConnection();
		
		boolean wasSuccessfulConnection = false;

		String query = "delete from internal_social_action_collaborator where project_code = "+codeProject+";";
		
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate(query);
			
			if(rs != 0) {
				
				wasSuccessfulConnection = true;
				
			}
			
			stmt.close();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasSuccessfulConnection;
		
	}
	
}
