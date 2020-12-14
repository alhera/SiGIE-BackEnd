package cr.ac.ucr.ie.sigie;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cr.ac.ucr.ie.sigie.domain.AccountCredentials;
import cr.ac.ucr.ie.sigie.security.AuthenticationService;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  public LoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(
	HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
	  String resu =  req.getReader().toString();
	  String username = req.getParameter("username");
	  String password = req.getParameter("password");
	  AccountCredentials accountCredentials = new AccountCredentials();
	  accountCredentials.setUsername(username);
	  accountCredentials.setPassword(password);
	/*AccountCredentials creds = new ObjectMapper()
        .readValue(req.getInputStream(), AccountCredentials.class);*/
	return getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
        		accountCredentials.getUsername(),
        		accountCredentials.getPassword(),
                Collections.emptyList()
        )
    );
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
	  AuthenticationService.addToken(res, auth.getName());
  }
}
