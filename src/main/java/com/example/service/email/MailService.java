package com.example.service.email;

import com.example.service.email.preparator.TemplatableMailPreparator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;

/**
 * Service for sending templatable emails (with thymeleaf templating engine).
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 * @see TemplatableMailPreparator
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailService {

	@NonNull
	private final JavaMailSender mailSender;

	@NotNull
	private final TemplateEngine emailTemplateEngine;

	@NotNull
	private final Environment environment;

	/**
	 * Send MimeMessage given by {@link TemplatableMailPreparator}
	 *
	 * @param preparator preparator from which the sent message will be created
	 * @throws Exception
	 */
	public void send(TemplatableMailPreparator preparator) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		preparator.prepare(mimeMessage);

		// template context & html content
		Context ctx = (Context) preparator.getContext();
		ctx.setVariable("baseUrl", environment.getProperty("static.base_url"));
		final String htmlContent = emailTemplateEngine.process(preparator.getTemplate(), ctx);
		final MimeMessageHelper message = preparator.getMessageHelper(mimeMessage);
		message.setText(htmlContent, true);

		send(mimeMessage);
	}

	public void send(MimeMessage message) {
		mailSender.send(message);
	}
}
