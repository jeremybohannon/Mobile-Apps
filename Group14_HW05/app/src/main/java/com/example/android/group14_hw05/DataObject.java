package com.example.android.group14_hw05;

public class DataObject {
    String updatedDate;
    String releaseDate;
    String title;
    String summary;
    String smallImageURL;
    String largeImageURL;

    public DataObject() {
        this.updatedDate = "";
        this.releaseDate = "";
        this.title = "";
        this.summary = "";
        this.smallImageURL = "";
        this.largeImageURL = "";
    }

    public DataObject(String updatedDate, String releaseDate, String title, String summary, String smallImageURL, String largeImageURL) {
        this.updatedDate = updatedDate;
        this.releaseDate = releaseDate;
        this.title = title;
        this.summary = summary;
        this.smallImageURL = smallImageURL;
        this.largeImageURL = largeImageURL;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "updatedDate='" + updatedDate + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", smallImageURL='" + smallImageURL + '\'' +
                ", largeImageURL='" + largeImageURL + '\'' +
                '}';
    }
}
