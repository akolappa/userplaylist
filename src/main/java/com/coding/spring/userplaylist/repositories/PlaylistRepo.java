package com.coding.spring.userplaylist.repositories;

import com.coding.spring.userplaylist.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepo extends JpaRepository<PlaylistEntity, Long> {

    Optional<PlaylistEntity> findByName(String name);

    Optional<PlaylistEntity> findByNameAndUserId(String name , String userId);

}
