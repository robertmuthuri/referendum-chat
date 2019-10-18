package com.example.referendum_chat;

import java.util.Objects;

public class ResourceCenter {
    private String imageUrl;
    private String name;
    private String location;
    private String url;

    public ResourceCenter(String imageUrl, String name, String location, String url) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.location = location;
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceCenter that = (ResourceCenter) o;
        return Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, name, location, url);
    }
}
