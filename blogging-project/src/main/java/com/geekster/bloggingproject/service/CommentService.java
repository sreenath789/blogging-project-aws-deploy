package com.geekster.bloggingproject.service;

import com.geekster.bloggingproject.model.Comment;
import com.geekster.bloggingproject.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    ICommentRepo iCommentRepo;

    public void addComment(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        iCommentRepo.save(comment);
    }

    public Comment findComment(Integer commentId) {
        return iCommentRepo.findById(commentId).orElse(null);
    }

    public void removeComment(Comment existingComment) {
        iCommentRepo.delete(existingComment);
    }
}
