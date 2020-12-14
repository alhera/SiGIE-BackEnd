package cr.ac.ucr.ie.sigie.data;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.util.List;
import cr.ac.ucr.ie.sigie.domain.News;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;
import cr.ac.ucr.ie.sigie.domain.UniversityBranch;


@Repository
public class NewsData {
	@Autowired
	private NewsCategoryData newsCategoryData;
	@Autowired
	private UniversityBranchData universityBranchData;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<News> findAll() throws SQLException {
		  String sqlSelect = "select news_id,title,content,publication_date,image,"
		  		+ "visible,expiration_date,author_name,author_last_name,author_mail,author_institucional_role,"
		  		+ "news_category_id,university_branch_id from news where visible=true;"; 
		  Connection conn=dataSource.getConnection();
		  ArrayList<News> newsList=new ArrayList<News>();	  		  
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int idNews = rs.getInt("news_id");
		    String title=rs.getString("title");
		    String content=rs.getString("content");
		    Date publicationDate=rs.getDate("publication_date");
		    Date expirationDate=rs.getDate("expiration_date");
		    String image=rs.getString("image");
		    boolean visible=rs.getBoolean("visible");
		    String authorName=rs.getString("author_name");
		    String authorLastName=rs.getString("author_last_name");
		    String authorMail=rs.getString("author_mail");
			String authorInstitutionRole=rs.getString("author_institucional_role");
			int newsCategoryId=rs.getInt("news_category_id");
			int branchId=rs.getInt("university_branch_id");
			
			News news = new News();
			news.setNewsId(idNews);
			news.setTitle(title);
		    news.setContent(content);
		    news.setPublicationDate(publicationDate);
		    news.setExpirationDate(expirationDate);
		    news.setImage(image);
		    news.setVisible(visible);
		    news.setAuthorName(authorName);
		    news.setAuthorLastName(authorLastName);
		    news.setAuthorMail(authorMail);
		    news.setAuthorInstitutionRole(authorInstitutionRole);
		    
		    news.setNewsCategory(newsCategoryData.findById(newsCategoryId));
		    news.setUniversityBranch(universityBranchData.findById(branchId));
		    newsList.add(news);
		   
		  }
		  conn.close();
		  return newsList;
		 }
	
	public ArrayList<News> findByDateRange(Date startDate,Date endDate) throws SQLException {
		  String sqlSelect = "select news_id,title,content,publication_date,image,"
		  		+ "visible,expiration_date,author_name,author_last_name,author_mail,author_institucional_role,"
		  		+ "news_category_id,university_branch_id from news where publication_date Between '"+startDate+"' and '"+endDate+"' AND visible=true;" ; 
		  Connection conn=dataSource.getConnection();
		  ArrayList<News> newsList=new ArrayList<News>();	  		  
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int idNews = rs.getInt("news_id");
		    String title=rs.getString("title");
		    String content=rs.getString("content");
		    Date publicationDate=rs.getDate("publication_date");
		    Date expirationDate=rs.getDate("expiration_date");
		    String image=rs.getString("image");
		    boolean visible=rs.getBoolean("visible");
		    String authorName=rs.getString("author_name");
		    String authorLastName=rs.getString("author_last_name");
		    String authorMail=rs.getString("author_mail");
			String authorInstitutionRole=rs.getString("author_institucional_role");
			int newsCategoryId=rs.getInt("news_category_id");
			int branchId=rs.getInt("university_branch_id");
			
			News news = new News();
			news.setNewsId(idNews);
			news.setTitle(title);
		    news.setContent(content);
		    news.setPublicationDate(publicationDate);
		    news.setExpirationDate(expirationDate);
		    news.setImage(image);
		    news.setVisible(visible);
		    news.setAuthorName(authorName);
		    news.setAuthorLastName(authorLastName);
		    news.setAuthorMail(authorMail);
		    news.setAuthorInstitutionRole(authorInstitutionRole);
		    
		    news.setNewsCategory(newsCategoryData.findById(newsCategoryId));
		    news.setUniversityBranch(universityBranchData.findById(branchId));
		    newsList.add(news);
		   
		  }
		  conn.close();
		  return newsList;
		 }
	public ArrayList<News> findByUniversityBranch(int searchUniversityBranchId) throws SQLException {
		  String sqlSelect = "select news_id,title,content,publication_date,image,"
		  		+ "visible,expiration_date,author_name,author_last_name,author_mail,author_institucional_role,"
		  		+ "news_category_id,university_branch_id from news where university_branch_id= "+searchUniversityBranchId+" AND visible=true;" ; 
		  Connection conn=dataSource.getConnection();
		  ArrayList<News> newsList=new ArrayList<News>();	  		  
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int idNews = rs.getInt("news_id");
		    String title=rs.getString("title");
		    String content=rs.getString("content");
		    Date publicationDate=rs.getDate("publication_date");
		    Date expirationDate=rs.getDate("expiration_date");
		    String image=rs.getString("image");
		    boolean visible=rs.getBoolean("visible");
		    String authorName=rs.getString("author_name");
		    String authorLastName=rs.getString("author_last_name");
		    String authorMail=rs.getString("author_mail");
			String authorInstitutionRole=rs.getString("author_institucional_role");
			int newsCategoryId=rs.getInt("news_category_id");
			int branchId=rs.getInt("university_branch_id");
			
			News news = new News();
			news.setNewsId(idNews);
			news.setTitle(title);
		    news.setContent(content);
		    news.setPublicationDate(publicationDate);
		    news.setExpirationDate(expirationDate);
		    news.setImage(image);
		    news.setVisible(visible);
		    news.setAuthorName(authorName);
		    news.setAuthorLastName(authorLastName);
		    news.setAuthorMail(authorMail);
		    news.setAuthorInstitutionRole(authorInstitutionRole);
		    
		    news.setNewsCategory(newsCategoryData.findById(newsCategoryId));
		    news.setUniversityBranch(universityBranchData.findById(branchId));
		    newsList.add(news);
		   
		  }
		  conn.close();
		  return newsList;
		 }
	public ArrayList<News> findByNewsCategory(int searchNewsCategoryId) throws SQLException {
		  String sqlSelect = "select news_id,title,content,publication_date,image,"
		  		+ "visible,expiration_date,author_name,author_last_name,author_mail,author_institucional_role,"
		  		+ "news_category_id,university_branch_id from news where university_branch_id= "+searchNewsCategoryId+" AND visible=true;" ; 
		  Connection conn=dataSource.getConnection();
		  ArrayList<News> newsList=new ArrayList<News>();	  		  
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int idNews = rs.getInt("news_id");
		    String title=rs.getString("title");
		    String content=rs.getString("content");
		    Date publicationDate=rs.getDate("publication_date");
		    Date expirationDate=rs.getDate("expiration_date");
		    String image=rs.getString("image");
		    boolean visible=rs.getBoolean("visible");
		    String authorName=rs.getString("author_name");
		    String authorLastName=rs.getString("author_last_name");
		    String authorMail=rs.getString("author_mail");
			String authorInstitutionRole=rs.getString("author_institucional_role");
			int newsCategoryId=rs.getInt("news_category_id");
			int branchId=rs.getInt("university_branch_id");
			
			News news = new News();
			news.setNewsId(idNews);
			news.setTitle(title);
		    news.setContent(content);
		    news.setPublicationDate(publicationDate);
		    news.setExpirationDate(expirationDate);
		    news.setImage(image);
		    news.setVisible(visible);
		    news.setAuthorName(authorName);
		    news.setAuthorLastName(authorLastName);
		    news.setAuthorMail(authorMail);
		    news.setAuthorInstitutionRole(authorInstitutionRole);
		    
		    news.setNewsCategory(newsCategoryData.findById(newsCategoryId));
		    news.setUniversityBranch(universityBranchData.findById(branchId));
		    newsList.add(news);
		   
		  }
		  conn.close();
		  return newsList;
		 }
	public News findById(int searchNewsId) throws SQLException {
		  String sqlSelect = "select news_id,title,content,publication_date,image,"
		  		+ "visible,expiration_date,author_name,author_last_name,author_mail,author_institucional_role,"
		  		+ "news_category_id,university_branch_id from news where news_id= "+searchNewsId+" AND visible=true;" ; 
		  Connection conn=dataSource.getConnection();	  		  
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  News news=null;
		  while (rs.next()) {
		    int idNews = rs.getInt("news_id");
		    String title=rs.getString("title");
		    String content=rs.getString("content");
		    Date publicationDate=rs.getDate("publication_date");
		    Date expirationDate=rs.getDate("expiration_date");
		    String image=rs.getString("image");
		    boolean visible=rs.getBoolean("visible");
		    String authorName=rs.getString("author_name");
		    String authorLastName=rs.getString("author_last_name");
		    String authorMail=rs.getString("author_mail");
			String authorInstitutionRole=rs.getString("author_institucional_role");
			int newsCategoryId=rs.getInt("news_category_id");
			int branchId=rs.getInt("university_branch_id");
			news = new News();
			news.setNewsId(idNews);
			news.setTitle(title);
		    news.setContent(content);
		    news.setPublicationDate(publicationDate);
		    news.setExpirationDate(expirationDate);
		    news.setImage(image);
		    news.setVisible(visible);
		    news.setAuthorName(authorName);
		    news.setAuthorLastName(authorLastName);
		    news.setAuthorMail(authorMail);
		    news.setAuthorInstitutionRole(authorInstitutionRole);
		    
		    news.setNewsCategory(newsCategoryData.findById(newsCategoryId));
		    news.setUniversityBranch(universityBranchData.findById(branchId));
		   
		  }
		  conn.close();
		  return news;
		 }
	
	public News save(News news) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			String stringPublication = "0001-01-01";
			Date datePublication = Date.valueOf(stringPublication);
			String stringExpiration = "0001-01-01";
			Date dateExpiration = Date.valueOf(stringExpiration);
			news.setPublicationDate(datePublication);
			news.setExpirationDate(dateExpiration);
			String sqlInsert = "INSERT INTO `sigie`.`news` (`news_id`, `title`, `content`, `publication_date`, `image`, `visible`, `expiration_date`, `author_name`, `author_last_name`, `author_mail`, `author_institucional_role`, `news_category_id`, `university_branch_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			//String sqlInsert = "INSERT INTO `sigie`.`news` (`news_id`,`title`,`content`,`publication_date`,`image`,`visible`,`expiration_date`,`author_name`,`author_last_name`,`author_mail`,`author_institucional_role`,`news_category_id`,`university_branch_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = connection.prepareStatement(sqlInsert);
			stmt.setInt(1, news.getNewsId());
			stmt.setString(2, news.getTitle());
			stmt.setString(3, news.getContent());
			stmt.setDate(4, (Date) datePublication);
			stmt.setString(5, news.getImage());
			stmt.setBoolean(6, news.isVisible());
			stmt.setDate(7, (Date)dateExpiration);
			stmt.setString(8, news.getAuthorName());
			stmt.setString(9, news.getAuthorLastName());
			stmt.setString(10, news.getAuthorMail());
			stmt.setString(11, news.getAuthorInstitutionRole());
			stmt.setInt(12, news.getNewsCategory().getNewsCategoryId());
			stmt.setInt(13, news.getUniversityBranch().getBranchId());
			stmt.execute();
			
			
			
			
			connection.commit();
			connection.close();
			return news;
		}catch(Exception e){
			try {
				System.out.print(e.toString());
				connection.rollback();
				
			}catch(Exception e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(connection != null) {
				try {
					connection.close();
				}catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	public void delete(int id) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		
			String sqlInsert = "delete from news where news_id=(?);";
			PreparedStatement stmt = connection.prepareStatement(sqlInsert);
			stmt.setInt(1, id);

			stmt.execute();

			connection.commit();
			connection.close();
		}catch(Exception e){
			try {
				System.out.print(e.toString());
				connection.rollback();
				
			}catch(Exception e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(connection != null) {
				try {
					connection.close();
				}catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	
	
	public List<News> findByTitle(String titleNews) {
		
		String sqlSelect = "SELECT `news`.`news_id`,`news`.`title`,`news`.`content`,`news`.`publication_date`,`news`.`image`,`news`.`visible`,`news`.`expiration_date`,`news`.`author_name`,`news`.`author_last_name`,`news`.`author_mail`,`news`.`author_institucional_role`,`news`.`news_category_id`,`news`.`university_branch_id` FROM `sigie`.`news`  where `news`.`publication_date` and `news`.`expiration_date` = '0001-01-01' and `news`.`title`='"+titleNews+"';";
		
		List<News> news = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
		new News(rs.getInt("news_id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getDate("publication_date"),
				rs.getDate("expiration_date"),
				rs.getString("image"),
				rs.getBoolean("visible"),
				new NewsCategory(rs.getInt("news_category_id"),""),
				new UniversityBranch(rs.getInt("university_branch_id"),""),
				rs.getString("author_name"),
				rs.getString("author_last_name"),
				rs.getString("author_mail"),
				rs.getString("author_institucional_role"))));
		return news;
		
	}
	public List<News> findByAuthorName(String authorNameNews) {
		String sqlSelect = "SELECT `news`.`news_id`,`news`.`title`,`news`.`content`,`news`.`publication_date`,`news`.`image`,`news`.`visible`,`news`.`expiration_date`,`news`.`author_name`,`news`.`author_last_name`,`news`.`author_mail`,`news`.`author_institucional_role`,`news`.`news_category_id`,`news`.`university_branch_id` FROM `sigie`.`news`  where `news`.`publication_date` and `news`.`expiration_date` = '0001-01-01' and `news`.`author_name`='"+authorNameNews+"';";
		
		List<News> news = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
				new News(rs.getInt("news_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("publication_date"),
						rs.getDate("expiration_date"),
						rs.getString("image"),
						rs.getBoolean("visible"),
						new NewsCategory(rs.getInt("news_category_id"),""),
						new UniversityBranch(rs.getInt("university_branch_id"),""),
						rs.getString("author_name"),
						rs.getString("author_last_name"),
						rs.getString("author_mail"),
						rs.getString("author_institucional_role"))));
		return news;
	}
	
	
	
	public List<News> findAllNewsPending() {
		String sqlSelect = "SELECT `news`.`news_id`,`news`.`title`,`news`.`content`,`news`.`publication_date`,`news`.`image`,`news`.`visible`,`news`.`expiration_date`,`news`.`author_name`,`news`.`author_last_name`,`news`.`author_mail`,`news`.`author_institucional_role`,`news`.`news_category_id`,`news`.`university_branch_id` FROM `sigie`.`news`  where `news`.`publication_date` and `news`.`expiration_date` = '0001-01-01';";
		
		List<News> news = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
				new News(rs.getInt("news_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("publication_date"),
						rs.getDate("expiration_date"),
						rs.getString("image"),
						rs.getBoolean("visible"),
						new NewsCategory(rs.getInt("news_category_id"),""),
						new UniversityBranch(rs.getInt("university_branch_id"),""),
						rs.getString("author_name"),
						rs.getString("author_last_name"),
						rs.getString("author_mail"),
						rs.getString("author_institucional_role"))));
		return news;
	}
	
	public int findEndNewsId() {
		//String sqlSelect = "SELECT `news`.`news_id`, `news`.`title`, `news`.`content`, `news`.`publication_date`, `news`.`image`, `news`.`visible`, `news`.`expiration_date`, `news`.`author_name`, `news`.`author_last_name`, `news`.`author_mail`, `news`.`author_institucional_role`, `news`.`news_category_id`, `news`.`university_branch_id` FROM `sigie`.`news`;";
		String sqlSelect = "SELECT news_id, title, content, publication_date, image, visible, expiration_date, author_name, author_last_name, author_mail, author_institucional_role, news_category_id, university_branch_id FROM news;";
		
		List<News> news = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
				new News(rs.getInt("news_id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("publication_date"),
						rs.getDate("expiration_date"),
						rs.getString("image"),
						rs.getBoolean("visible"),
						new NewsCategory(rs.getInt("news_category_id"),""),
						new UniversityBranch(rs.getInt("university_branch_id"),""),
						rs.getString("author_name"),
						rs.getString("author_last_name"),
						rs.getString("author_mail"),
						rs.getString("author_institucional_role"))));
		 
		  
		  int endId = news.get(news.size()-1).getNewsId();
		  return endId;
	}
}
