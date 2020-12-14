package cr.ac.ucr.ie.sigie.data;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.domain.Area;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.StudyPlan;

@Repository
public class StudyPlanData {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	CourseData courseData;
	
	//FEA-1 Ariel y Dilan
	@Transactional
	public StudyPlan create(StudyPlan studyPlan) {
		Connection conexion = null;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csAddStudyPlan = conexion.prepareCall("{call addStudyPlan(?, ?, ?, ?, ?, ?, ?, ?)}");
			csAddStudyPlan.registerOutParameter(1, Types.VARCHAR);
			csAddStudyPlan.setString(2, studyPlan.getStudyPlanCode());
			csAddStudyPlan.setString(3, studyPlan.getCareerName());
			csAddStudyPlan.setInt(4, studyPlan.getApprovalYear());
			csAddStudyPlan.setBoolean(5, studyPlan.isActive());
			byte[] studyPlanDocument = Files.readAllBytes(studyPlan.getStudyPlanDocument().toPath());
			csAddStudyPlan.setBytes(6, studyPlanDocument);
			byte[] ucrApprovalDocument = Files.readAllBytes(studyPlan.getUcrApprovalDocument().toPath());
			csAddStudyPlan.setBytes(7, ucrApprovalDocument);
			csAddStudyPlan.setString(8, studyPlan.getCareerCode());

			csAddStudyPlan.execute();
			csAddStudyPlan.close();
			for (int i = 0; i < studyPlan.getEmphasiss().size(); i++) {
				CallableStatement csSaveEmphasiss = conexion.prepareCall("{call addEmphasis(?, ?, ?)}");
				csSaveEmphasiss.setString(1, studyPlan.getEmphasiss().get(i).getEmphasisId());
				csSaveEmphasiss.setString(2, studyPlan.getEmphasiss().get(i).getEmphasisName());
				csSaveEmphasiss.setString(3, studyPlan.getStudyPlanCode());
				csSaveEmphasiss.execute();
				csSaveEmphasiss.close();
			}
			
			for (int i = 0; i < studyPlan.getAreas().size(); i++) {
				CallableStatement csSaveAreas = conexion.prepareCall("{call addArea(?, ?, ?)}");
				csSaveAreas.setString(1, studyPlan.getAreas().get(i).getAreaId());
				csSaveAreas.setString(2, studyPlan.getAreas().get(i).getAreaName());
				csSaveAreas.setString(3, studyPlan.getStudyPlanCode());
				csSaveAreas.execute();
				csSaveAreas.close();
			}
			
			conexion.commit();
		} catch (SQLException | IOException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return studyPlan;
	}
	
	
	//metodos FEA-2 / Albin y Josue
	//Trae planes de estudio con nombre y código
	@Transactional(readOnly=true)
	public List<StudyPlan> findAllStudyPlan() {
		String query = "SELECT study_plan_code, career_name FROM study_plan";
	
		ArrayList<StudyPlan> studyPlanList = new ArrayList<>();
		try {//Crea conexion
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()){ //agrega cada plan de estudios a la lista
				studyPlanList.add(new StudyPlan(// crea un plan de estudios con el código y nombre
					rs.getString("study_plan_code"),
					rs.getString("career_name")
				));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Connection Error");
		}
		return studyPlanList;
	}

	//Retorna la información de un plan de estudios específico
	@Transactional(readOnly=true)
	public StudyPlan findStudyPlanByCode(String studyPlanCode) {
		StudyPlan studyPlan;
		try {
			Connection connection = dataSource.getConnection();
			
			//Trae la información básica del plan
			studyPlan = findStudyPlanBasicData(studyPlanCode, connection);
			
			//Pone los cursos del plan de estudios
			studyPlan.setCourses(courseData.findCoursesBasicDataByStudyPlanId(studyPlanCode));
			
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Connection Error");
		}

		return studyPlan;
	}
	
	
	
	//Trae los datos del plan de estudio 
	@Transactional(readOnly=true)
	private StudyPlan findStudyPlanBasicData(String studyPlanCode, Connection connection) throws SQLException {
		String query = "SELECT" + 
				" career_name," + 
				" approval_year," + 
				" is_active," + 
				" study_plan_document," + 
				" ucr_approval_document," + 
				" career_code," + 
				" levels_quantity," + 
				" study_plan_description" +
				" FROM study_plan" + 
				" WHERE study_plan_code = ?;";
		
		StudyPlan studyPlan = new StudyPlan();
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, studyPlanCode);
		
		ResultSet rs = statement.executeQuery();
		if(!rs.next())
			throw new RuntimeException("El código "+studyPlanCode+" del plan de estudio no existe.");
		
		//Pone los datos recolectados en el plan de estudio
		studyPlan.setStudyPlanCode(studyPlanCode);
		studyPlan.setStudyPlanResultSet(rs);
		
		//cierra conexiones
		statement.close();
		rs.close();
		
		return studyPlan;
	}
	

}
	
