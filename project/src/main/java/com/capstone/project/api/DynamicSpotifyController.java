package com.capstone.project.api;

import com.capstone.project.model.SpotifyGetData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/dynamicspotify/{account}")
@RestController
public class DynamicSpotifyController {

    @GetMapping
    public ArrayList<ArrayList<ArrayList<String>>> getSpotify(@PathVariable(value = "account") String account) {
        ArrayList<ArrayList<ArrayList<String>>> playlists = null;
        try {
            playlists = SpotifyGetData.refreshAndPlaylists(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }
}
