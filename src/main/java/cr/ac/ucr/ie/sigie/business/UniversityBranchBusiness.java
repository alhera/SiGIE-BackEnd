package cr.ac.ucr.ie.sigie.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.UniversityBranchData;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@Service
public class UniversityBranchBusiness {
	
	
	@Autowired
	UniversityBranchData universityBranchData;
	 public UniversityBranch findById(int id) throws SQLException {
		 return universityBranchData.findById(id);
	 }
	 
	 public List<UniversityBranch> findAll(){
		 return universityBranchData.findAllUniversityBranch();
	 }
	 
}
