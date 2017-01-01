package com.example.service.email.decorator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Mail sender decorator for development environment.
 * Instead of sending emails just print them to console.
 * <p>ALERT: This decorator does NOT propagate calls to next decorators in chain.
 * So recommended usage is like following:</p>
 *
 * <pre class="code">
 *     new AnotherMailSenderDecorator(new ConsoleMailSender(mailSender));
 * </pre>
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 */
@Slf4j
public class ConsoleMailSender extends AbstractMailSenderDecorator {

	public ConsoleMailSender(JavaMailSender mailSender) {
		super(mailSender);
	}

	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		try {
			System.out.println("---- START OF EMAIL MESSAGE ----");
			System.out.println();

			mimeMessage.writeTo(System.out);

			System.out.println();
			System.out.println("----- END OF EMAIL MESSAGE -----");
		} catch (MessagingException | IOException e) {
			LOG.error("Error sending to console: {}", e.getMessage());
		}
	}
}
