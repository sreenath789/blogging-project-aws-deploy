package com.geekster.bloggingproject.controller;

import com.geekster.bloggingproject.model.Comment;
import com.geekster.bloggingproject.model.Follow;
import com.geekster.bloggingproject.model.Post;
import com.geekster.bloggingproject.model.User;
import com.geekster.bloggingproject.model.dto.SignInInput;
import com.geekster.bloggingproject.model.dto.SignUpOutput;
import com.geekster.bloggingproject.service.AuthenticationService;
import com.geekster.bloggingproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    //sign up the user
    @PostMapping("user/sign-up")
    public SignUpOutput signUpUser(@RequestBody User user){
        return userService.signUpUser(user);
    }

    //sign in the user
    @PostMapping("user/sign-in")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    //sign out the user
    @DeleteMapping("user/sign-out")
    public String signOutUser(String email,String token){
        if(authenticationService.authenticateUser(email,token)){
            return userService.signOutUser(email);
        }
        return "sign out not allowed for non-authenticate user!";
    }

    //create a post
    @PostMapping("post")
    public String createPost(@RequestBody Post post,@RequestParam String email,@RequestParam String token){
        if(authenticationService.authenticateUser(email,token)){
            return userService.createPost(post,email);
        }
        return "Not an authenticated user activity!";
    }

    //remove post
    @DeleteMapping("post")
    public String removePost(@RequestParam Integer postId,@RequestParam String email,@RequestParam String token){
        if(authenticationService.authenticateUser(email,token)){
            return userService.removePost(postId,email);
        }
        return "Not an authenticated user activity!";
    }

    //update post
    @PutMapping("post")
    public String updatePost(@RequestParam String email,@RequestParam String token,@RequestParam Integer postId,@RequestParam String postContent){
        if(authenticationService.authenticateUser(email,token)){
            return userService.updatePost(postId,email,postContent);
        }
        return "Not an authenticated user activity!";
    }

    //get all posts based on location
    @GetMapping("posts")
    public ResponseEntity<List<Post>> searchPostsByLocation(@RequestParam String postLocation, @RequestParam String email, @RequestParam String token){
        HttpStatus status;
        List<Post> postList=null;
        if(authenticationService.authenticateUser(email,token)){
            postList = userService.searchPostsByLocation(postLocation);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<List<Post>>(postList,status);
    }

    //add comment
    @PostMapping("comment")
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String token){
        if(authenticationService.authenticateUser(commenterEmail,token)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    //remove comment
    @DeleteMapping("comment")
    public String removeComment(@RequestParam Integer commentId,@RequestParam String email,@RequestParam String token){
        if(authenticationService.authenticateUser(email,token)) {
            return userService.removeComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    //follow the user
    @PostMapping("follow")
    public String followUser(@RequestBody Follow follow, @RequestParam String followerEmail, @RequestParam String token){
        if(authenticationService.authenticateUser(followerEmail,token)){
            return userService.followUser(follow,followerEmail);
        }
        else{
            return "Not an Authenticated user activity!!!";
        }
    }

    //unfollow the user
    @DeleteMapping("unfollow/target/{followId}")
    public String unFollowUser(@PathVariable Integer followId, @RequestParam String followerEmail, @RequestParam String followerToken)
    {
        if(authenticationService.authenticateUser(followerEmail,followerToken)) {
            return userService.unFollowUser(followId,followerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

}
