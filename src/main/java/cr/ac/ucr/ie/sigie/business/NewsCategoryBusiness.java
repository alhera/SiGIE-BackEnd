package cr.ac.ucr.ie.sigie.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.NewsCategoryData;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;

@Service
public class NewsCategoryBusiness {
	
	
	@Autowired
	NewsCategoryData newsCategoryData;
	public NewsCategory findById(int searchNewsCategoryId) throws SQLException {
		return newsCategoryData.findById(searchNewsCategoryId);
	}
	
	public List<NewsCategory> findAll(){
		return newsCategoryData.findAllNewsCategory();
	}
}
