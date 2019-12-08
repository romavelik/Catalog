package com.velykorod.productcatalogue.service;

import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;

import java.util.List;

public interface AudioTrackService {
    void addTrack(AudioTrack audioTrack);
    void addTrackList(List<AudioTrack> tracks);
}
