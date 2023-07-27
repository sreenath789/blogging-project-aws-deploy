package com.geekster.bloggingproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentBody;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime commentCreationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_comment_post_id")
    private Post instaPost;

    @ManyToOne
    @JoinColumn(name="fk_comment_user_id")
    private User commentUser;



}
