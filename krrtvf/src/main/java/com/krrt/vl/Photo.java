package com.krrt.vl;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Photo {
    private Long id;
    private Date date;
    private String title;
    private String filename;
    private Set<Image> images;

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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id.equals(photo.id) &&
                date.equals(photo.date) &&
                Objects.equals(title, photo.title) &&
                Objects.equals(filename, photo.filename) &&
                Objects.equals(images, photo.images);
    }
}