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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes("comment")
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model) {
        Optional<Post> postOptional = this.postService.getById(id);
        if (postOptional.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(postOptional.get());
            model.addAttribute(comment);
            return "comment";
        } else {
            return "error";
        }
    }

    @PostMapping("/comment")
    public String validateComment(@Valid @ModelAttribute Comment comment, BindingResult bindingResult, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            System.err.println("Klaida");
            return "comment";
        }
        this.commentService.save(comment);
        sessionStatus.setComplete();
        return "redirect:/post/" + comment.getPost().getId();
    }
}