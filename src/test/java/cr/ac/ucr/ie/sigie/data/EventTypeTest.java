package cr.ac.ucr.ie.sigie.data;

import static org.junit.jupiter.api.Assertions.*;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.ie.sigie.data.EventTypeData;
import cr.ac.ucr.ie.sigie.domain.EventType;
@SpringBootTest
class EventTypeTest {
	@Autowired
	EventTypeData eventTypeData;
	@Test
	void findAll() {
		
		List<EventType> events=eventTypeData.findAll();
		
		for(int i=0;i<events.size();i++) {
			System.out.println(events.get(i).getEventTypeId()+events.get(i).getEventTypeName());
		}
		
		assertTrue(eventTypeData.findAll().size() > 0);
	}

}
