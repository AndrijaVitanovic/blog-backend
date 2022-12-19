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
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}
