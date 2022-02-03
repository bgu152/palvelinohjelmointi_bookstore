package com.example.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.bookstore.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//you can lock users out of methods in Java code, that is block parts of webpage
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	 private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests().antMatchers("/css/**").permitAll() //Enable css when logged out, you can have multiple endpoints separated by comma
		.and()//we can have multpiple rules
		.authorizeRequests().anyRequest().authenticated()//all other endpoints need authentification
		.and() //We could specify for example .authorizeRequests().antMatchers("/admin").authenticated(), but pointless here
	.formLogin()
		.loginPage("/login")//This if for your own login page
		.defaultSuccessUrl("/booklist",true)//Where we go after successfull login
		.permitAll()
		.and()
	.logout()
		.permitAll();//anyone can log out
		
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
