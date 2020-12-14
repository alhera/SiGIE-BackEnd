package cr.ac.ucr.ie.sigie.data;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.ie.sigie.domain.Event;
import cr.ac.ucr.ie.sigie.domain.EventActivity;
import cr.ac.ucr.ie.sigie.domain.EventType;
import cr.ac.ucr.ie.sigie.domain.ProgrammeDay;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;


@SpringBootTest
public class EventDataTest {
	
	@Autowired 
	private EventData eventData;
	
	@Test
	void eventWithoutProgramme() {
		
		EventType eventType= new EventType(1,"Conferencias");
		UniversityBranch branch = new UniversityBranch(1,"Recinto Paraiso");
		
		eventData.save(new Event(0,"PB-JU-Evento 2", null, "Estudiantes","Esta es una prueba con JUnit", "","Alvaro","Mena Monge","alvaromena@ucr.ac.cr",true,"https://stackoverflow.com/questions/3142444/stored-procedure-return-identity-as-output-parameter-or-scalar","",false,null,"AP",eventType,null,branch));
	}

	@Test
	void eventWithProgramme() {
		
		EventType eventType= new EventType(1,"Conferencias");
		UniversityBranch branch = new UniversityBranch(2,"Recinto Paraiso");
		
		EventActivity activity1= new EventActivity(0,"PB-Activity1",null,null,"PB-Venue1"); 
		EventActivity activity2= new EventActivity(0,"PB-Activity2",null,null,"PB-Venue2"); 
		EventActivity activity3= new EventActivity(0,"PB-Activity3",null,null,"PB-Venue3"); 
		EventActivity activity4= new EventActivity(0,"PB-Activity4",null,null,"PB-Venue4");
		
		ArrayList<EventActivity> eventActivities1 = new ArrayList<>();
		eventActivities1.add(activity1);
		eventActivities1.add(activity2);
		
		ArrayList<EventActivity> eventActivities2 = new ArrayList<>();
		eventActivities2.add(activity3);
		eventActivities2.add(activity4);
		
		ProgrammeDay programme1= new ProgrammeDay(null,eventActivities1,0);
		ProgrammeDay programme2= new ProgrammeDay(null,eventActivities2,0);
		
		ArrayList<ProgrammeDay> programeDays = new ArrayList<>();
		programeDays.add(programme1);
		programeDays.add(programme2);
		
		eventData.save(new Event(0,"PB-JU Event 3", null, "Estudiantes","Esta es una prueba con JUnit", "","María José","Leiva Fernández","majo@ucr.ac.cr",true,"https://stackoverflow.com/questions/3142444/stored-procedure-return-identity-as-output-parameter-or-scalar","",true,null,"AP",eventType,programeDays,branch));
	}
	
	@Test
	void findAll() {
		assertTrue(eventData.findAll().size() > 0);
	}
	
	@Test
	void findByEventTypeId() {
		assertTrue(eventData.findByEventTypeId(1).size() > 0);
	}
	
	@Test
	void findByBranchId() {
		assertTrue(eventData.findByBranchId(2).size() > 0);
	}
	
	@Test
	void findByStatus() {
		assertTrue(eventData.findByStatus("AP").size() > 0);
	}


}
