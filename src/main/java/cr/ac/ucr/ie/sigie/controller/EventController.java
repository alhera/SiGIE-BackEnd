package cr.ac.ucr.ie.sigie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cr.ac.ucr.ie.sigie.business.EventBusiness;
import cr.ac.ucr.ie.sigie.domain.Event;


@RestController
@RequestMapping(value="/api/event", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*") 
public class EventController {

	@Autowired
	private EventBusiness eventBusiness;
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> save(@RequestBody  @Valid Event event){
		Event insertedEvent = eventBusiness.save(event);
		return new ResponseEntity<>(insertedEvent, HttpStatus.CREATED);
	}

	@GetMapping(path = "/type")
	public ResponseEntity<List<Event>> findByTypeId(
			@RequestParam("eventTypeId") int eventTypeId){
		List<Event> events = eventBusiness.findByTypeId(eventTypeId);
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}
	
	@GetMapping(path = "/branch")
	public ResponseEntity<List<Event>> findByBranchId(
			@RequestParam("branchId") int branchId){
		List<Event> events = eventBusiness.findByBranchId(branchId);
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Event>> findByStatus(
			@RequestParam("status") String status){
		List<Event> events = eventBusiness.findByStatus(status);
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Event>> findAll(){
		List<Event> events = eventBusiness.findAll();
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}
}
