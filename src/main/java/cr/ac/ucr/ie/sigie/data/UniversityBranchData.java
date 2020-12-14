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

import cr.ac.ucr.ie.sigie.domain.UniversityBranch;


@Repository
public class UniversityBranchData {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	 public UniversityBranch findById(int id) throws SQLException {
		  
		  String sqlSelect = "select university_branch_id,name from university_branch where university_branch_id= "+id+";";
		  Connection conn=dataSource.getConnection();
		  UniversityBranch universityBranch=new UniversityBranch();
		  Statement state=conn.createStatement();
		  ResultSet rs=state.executeQuery(sqlSelect);
		  while (rs.next()) {
		    int branchId = rs.getInt("university_branch_id");
		    String name=rs.getString("name");
		    
		    universityBranch.setBranchId(branchId);
		    universityBranch.setName(name);   
		  }
		  conn.close();
		  return universityBranch;
		 }
	 
	 public List<UniversityBranch> findAllUniversityBranch(){
			String sqlSelect = "SELECT `university_branch`.`university_branch_id`,`university_branch`.`name` FROM `sigie`.`university_branch`;";
			List<UniversityBranch> universityBranch = new LinkedList<>(jdbcTemplate.query(sqlSelect, new Object[]{}, (rs, row) ->
			new UniversityBranch(rs.getInt("university_branch_id"),
					rs.getString("name"))));
			return universityBranch;
		}

}
