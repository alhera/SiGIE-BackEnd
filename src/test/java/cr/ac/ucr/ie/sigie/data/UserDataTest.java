package cr.ac.ucr.ie.sigie.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import cr.ac.ucr.ie.sigie.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDataTest {
	@Autowired
	private UserData userData;

	@Test
	public void findByEmailNotFound() {
		User user = userData.findByEmail("alvaro.mena@ucr");
		assertTrue(user.getUserId() == 0);
	}
	@Test
	public void findByEmailFound() {
		User user = userData.findByEmail("alvaro.mena@ucr.ac.cr");
		getCryptHash();
		assertTrue(user.getUserId() != 0);
	}
	//Encriptar
	private void getCryptHash() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		System.out.println(encoder.encode("amm"));
	}
}

