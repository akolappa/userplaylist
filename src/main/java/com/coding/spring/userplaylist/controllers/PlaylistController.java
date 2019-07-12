package com.coding.spring.userplaylist.controllers;

import com.coding.spring.userplaylist.entities.PlaylistEntity;
import com.coding.spring.userplaylist.pojos.PlaylistPojo;
import com.coding.spring.userplaylist.services.PlaylistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<List<PlaylistPojo>> getAllPlaylist(){
        return ResponseEntity.ok(playlistService.getAllPlaylist()
                .stream()
                .map(this::convertToPojo)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistPojo> getPlayList(@PathVariable Long playlistId){

        Optional<PlaylistEntity> response = playlistService.getPlayList(playlistId);
        if (!response.isPresent()){
           return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok(convertToPojo(response.get()));
    }

    @GetMapping
    public ResponseEntity<PlaylistPojo> getPlayList(@RequestParam String playlistName, @RequestParam String userId){

        Optional<PlaylistEntity> response = playlistService.getPlayList(playlistName, userId);
        if (!response.isPresent()){
           return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok(convertToPojo(response.get()));
    }

    @PostMapping
    public ResponseEntity<Boolean> createPlaylist(@RequestBody PlaylistPojo playlistPojo){
        if(!playlistService.createPlaylist(convertToEntity(playlistPojo))){
            return ResponseEntity.status(HttpStatus.FOUND).body(Boolean.FALSE);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @PutMapping
    public ResponseEntity<PlaylistPojo> updatePlaylist(@RequestBody PlaylistPojo playlistPojo){
        if(!playlistService.getPlayList(playlistPojo.getId()).isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToPojo(
                    playlistService.updatePlaylist(
                        convertToEntity(playlistPojo)
                    )));
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Boolean> deletePlaylist(@PathVariable Long playlistId){
        if(!playlistService.getPlayList(playlistId).isPresent()){
            return ResponseEntity.notFound().build();
        }
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }

    private PlaylistPojo convertToPojo (PlaylistEntity playlistEntity){
        return modelMapper.map(playlistEntity,PlaylistPojo.class);
    }

    private PlaylistEntity convertToEntity (PlaylistPojo playlistPojo){
        return modelMapper.map(playlistPojo, PlaylistEntity.class);
    }
}
