/*
package com.stackroute.SeedData;


import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropertyConfigSeedData implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logs=Logger.getLogger(PropertyConfigSeedData.class);
    private TrackRepository trackRepository;

    @Autowired
    public PropertyConfigSeedData(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    @Value("${trackID}")
    private int trackID;

    @Value("${trackName}")
    private String trackName;

    @Value("${trackComments}")
    private String trackComments;

    @Autowired
    Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        logs.info("Inserting data on start");

        Track track1 = new Track(trackID,trackName,trackComments);
        trackRepository.save(track1);

        Track track2 = new Track(Integer.parseInt(env.getProperty("trackID")),env.getProperty("trackName"),env.getProperty("trackComments"));
        trackRepository.save(track2);

        logs.info("data successfully inserted");
    }
}


*/
