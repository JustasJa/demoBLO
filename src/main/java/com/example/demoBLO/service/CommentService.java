package com.example.demoBLO.service;

import com.example.demoBLO.model.Comment;


public interface CommentService {

    Comment save(Comment comment);

    void delete(Comment comment);

}