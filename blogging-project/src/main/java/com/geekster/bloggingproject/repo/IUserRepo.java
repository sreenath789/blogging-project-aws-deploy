package com.geekster.bloggingproject.repo;

import com.geekster.bloggingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String signupMail);
}
