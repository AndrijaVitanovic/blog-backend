package com.blog.entity;

import com.blog.entity.domain.RecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "user")
public class User extends Auditable implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Column(name="display_name")
    private String displayName;

    @Column(name="email_verified")
    @JsonIgnore
    private Boolean emailVerified = false;

    @Lob
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts = new ArrayList<>();

    @Override
    @JsonIgnore
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
        return emailVerified;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getRecordStatus() != RecordStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getRecordStatus() != RecordStatus.CREDENTIALS_EXPIRED;
    }

    @Override
    public boolean isEnabled() {
        return emailVerified && getRecordStatus() == RecordStatus.ACTIVE;
    }
}
