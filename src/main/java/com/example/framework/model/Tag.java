package com.example.framework.model;

import com.framework.common.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;


@Data
@Entity(name = "tags")
public class Tag extends AbstractEntity {

	protected String name;

	@ManyToMany(mappedBy = "tags")
	List<Post> posts;
}
