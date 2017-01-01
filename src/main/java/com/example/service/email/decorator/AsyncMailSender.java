package com.example.service.email.decorator;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;

/**
 * Async mail sender. In background {@link Async @Async} annotation is used.
 * For correct functionality {@link org.springframework.scheduling.annotation.EnableAsync @EnableAsync}
 * has to be used somewhere in your application.
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 * @see Async
 * @see org.springframework.scheduling.annotation.EnableAsync
 */
public class AsyncMailSender extends AbstractMailSenderDecorator {

	public AsyncMailSender(JavaMailSender mailSender) {
		super(mailSender);
	}

	@Async
	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		super.send(mimeMessage);
	}
}
