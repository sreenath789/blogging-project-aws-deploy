package com.geekster.bloggingproject.service;

import com.geekster.bloggingproject.model.AuthenticationToken;
import com.geekster.bloggingproject.model.User;
import com.geekster.bloggingproject.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public void saveToken(AuthenticationToken token) {
        iAuthenticationRepo.save(token);
    }

    public boolean authenticateUser(String email, String token) {
        AuthenticationToken authenticationToken = iAuthenticationRepo.findFirstByTokenValue(token);
        if(authenticationToken==null){
            return false;
        }
        String tokenConnectedMail = authenticationToken.getUser().getUserEmail();
        return tokenConnectedMail.equals(email);

    }

    public AuthenticationToken findFirstByUser(User user) {
        return iAuthenticationRepo.findFirstByUser(user);
    }

    public void deleteToken(AuthenticationToken authenticationToken) {
        iAuthenticationRepo.delete(authenticationToken);
    }
}
