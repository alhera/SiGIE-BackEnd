package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cr.ac.ucr.ie.sigie.data.EventData;
import cr.ac.ucr.ie.sigie.domain.Event;

@Service
public class EventBusiness {
	@Autowired
	private EventData eventData;
	
	public Event save(Event event) {
		return eventData.save(event);
	}
	
	public List<Event> findAll(){
		return eventData.findAll();
	}
	
	public List<Event> findByTypeId(int eventTypeId){
		return eventData.findByEventTypeId(eventTypeId);
	}
	
	public List<Event> findByBranchId(int branchId){
		return eventData.findByBranchId(branchId);
	}
	
	public List<Event> findByStatus(String status){
		return eventData.findByStatus(status);
	}
	
}
