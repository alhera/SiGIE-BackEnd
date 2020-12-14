package cr.ac.ucr.ie.sigie.data;


import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ie.sigie.domain.EventType;

@Repository
public class EventTypeData {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	@Transactional
	public List<EventType> findAll() {
		List<EventType> eventTypes = new LinkedList<>();
		String sqlSelect = "SELECT * " + "FROM event_type ";
		jdbcTemplate.query(sqlSelect, new Object[] {},
						(rs, row) -> new EventType(rs.getInt("event_type_id"), rs.getString("event_type_name")))
				.forEach(entry -> eventTypes.add(entry));
		return eventTypes;
	}
}
