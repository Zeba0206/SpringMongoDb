package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track,Integer> {

   /* @Query(value = "select * from track where track_name = ?1", nativeQuery = true)
    public List<Track> getTrackByName(String trackName);*/

}
