package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("api/v1/")
@Configuration
@PropertySource("classpath:application.properties")
public class TrackControl {
    
    
    private TrackService trackService;
    
    @Autowired
    public TrackControl(TrackService trackService) {
        this.trackService = trackService;
    }

   public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @ApiOperation(value = "Save track")
    @PostMapping(value = "track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track trackOne = trackService.saveTrack(track);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex)
        {
            responseEntity = new ResponseEntity <String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;

    }


    @ApiOperation(value = "Get All Track")
    @GetMapping(value = "track")
    public ResponseEntity<List<Track>> showAllTracks() {
        List<Track > trackOne = trackService.showAllTracks();
        return new ResponseEntity<List<Track>>(trackOne, HttpStatus.OK);
    }

    
    @ApiOperation(value = "Update Track")
    @PutMapping(value = "track")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            Track trackOne = trackService.updateComment(track);
            return new ResponseEntity<Track>(trackOne, HttpStatus.OK);
        }catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity <String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @ApiOperation(value = "Delete Track")
    @DeleteMapping(value = "track")
    public ResponseEntity<String > deleteTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            boolean answer = trackService.deleteTrack(track);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        }catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity <String>("Something went ",HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;

    }
}
