package com.coding.spring.userplaylist.services;

import com.coding.spring.userplaylist.entities.PlaylistEntity;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    Boolean createPlaylist(PlaylistEntity playlistEntity);
    List<PlaylistEntity> getAllPlaylist();
    Optional<PlaylistEntity> getPlayList(Long playlistId);
    Optional<PlaylistEntity> getPlayList(String name, String userId);
    PlaylistEntity updatePlaylist(PlaylistEntity playlistEntity);
    void deletePlaylist(Long playlistId);
}
