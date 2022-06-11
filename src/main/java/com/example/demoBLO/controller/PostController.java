package com.example.demoBLO.controller;


import com.example.demoBLO.model.Comment;
import com.example.demoBLO.model.Post;
import com.example.demoBLO.service.CommentService;
import com.example.demoBLO.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            return "404";
        }
    }

    @GetMapping("/createNewPost")
    public String createNewPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "postForm";
    }

    @PostMapping("/createNewPost")
    public String createNewPost(@Valid @ModelAttribute Post post, BindingResult bindingResult, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "postForm";
        }
        this.postService.save(post);
        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }
    @GetMapping("editPost/{id}")
    public String editPost(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = this.postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "postForm";
        }
        return "error";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id) {

        Optional<Post> optionalPost = this.postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            this.postService.delete(post);
            return "redirect:/";
        }
        return "error";
    }
}