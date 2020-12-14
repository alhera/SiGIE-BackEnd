package cr.ac.ucr.ie.sigie.controller;
import org.springframework.http.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.EventTypeBusiness;
import cr.ac.ucr.ie.sigie.domain.EventType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="/api/eventtype/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins="*") 
public class EventTypeController {
	@Autowired
	private EventTypeBusiness eventTypeBusiness;
	
	@GetMapping(path = "/")
	public List<EventType> findAll() {
		return eventTypeBusiness.findAll();
		
	}
}
