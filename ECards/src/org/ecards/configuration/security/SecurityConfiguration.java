package org.ecards.configuration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
	
	/**
	 * We Will activate this code in case we want to use the built-in login of spring 
	 * instead of our custom logic 
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .passwordEncoder(getPasswordEncoder());
		    .passwordEncoder(new BCryptPasswordEncoder());
	}
    **/ 

	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder());
    }
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	
		 http.authorizeRequests()
	                .antMatchers("/js/**","/css/**","/registration").permitAll()
	                .antMatchers("/").access("hasRole('ROLE_ADMIN') Or hasRole('ROLE_USER')")
	                .antMatchers("/cards").access("hasRole('ROLE_ADMIN') Or hasRole('ROLE_USER')")
	                .anyRequest().authenticated()
	                .and()
	         .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	         .logout()
	         .permitAll()
	         .logoutSuccessUrl("/")
			 .logoutUrl("/")
			 .invalidateHttpSession(true);
		 
		//http.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Bean
	public PasswordEncoder noOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
