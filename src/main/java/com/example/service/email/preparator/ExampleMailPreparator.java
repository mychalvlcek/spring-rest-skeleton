package com.example.service.email.preparator;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

/**
 * Example mail preparator implementing {@link TemplatableMailPreparator}.
 *
 * @author Michal Vlcek <mychalvlcek@gmail.com>
 * @see TemplatableMailPreparator
 */
public class ExampleMailPreparator extends TemplatableMailPreparator {

	@Override
	public IContext getContext() {
		final Context ctx = new Context();
		ctx.setVariable("now", new Date());
		ctx.setVariable("name", "Name Surname");
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

		return ctx;
	}

	@Override
	public String getTemplate() {
		return "example";
	}

	@Override
	public void prepare(MimeMessage mimeMessage) throws Exception {
		final MimeMessageHelper message = getMessageHelper(mimeMessage);
		message.setSubject("Example HTML email");
		message.setFrom("mychalvlcek@gmail.com");
		message.setTo("springtest@gustr.com");

		// attachment
		ByteArrayResource string = new ByteArrayResource("test".getBytes(StandardCharsets.UTF_8));
		message.addAttachment("email.txt", string);
	}
}
