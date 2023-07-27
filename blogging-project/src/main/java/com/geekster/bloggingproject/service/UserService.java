package com.geekster.bloggingproject.service;

import com.geekster.bloggingproject.model.*;
import com.geekster.bloggingproject.model.dto.SignInInput;
import com.geekster.bloggingproject.model.dto.SignUpOutput;
import com.geekster.bloggingproject.repo.IUserRepo;
import com.geekster.bloggingproject.service.utility.emailutility.EmailHandler;
import com.geekster.bloggingproject.service.utility.hashingutility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    public SignUpOutput signUpUser(User user) {
        //valid email
        String signupMail = user.getUserEmail();
        if(signupMail==null){
            return new SignUpOutput(false,"Invalid Mail!!");
        }

        //existed user or not
        User existingUser = iUserRepo.findFirstByUserEmail(signupMail);
        if(existingUser!=null){
            return new SignUpOutput(false,"User already exists!! please sign in");
        }

        //save the user
        try {
            String encryptedPassword = PasswordEncrypter.getPasswordEncrypter(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            iUserRepo.save(user);
            return new SignUpOutput(true,"Account created successfully!");
        }
        catch (Exception e){
            return new SignUpOutput(false,"Internal server error occured!");
        }
    }

    public String signInUser(SignInInput signInInput) {
        //valid mail
        String signInEmail = signInInput.getSignInEmail();
        if(signInEmail==null){
            return "Invalid email";
        }

        //user exists or not
        User existingUser = iUserRepo.findFirstByUserEmail(signInEmail);
        if(existingUser==null){
            return "User not exists please sign up!";
        }

        //existing user
        try{
            String encryptedPassword = PasswordEncrypter.getPasswordEncrypter(signInInput.getSignInPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword)){
                AuthenticationToken token = new AuthenticationToken(existingUser);
                authenticationService.saveToken(token);

                EmailHandler.sendEmail(signInEmail,"Authentication Token",token.getTokenValue());
                return "Authentication code sent to your email!";
            }
            else{
                return "Invalid credentials";
            }
        }
        catch (Exception e){
            return "Internal server error occurred!";
        }

    }

    public String signOutUser(String email) {
        User user = iUserRepo.findFirstByUserEmail(email);
        AuthenticationToken authenticationToken = authenticationService.findFirstByUser(user);
        authenticationService.deleteToken(authenticationToken);
        return "User signed out successfully!!";
    }

    public String createPost(Post post, String email) {
        User user = iUserRepo.findFirstByUserEmail(email);
        post.setPostOwner(user);
        return postService.createPost(post);
    }

    public String removePost(Integer postId, String email) {
        User user = iUserRepo.findFirstByUserEmail(email);
        return postService.removePost(postId,user);
    }

    public String updatePost(Integer postId, String email, String postContent) {
        User user = iUserRepo.findFirstByUserEmail(email);
        return postService.updatePost(postId,user,postContent);
    }

    public String addComment(Comment comment, String commenterEmail) {
        boolean validPost = postService.validPost(comment.getInstaPost());
        if(validPost){
            User commenter = iUserRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommentUser(commenter);
            commentService.addComment(comment);
            return "Comment added successfully";
        }
        else{
            return "Cannot comment on Invalid Post!!";
        }
    }

    public String removeComment(Integer commentId, String email) {
        Comment existingComment = commentService.findComment(commentId);
        if(existingComment!=null){
            if(authoriseRemover(existingComment,email)){
                commentService.removeComment(existingComment);
                return "Comment removed";
            }
            else{
                return "Un-Authorise delete detected!!!!";
            }
        }
        else{
            return "comment to be deleted not found";
        }

    }

    private boolean authoriseRemover(Comment existingComment, String email) {
        String postOwnerEmail = existingComment.getInstaPost().getPostOwner().getUserEmail();
        String commenterMail = existingComment.getCommentUser().getUserEmail();
        return postOwnerEmail.equals(email) || commenterMail.equals(email);
    }

    public List<Post> searchPostsByLocation(String postLocation) {
        return postService.searchPostsByLocation(postLocation);
    }

    public String followUser(Follow follow, String followerEmail) {
        User followTargetUser = iUserRepo.findById(follow.getCurrentUser().getUserId()).orElse(null);
        User follower = iUserRepo.findFirstByUserEmail(followerEmail);

        if(followTargetUser!=null){
            if(followService.isFollowAllowed(followTargetUser,follower)){
                followService.startFollowing(follow,follower);
                return follower.getFirstName()  + " is now following " + followTargetUser.getFirstName();
            }
            else if(followTargetUser.equals(follower)){
                return "you cant follow your own account";
            }
            else{
                return follower.getFirstName()  + " is Already following " + followTargetUser.getFirstName();
            }
        }
        else{
            return "Follow target user not found!!!!";
        }

    }

    public String unFollowUser(Integer followId, String followerEmail) {
        Follow existingFollow = followService.findFollow(followId);
        if(existingFollow!=null){
            if(authoriseUnFollowUser(existingFollow,followerEmail)){
                followService.unFollow(existingFollow);
                return existingFollow.getCurrentUser().getFirstName() +"not followed by"+iUserRepo.findFirstByUserEmail(followerEmail).getFirstName();
            }
            else{
                return "unauthorise unfollow detected!!";
            }
        }
        else{
            return "Invalid follow mapping!!!";
        }
    }

    private boolean authoriseUnFollowUser(Follow existingFollow, String email) {
        String targetEmail  = existingFollow.getCurrentUser().getUserEmail();
        String followerEmail = existingFollow.getCurrentUserFollower().getUserEmail();
        return targetEmail.equals(email) || followerEmail.equals(email);
    }
}
