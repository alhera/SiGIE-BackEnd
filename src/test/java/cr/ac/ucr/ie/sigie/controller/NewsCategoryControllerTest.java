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
import cr.ac.ucr.ie.sigie.domain.NewsCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
class NewsCategoryControllerTest {
	
	@Autowired
	NewsCategoryController newsCategoryController;
	private static final String URL = "http://localhost:8086/ie/api/newscategory";
	final private RestTemplate restTemplate = new RestTemplate();
	@Test
	public void findById() {
		int searchNewsCategoryId=1;
		NewsCategory newsCategory =  restTemplate.getForObject(URL + "/findById/"+searchNewsCategoryId, NewsCategory.class);
		assertNotNull( newsCategory);
		assertTrue(newsCategory.getNewsCategoryId()!=0);
	}
	
	@Test
	public void findAllNewsCategory() {
		List<NewsCategory> findNewsCategory = Arrays.asList(restTemplate.getForObject(URL + "/findNewsCategory", NewsCategory[].class));
			
		for(int i=0; i< findNewsCategory.size();i++) {
			assertNotNull(findNewsCategory.get(i));
		}
			
		assertTrue(findNewsCategory != null);
	}

}
