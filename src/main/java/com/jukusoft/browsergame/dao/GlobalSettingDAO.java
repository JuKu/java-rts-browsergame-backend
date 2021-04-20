package com.jukusoft.browsergame.dao;

import com.jukusoft.browsergame.entity.general.GlobalSettingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalSettingDAO extends PagingAndSortingRepository<GlobalSettingEntity, String> {

    //

}
