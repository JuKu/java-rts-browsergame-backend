package com.jukusoft.browsergame.dao;

import com.jukusoft.browsergame.entity.island.ResourceTypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceTypeDAO extends PagingAndSortingRepository<ResourceTypeEntity, Long> {

    public boolean existsByKey(String key);

    public Optional<ResourceTypeEntity> findByKey(String key);

}
