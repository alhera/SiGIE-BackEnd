package cr.ac.ucr.ie.sigie.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ie.sigie.domain.Role;
import cr.ac.ucr.ie.sigie.domain.User;

@Repository
public class UserData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findByEmail(String email) {
		String selectSql = "SELECT u.user_id,u.person_name,u.email,u.password, r.role_id, r.role_name, ur.active  " + 
				"  FROM users u, role r, user_role ur" + 
				"  WHERE u.user_id = ur.user_id and r.role_id = ur.role_id" + 
				"  and u.email = '" + email + "' and ur.active=1";
		List<User> users = jdbcTemplate.query(selectSql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setPersonName(rs.getString("person_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				do {
						Role role = new Role();
						role.setRoleId(rs.getInt("role_id"));
						role.setRoleName(rs.getString("role_name"));
						role.setActive(rs.getBoolean("active"));
						user.getRoles().add(role);
				}while(rs.next());
				return user;
			}//mapRow
		});
		return (users.isEmpty()?new User():users.get(0));
	}//findByEmail

	public void save(User user) {
		//TODO pendiente
	}
}
