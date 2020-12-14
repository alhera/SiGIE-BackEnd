package cr.ac.ucr.ie.sigie.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.UniversityBranchBusiness;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;


@RestController
@RequestMapping(value = "/api/universityBranch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*")
public class UniversityBranchController {
	
	
	@Autowired
	UniversityBranchBusiness universityBranchBusiness;
	
	@GetMapping(path = "/findById/{searchUniversityBranchId}")
	public ResponseEntity<UniversityBranch> findById(
		@PathVariable("searchUniversityBranchId") int searchUniversityBranchId){
		UniversityBranch universityBranch=new UniversityBranch();
		try {
			universityBranch = universityBranchBusiness.findById(searchUniversityBranchId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<UniversityBranch>(universityBranch, HttpStatus.OK);
	}
	
	@GetMapping("/findUniversityBranch")
	public ResponseEntity<List<UniversityBranch>> findUniversityBranch(){
		List<UniversityBranch> universityBranch = universityBranchBusiness.findAll();
		return new ResponseEntity<List<UniversityBranch>>(universityBranch, HttpStatus.OK);
	}
}
