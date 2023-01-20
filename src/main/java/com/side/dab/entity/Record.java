package com.side.dab.entity;

import com.side.dab.dto.record.RecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "record")
public class Record {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_title", nullable = false)
    private boolean isTitle;

    @Column(name = "music_file_url", nullable = false)
    private String musicFileUrl;

    @Column(name = "description")
    private String description;

    // 작곡가
    @Column(name = "composer")
    private String composer;

    // 작사가
    @Column(name = "songwriter")
    private String songwriter;

    // 가수
    @Column(name = "singer")
    private String singer;

    // 가사
    @Column(name = "lyric")
    private String lyric;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "genre_id")
//    private Genre genre;

    @Column(name = "genre_id", nullable = false)
    private short genreId;

    public Record(RecordDto.PostRequest postRequest){
        this.title = postRequest.getTitle();
        this.isTitle = postRequest.getIsTitle();
        this.musicFileUrl = postRequest.getMusicFileUrl();
        this.description = postRequest.getDescription();
        this.composer = postRequest.getComposer();
        this.songwriter = postRequest.getSongwriter();
        this.singer = postRequest.getSinger();
        this.lyric = postRequest.getLyric();
        this.genreId = postRequest.getGenreId();
    }

    public boolean getIsTitile(){
        return this.isTitle;
    }
}
