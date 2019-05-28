package com.stackroute.playmusic.trackService;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTests {
//pure junit tests

        private Track track;

        //Create a mock for UserRepository
        @Mock
        private TrackRepository trackRepository;

        //Inject the mocks as dependencies into UserServiceImpl
        @InjectMocks
        private TrackServiceImpl trackService;
        List<Track> list= null;


        @Before
        public void setUp(){
            //Initialising the mock object
            MockitoAnnotations.initMocks(this);
            track = new Track();
            track.setTrackID(26);
            track.setTrackName("Zeba");
            track.setComments("hjfuregfiuehofhjewld");
            list = new ArrayList<>();
            list.add(track);


        }

        @Test
        public void saveUserTestSuccess() throws TrackAlreadyExistsException {

            when(trackRepository.save((Track) any())).thenReturn(track);
            Track savedTrack = trackService.saveTrack(track);
            Assert.assertEquals(track,savedTrack);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }

        @Test(expected = TrackAlreadyExistsException.class)
        public void saveUserTestFailure() throws TrackAlreadyExistsException {
            when(trackRepository.save((Track)any())).thenReturn(null);
            Track savedTrack = trackService.saveTrack(track);
            System.out.println("savedTrack" + savedTrack);
            Assert.assertEquals(track,savedTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


        }

        @Test
        public void showAllTracks(){

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.showAllTracks();
            Assert.assertEquals(list,userlist);
        }





    }

