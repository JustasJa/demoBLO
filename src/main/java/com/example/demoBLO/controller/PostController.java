package com.example.demoBLO.controller;


import com.example.demoBLO.model.Comment;
import com.example.demoBLO.service.CommentService;
import com.example.demoBLO.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", this.postService.getById(id).get());

        return "post";
    }
}