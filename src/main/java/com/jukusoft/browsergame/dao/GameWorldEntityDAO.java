package com.jukusoft.browsergame.dao;

import com.jukusoft.browsergame.entity.gameworld.GameWorldEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameWorldEntityDAO extends PagingAndSortingRepository<GameWorldEntity, Long> {

    public Optional<GameWorldEntity> findOneByKey(String key);

    List<GameWorldEntity> findAll();

}
