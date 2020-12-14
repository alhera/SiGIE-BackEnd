package cr.ac.ucr.ie.sigie.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import java.util.Collections;

public class AuthenticationService {


  static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds
  static final String SIGNING_KEY = "SecretKey"; // an algorithm-specific signing key that's used to digitally sign the JWT
  static final String PREFIX = "Bearer";

  static public void addToken(HttpServletResponse res, String username) {
    String JwtToken = Jwts.builder().setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
        .compact(); // The signing key is encoded using the SHA-512 algorithm
    res.addHeader("Authorization", PREFIX + " " + JwtToken);
	res.addHeader("Access-Control-Expose-Headers", "Authorization");
  }

  static public Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      String user = Jwts.parser()
          .setSigningKey(SIGNING_KEY)
          .parseClaimsJws(token.replace(PREFIX, ""))
          .getBody()
          .getSubject();

      if (user != null) 
    	  return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
    return null;
  }
}

