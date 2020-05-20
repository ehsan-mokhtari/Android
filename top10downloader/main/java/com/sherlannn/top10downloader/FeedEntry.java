package com.sherlannn.top10downloader;

//for storing the data we've downloaded from url

public class FeedEntry {
    private String name;
    private String summary;
    private String releaseDate;
    private String artist;

    public String getName() {
        return name;}

    public void setName(String name) {
        this.name = name;}

    public String getSummary() {
        return summary;}

    public void setSummary(String summary) {
        this.summary = summary;}

    public String getReleaseDate() {
        return releaseDate;}

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;}

    public String getArtist() {
        return artist;}

    public void setArtist(String artist) {
        this.artist = artist;}
}
