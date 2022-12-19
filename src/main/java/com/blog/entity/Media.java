package com.blog.entity;

import com.blog.entity.domain.MediaType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer id;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "type")
    private MediaType type = MediaType.POST_IMAGE;
    @Column(name = "uri")
    private String uri;
    @Column(name = "height")
    private Integer height;
    @Column(name = "width")
    private Integer width;
    @Column(name = "size")
    private Long size;
}
