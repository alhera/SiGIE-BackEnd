package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.EventTypeData;
import cr.ac.ucr.ie.sigie.domain.EventType;

@Service
public class EventTypeBusiness {
	
	@Autowired
	EventTypeData eventTypeData;
	public List<EventType> findAll(){
		
		return eventTypeData.findAll();
	}
}
