package com.krrt.vl;

import java.util.Date;
import java.util.Objects;

public class Video {
    private Long id;
    private Date date;
    private String title;
    private String filename;
    private String videofilename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getVideofilename() {
        return videofilename;
    }

    public void setVideofilename(String videofilename) {
        this.videofilename = videofilename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return id.equals(video.id) &&
                date.equals(video.date) &&
                Objects.equals(title, video.title) &&
                Objects.equals(filename, video.filename) &&
                Objects.equals(videofilename, video.videofilename);
    }
}
