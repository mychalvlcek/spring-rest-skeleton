package com.example.framework.model.init;

import com.example.framework.model.Comment;
import com.example.framework.model.Post;
import com.example.framework.model.Tag;
import com.example.framework.repository.PostRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
@Profile("!production")
class FixturesInitializer {

	private final Faker faker = new Faker();

	private final PostRepository postRepository;

	@EventListener
	public void init(ApplicationReadyEvent event) {

		if (postRepository.count() > 0) {
			return;
		}

		IntStream.range(0, 10).forEach(
			n -> {
				Tag tag = new Tag();
				tag.setName(faker.lorem().word());

				Comment comment = new Comment();
				comment.setText(faker.lorem().sentence());

				Post post = new Post();
				post.setTitle(faker.company().name());
				post.setContent(faker.lorem().paragraph());
				post.setComments(Arrays.asList(comment));
				post.setTags(Arrays.asList(tag));

				comment.setPost(post);

				postRepository.save(post);
			}
		);
	}
}
