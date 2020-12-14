package cr.ac.ucr.ie.sigie.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cr.ac.ucr.ie.sigie.domain.News;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@RunWith(SpringRunner.class)
@SpringBootTest
class UniversityBranchControllerTest {
	
	@Autowired
	UniversityBranchController universityBranchController;
	private static final String URL = "http://localhost:8086/ie/api/universityBranch";
	final private RestTemplate restTemplate = new RestTemplate();
	@Test
	public void findById() {
		int searchUniversityBranchId=1;
		UniversityBranch universityBranch =  restTemplate.getForObject(URL + "/findById/"+searchUniversityBranchId, UniversityBranch.class);
		assertNotNull( universityBranch);
		assertTrue(universityBranch.getBranchId()!=0);
	}
	
	@Test
	public void findAllUniversityBranch() {
		List<UniversityBranch> findUniversityBranch = Arrays.asList(restTemplate.getForObject(URL + "/findUniversityBranch", UniversityBranch[].class));
			
		for(int i=0; i< findUniversityBranch.size();i++) {
			assertNotNull(findUniversityBranch.get(i));
		}
			
		assertNotNull( findUniversityBranch.get(0));
		assertTrue(findUniversityBranch.size() >= 1);
	}

}
