package com.quad.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class TweetRequest {
    private String text;
    private boolean anonymous;
    private List<MediaItem> media;
    private PollRequest poll;

    public List<String> getMediaUris() {
        return media == null ? List.of() : media.stream().map(MediaItem::getUri).toList();
    }

    @Data
    public static class MediaItem {
        private String uri;
        private String type;
    }

    @Data
    public static class PollRequest {
        private List<String> options;
        private String duration;
    }
}