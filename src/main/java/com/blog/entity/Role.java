package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role implements GrantedAuthority {
    public static final Role ADMIN_ROLE = new Role(1, "ADMIN");
    public static final Role AUTHOR_ROLE = new Role(2, "AUTHOR");
    public static final Role USER_ROLE = new Role(3, "USER");
    public static final String PREFIX = "ROLE_";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return PREFIX + name;
    }
}
