package cr.ac.ucr.ie.sigie.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import cr.ac.ucr.ie.sigie.domain.NewsCategory;

@Repository
public class NewsCategoryData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	 public NewsCategory findById(int searchNewsCategoryId) throws SQLException {
		  
		  String sqlSelect = "select news_category_id,name from news_category where news_category_id= "+searchNewsCategoryId+";";
		  Connection conn=dataSource.getConnection();
		  NewsCategory newsCategory = new NewsCategory(); 
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int idNewsCategory = rs.getInt("news_category_id");
		    String name=rs.getString("name");
		    
		    newsCategory.setNewsCategoryId(idNewsCategory);
		    newsCategory.setNewsCategoryName(name);   
		  }
		  conn.close();
		  return newsCategory;
		 }
	 
	 public List<NewsCategory> findAllNewsCategory() {
			String sqlSelect = "SELECT `news_category_id`, `name` FROM `news_category`";
			List<NewsCategory> newsCategory = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
			new NewsCategory(rs.getInt("news_category_id"),
					rs.getString("name"))));
			return newsCategory;
		}
}
