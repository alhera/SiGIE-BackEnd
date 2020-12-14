package cr.ac.ucr.ie.sigie.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.domain.Emphasis;

@Repository
public class EmphasisData{
	
	@Autowired
	private DataSource dataSource;

	@Transactional(readOnly=true)
	public List<Emphasis> listAllEmphasis(){
		Connection conexion = null;
		List<Emphasis> emphasiss;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectEmphasis = conexion.prepareCall("{call select_emphasis ()}");
			csSelectEmphasis.execute();
			
			conexion.commit();
			
			ResultSet rs = csSelectEmphasis.getResultSet(); 
			
			emphasiss = new ArrayList<>();
			Emphasis emphasis = new Emphasis();
			while(rs.next()) {
				emphasis.setEmphasisId(rs.getString("emphasis_id"));
				emphasis.setEmphasisName(rs.getString("name"));
				emphasiss.add(emphasis);
				emphasis = new Emphasis();
			}
			csSelectEmphasis.close();
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}  
		return emphasiss;
	}
}
