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
import cr.ac.ucr.ie.sigie.business.AreaBusiness;
import cr.ac.ucr.ie.sigie.domain.Area;



@RestController
@RequestMapping(value="/api/area", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*") 
public class AreaController {
	@Autowired
	private AreaBusiness areaBusiness;
	
	
	@GetMapping(path = "/viewAll")
	public ResponseEntity<List<Area>> listAllAreas(){
		List<Area> area = areaBusiness.listAllAreas();
		return new ResponseEntity<List<Area>>(area, HttpStatus.OK);
	}
	

}