package com.geekster.bloggingproject.repo;

import com.geekster.bloggingproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
