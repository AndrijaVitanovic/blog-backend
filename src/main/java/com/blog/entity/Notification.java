package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "notification")
public class Notification extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "notification_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @Column(name = "seen")
    private Boolean seen;
    @Column(name = "seen_time")
    private Instant seenTime;
    @Column(name = "`read`")
    private Boolean read;
    @Column(name = "type")
    private String type;
    @Column(name = "action_url")
    private String actionUrl;
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
