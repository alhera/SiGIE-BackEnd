package cr.ac.ucr.ie.sigie.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.ProgrammeDay;

@Repository
public class ProgrammeDayData {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	private EventActivityData eventActivityData;
	
	
	
	public ProgrammeDayData(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}



	public ArrayList<ProgrammeDay> findByEventId(Integer actualEventId) {
		Connection conexion = null;
		ArrayList<ProgrammeDay> programmeDays = new ArrayList<>();
		ProgrammeDay programmeDay = null;
		this.eventActivityData = new EventActivityData(dataSource);

		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);

			CallableStatement csProgrammeDays = conexion.prepareCall("{call getProgrammeDays(?)}");
			csProgrammeDays.setInt("event_id", actualEventId);
			csProgrammeDays.execute();

			ResultSet rs = csProgrammeDays.getResultSet();
			conexion.commit();

			while (rs.next()) {
				Integer actualProgrammeId = rs.getInt("programme_day_id");
				if (programmeDay == null) {
					programmeDay = new ProgrammeDay();
					programmeDay.setProgrammeDayId(actualProgrammeId);
					programmeDay.setEventDayDate(rs.getDate("event_day_date"));
					programmeDay.setEventActivities(this.eventActivityData.findByProgrammeId(actualProgrammeId));

					programmeDays.add(programmeDay);
					programmeDay = null;
				}

			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programmeDays;
	}

}
