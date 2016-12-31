package com.example.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

	@NonNull
	private MessageSource messageSource;


	@RequestMapping("/i18n")
	public String i18n(Locale locale) {
		Object[] args = {2, locale.getLanguage()};
		String message = messageSource.getMessage("it.works", args, locale);
		LOG.info(message);

		return message;
	}
}
