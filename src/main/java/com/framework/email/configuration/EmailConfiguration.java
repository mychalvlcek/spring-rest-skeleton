package com.framework.email.configuration;

import com.framework.email.service.decorator.AsyncMailSender;
import com.framework.email.service.decorator.ConsoleMailSender;
import com.framework.email.service.decorator.LoggableMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class EmailConfiguration {

	private final MailProperties properties;

	@Autowired
	private MessageSource messageSource;

	public EmailConfiguration(MailProperties properties) {
		this.properties = properties;

	}

	@Bean
	@Primary
	@DependsOn("mailSender")
	@Profile({"dev"})
	public JavaMailSender consoleMailSender(JavaMailSender mailSender) {
		return new AsyncMailSender(new LoggableMailSender(new ConsoleMailSender(mailSender)));
	}

	@Bean
	@Primary
	@DependsOn("mailSender")
	@Profile({"!dev"})
	public JavaMailSender loggableMailSender(JavaMailSender mailSender) {
		return new AsyncMailSender(new LoggableMailSender(mailSender));
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

	@Bean
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		templateEngine.setTemplateEngineMessageSource(messageSource);
		return templateEngine;
	}

	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		return templateResolver;
	}
}
