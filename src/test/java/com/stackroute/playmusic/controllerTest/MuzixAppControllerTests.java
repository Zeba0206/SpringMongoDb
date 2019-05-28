package com.stackroute.playmusic.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.TrackControl;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class MuzixAppControllerTests {

        @Autowired
        private MockMvc mockMvc;
        private Track track;
        @MockBean
        private TrackService trackService;
        @InjectMocks
        private TrackControl trackControl;

        private List<Track> list =null;

        @Before
        public void setUp(){

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(trackControl).build();
            track = new Track();
            track.setTrackID(26);
            track.setTrackName("Zeba");
            track.setComments("hjfuregfiuehofhjewld");
            list = new ArrayList();
            list.add(track);
        }

        @Test
        public void saveUser() throws Exception {
            when(trackService.saveTrack(any())).thenReturn(track);// we r instructing mock if trackservice has called, just return the track object
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void saveTrackFailure() throws Exception {
            when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON) //creating a json object of setup()
                    .content(asJsonString(track))) //it will send the json object
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void getAllTracks() throws Exception {
            when(trackService.showAllTracks()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }


        private static String asJsonString(final Object obj)
        {
            try{
                return new ObjectMapper().writeValueAsString(obj);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }

    }
