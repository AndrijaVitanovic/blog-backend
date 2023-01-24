package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
public class Contact extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "contact_id")
        private Long id;
        @Column(name = "contact_type")
        private String type;
        @Column(name = "value")
        private String value;
        @JsonIgnore
        @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
        @ManyToOne
        private User user;
}

