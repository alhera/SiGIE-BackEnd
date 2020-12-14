package cr.ac.ucr.ie.sigie.controller;

import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cr.ac.ucr.ie.sigie.business.NewsBusiness;
import cr.ac.ucr.ie.sigie.domain.News;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@RunWith(SpringRunner.class)
@SpringBootTest
class NewsControllerTest {
	
	@Autowired
	NewsBusiness newsBusiness;
	private static final String URL = "http://localhost:8086/ie/api/news";
	final private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void findAll() {
		List<News> newsList =  Arrays.asList(restTemplate.getForObject(URL + "/findNews", News[].class));
		assertNotNull( newsList.get(0));
		assertTrue(newsList.size() >= 1);
	}
	@Test
public void filterNews() throws SQLException {
		String stringStartDate="0001-01-01";  
		String stringEndDate="2020-07-20";
		int searchUniversityBranchId=1;
		String searchTitle="r";
		List<News> newsList =  Arrays.asList(restTemplate.getForObject(URL + "/filterNews/"+stringStartDate+"/"+
				stringEndDate+"/"+searchUniversityBranchId+"/"+null, News[].class));
	
		System.out.print(newsList.toString());
		assertNotNull( newsList.get(0));
		assertTrue(newsList.size() >= 1);
	}
	@Test
	public void findByDateRange() {
		String stringStartDate="2020-06-05";  
		String stringEndDate="2020-07-20";
		List<News> newsList =  Arrays.asList(restTemplate.getForObject(URL + "/findByDateRange/"+stringStartDate+"/"+stringEndDate, News[].class));
		assertNotNull( newsList.get(0));
		assertTrue(newsList.size() >= 1);
	}
	@Test
	public void findByUniversityBranch() {
		int searchUniversityBranchId=1;
		List<News> newsList =  Arrays.asList(restTemplate.getForObject(URL + "/findByUniversityBranch/"+searchUniversityBranchId, News[].class));
		assertNotNull( newsList.get(0));
		assertTrue(newsList.size() >= 1);
	}
	@Test
	public void findByNewsCategory() {
		int searchNewsCategoryId=1;
		List<News> newsList =  Arrays.asList(restTemplate.getForObject(URL + "/findByNewsCategory/"+searchNewsCategoryId, News[].class));
		assertNotNull( newsList.get(0));
		assertTrue(newsList.size() >= 1);
	}
	@Test
	public void findById() {
		int searchNewsId=1;
		News news =  restTemplate.getForObject(URL + "/findById/"+searchNewsId, News.class);
		assertNotNull( news);
		assertTrue(news.getNewsId()!=0);
	}
	
	
	@Test
	public void save() {
		String dateForm = "0001-01-01";
		Date date = Date.valueOf(dateForm);
		News newsForInsert = new News(3, "Expo", "Innovación", date, date, "http:sjhcgydcg", false, new NewsCategory(1, "Negocios"), new UniversityBranch(1,"Recinto Paraíso"), "Luis", "Rolo Rolo", "luis@ucr.ac.cr", "Profesor");
		News news = restTemplate.postForObject(URL + "/save", newsForInsert, News.class);
		
		assertTrue(news.getTitle().equals("Expo"));
		
	}
	
	//@Test
	public void findAllNewsPending() {
		List<News> findNews = Arrays.asList(restTemplate.getForObject(URL + "/findNewsPendingApprove", News[].class));
			
		for(int i=0; i< findNews.size();i++) {
			assertNotNull(findNews.get(i));
		}
			
		assertTrue(findNews != null);
	}
	
	//@Test
	public void findByTitle() {
		String title = "Feria";
		List<News> news = Arrays.asList(restTemplate.getForObject(URL + "/findNewsPendingApproveByTitle/" + title, News[].class));
			
		for(int i=0; i< news.size();i++) {
			assertNotNull(news.get(i));
		}
			
		assertTrue(news != null);
	}
	
	//@Test
	public void findByAuthorName() {
		String authorName = "Luis";
		List<News> news = Arrays.asList(restTemplate.getForObject(URL + "/findNewsPendingApproveByAuthorName/" + authorName, News[].class));
			
		for(int i=0; i< news.size();i++) {
			assertNotNull(news.get(i));
		}
			
		assertTrue(news != null);
	}
}
