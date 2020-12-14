package cr.ac.ucr.ie.sigie.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.ProfessorData;
import cr.ac.ucr.ie.sigie.domain.Professor;

@Service
public class ProfessorBusiness {
	@Autowired
	ProfessorData professordao;
	
	public Professor getProfessorByID(int professorId){
		return professordao.getProfessorByID(professorId);
	}

	public Professor insertProfessor(Professor professor) {
		return professordao.insertProfessor(professor);

	}
	public List<Professor> getProfessor(){
		return professordao.getProfessor();
	}	
	public boolean updateProfessor(Professor professor) {
		return professordao.updateProfessor(professor);
	}
	public List<Professor> getProfessors(Professor professor) {
		return professordao.getProfessors(professor);
	}
	
	public List<Professor> findByBranchId(int branchId)  {
		return professordao.findByIdBranch(branchId);
	}
}
