package com.example.demoBLO.service;

import com.example.demoBLO.model.Post;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> getById(Long id);

    Collection<Post> getAll();

    Page<Post> getAllPageable(int page);

    Post save(Post post);

    void delete(Post post);
}
