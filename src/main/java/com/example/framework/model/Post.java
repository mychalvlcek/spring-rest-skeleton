package com.example.framework.model;

import com.framework.common.model.AbstractEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@Entity(name = "posts")
public class Post extends AbstractEntity {

	protected String title;

	protected String content;

	@OneToMany(mappedBy = "post", cascade = {CascadeType.ALL})
	protected List<Comment> comments;

	@ManyToMany(cascade = {CascadeType.ALL})
	List<Tag> tags;
}
