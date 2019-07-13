package com.coding.spring.userplaylist.services.impl;

import com.coding.spring.userplaylist.entities.PlaylistEntity;
import com.coding.spring.userplaylist.repositories.PlaylistRepo;
import com.coding.spring.userplaylist.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    PlaylistRepo playlistRepo;

    @Override
    public Boolean createPlaylist(PlaylistEntity playlistEntity) {
        if(playlistRepo.findByNameAndUserId(playlistEntity.getName(),playlistEntity.getUserId()).isPresent()){
            return false;
        }
        playlistRepo.save(playlistEntity);
        return true;
    }

    @Override
    public List<PlaylistEntity> getAllPlaylist() {
        return playlistRepo.findAll();
    }

    @Override
    public Optional<PlaylistEntity> getPlayList(Long playlistId, String userId) {
        return playlistRepo.findByIdAndUserId(playlistId,userId);
    }

    @Override
    public Optional<PlaylistEntity> getPlayList(String name, String userId) {

        return playlistRepo.findByNameAndUserId(name,userId);
    }

    @Override
    public PlaylistEntity updatePlaylist(PlaylistEntity playlistEntity) {
        return playlistRepo.save(playlistEntity);
    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playlistRepo.deleteById(playlistId);
    }
}
