package com.example.service.email.preparator;

import com.example.service.email.MailService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Callback interface for the preparation of templatable JavaMail MIME messages.
 * <p>
 * <p>The corresponding {@code send} method of {@link MailService}
 * will take care of the actual creation of a {@link MimeMessage} instance,
 * and of proper exception conversion.
 * <p>
 * <p>It is often convenient to use a {@link MimeMessageHelper} for populating
 * the passed-in MimeMessage, in particular when working with attachments or
 * special character encodings.
 * See {@link MimeMessageHelper MimeMessageHelper's javadoc} for an example.
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 * @see MailService#send(TemplatableMailPreparator)
 * @see MimeMessageHelper
 */
public abstract class TemplatableMailPreparator implements MimeMessagePreparator {

	private MimeMessageHelper message;

	/**
	 * Returns mail template context.
	 * It represents all the data you want pass to an email template.
	 */
	public abstract IContext getContext();

	/**
	 * Returns path to mail template.
	 */
	public abstract String getTemplate();

	/**
	 * Returns {@link MimeMessageHelper} for given {@link MimeMessage}
	 * @param mimeMessage
	 * @return MimeMessageHelper
	 * @throws MessagingException
	 */
	public MimeMessageHelper getMessageHelper(MimeMessage mimeMessage) throws MessagingException {
		if (message == null) {
			message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		}

		return message;
	}
}
