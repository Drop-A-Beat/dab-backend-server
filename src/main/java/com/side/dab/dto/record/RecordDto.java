package com.side.dab.dto.record;

import com.side.dab.entity.Album;
import com.side.dab.entity.Record;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordDto {

    private final long id;
    private final Album album;
    private final String title;
    private final boolean isTitle;
    private final String musicFileUrl;
    private final String description;
    private final String composer;
    private final String songwriter;
    private final String singer;
    private final String lyric;
    private final short genreId;

    public RecordDto(Record record) {
        this.id = record.getId();
        this.album = record.getAlbum();
        this.title = record.getTitle();
        this.isTitle = record.getIsTitile();
        this.musicFileUrl = record.getMusicFileUrl();
        this.description = record.getDescription();
        this.composer = record.getComposer();
        this.songwriter = record.getSongwriter();
        this.singer = record.getSinger();
        this.lyric = record.getLyric();
        this.genreId = record.getGenreId();
    }

    @Getter
    @RequiredArgsConstructor
    public static class SearchRequest{
        private final String title;
        private final Boolean isTitle;
        private final String composer;
        private final String songwriter;
        private final String singer;
        private final Short genreId;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SearchResponse{
        private final long id;
        private final Album album;
        private final String title;
        private final boolean isTitle;
        private final String musicFileUrl;
        private final String description;
        private final String composer;
        private final String songwriter;
        private final String singer;
        private final String lyric;
        private final short genreId;

        public SearchResponse(Record record) {
            this.id = record.getId();
            this.album = record.getAlbum();
            this.title = record.getTitle();
            this.isTitle = record.getIsTitile();
            this.musicFileUrl = record.getMusicFileUrl();
            this.description = record.getDescription();
            this.composer = record.getComposer();
            this.songwriter = record.getSongwriter();
            this.singer = record.getSinger();
            this.lyric = record.getLyric();
            this.genreId = record.getGenreId();
        }
    }


    @Getter
    @RequiredArgsConstructor
    public static class PostRequest {
        private final String title;
        private final Boolean isTitle;
        private final String musicFileUrl;
        private final String description;
        private final String composer;
        private final String songwriter;
        private final String singer;
        private final String lyric;
        private final Short genreId;
    }

}
