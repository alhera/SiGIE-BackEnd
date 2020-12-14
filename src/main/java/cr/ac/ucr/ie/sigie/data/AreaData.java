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

import cr.ac.ucr.ie.sigie.domain.Area;

@Repository
public class AreaData{
	
	@Autowired
	private DataSource dataSource;

	@Transactional(readOnly=true)
	public List<Area> listAllAreas(){
		Connection conexion = null;
		List<Area> areas;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csSelectAreas = conexion.prepareCall("{call select_areas ()}");
			csSelectAreas.execute();
			
			conexion.commit();
			
			ResultSet rs = csSelectAreas.getResultSet(); 
			
			areas = new ArrayList<>();
			Area area = new Area();
			while(rs.next()) {
				area.setAreaId(rs.getString("area_id"));
				area.setAreaName(rs.getString("name"));
				areas.add(area);
				area = new Area();
			}
			
			csSelectAreas.close();
			
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
		return areas;
	}
	
	
}