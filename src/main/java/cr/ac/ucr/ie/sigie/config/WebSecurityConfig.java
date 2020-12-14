package cr.ac.ucr.ie.sigie.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cr.ac.ucr.ie.sigie.AuthenticationFilter;
import cr.ac.ucr.ie.sigie.LoginFilter;
import cr.ac.ucr.ie.sigie.security.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailService userDetailsService; 

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable().cors().and().authorizeRequests()
		  .antMatchers(HttpMethod.POST, "/login").permitAll()
		  .antMatchers(HttpMethod.GET, "/api/studyplan/").permitAll()
		  .antMatchers(HttpMethod.GET, "/api/studyplan/{id}").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        // Filter for the api/login requests
	        .addFilterBefore(new LoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // Filter for other requests to check JWT in header
	        .addFilterBefore(new AuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	  }
  
	  // short for Cross-Origin Resource Sharing
	  //this is needed for the frontend, which is sending requests from the other origin
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(Arrays.asList("*"));
			config.setAllowedMethods(Arrays.asList("*"));
			config.setAllowedHeaders(Arrays.asList("*"));
			config.setAllowCredentials(true);
	      config.applyPermitDefaultValues();
	      
	      source.registerCorsConfiguration("/**", config);
	      return source;
	}	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	//Crea el encriptador de contraseñas
	@Autowired
	@Bean
    public PasswordEncoder passwordEncoder() {
		//El numero 10 representa qué tan fuerte se desea la encriptacion.
		//Se puede en un rango entre 4 y 31. 
		//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
		//que se inicie la aplicacion, por lo cual tus contrasenas encriptadas no funcionarán bien
        return new BCryptPasswordEncoder(10);
        
    }

}