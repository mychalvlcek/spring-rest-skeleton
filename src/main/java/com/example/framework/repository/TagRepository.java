package com.example.framework.repository;

import com.example.framework.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TagRepository extends CrudRepository<Tag, Long> {
}
