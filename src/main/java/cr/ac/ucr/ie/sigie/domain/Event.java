package cr.ac.ucr.ie.sigie.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Event {
	private int eventId;
	private String eventName;
	private Date eventDate;
	private String audience;
	private String synopsis;
	private String participantRequirements;
	private String responsibleName;
	private String responsibleEmail;
	private String responsibleLastName;
	private boolean bookingRequired;
	private String onlineBookingURL;
	private String additionalInformationURL;
	private boolean haveProgramme;
	private Time startTime;
	private String status;
	private EventType eventType;
	private List<ProgrammeDay> programmeDays;
	private UniversityBranch branch;
	
	public Event() {
		super();
	}

	public Event(int eventId, String eventName, Date eventDate, String audience, String synopsis,
			String participantRequirements, String responsibleName, String responsibleEmail, String responsibleLastName,
			boolean bookingRequired, String onlineBookingURL, String additionalInformationURL, boolean haveProgramme,
			Time startTime, String status, EventType eventType, List<ProgrammeDay> programmeDays, UniversityBranch branch) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.audience = audience;
		this.synopsis = synopsis;
		this.participantRequirements = participantRequirements;
		this.responsibleName = responsibleName;
		this.responsibleEmail = responsibleEmail;
		this.responsibleLastName = responsibleLastName;
		this.bookingRequired = bookingRequired;
		this.onlineBookingURL = onlineBookingURL;
		this.additionalInformationURL = additionalInformationURL;
		this.haveProgramme = haveProgramme;
		this.startTime = startTime;
		this.status = status;
		this.eventType = eventType; 
		this.programmeDays = programmeDays;
		this.branch = branch;
		
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getParticipantRequirements() {
		return participantRequirements;
	}

	public void setParticipantRequirements(String participantRequirements) {
		this.participantRequirements = participantRequirements;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getResponsibleEmail() {
		return responsibleEmail;
	}

	public void setResponsibleEmail(String responsibleEmail) {
		this.responsibleEmail = responsibleEmail;
	}

	public String getResponsibleLastName() {
		return responsibleLastName;
	}

	public void setResponsibleLastName(String responsibleLastName) {
		this.responsibleLastName = responsibleLastName;
	}

	public boolean isBookingRequired() {
		return bookingRequired;
	}

	public void setBookingRequired(boolean bookingRequired) {
		this.bookingRequired = bookingRequired;
	}

	public String getOnlineBookingURL() {
		return onlineBookingURL;
	}

	public void setOnlineBookingURL(String onlineBookingURL) {
		this.onlineBookingURL = onlineBookingURL;
	}

	public String getAdditionalInformationURL() {
		return additionalInformationURL;
	}

	public void setAdditionalInformationURL(String additionalInformationURL) {
		this.additionalInformationURL = additionalInformationURL;
	}

	public boolean isHaveProgramme() {
		return haveProgramme;
	}

	public void setHaveProgramme(boolean haveProgramme) {
		this.haveProgramme = haveProgramme;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public List<ProgrammeDay> getProgrammeDays() {
		return programmeDays;
	}

	public void setProgrammeDays(List<ProgrammeDay> programmeDays) {
		this.programmeDays = programmeDays;
	}

	public UniversityBranch getBranch() {
		return branch;
	}

	public void setBranch(UniversityBranch branch) {
		this.branch = branch;
	}

	
}
