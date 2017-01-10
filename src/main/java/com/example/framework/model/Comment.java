package com.example.framework.model;

import com.framework.common.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Data
@Entity(name = "comments")
public class Comment extends AbstractEntity {

	protected String text;

	@ManyToOne
	protected Post post;
}
