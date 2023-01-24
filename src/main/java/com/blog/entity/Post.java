package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "excerpt")
    private String excerpt;
    @Column(name = "body")
    private String body;
    @Column(name = "published")
    private Boolean published;
    @Column(name = "views")
    private Long views;
    @Column(name = "slug")
    private String slug;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = WRITE_ONLY)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_fk"), inverseJoinColumns = @JoinColumn(name = "tag_fk"))
    private List<Tag> tags = new ArrayList<>();
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    @ManyToOne
    private User user;
}





