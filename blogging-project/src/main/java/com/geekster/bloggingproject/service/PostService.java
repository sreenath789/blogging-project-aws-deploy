package com.geekster.bloggingproject.service;

import com.geekster.bloggingproject.model.Post;
import com.geekster.bloggingproject.model.User;
import com.geekster.bloggingproject.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo iPostRepo;

    public String createPost(Post post) {
        post.setPostCreatedTimeStamp(LocalDateTime.now());
        iPostRepo.save(post);
        return "Post created successfully!!!!";
    }

    public String removePost(Integer postId, User user) {
        Post existingPost = iPostRepo.findById(postId).orElse(null);
        if(existingPost!=null && existingPost.getPostOwner().equals(user)){
            iPostRepo.delete(existingPost);
            return "Post deleted successfully";
        }
        else if(existingPost==null){
            return "post to be deleted doesn't exist";
        }
        else{
            return "Un-Authorize delete detected";
        }
    }

    public String updatePost(Integer postId, User user, String postContent) {
        Post existingPost = iPostRepo.findById(postId).orElse(null);
        if(existingPost!=null && existingPost.getPostOwner().equals(user)){
            existingPost.setPostContent(postContent);
            iPostRepo.save(existingPost);
            return "Post updated successfully";
        }
        else if(existingPost==null){
            return "post to be updated doesn't exist";
        }
        else{
            return "You are not the post owner,Post owner can only update the post!!!";
        }
    }

    public boolean validPost(Post instaPost) {
        return (instaPost!=null && iPostRepo.existsById(instaPost.getPostId()));
    }

    public List<Post> searchPostsByLocation(String postLocation) {
        return iPostRepo.searchPosts(postLocation);
    }
}
