package com.geekster.bloggingproject.service;

import com.geekster.bloggingproject.model.Follow;
import com.geekster.bloggingproject.model.User;
import com.geekster.bloggingproject.repo.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    IFollowRepo iFollowRepo;


    public boolean isFollowAllowed(User followTargetUser, User follower) {
        List<Follow> followList = iFollowRepo.findByCurrentUserAndCurrentUserFollower(followTargetUser,follower);
        return followList!=null && followList.isEmpty() && !followTargetUser.equals(follower);
    }

    public void startFollowing(Follow follow, User follower) {
        follow.setCurrentUserFollower(follower);
        iFollowRepo.save(follow);
    }

    public Follow findFollow(Integer followId) {
        return iFollowRepo.findById(followId).orElse(null);
    }

    public void unFollow(Follow existingFollow) {
        iFollowRepo.delete(existingFollow);
    }
}
