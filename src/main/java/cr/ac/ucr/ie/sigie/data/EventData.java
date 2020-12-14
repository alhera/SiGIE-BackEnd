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

import cr.ac.ucr.ie.sigie.domain.Event;
import cr.ac.ucr.ie.sigie.domain.EventActivity;
import cr.ac.ucr.ie.sigie.domain.EventType;
import cr.ac.ucr.ie.sigie.domain.ProgrammeDay;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@Repository
public class EventData {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	private ProgrammeDayData programmeDayData;

	public Event save(Event event) {

		Connection conexion = null;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);

			CallableStatement csInsert = conexion.prepareCall("{CALL addEvent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)};");

			csInsert.setString(2, event.getEventName());
			csInsert.setDate(3, event.getEventDate());
			csInsert.setString(4, event.getAudience());
			csInsert.setString(5, event.getSynopsis());
			csInsert.setString(6, event.getParticipantRequirements());
			csInsert.setString(7, event.getResponsibleName());
			csInsert.setString(8, event.getResponsibleLastName());
			csInsert.setString(9, event.getResponsibleEmail());
			csInsert.setBoolean(10, event.isBookingRequired());
			csInsert.setString(11, event.getOnlineBookingURL());
			csInsert.setString(12, event.getAdditionalInformationURL());
			csInsert.setBoolean(13, event.isHaveProgramme());
			csInsert.setTime(14, event.getStartTime());
			csInsert.setString(15, event.getStatus());

			csInsert.setInt(16, event.getEventType().getEventTypeId());
			csInsert.setInt(17, event.getBranch().getBranchId());

			csInsert.registerOutParameter("event_id", java.sql.Types.INTEGER);
			csInsert.executeUpdate();
			int idEvent = csInsert.getInt("event_id");

			if (event.isHaveProgramme()) {

				for (ProgrammeDay programmeDay : event.getProgrammeDays()) {
					CallableStatement csInsertProgrammeDay = conexion.prepareCall("{CALL addProgrammeDay(?,?,?)};");
					csInsertProgrammeDay.setInt(2, idEvent);
					csInsertProgrammeDay.setDate(3, programmeDay.getEventDayDate());

					csInsertProgrammeDay.registerOutParameter(1, java.sql.Types.INTEGER);
					csInsertProgrammeDay.executeUpdate();
					int idProgrammeDay = csInsertProgrammeDay.getInt(1);

					for (EventActivity eventActivity : programmeDay.getEventActivities()) {
						CallableStatement csInsertEventActivity = conexion
								.prepareCall("{CALL addEventActivity(?,?,?,?,?,?)};");
						csInsertEventActivity.setInt(2, idProgrammeDay);
						csInsertEventActivity.setString(3, eventActivity.getActivityName());
						csInsertEventActivity.setTime(4, eventActivity.getActivityStartTime());
						csInsertEventActivity.setTime(5, eventActivity.getActivityEndingTime());
						csInsertEventActivity.setString(6, eventActivity.getVenue());
						csInsertEventActivity.executeUpdate();
					}

				}
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
		return event;
	}

	public List<Event> findAll() {
		Connection conexion = null;
		ArrayList<Event> events = new ArrayList<>();
		Event event = null;
		this.programmeDayData = new ProgrammeDayData(dataSource);

		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);

			CallableStatement csEvents = conexion.prepareCall("{call getAllEvents()}");
			csEvents.execute();

			ResultSet rs = csEvents.getResultSet();
			conexion.commit();

			while (rs.next()) {
				Integer actualEventId = rs.getInt("event_id");
				if (event == null) {
					event = new Event();
					event.setEventId(actualEventId);
					event.setEventName(rs.getString("event_name"));
					event.setEventDate(rs.getDate("event_date"));
					event.setAudience(rs.getString("audience"));
					event.setSynopsis(rs.getString("synopsis"));
					event.setParticipantRequirements(rs.getString("participant_requirements"));
					event.setResponsibleName(rs.getString("responsible_name"));
					event.setResponsibleLastName(rs.getString("responsible_lastname"));
					event.setResponsibleEmail(rs.getString("responsible_email"));
					event.setBookingRequired(rs.getBoolean("booking_required"));
					event.setOnlineBookingURL(rs.getString("online_booking_url"));
					event.setAdditionalInformationURL(rs.getString("additional_information_url"));
					event.setHaveProgramme(rs.getBoolean("have_programme"));
					event.setStartTime(rs.getTime("start_time"));
					event.setStatus(rs.getString("status"));
					event.setEventType(new EventType(rs.getInt("event_type_id"), rs.getString("event_type_name")));
					event.setBranch(new UniversityBranch(rs.getInt("branch_id"), rs.getString("name")));

					if (rs.getBoolean("have_programme")) {
						ArrayList<ProgrammeDay> programmeDays = this.programmeDayData.findByEventId(actualEventId);
						event.setProgrammeDays(programmeDays);
					}
					events.add(event);
					event = null;

				}
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}


	public List<Event> findByEventTypeId(int eventTypeId) {
		ArrayList<Event> events = (ArrayList<Event>) findAll();
		ArrayList<Event> eventsByType = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);

			if (event.getEventType().getEventTypeId() == eventTypeId) {
				eventsByType.add(event);
			}

		}
		return eventsByType;
	}

	public List<Event> findByBranchId(int branchId) {
		ArrayList<Event> events = (ArrayList<Event>) findAll();
		ArrayList<Event> eventsByBranch = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);

			if (event.getBranch().getBranchId() == branchId) {
				eventsByBranch.add(event);
			}

		}
		return eventsByBranch;
	}

	public List<Event> findByStatus(String status) {
		ArrayList<Event> events = (ArrayList<Event>) findAll();
		ArrayList<Event> eventsByStatus = new ArrayList<>();
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);

			if (event.getStatus().equals(status)) {
				eventsByStatus.add(event);
			}

		}
		return eventsByStatus;
	}
}
