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

import cr.ac.ucr.ie.sigie.business.NewsCategoryBusiness;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
class NewsCategoryDataTest {
	@Autowired
	private NewsCategoryData newsCategoryData;
	@Autowired
	private NewsCategoryBusiness newsCategoryBusiness;
	@Test
	void findById() {
		try {
			int searchNewsCategoryId=1;
			NewsCategory newsCategory=newsCategoryData.findById(searchNewsCategoryId);
			assertNotNull( newsCategory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllNewsCategory() {
		List<NewsCategory> newsCategory = newsCategoryBusiness.findAll();
			
		for(int i=0; i< newsCategory.size();i++) {
			System.out.println(newsCategory.get(i).getName());
			assertNotNull(newsCategory.get(i));
		}
			
		assertNotNull(newsCategory);
	}

}
