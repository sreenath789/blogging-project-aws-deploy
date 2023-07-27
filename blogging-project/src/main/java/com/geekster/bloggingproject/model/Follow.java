package com.geekster.bloggingproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;

    @ManyToOne
    @JoinColumn(name = "fk_actual_user")
    private User currentUser;

    @ManyToOne
    @JoinColumn(name = "fk_follower_of_actual_user")
    private User currentUserFollower;

}
