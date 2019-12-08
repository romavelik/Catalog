package com.velykorod.productcatalogue.persistance.repository;

import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioTrackRepository extends JpaRepository<AudioTrack, Long> {

}
