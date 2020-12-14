package cr.ac.ucr.ie.sigie.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.domain.AcademicDegree;
import cr.ac.ucr.ie.sigie.domain.Professor;
import cr.ac.ucr.ie.sigie.domain.ProfessorBranchSituation;
import cr.ac.ucr.ie.sigie.domain.Specialization;
import cr.ac.ucr.ie.sigie.domain.TopicOfInterest;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@Repository
public class ProfessorData {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public Professor getProfessorByID1(int professorId) {
		String sqlSelect = "select * from professor where professor_id = ?";
		return jdbcTemplate.query(sqlSelect, new Object[] { professorId }, new ProfessorByIDExtractor());
	}
	@Transactional(readOnly = true)
	public List<Professor> getProfessors(Professor professor) {
		String sql = "select * from professor";
		List<Professor> professors =jdbcTemplate.query(sql,  new getProfessors());
		return professors;
	}
	@Transactional(readOnly = true)
	public Professor getProfessorByID(int professorId) {
        String sqlSelect = "select * from professor where professor_id = ?";
        Professor professor = jdbcTemplate
                .query(sqlSelect, new Object[] { professorId }, (rs, row) -> new Professor(rs.getString("name"), rs.getString("last_name"), 
                        rs.getString("academic_degree_acronym"), rs.getString("institutional_mail"))).get(0);
        professor.setProfessorId(professorId);
        String getProfessorBranchsituacion = "select ptoi.id_topic, toi.topic from topics_of_interest toi " + 
                "join professor_topic_of_interest ptoi on toi.id_topic = ptoi.id_topic " + 
                "where ptoi.professor_id= ?";
        professor.setTopicsOfInterest(jdbcTemplate.query(getProfessorBranchsituacion,new Object[] {professor.getProfessorId()}, new getTopicsOfInterest()));
        String getAcademicDegree ="select * from academic_degree ad " + 
                "join specialization s on s.id_specialization = ad.id_specialization " + 
                "where ad.professor_id = ?";
        professor.setAcademicDegrees(jdbcTemplate.query(getAcademicDegree,new Object[] {professor.getProfessorId()}, new getAcademicDergreeSpecialization()));
        String getProfessorBranchsituation = "select * from university_branch ub " + 
                "join professor_branch_situation pbs on ub.university_branch_id = pbs.university_branch_id " + 
                "where pbs.professor_id= ? ";
        professor.setBranchSituations(jdbcTemplate.query(getProfessorBranchsituation,new Object[] {professor.getProfessorId()}, new getProfessorBranchsituation()));
        return professor;
        
    }
	@Transactional
	public boolean updateProfessor(Professor professor) {
		boolean flag;
		String sqlInsert = "update professor set name=?,last_name=?, institutional_mail=?,academic_degree_acronym=? where professor_id = ?";
		 flag = jdbcTemplate.execute(sqlInsert, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, professor.getName());
				ps.setString(2, professor.getLastName());
				ps.setString(3, professor.getInstitutionalMail());
				ps.setString(4, professor.getAcademicDegreeAcronym());
				ps.setInt(5, professor.getProfessorId());
				return ps.execute();

			}
		});
		String sqlProfesorBranchsutuacion = "delete from professor_branch_situation where (professor_id = ? and university_branch_id = ?)";
		for (ProfessorBranchSituation pbs : professor.getBranchSituations()) {
			flag =	jdbcTemplate.execute(sqlProfesorBranchsutuacion, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setInt(1, professor.getProfessorId());
					ps.setInt(2, pbs.getBranch().getBranchId());
					return ps.execute();

				}
			});
		}
		String sqlInserNewsBranchsituation = "insert into professor_branch_situation value (?,?,?)";
		for (ProfessorBranchSituation pbs : professor.getBranchSituations()) {
			jdbcTemplate.execute(sqlInserNewsBranchsituation, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setInt(1, professor.getProfessorId());
					ps.setInt(2, pbs.getBranch().getBranchId());
					ps.setBoolean(3, pbs.isActive());
					return ps.execute();

				}
			});
		}

		String sqlTopicOfinterest = "update topic_of_interest set topic=? where topic_id = ?";
		for (TopicOfInterest toi : professor.getTopicsOfInterest()) {
			flag =jdbcTemplate.execute(sqlTopicOfinterest, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, toi.getTopicDescription());
					ps.setInt(2, toi.getTopicId());
					return ps.execute();

				}
			});
		}

		String sqlSpecialization = "update specialization set specializarion = ?, where id_specializatio = ? ";
		for (AcademicDegree ad : professor.getAcademicDegrees()) {
			flag =jdbcTemplate.execute(sqlSpecialization, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, ad.getSpecialization().getSpecialization());
					ps.setInt(2,ad.getSpecialization().getSpecializationId() );
					return ps.execute();

				}
			});

			String sqlAcademicDegree = "update academic_degree set certification_name = ?, certificated_by = ?, degree_acronym = ? where degree_id = ?";
			flag =jdbcTemplate.execute(sqlAcademicDegree, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, ad.getDegreeName());
					ps.setString(2, ad.getInstitutionName());
					ps.setString(3, ad.getDegreeAcronym());
					ps.setInt(4, ad.getDegreeId());
					return ps.execute();

				}
			});
		}

		return flag;
	}
	@Transactional(readOnly = true)
	public List<Professor> getProfessor() {
		String getProfessor = "select * from professor";
		List<Professor> professors = jdbcTemplate.query(getProfessor, new getProfessor());

		for (Professor professor : professors) {
			String getTopicOfinterest = "select ptoi.id_topic, toi.topic from topics_of_interest toi\r\n"
					+ "join professor_topic_of_interest ptoi on toi.id_topic = ptoi.id_topic\r\n"
					+ "where ptoi.professor_id= ?";
			professor.setTopicsOfInterest(jdbcTemplate.query(getTopicOfinterest,
					new Object[] { professor.getProfessorId() }, new getTopicsOfInterest()));
			String getAcademicDegree = "select * from academic_degree ad\r\n"
					+ "join specialization s on s.id_specialization = ad.id_specialization\r\n"
					+ "where ad.professor_id = ?\r\n";
			professor.setAcademicDegrees(jdbcTemplate.query(getAcademicDegree,
					new Object[] { professor.getProfessorId() }, new getAcademicDergreeSpecialization()));
			String getProfessorBranchsituation = "select * from university_branch ub\r\n"
					+ "join professor_branch_situation pbs on ub.university_branch_id = pbs.university_branch_id\r\n"
					+ "where pbs.professor_id= ?\r\n";
			professor.setBranchSituations(jdbcTemplate.query(getProfessorBranchsituation,
					new Object[] { professor.getProfessorId() }, new getProfessorBranchsituation()));

		}

		return professors;

	}
	@Transactional
	public Professor insertProfessor(Professor professor) {
		String sqlInsert = "insert into professor(name,last_name, institutional_mail,academic_degree_acronym) value(?,?,?,?)";
		jdbcTemplate.execute(sqlInsert, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, professor.getName());
				ps.setString(2, professor.getLastName());
				ps.setString(3, professor.getInstitutionalMail());
				ps.setString(4, professor.getAcademicDegreeAcronym());
				return ps.execute();

			}
		});
		String getProfessorId = "select LAST_INSERT_ID(professor_id) from professor order by(professor_id) desc LIMIT 1";
		int professorId = jdbcTemplate.query(getProfessorId, new getLastProfessorIdExtractor());

		String sqlProfesorBranchsutuacion = "insert into professor_branch_situation value (?,?,?)";
		for (ProfessorBranchSituation pbs : professor.getBranchSituations()) {
			jdbcTemplate.execute(sqlProfesorBranchsutuacion, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setInt(1, professorId);
					ps.setInt(2, pbs.getBranch().getBranchId());
					ps.setBoolean(3, pbs.isActive());
					return ps.execute();

				}
			});
		}

		String sqlTopicOfinterest = "insert into topic_of_interest value (?)";
		for (TopicOfInterest toi : professor.getTopicsOfInterest()) {
			jdbcTemplate.execute(sqlTopicOfinterest, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, toi.getTopicDescription());
					return ps.execute();

				}
			});
			String getTopicId = "select LAST_INSERT_ID(id_topic) from topic_of_interest order by(id_topic) desc LIMIT 1";
			int topicId = jdbcTemplate.query(getTopicId, new getLastTopicOfInterestIdExtractor());

			String sqlProfessorTopicOfinterest = "insert into professor_topic_of_interest value (?,?)";
			jdbcTemplate.execute(sqlProfessorTopicOfinterest, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setInt(1, professorId);
					ps.setInt(2, topicId);
					return ps.execute();

				}
			});
		}

		String sqlSpecialization = "insert into specialization value (?)";
		for (AcademicDegree ad : professor.getAcademicDegrees()) {
			jdbcTemplate.execute(sqlSpecialization, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, ad.getSpecialization().getSpecialization());
					return ps.execute();

				}
			});
			String getSpecializationId = "select LAST_INSERT_ID(id_specialization) from specialization order by(id_specialization) desc LIMIT 1";
			int specializationId = jdbcTemplate.query(getSpecializationId, new getSpecializationIdExtractor());

			String sqlAcademicDegree = "insert into academic_degree value (?,?,?,?,?)";
			jdbcTemplate.execute(sqlAcademicDegree, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, ad.getDegreeName());
					ps.setString(2, ad.getInstitutionName());
					ps.setString(3, ad.getDegreeAcronym());
					ps.setInt(4, professorId);
					ps.setInt(5, specializationId);
					return ps.execute();

				}
			});
		}

		return professor;

	}
	
	public List<Professor> findByIdBranch(int id)  {
		String sqlSelect = "select p.professor_id,p.name,p.last_name,p.institutional_mail,p.academic_degree_acronym, "
				+ "toi.topic,toi.id_topic," + "pbs.university_branch_id, pbs.active,u.name as universityBranchName "
				+ ",ad.degree_id, ad.certification_name, ad.certificated_by, ad.degree_acronym, ad.id_specialization,s.specializarion"
				+ " from professor  p, professor_topic_of_interest  pti, topics_of_interest  toi, professor_branch_situation pbs,"
				+ "academic_degree  ad,specialization s, university_branch u "
				+ "where p.professor_id=pti.professor_id and toi.id_topic=pti.id_topic and pbs.professor_id=p.professor_id and ad.professor_id=p.professor_id "
				+ " AND pbs.university_branch_id=u.university_branch_id AND ad.id_specialization=s.id_specialization and ad.professor_id=p.professor_id "
				+ " AND pbs.active=1 AND pbs.university_branch_id="+id;
		System.out.print(sqlSelect); 
		return jdbcTemplate.query(sqlSelect, new ProfessorExtractor());
	}

	class getSpecializationIdExtractor implements ResultSetExtractor<Integer> {
		@Override
		public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

			int specializationId = 0;
			while (rs.next()) {
				specializationId = rs.getInt("LAST_INSERT_ID(id_specialization)");

			} // while

			return specializationId;
		}
	}

	class getLastTopicOfInterestIdExtractor implements ResultSetExtractor<Integer> {
		@Override
		public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

			int topicId = 0;
			while (rs.next()) {
				topicId = rs.getInt("LAST_INSERT_ID(id_topic)");

			} // while

			return topicId;
		}
	}

	class getLastProfessorIdExtractor implements ResultSetExtractor<Integer> {
		@Override
		public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

			int professorId = 0;
			while (rs.next()) {
				professorId = rs.getInt("LAST_INSERT_ID(professor_id)");

			} // while

			return professorId;
		}
	}

	class getProfessor implements ResultSetExtractor<List<Professor>> {

		@Override
		public List<Professor> extractData(ResultSet rs) throws SQLException, DataAccessException {

			Map<Integer, Professor> map = new HashMap<>();
			Professor professor = null;
			while (rs.next()) {
				Integer professorId = rs.getInt("professor_id");
				professor = map.get(professorId);
				if (professor == null) {
					professor = new Professor();
					professor.setProfessorId(professorId);
					professor.setName(rs.getString("name"));
					professor.setLastName(rs.getString("last_name"));
					professor.setInstitutionalMail(rs.getString("institutional_mail"));
					professor.setAcademicDegreeAcronym(rs.getString("academic_degree_acronym"));
					map.put(professorId, professor);

				}
			}
			return new ArrayList<Professor>(map.values());
		}
	}

	class getTopicsOfInterest implements ResultSetExtractor<List<TopicOfInterest>> {

		@Override
		public List<TopicOfInterest> extractData(ResultSet rs) throws SQLException, DataAccessException {

			Map<Integer, TopicOfInterest> map = new HashMap<>();
			TopicOfInterest toi = null;
			while (rs.next()) {
				Integer topicId = rs.getInt("id_topic");
				toi = map.get(topicId);
				if (toi == null) {
					toi = new TopicOfInterest();
					toi.setTopicId(topicId);
					toi.setTopicDescription(rs.getString("topic"));
					map.put(topicId, toi);

				}
			}
			return new ArrayList<TopicOfInterest>(map.values());
		}
	}

	class ProfessorByIDExtractor implements ResultSetExtractor<Professor> {

		@Override
		public Professor extractData(ResultSet rs) throws SQLException, DataAccessException {

			Professor professor = null;
			while (rs.next()) {
				Integer professorId = rs.getInt("professor_id");
				// professor = map.get(professorId);
				if (professor == null) {
					professor = new Professor();
					professor.setProfessorId(professorId);
					professor.setName(rs.getString("name"));
					professor.setLastName(rs.getString("last_name"));
					professor.setInstitutionalMail(rs.getString("institutional_mail"));
					professor.setAcademicDegreeAcronym(rs.getString("academic_degree_acronym"));
					// map.put(professorId, professor);
				}
			} // while

			return professor;
		}
	}

	class getAcademicDergreeSpecialization implements ResultSetExtractor<List<AcademicDegree>> {

		@Override
		public List<AcademicDegree> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, AcademicDegree> map = new HashMap<Integer, AcademicDegree>();
			AcademicDegree ad = null;
			while (rs.next()) {
				Integer academicId = rs.getInt("degree_id");
				ad = map.get(academicId);
				if (ad == null) {
					ad = new AcademicDegree();
					ad.setDegreeId(academicId);
					ad.setDegreeName(rs.getString("certification_name"));
					ad.setInstitutionName(rs.getString("certificated_by"));
					ad.setDegreeAcronym(rs.getString("degree_acronym"));
					map.put(academicId, ad);
				}
				Specialization specialization = new Specialization();
				specialization.setSpecializationId(rs.getInt("id_specialization"));
				specialization.setSpecialization(rs.getString("specializarion"));
				ad.setSpecialization(specialization);
			} // while

			return new ArrayList<AcademicDegree>(map.values());
		}
			

	}

	class getProfessorBranchsituation implements ResultSetExtractor<List<ProfessorBranchSituation>> {

		@Override
		public List<ProfessorBranchSituation> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, ProfessorBranchSituation> map = new HashMap<Integer, ProfessorBranchSituation>();
			ProfessorBranchSituation pbs = null;
			while (rs.next()) {
				Integer professorBranchId = rs.getInt("university_branch_id");
				pbs = map.get(professorBranchId);
				if (pbs == null) {
					pbs = new ProfessorBranchSituation();
					pbs.setActive(rs.getBoolean("active"));
					map.put(professorBranchId, pbs);
				}
				UniversityBranch universityBranch = new UniversityBranch();
				universityBranch.setBranchId(rs.getInt("university_branch_id"));
				universityBranch.setName(rs.getString("name"));
				pbs.setBranch(universityBranch);
			} // while

			return new ArrayList<ProfessorBranchSituation>(map.values());
		}

	}
	class getProfessors implements ResultSetExtractor<List<Professor>> {

		@Override
		public List<Professor> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Professor> map = new HashMap<Integer, Professor>();
			Professor professor = null;
			while (rs.next()) {
				Integer professorId = rs.getInt("professor_id");
				professor = map.get(professorId);
				if (professor == null) {
					professor = new Professor();
					professor.setProfessorId(professorId);
					professor.setName(rs.getString("name"));
					professor.setLastName(rs.getString("last_name"));
					professor.setInstitutionalMail(rs.getString("institutional_mail"));
					professor.setAcademicDegreeAcronym(rs.getString("academic_degree_acronym"));
					map.put(professorId, professor);
				}
			} // while

			return new ArrayList<Professor>(map.values());
		}

	}
	
	class ProfessorExtractor implements ResultSetExtractor<List<Professor>> {

		@Override
		public List<Professor> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Professor> mapP = new HashMap<Integer, Professor>();
			Map<Integer, AcademicDegree> mapA = new HashMap<Integer, AcademicDegree>();
			Map<Integer, TopicOfInterest> mapT = new HashMap<Integer, TopicOfInterest>();
			Map<Integer, ProfessorBranchSituation> mapB = new HashMap<Integer, ProfessorBranchSituation>();
			Professor professor = null;
			AcademicDegree academicDegree = null;
			TopicOfInterest topicOfInterest=null;
			ProfessorBranchSituation professorBranchSituation=null;

			while (rs.next()) {
				Integer professorId = rs.getInt("professor_id");
				professor = mapP.get(professorId);
				if (professor == null) {
					mapT.clear();
					mapB.clear();
					mapA.clear();
					professor = new Professor();
					professor.setProfessorId(professorId);
					professor.setAcademicDegreeAcronym(rs.getString("academic_degree_acronym"));
					professor.setInstitutionalMail(rs.getString("institutional_mail"));
					professor.setLastName(rs.getString("last_name"));
					professor.setName(rs.getString("name"));
					mapP.put(professorId, professor);

				}
				Integer academicDegreeId = rs.getInt("degree_id");
				academicDegree = mapA.get(academicDegreeId);
				if(academicDegree==null) {
					academicDegree=new AcademicDegree();
					academicDegree.setDegreeId(academicDegreeId);
					academicDegree.setDegreeAcronym(rs.getString("degree_acronym"));
					academicDegree.setDegreeName(rs.getString("certification_name"));
					academicDegree.setInstitutionName(rs.getString("certificated_by"));
					academicDegree.getSpecialization().setSpecializationId(rs.getInt("id_specialization"));
					academicDegree.getSpecialization().setSpecialization(rs.getString("specializarion"));
					mapA.put(academicDegreeId, academicDegree);
					professor.getAcademicDegrees().add(academicDegree);
				}
				Integer topicOfInterestId=rs.getInt("id_topic");
				topicOfInterest=mapT.get(topicOfInterestId);
				if(topicOfInterest==null) {
					topicOfInterest=new TopicOfInterest();
					topicOfInterest.setTopicId(topicOfInterestId);
					topicOfInterest.setTopicDescription(rs.getString("topic"));
					mapT.put(topicOfInterestId, topicOfInterest);
					professor.getTopicsOfInterest().add(topicOfInterest);
				}
				Integer univerisityBranchId=rs.getInt("university_branch_id");
				professorBranchSituation=mapB.get(univerisityBranchId);
				if(professorBranchSituation==null) {
					professorBranchSituation=new ProfessorBranchSituation();
					UniversityBranch universityBranch=new UniversityBranch();
					universityBranch.setBranchId(univerisityBranchId);
					universityBranch.setName(rs.getString("universityBranchName"));
					professorBranchSituation.setBranch(universityBranch);
					mapB.put(univerisityBranchId, professorBranchSituation);
					professor.getBranchSituations().add(professorBranchSituation);
					
				}
			}
			return new ArrayList<Professor>(mapP.values());
		}
	}
}
