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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cr.ac.ucr.ie.sigie.business.ProfessorBusiness;
import cr.ac.ucr.ie.sigie.domain.Professor;

@RestController
@RequestMapping(value="/api/professors", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
public class ProfessorController {

	@Autowired
	private ProfessorBusiness professorBusiness;

	@GetMapping(value = "/{professorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Professor getProfessorById(@PathVariable int professorId) {
		Professor professorById = professorBusiness.getProfessorByID(professorId);
		return professorById;
	}

	@PostMapping(value = "/insertProfessor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid Professor professor) {
		List<Professor> validateEmail = professorBusiness.getProfessors(professor);
		int flag = -1;
		for (Professor p : validateEmail) {
			if (professor.getInstitutionalMail().equals(p.getInstitutionalMail())) {
				flag = 0;
				break;
			} else {
				flag = 1;
			}
		}
		if (flag != 0) {
			Professor newProfessor = professorBusiness.insertProfessor(professor);
			return new ResponseEntity<Professor>(newProfessor, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<EmailError>(new EmailError("institutionalMail",
					"El correo ya se encuentra registrado en nuestro sistema"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Professor> getProfessor() {
		List<Professor> professorList = professorBusiness.getProfessor();
		return professorList;
	}

	@PutMapping(value = "/updateProfessor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateProfessor(@RequestBody Professor professor) {
		boolean professorInserted = professorBusiness.updateProfessor(professor);
		return new ResponseEntity<>(professorInserted, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/branchId/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Professor>> getProfessorByIdUniversityBranch(@PathVariable int branchId) {
		List<Professor> professors = professorBusiness.findByBranchId(branchId);
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}

}
class EmailError {
	String field;
	String message;

	public EmailError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [field=" + field + ", message=" + message + "]";
	}
}
