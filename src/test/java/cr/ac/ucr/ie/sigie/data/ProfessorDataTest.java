package cr.ac.ucr.ie.sigie.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cr.ac.ucr.ie.sigie.domain.Professor;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProfessorDataTest {

	
		@Autowired
		ProfessorData professordata;
	
	@Test
	public void getProfessor() {
		List<Professor> professor = professordata.getProfessor();
		assertNotNull(professor);
		assertTrue(!professor.isEmpty());
	}
	@Test
	public void insertProfessor() {
		Professor p = new Professor();
		
	}
}
