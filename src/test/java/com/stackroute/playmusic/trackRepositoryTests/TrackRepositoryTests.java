package com.stackroute.playmusic.trackRepositoryTests;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTests {

        @Autowired
        private TrackRepository trackRepository;
        private Track track;

        @Before
        public void setUp()
        {
            track = new Track();
            track.setTrackID(4);
            track.setTrackName("Zeba");
            track.setComments("hjfuregfiuehofhjewld");
        }

        @After
        public void tearDown(){

            trackRepository.deleteAll();
        }


        @Test
        public void testSaveTrack(){
            trackRepository.save(track);
            Track fetchUser = trackRepository.findById(track.getTrackID()).get();
            Assert.assertEquals(4,fetchUser.getTrackID());

        }

        @Test
        public void testSaveTrackFailure(){
            Track testTrack = new Track(5,"My Favourite","Good");
            trackRepository.save(track);
            Track fetchUser = trackRepository.findById(track.getTrackID()).get();
            Assert.assertNotSame(testTrack,track);
        }

        @Test
        public void testShowAllTracks  (){
            Track u = new Track(6,"love me like you do","It's Good");
            Track u1 = new Track(7,"Don't give up","It's Awesome");
            trackRepository.save(u);
            trackRepository.save(u1);

            List<Track> list = (List<Track>) trackRepository.findAll();
            Assert.assertEquals("love me like you do",list.get(0).getTrackName());




        }


    }

