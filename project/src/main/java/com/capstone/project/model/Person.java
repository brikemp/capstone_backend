package com.capstone.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {

    private final UUID id;

    @NotBlank
    private final String name;

    private final String twitterName;

    private final String spotifyName;

    private final String pinterestName;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("twitter_name") String twitterName,
                  @JsonProperty("spotify_name") String spotifyName,
                  @JsonProperty("pinterest_name") String pinterestName) {
        this.id = id;
        this.name = name;
        this.twitterName = twitterName;
        this.spotifyName = spotifyName;
        this.pinterestName = pinterestName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public String getSpotifyName() {
        return spotifyName;
    }

    public String getPinterestName() {
        return pinterestName;
    }
}
