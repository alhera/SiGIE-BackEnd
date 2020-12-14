package cr.ac.ucr.ie.sigie.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.ie.sigie.data.UserData;
import cr.ac.ucr.ie.sigie.domain.User;

@Service
public class UserBusiness {
	@Autowired
	private UserData userData;
	
	public void save(User user) {
		userData.save(user);
	}

}
