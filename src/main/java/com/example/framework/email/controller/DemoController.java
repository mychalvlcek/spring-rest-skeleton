package com.example.framework.email.controller;

import com.example.framework.email.preparator.ExampleMailPreparator;
import com.framework.email.service.MailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/demo")
public class DemoController {

	@NonNull
	private MessageSource messageSource;

	@NonNull
	private MailService mailService;


	@RequestMapping("/i18n")
	public String i18n(Locale locale) {
		Object[] args = {2, locale.getLanguage()};
		String message = messageSource.getMessage("it.works", args, locale);
		LOG.info(message);
		return message;
	}

	@RequestMapping(path = {"/mail"})
	public String testEmail() throws Exception {
		LOG.info("test email sending");
		mailService.send(new ExampleMailPreparator());

		return "Email was sent";
	}
}
