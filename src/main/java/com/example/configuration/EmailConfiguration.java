package com.example.configuration;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class EmailConfiguration {

	private final MailProperties properties;

	public EmailConfiguration(MailProperties properties) {
		this.properties = properties;

	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(this.properties.getHost());
		sender.setPort(this.properties.getPort());
		sender.setUsername(this.properties.getUsername());
		sender.setPassword(this.properties.getPassword());
		sender.setProtocol(this.properties.getProtocol());
		sender.setDefaultEncoding(this.properties.getDefaultEncoding().name());
		// extra properties
		if (!this.properties.getProperties().isEmpty()) {
			Properties mailProperties = new Properties();
			mailProperties.putAll(this.properties.getProperties());
			sender.setJavaMailProperties(mailProperties);
		}
		return sender;
	}
}
