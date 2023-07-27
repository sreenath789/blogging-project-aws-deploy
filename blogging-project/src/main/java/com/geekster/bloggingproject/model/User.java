package com.geekster.bloggingproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false)
    @NotEmpty
    private String firstName;
    @Column(nullable = false)
    @NotEmpty
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String blogName;
    private String blogBio;
    @Column(unique = true,nullable = false)
    @Email
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
    @Column(nullable = false)
    @Pattern(regexp = "\\d{2}-\\d{10}", message = "Provide valid mobile number")
    private String mobileNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//to hide in json
    private boolean blueTick;




}
