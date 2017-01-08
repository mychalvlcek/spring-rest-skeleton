package com.framework.email.service.decorator;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

/**
 * Base {@link JavaMailSender} decorator class.
 * Class just wraps underlying (decorated) object.
 * Furthermore it is intended to use it with other decorators.
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 * @see AsyncMailSender
 * @see LoggableMailSender
 */
public abstract class AbstractMailSenderDecorator implements JavaMailSender {

	/**
	 * The {@link JavaMailSender} decorated instance.
	 */
	protected JavaMailSender mailSender;

	public AbstractMailSenderDecorator(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	@Override
	public MimeMessage createMimeMessage() {
		return this.mailSender.createMimeMessage();
	}

	@Override
	public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
		return this.mailSender.createMimeMessage(contentStream);
	}

	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		this.mailSender.send(mimeMessage);
	}

	@Override
	public void send(MimeMessage... mimeMessages) throws MailException {
		this.mailSender.send(mimeMessages);
	}

	@Override
	public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
		this.mailSender.send(mimeMessagePreparator);
	}

	@Override
	public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
		this.mailSender.send(mimeMessagePreparators);
	}

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		this.mailSender.send(simpleMessage);
	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		this.mailSender.send(simpleMessages);
	}
}
