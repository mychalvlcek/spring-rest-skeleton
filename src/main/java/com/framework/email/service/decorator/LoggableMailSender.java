package com.framework.email.service.decorator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Loggable mail sender decorator
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 */
@Slf4j
public class LoggableMailSender extends AbstractMailSenderDecorator {

	public LoggableMailSender(JavaMailSender mailSender) {
		super(mailSender);
	}

	/**
	 * Extracts string data from {@link MimeMessage}.
	 *
	 * @param mimeMessage
	 * @return
	 */
	public static String getStringInfo(MimeMessage mimeMessage) {
		StringBuilder sb = new StringBuilder("MimeMessage: ");
		try {
			sb.append("from=").append(StringUtils.arrayToCommaDelimitedString(mimeMessage.getFrom())).append("; ");
			sb.append("replyTo=").append(StringUtils.arrayToCommaDelimitedString(mimeMessage.getReplyTo())).append("; ");
			sb.append("to=").append(StringUtils.arrayToCommaDelimitedString(mimeMessage.getRecipients(Message.RecipientType.TO))).append("; ");
			sb.append("cc=").append(StringUtils.arrayToCommaDelimitedString(mimeMessage.getRecipients(Message.RecipientType.CC))).append("; ");
			sb.append("bcc=").append(StringUtils.arrayToCommaDelimitedString(mimeMessage.getRecipients(Message.RecipientType.BCC))).append("; ");
			sb.append("subject=").append(mimeMessage.getSubject());
		} catch (MessagingException e) {
			LOG.error("Error retrieving log message: {}", e.getMessage());
		}

		return sb.toString();
	}

	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		try {
			LOG.info("Sending... {}", getStringInfo(mimeMessage));
			mailSender.send(mimeMessage);
			LOG.info("Successfully sent...");
		} catch (MailException e) {
			LOG.error("Error sending: {}", e.getMessage());
			throw e;
		}
	}
}
