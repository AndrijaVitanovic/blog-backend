package com.blog.entity;

import com.blog.entity.domain.MediaType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;

/**
 * Right now is not used, needs to be optimized.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "media")
public class Media extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "media_id")
    private Long id;
    @Column(name = "keywords")
    private String keywords;
    @Enumerated(STRING)
    @Column(name = "type")
    private MediaType type;
    @Column(name = "uri")
    private String uri;
    @Column(name = "height")
    private Integer height;
    @Column(name = "width")
    private Integer width;
    @Column(name = "size")
    private Long size;
}
