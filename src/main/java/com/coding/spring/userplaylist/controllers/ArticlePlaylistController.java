package com.coding.spring.userplaylist.controllers;

import com.coding.spring.userplaylist.entities.ArticlePlaylistEntity;
import com.coding.spring.userplaylist.entities.PlaylistEntity;
import com.coding.spring.userplaylist.pojos.ArticlePojo;
import com.coding.spring.userplaylist.services.ArticlePlaylistService;
import com.coding.spring.userplaylist.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/playlist/article")
public class ArticlePlaylistController {

    @Autowired
    private ArticlePlaylistService articlePlaylistService;

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Boolean> addArticleToPlaylist(@RequestBody ArticlePlaylistEntity articlePlaylistEntity){
        articlePlaylistService.addArticleToPlayList(articlePlaylistEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Boolean> removeArticleFromPlaylist(@PathVariable String articleId,
                                                             @RequestParam String userId,
                                                             @RequestParam String playlistName){

        Optional<PlaylistEntity> result = playlistService.getPlayList(playlistName,userId);
        if(!result.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Boolean deleted = articlePlaylistService.removeArticleFromPlayList(articleId,result.get().getId());
        if (!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ArticlePojo>> getArticlesInPlaylist(@RequestParam String userId,
                                                                   @RequestParam String playlistName){
        Optional<PlaylistEntity> result = playlistService.getPlayList(playlistName,userId);
        if(!result.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articlePlaylistService.
                                    getArticlesInUserPlaylist(result.get().getId()));
    }

}
