package com.geekster.bloggingproject.repo;

import com.geekster.bloggingproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post,Integer> {
    @Query("select a1 from Post a1 where a1.postLocation=?1")
    List<Post> searchPosts(String postLocation);
}
