package cr.ac.ucr.ie.sigie.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.domain.Area;
import cr.ac.ucr.ie.sigie.domain.Course;
import cr.ac.ucr.ie.sigie.domain.Emphasis;

@Repository
public class CourseData {

	@Autowired
	private DataSource dataSource;

	@Transactional
	public Course create(Course course, String studyPlanCode) {

		Connection conexion = null;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSaveCourse = conexion.prepareCall("{call save_course(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			csSaveCourse.setString(1, course.getCourseId());
			csSaveCourse.setString(2, course.getName());
			csSaveCourse.setInt(3, course.getLevel());
			csSaveCourse.setInt(4, course.getCredits());
			csSaveCourse.setString(5, course.getArea().getAreaId());
			csSaveCourse.setBoolean(6, course.isElective());
			csSaveCourse.setInt(7, course.getHoursTheory());
			csSaveCourse.setInt(8, course.getHoursPractice());
			csSaveCourse.setInt(9, course.getHoursLab());
			csSaveCourse.setInt(10, course.getHoursTheoryPractice());

			CallableStatement csSaveCourseStudyPlan = conexion.prepareCall("{call addStudyPlanCourse(?, ?)}");
			csSaveCourseStudyPlan.setString(1, course.getCourseId());
			csSaveCourseStudyPlan.setString(2, studyPlanCode);

			csSaveCourseStudyPlan.execute();
			csSaveCourse.execute();
			csSaveCourseStudyPlan.close();
			csSaveCourse.close();

			for (int i = 0; i < course.getEmphasiss().size(); i++) {
				CallableStatement csSaveCourseEmphasiss = conexion.prepareCall("{call save_course_emphasis(?, ?)}");
				csSaveCourseEmphasiss.setString(1, course.getCourseId());
				csSaveCourseEmphasiss.setString(2, course.getEmphasiss().get(1).getEmphasisId());
				csSaveCourseEmphasiss.execute();
				csSaveCourseEmphasiss.close();
			}

			for (int i = 0; i < course.getChildrenElectiveCourses().size(); i++) {
				CallableStatement csSaveElectiveCourses = conexion.prepareCall("{call save_elective_course(?, ?)}");
				csSaveElectiveCourses.setString(1, course.getChildrenElectiveCourses().get(i).getCourseId());
				csSaveElectiveCourses.setString(2, course.getCourseId());
				csSaveElectiveCourses.execute();
				csSaveElectiveCourses.close();
			}

			for (int i = 0; i < course.getCorequisites().size(); i++) {
				CallableStatement csSaveCorrequisiteCourse = conexion
						.prepareCall("{call save_corequisite_course(?,?)}");
				csSaveCorrequisiteCourse.setString(1, course.getCorequisites().get(i).getCourseId());
				csSaveCorrequisiteCourse.setString(2, course.getCourseId());
				csSaveCorrequisiteCourse.execute();
				csSaveCorrequisiteCourse.close();
			}

			for (int i = 0; i < course.getRequisites().size(); i++) {
				CallableStatement csSaveRequisiteCourse = conexion.prepareCall("{call save_requisite_course(?,?)}");
				csSaveRequisiteCourse.setString(1, course.getRequisites().get(i).getCourseId());
				csSaveRequisiteCourse.setString(2, course.getCourseId());
				csSaveRequisiteCourse.execute();
				csSaveRequisiteCourse.close();
			}

			conexion.commit();
		} catch (SQLException e) {
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
		return course;
	}

	@Transactional(readOnly = true)
	public List<Course> findCoursesByStudyPlanCode(String studyPlanCode) {
		Connection conexion = null;
		List<Course> courses;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectCourses = conexion.prepareCall("{call getCoursesByStudyPlan (?)}");
			csSelectCourses.setString(1, studyPlanCode);
			csSelectCourses.execute();

			conexion.commit();

			ResultSet rs = csSelectCourses.getResultSet();

			courses = new ArrayList<>();
			Course course = new Course();
			while (rs.next()) {
				course.setCourseId(rs.getString("course_id"));
				course.setName(rs.getString("name"));
				course.setLevel(rs.getInt("level"));
				courses.add(course);
				course = new Course();
			}
			csSelectCourses.close();
		} catch (SQLException e) {
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
		return courses;
	}

	@Transactional(readOnly = true)
	public List<Course> findElectiveCoursesByStudyPlanCode(String studyPlanCode) {
		Connection conexion = null;
		List<Course> courses;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectCourses = conexion.prepareCall("{call getElectiveCoursesByStudyPlan (?)}");
			csSelectCourses.setString(1, studyPlanCode);
			csSelectCourses.execute();

			conexion.commit();

			ResultSet rs = csSelectCourses.getResultSet();

			courses = new ArrayList<>();
			Course course = new Course();
			List<Course> electives;
			while (rs.next()) {
				course.setCourseId(rs.getString("course_id"));
				course.setName(rs.getString("name"));
				course.setLevel(rs.getInt("level"));
				CallableStatement csElectives = conexion
						.prepareCall("{call getElectiveCoursesByStudyPlanAndCourse(?)}");
				csElectives.setString(1, course.getCourseId());
				csElectives.execute();

				ResultSet rs2 = csElectives.getResultSet();

				electives = new ArrayList<>();
				Course elective = new Course();
				while (rs2.next()) {
					elective.setCourseId(rs2.getString("course_id"));
					elective.setName(rs2.getString("name"));
					elective.setLevel(rs2.getInt("level"));
					electives.add(elective);
					elective = new Course();
				}
				course.setChildrenElectiveCourses(electives);
				csElectives.close();
				courses.add(course);
				course = new Course();
			}

			csSelectCourses.close();
		} catch (SQLException e) {
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
		return courses;
	}

	@Transactional(readOnly = true)
	public List<Emphasis> findAllEmphasis() {
		Connection conexion = null;
		List<Emphasis> emphasiss;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectEmphasis = conexion.prepareCall("{call select_emphasis ()}");
			csSelectEmphasis.execute();

			conexion.commit();

			ResultSet rs = csSelectEmphasis.getResultSet();

			emphasiss = new ArrayList<>();
			Emphasis emphasis = new Emphasis();
			while (rs.next()) {
				emphasis.setEmphasisId(rs.getString("emphasis_id"));
				emphasis.setEmphasisName(rs.getString("name"));
				emphasiss.add(emphasis);
				emphasis = new Emphasis();
			}
			csSelectEmphasis.close();
		} catch (SQLException e) {
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
		return emphasiss;
	}

	// Este método solo obtine la sigla, el nombre y el nivel del curso para
	// mostrarlos al usuario
	@Transactional(readOnly = true)
	public List<Course> findAllCourses() {
		Connection conexion = null;
		List<Course> courses;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectCourses = conexion.prepareCall("{call select_courses ()}");
			csSelectCourses.execute();

			conexion.commit();

			ResultSet rs = csSelectCourses.getResultSet();

			courses = new ArrayList<>();
			Course course = new Course();
			while (rs.next()) {
				course.setCourseId(rs.getString("course_id"));
				course.setName(rs.getString("name"));
				course.setLevel(rs.getInt("level"));
				course.setCredits(rs.getInt("credits"));
				Area area1 = new Area();
				area1.setAreaId(rs.getString("area_id"));
				course.setArea(area1);
				course.setElective(rs.getBoolean("elective"));
				course.setHoursTheory(rs.getInt("hours_theory"));
				course.setHoursPractice(rs.getInt("hours_practice"));
				course.setHoursLab(rs.getInt("hours_laboratory"));
				course.setHoursTheoryPractice(rs.getInt("hours_theory_practice"));
				courses.add(course);
				course = new Course();
			}
			csSelectCourses.close();
		} catch (SQLException e) {
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
		return courses;
	}

	// FEA#2 Albin-Josue
	// Trae los datos basico de los cursos por el codigo del plan de estudios
	@Transactional(readOnly = true)
	public List<Course> findCoursesBasicDataByStudyPlanId(String studyPlanCode) {
		String query = "SELECT" 
					 + " c.course_id," 
					 + " c.name," 
					 + " c.credits," 
					 + " c.level," 
					 + " c.elective," 
					 + " a.name"
					 + " FROM course c JOIN course_study_plan csp" 
					 + " ON csp.course_id = c.course_id"
					 + " JOIN area a ON c.area_id = a.area_id" 
					 + " WHERE csp.study_plan_code = ?;";

		ArrayList<Course> coursesList = new ArrayList<>();

		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, studyPlanCode);

			ResultSet rs = statement.executeQuery();
			String newCourseId;
			
			
			while (rs.next()) {// Agrega todos los cursos a la lista
				Course newCourse = new Course();
				// pone los datos del curso que tiene el resultset
				newCourse.setCourseResultSet(rs);

				newCourseId = newCourse.getCourseId();

				// Setea los requisitos y corequisitos
				newCourse.setRequisites(findCoursesRequisitesById(newCourseId, connection));
				newCourse.setCorequisites(findCoursescorequisitesById(newCourseId, connection));

				if (newCourse.isElective())
					newCourse.setChildrenElectiveCourses(findElectiveCoursesById(newCourseId, connection));
				
				newCourse.setEmphasiss(findEmphasisById(newCourseId, connection));
				coursesList.add(newCourse);
			}
			// cierra conexiones
			statement.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Connection Error");
		}

