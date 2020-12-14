package cr.ac.ucr.ie.sigie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.ie.sigie.business.UserBusiness;
import cr.ac.ucr.ie.sigie.domain.User;

@RestController
@RequestMapping(value="/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*") 

public class UserController {
	 @Autowired
	 private UserBusiness userBusiness;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @PostMapping("/register")
	    private void registerUser(@RequestBody @Validated User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userBusiness.save(user);
	    }
}
