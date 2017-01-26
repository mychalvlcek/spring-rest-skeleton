package com.example.framework.repository;

import com.example.framework.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface PostRepository extends CrudRepository<Post, Long> {
}
