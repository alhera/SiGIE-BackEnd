package cr.ac.ucr.ie.sigie.domain;

public class EventType {
	private int eventTypeId;
	private String eventTypeName;
	
	public EventType() {
		super();
	}

	public EventType(int eventTypeId, String eventTypeName) {
		super();
		this.eventTypeId = eventTypeId;
		this.eventTypeName = eventTypeName;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	
	
	
	

}
