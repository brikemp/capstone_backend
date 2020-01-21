package com.capstone.project.api;

import com.capstone.project.model.SpotifyGetData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/spotify")
@RestController
public class SpotifyController {

    @GetMapping
    public ArrayList<ArrayList<String>> getSpotify() {
        ArrayList<ArrayList<String>> playlists = null;
        try {
//            playlists = SpotifyGetData.refreshAndPlaylists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }
}
