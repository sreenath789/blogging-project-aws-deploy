package com.geekster.bloggingproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignInInput {

    private String signInEmail;
    private String signInPassword;
}
