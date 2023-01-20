package com.side.dab.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cover_image_file_url")
    private String coverImageFileUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "is_show", nullable = false)
    private boolean isShow;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;
}
