package cr.ac.ucr.ie.sigie.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.UserData;
import cr.ac.ucr.ie.sigie.domain.User;


@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserData userData;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userData.findByEmail(username);
		if (user.getUserId() ==0)
			throw new UsernameNotFoundException("Email " + username + " not found");
		else {
			// The user that is returned by UserData only contains only authorities that are active
			// That is why the next constructor is used
			// User (username, password,
			//       enabled, accountNonExpired,credentialsNonExpired,accountNonLocked, 
			//       authorities) 
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				true,true,true,true,
				getAuthorities(user));
		}
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user){
		
		String[] userRoles = user.getRoles()
				.stream()
				.map((role)-> role.getRoleName())
				.toArray(String[]::new);
		
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}