		return coursesList;
	}

	// Trae los correquisitos asociados al un curso
	@Transactional(readOnly = true)
	private List<Course> findCoursescorequisitesById(String courseId, Connection connection) throws SQLException {
		String query = "SELECT" 
					 + " cc.course_id_corequisite" 
					 + " FROM course c JOIN corequisite_course cc"
					 + " ON c.course_id = cc.course_id" 
					 + " WHERE c.course_id = ?;";
		ArrayList<Course> corequisitesList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, courseId);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Course newCourse = new Course();
			newCourse.setCourseId(rs.getString("cc.course_id_corequisite"));
			corequisitesList.add(newCourse);
		}
		// cierra conexiones
		statement.close();
		rs.close();

		return corequisitesList;
	}

	// Trae los requisitos asociados al un curso
	@Transactional(readOnly = true)
	private List<Course> findCoursesRequisitesById(String courseId, Connection connection) throws SQLException {
		String query = "SELECT" 
					 + " rc.course_id_requisite"
					 + " FROM course c JOIN requisite_course rc"
					 + " ON c.course_id = rc.course_id" 
					 + " WHERE c.course_id = ?;";
		ArrayList<Course> requisitesList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, courseId);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Course newCourse = new Course();
		
			newCourse.setCourseId(rs.getString("rc.course_id_requisite"));

			requisitesList.add(newCourse);
		}
		// cierra conexiones
		statement.close();
		rs.close();

		return requisitesList;
	}

	// Trae los cursos electivos asociados al un curso
	@Transactional(readOnly = true)
	private List<Course> findElectiveCoursesById(String courseId, Connection connection) throws SQLException {
		String query = "SELECT" 
					 + " ec.course_id_elective,"
					 + " c.name,"
					 + " c.credits" 
					 + " FROM  elective_course ec JOIN course c"
					 + " ON ec.course_id_elective = c.course_id" 
					 + " WHERE ec.course_id = ?;";
		ArrayList<Course> electiveCoursesList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, courseId);

		ResultSet rs = statement.executeQuery();
		
		String newCourseId;
		while (rs.next()) {
			Course newCourse = new Course();
			
			newCourseId = rs.getString("ec.course_id_elective");
					
			newCourse.setCourseId(newCourseId);
			newCourse.setCredits(rs.getInt("c.credits"));
			newCourse.setName(rs.getString("c.name"));
			
			// Setea los requisitos y corequisitos
			newCourse.setRequisites(findCoursesRequisitesById(newCourseId, connection));
			newCourse.setCorequisites(findCoursescorequisitesById(newCourseId, connection));
			electiveCoursesList.add(newCourse);
		}
		// cierra conexiones
		statement.close();
		rs.close();

		return electiveCoursesList;
	}

	private List<Emphasis> findEmphasisById(String courseId, Connection connection) throws SQLException {
		String query = "SELECT" 
					 + " e.emphasis_id," 
					 + " e.name" 
					 + " FROM emphasis e join course_emphasis ce"
					 + " ON e.emphasis_id = ce.emphasis_id" 
					 + " WHERE ce.course_id = ?;";

		ArrayList<Emphasis> emphasisList = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, courseId);

		ResultSet rs = statement.executeQuery();


		while (rs.next()) {
			Emphasis newEmphasis = new Emphasis();
			newEmphasis.setEmphasisId(rs.getString("e.emphasis_id"));
			newEmphasis.setEmphasisName(rs.getString("e.name"));

			emphasisList.add(newEmphasis);
		}
		// cierra conexiones
		statement.close();
		rs.close();

		return emphasisList;
	}

}
