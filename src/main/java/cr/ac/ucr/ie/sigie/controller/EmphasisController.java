package cr.ac.ucr.ie.sigie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.EmphasisBusiness;
import cr.ac.ucr.ie.sigie.domain.Emphasis;



@RestController
@RequestMapping(value="/api/emphasis", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*") 
public class EmphasisController {
	@Autowired
	private EmphasisBusiness emphasisBusiness;
	
	
	
	@GetMapping(path = "/viewAll")
	public ResponseEntity<List<Emphasis>> listAllEmphasis(){
		List<Emphasis> emphasis = emphasisBusiness.listAllEmphasis();
		return new ResponseEntity<List<Emphasis>>(emphasis, HttpStatus.OK);
	}
	

}