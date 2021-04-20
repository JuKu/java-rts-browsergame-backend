package com.jukusoft.browsergame.dao;

import com.jukusoft.browsergame.entity.user.PermissionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDAO extends PagingAndSortingRepository<PermissionEntity, String> {
}
