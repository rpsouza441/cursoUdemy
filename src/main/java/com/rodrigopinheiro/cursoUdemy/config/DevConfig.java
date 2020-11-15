package com.rodrigopinheiro.cursoUdemy.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigopinheiro.cursoUdemy.services.DBService;
import com.rodrigopinheiro.cursoUdemy.services.EmailService;
import com.rodrigopinheiro.cursoUdemy.services.SMTPEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbservice;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;

		}
		dbservice.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SMTPEmailService();
	}

}
