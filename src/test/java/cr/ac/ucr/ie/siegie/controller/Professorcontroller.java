package cr.ac.ucr.ie.siegie.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cr.ac.ucr.ie.sigie.domain.Professor;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class Professorcontroller {

	private static final String ROOT_URL = "http://localhost:8086/ie/professor";
	final private RestTemplate restTemplate = new RestTemplate();
	@Test
	public void getPeliculasByTitleAndGenreAlternative2() {
		ResponseEntity<Professor[]> responseEntity = restTemplate.getForEntity(ROOT_URL, Professor[].class);
		List<Professor> peliculas = Arrays.asList(responseEntity.getBody());
		assertNotNull(peliculas);
	}

}
	