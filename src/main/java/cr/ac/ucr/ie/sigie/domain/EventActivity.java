package cr.ac.ucr.ie.sigie.domain;

import java.sql.Time;

public class EventActivity {
	
	
    private int activityId;
    private String activityName;
    private Time activityStartTime;
    private Time activityEndingTime;
    private String venue;
    
    
	public EventActivity() {
		super();
	}
	public EventActivity(int activityId, String activityName, Time activityStartTime, Time activityEndingTime,
			String venue) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityStartTime = activityStartTime;
		this.activityEndingTime = activityEndingTime;
		this.venue = venue;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public Time getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(Time activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	
	public Time getActivityEndingTime() {
		return activityEndingTime;
	}
	public void setActivityEndingTime(Time activityEndingTime) {
		this.activityEndingTime = activityEndingTime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
    
    
    
    
    
}
