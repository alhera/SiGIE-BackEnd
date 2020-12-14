package cr.ac.ucr.ie.sigie.data;

import static org.junit.Assert.assertNotNull;


import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cr.ac.ucr.ie.sigie.business.UniversityBranchBusiness;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@RunWith(SpringRunner.class)
@SpringBootTest
class UniversityBranchDataTest {
	@Autowired
	private UniversityBranchData universityBranchData;
	@Autowired
	private UniversityBranchBusiness universityBranchBusiness;
	@Test
	void findById() {
		try {
			int searchUniversityBranchId=1;
			UniversityBranch universityBranch=universityBranchData.findById(searchUniversityBranchId);
			assertNotNull( universityBranch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllUniversityBranch() {
		List<UniversityBranch> universityBranch = universityBranchBusiness.findAll();
			
		for(int i=0; i< universityBranch.size();i++) {
			System.out.println(universityBranch.get(i).getName());
			assertNotNull(universityBranch.get(i));
		}
			
		assertNotNull(universityBranch);
	}
}
