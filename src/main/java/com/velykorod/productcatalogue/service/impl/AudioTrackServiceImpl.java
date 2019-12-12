package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import com.velykorod.productcatalogue.persistance.repository.AudioTrackRepository;
import com.velykorod.productcatalogue.service.AudioTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioTrackServiceImpl implements AudioTrackService {
    @Autowired
    AudioTrackRepository audioTrackRepository;
    @Override
    public void addTrack(AudioTrack audioTrack) {
        audioTrackRepository.save(audioTrack);
    }

    @Override
    public void addTrackList(List<AudioTrack> tracks) {
        audioTrackRepository.saveAll(tracks);
    }
}

