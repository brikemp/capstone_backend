package com.capstone.project.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

public class SpotifyGetData {

    private static final String clientId = "fc90c0592e50485788abaacaef4bb4ed";
    private static final String clientSecret = "2593c0575d984a218ea2cb3ce8b1c91f";
    private static final String refreshToken = "AQAUlNuaCISjUuA6hL3yCzYcafdVb2KwduabXxKL7NZqaSt-O7qbp9KJOzN4g03anDSys60KpTws0ppy3aIyjBtXEt5E2TKQC30fiP_754Ui7J0Qk1h7Y_6mQZZw2i17kA8";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken)
            .build();
    private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
            .build();

    public static void authorizationCodeRefresh_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

//            System.out.println("Token: " + authorizationCodeCredentials.getAccessToken());
//            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String userId = "default"; //rb8zegy2gc68jbtgmjtxfcd76

    public static ArrayList<ArrayList<ArrayList<String>>> getListOfUsersPlaylists_Sync() {
        ArrayList<ArrayList<ArrayList<String>>> playlistArray = new ArrayList<ArrayList<ArrayList<String>>>(20);

        try {
//            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = spotifyApi.getListOfUsersPlaylists(userId).build().execute();
            PlaylistSimplified[] playlists;

//            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
//            System.out.println(playlistSimplifiedPaging.getItems().getClass());
            playlists = playlistSimplifiedPaging.getItems();

            for (PlaylistSimplified playlist : playlists) {
                ArrayList<ArrayList<String>> playlistInfo = new ArrayList<ArrayList<String>>(20);
                ArrayList<String> playlistName = new ArrayList<String>(1);
                playlistName.add(playlist.getName());
                System.out.println(playlist.getOwner().getDisplayName());
                playlistInfo.add(playlistName);

                final Paging<PlaylistTrack> playlistTracks = spotifyApi.getPlaylistsTracks(playlist.getId()).build().execute();

                PlaylistTrack[] tracks;
                tracks = playlistTracks.getItems();

                for (PlaylistTrack track : tracks) {
                    ArrayList<String> trackInfo = new ArrayList<String>(20);
                    trackInfo.add(track.getTrack().getName());
                    trackInfo.add(track.getTrack().getPreviewUrl());
                    trackInfo.add(track.getTrack().getArtists()[0].getName());
                    trackInfo.add(track.getTrack().getUri());
                    try {
                        trackInfo.add(track.getTrack().getAlbum().getImages()[0].getUrl());
                    } catch (Exception e) {
                        trackInfo.add("https://developer.spotify.com/assets/branding-guidelines/icon3@2x.png");
                    }

                    playlistInfo.add(trackInfo);
                }
                playlistArray.add(playlistInfo);
            }
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println(spotifyApi.getAccessToken());
            ArrayList<ArrayList<String>> tempArray = new ArrayList<ArrayList<String>>(1);
            ArrayList<String> tempArrayTwo = new ArrayList<String>(1);
            tempArrayTwo.add("User not Found");
            tempArray.add(tempArrayTwo);
            playlistArray.add(tempArray);
        }
        return playlistArray;
    }

    public static ArrayList<ArrayList<ArrayList<String>>> refreshAndPlaylists(String account) {
        System.out.println("account");
        System.out.println(account);
        userId = account;
        authorizationCodeRefresh_Sync();
        return getListOfUsersPlaylists_Sync();
    }
}