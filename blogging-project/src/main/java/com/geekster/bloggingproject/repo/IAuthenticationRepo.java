package com.geekster.bloggingproject.repo;

import com.geekster.bloggingproject.model.AuthenticationToken;
import com.geekster.bloggingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByUser(User user);
}
