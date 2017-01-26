package com.example.framework.repository;

import com.example.framework.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
