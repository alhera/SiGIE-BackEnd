package cr.ac.ucr.ie.sigie.business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.NewsData;
import cr.ac.ucr.ie.sigie.domain.News;

@Service
public class NewsBusiness {
	
	
	@Autowired
	NewsData newsData;
	public ArrayList<News> findAll() throws SQLException {
		return newsData.findAll();
	} 
	public ArrayList<News> filterNews(Date startDate,Date endDate,
			int searchUniversityBranchId,String searchTitle) throws SQLException {
		ArrayList<News> allNews=newsData.findByDateRange(startDate, endDate);
		ArrayList<News> leakedNews=new ArrayList<News>();
		
		for (News news : allNews) {
			if(searchUniversityBranchId!=0 && searchTitle!=null) {
				if ( news.getUniversityBranch().getBranchId()==searchUniversityBranchId  && news.getTitle().contains(searchTitle)) {
					leakedNews.add(news);
				}	
				}else if (searchUniversityBranchId==0 && searchTitle!=null) {
					if ( news.getTitle().contains(searchTitle)) {
						leakedNews.add(news);
					}	
				}else if (searchUniversityBranchId!=0 && searchTitle==null) {
					if ( news.getUniversityBranch().getBranchId()==searchUniversityBranchId  ) {
						leakedNews.add(news);
					}	
				}else if (searchUniversityBranchId==0 && searchTitle==null) {
					
						leakedNews.add(news);
						
				}
			
				}			
		
		
		return leakedNews;
	} 
	public ArrayList<News> findByDateRange(Date startDate,Date endDate) throws SQLException{
		return newsData.findByDateRange(startDate, endDate);
	}
	public ArrayList<News> findByUniversityBranch(int searchUniversityBranchId) throws SQLException {
		return newsData.findByUniversityBranch(searchUniversityBranchId);
	}
	public ArrayList<News> findByNewsCategory(int searchNewsCategoryId) throws SQLException {
		return newsData.findByNewsCategory(searchNewsCategoryId);
	}
	public News findById(int searchNewsId) throws SQLException {
		return newsData.findById(searchNewsId);
	}
	
	public News save(News news) {
		news.setNewsId(newsData.findEndNewsId()+1);
		return newsData.save(news);
	}
	
	public List<News> findAllNewsPending(){
		return newsData.findAllNewsPending();
	}
	
	public List<News> findByTitle(String titleNews){
		return newsData.findByTitle(titleNews);
	}
	
	public List<News> findByAuthorName(String authorNameNews){
		return newsData.findByAuthorName(authorNameNews);
	}
	
}
