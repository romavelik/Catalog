package com.velykorod.productcatalogue.service.impl;


import com.velykorod.productcatalogue.persistance.domain.impl.AudioTrack;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.persistance.repository.AudioTrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class AudioTrackServiceImplTest {
    @InjectMocks
    AudioTrackServiceImpl audioTrackService;

    @Mock
    AudioTrackRepository audioTrackRepository;

    private AudioTrack audioTrack = new AudioTrack("test", new Product());
    private AudioTrack otherAudiotrack = new AudioTrack("other", new Product());
    private List <AudioTrack> audioTracks = Arrays.asList(audioTrack, otherAudiotrack);

    @Test
    public void addTrack() {
        audioTrackService.addTrack(audioTrack);
        Mockito.verify(audioTrackRepository, Mockito.times(1)).save(audioTrack);
    }

    @Test
    public void addTrackList() {
        audioTrackService.addTrackList(audioTracks);
        Mockito.verify(audioTrackRepository, Mockito.times(1)).saveAll(audioTracks);
    }
}
