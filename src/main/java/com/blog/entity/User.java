package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends Auditable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "about")
    private String about;
    @JoinColumn(name = "image_fk", referencedColumnName = "media_id")
    @ManyToOne
    private Media image;
    @Column(name="display_name")
    private String displayName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = WRITE_ONLY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
    private List<Role> roles = new ArrayList<>();

    //TODO: Add contacts column

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO: implement RecordStatus enum
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO: implement RecordStatus enum
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO: implement RecordStatus enum
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO: implement RecordStatus enum
        return false;
    }
}
