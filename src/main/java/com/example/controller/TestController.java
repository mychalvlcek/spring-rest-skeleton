package com.example.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/test")
public class TestController {

	@NonNull
	private JavaMailSender mailSender;

	@NonNull
	private MessageSource messageSource;


	@RequestMapping("/i18n")
	public String i18n(Locale locale) {
		Object[] args = {2, locale.getLanguage()};
		String message = messageSource.getMessage("it.works", args, locale);
		LOG.info(message);

		return message;
	}

	@RequestMapping("/email")
	public String testEmail() throws MessagingException {
		LOG.info("test email sending");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("mychalvlcek@gmail.com");
		helper.setTo("springtest@gustr.com");
		helper.setSubject("Test Subject!");
		helper.setText("Test of email message from Java." +
			"Message could be found @ http://www.fakemailgenerator.com/#/gustr.com/springtest/");
		mailSender.send(message);

		return "Email was sent";
	}
}
