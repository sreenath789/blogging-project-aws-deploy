package com.geekster.bloggingproject.repo;

import com.geekster.bloggingproject.model.Follow;
import com.geekster.bloggingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowRepo extends JpaRepository<Follow,Integer> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User followTargetUser, User follower);
}
