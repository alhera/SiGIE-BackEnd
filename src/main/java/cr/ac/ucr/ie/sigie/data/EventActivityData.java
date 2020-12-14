package cr.ac.ucr.ie.sigie.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.EventActivity;

@Repository
public class EventActivityData {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;	
	
	public EventActivityData(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<EventActivity> findByProgrammeId(Integer actualProgrammeId) {
		Connection conexion = null;
		ArrayList<EventActivity> eventActivities = new ArrayList<>();
		EventActivity eventActivity = null;

		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);

			CallableStatement csEventActivities = conexion.prepareCall("{call getEventActivities(?)}");
			csEventActivities.setInt("programme_day_id", actualProgrammeId);
			csEventActivities.execute();

			ResultSet rs = csEventActivities.getResultSet();
			conexion.commit();

			while (rs.next()) {
				Integer actualActivityId = rs.getInt("activity_id");
				if (eventActivity == null) {
					eventActivity = new EventActivity();
					eventActivity.setActivityId(actualActivityId);
					eventActivity.setActivityName(rs.getString("activity_name"));
					eventActivity.setActivityStartTime(rs.getTime("activity_start_time"));
					eventActivity.setActivityEndingTime(rs.getTime("activity_ending_time"));
					eventActivity.setVenue(rs.getString("venue"));

					eventActivities.add(eventActivity);
					eventActivity = null;
				}
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventActivities;
	}
}
