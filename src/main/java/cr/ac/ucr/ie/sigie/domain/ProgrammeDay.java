package cr.ac.ucr.ie.sigie.domain;

import java.sql.Date;
import java.util.List;

public class ProgrammeDay {
	private Date eventDayDate;
	private List<EventActivity> eventActivities;
	private int programmeDayId;

	public ProgrammeDay() {
		super();
	}

	public ProgrammeDay(Date eventDayDate, List<EventActivity> eventActivities, int programmeDayId) {
		super();
		this.eventDayDate = eventDayDate;
		this.eventActivities = eventActivities;
		this.programmeDayId = programmeDayId;
	}

	public Date getEventDayDate() {
		return eventDayDate;
	}

	public void setEventDayDate(Date eventDayDate) {
		this.eventDayDate = eventDayDate;
	}

	public List<EventActivity> getEventActivities() {
		return eventActivities;
	}

	public void setEventActivities(List<EventActivity> eventActivities) {
		this.eventActivities = eventActivities;
	}

	public int getProgrammeDayId() {
		return programmeDayId;
	}

	public void setProgrammeDayId(int programmeDayId) {
		this.programmeDayId = programmeDayId;
	}

	

	
	
	
	
}
