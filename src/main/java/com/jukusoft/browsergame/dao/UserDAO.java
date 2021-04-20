package com.jukusoft.browsergame.dao;

import com.jukusoft.browsergame.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Long> {

    public Optional<UserEntity> findOneByUsername(String username);
    List<UserEntity> findAll();

}
