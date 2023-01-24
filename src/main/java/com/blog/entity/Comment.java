package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "body")
    private String body;

    @JoinColumn(name="user_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "parent")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="comment_fk", referencedColumnName = "comment_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Comment parent;

    @ManyToOne
    @JoinColumn(name="post_fk", referencedColumnName = "post_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post post;

}
