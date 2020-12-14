package cr.ac.ucr.ie.sigie.data;



import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cr.ac.ucr.ie.sigie.business.NewsBusiness;
import cr.ac.ucr.ie.sigie.domain.News;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsDataTest {
	@Autowired
	private NewsData newsData;
	@Autowired
	private NewsCategoryData newsCategoryData;
	@Autowired
	private UniversityBranchData universityBranchData;
	@Autowired
	private NewsBusiness newsBusiness;
	
	@Before
	void onInit() throws SQLException {
	  int searchNewsCategoryId=1;
	  NewsCategory newsCategory=newsCategoryData.findById(searchNewsCategoryId);
	  int searchUniversityBranchId=1;
	  UniversityBranch universityBranch=universityBranchData.findById(searchUniversityBranchId);
	  String stringStartDate="2020-06-15";  
		String stringEndDate="2020-12-30";
	    
		Date startDate=Date.valueOf(stringStartDate);
	    Date endDate=Date.valueOf(stringEndDate);
	    
	  News news=new News(45, "Nuevo Congreso", "Realizaremos un nuevo congreso", startDate, endDate,
			  "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Momotus_momota_-_Amazonian_motmot%3B_Bonito%2C_Mato_Grosso_do_Sul%2C_Brazil.jpg/280px-Momotus_momota_-_Amazonian_motmot%3B_Bonito%2C_Mato_Grosso_do_Sul%2C_Brazil.jpg", 
			  true, newsCategory, universityBranch, "Alvaro", "Mena", "dsjjkd@gmail.com", "Profesor");
	  newsData.save(news);
	}
	@After
	void delete() throws SQLException {
	  int newsId=45;
	 
	  newsData.delete(newsId);
	}
	
	@Test
	public void findAllTest() {
		try {
			ArrayList<News> newsList=newsData.findAll();
			assertNotNull(newsList.get(0));
			assertTrue(!newsList.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void findByDateRange() {
		try {
			String stringStartDate="2020-06-05";  
			String stringEndDate="2020-07-20";
		    Date startDate=Date.valueOf(stringStartDate);
		    
		    Date endDate=Date.valueOf(stringEndDate);
			ArrayList<News> newsList=newsData.findByDateRange(startDate,endDate);
			assertNotNull(newsList.get(0));
			assertTrue(!newsList.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	@Test
	public void findByUniversityBranch() {
		try {
			int searchUniversityBranchId=1;
			ArrayList<News> newsList=newsData.findByUniversityBranch(searchUniversityBranchId);
			assertNotNull(newsList.get(0));
			assertTrue(!newsList.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void findByNewsCategory() {
		try {
			int searchNewsCategoryId=1;
			ArrayList<News> newsList=newsData.findByNewsCategory(searchNewsCategoryId);
			assertNotNull(newsList.get(0));
			assertTrue(!newsList.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void findById() {
		try {
			int searchNewsId=1;
			News news=newsData.findById(searchNewsId);
			assertNotNull( news);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public	void saveNewsPending() {
			
		String dateForm = "0001-01-01";
		Date date = Date.valueOf(dateForm);
		News news = new News(1, "Feria", "Disfruta de los nuevos productos", date, date, "http:sjhcgydcg", false, new NewsCategory(1, "Negocios"), new UniversityBranch(1,"Recinto Paraíso"), "Luis", "Rolo Rolo", "luis@ucr.ac.cr", "Profesor");
			
		//News newsSave =newsBusiness.save(news);
		//assertNotNull(newsSave);
			
			
			
			
	}
		
	@Test
	public void findAllNewsPending() {
		java.util.List<News> newsList = newsBusiness.findAllNewsPending();
			
		for(int i=0; i< newsList.size();i++) {
			System.out.println(newsList.get(i).getTitle());
			assertNotNull(newsList.get(i));
		}
			
		assertTrue(newsList != null);
	}
	
	@Test
	public void findByTitle() {
		List<News> news = newsBusiness.findByTitle("Feria");
		for(int i=0; i< news.size();i++) {
			System.out.println(news.get(i).getTitle());
			assertNotNull(news.get(i).getTitle());
		}
			
		assertTrue(news != null);
	}
	
	@Test
	public void findByAuthorName() {
		List<News> news = newsBusiness.findByAuthorName("Luis");
		for(int i=0; i< news.size();i++) {
			System.out.println(news.get(i).getTitle());
			assertNotNull(news.get(i).getAuthorName());
		}
			
		assertTrue(news != null);
	}
}
