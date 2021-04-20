package com.jukusoft.browsergame.entity;

import com.jukusoft.browsergame.entity.general.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "game_worlds", indexes = {
        //@Index(columnList = "email", name = "email_idx"),
}, uniqueConstraints = {
        //@UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GameWorldEntity extends AbstractEntity {

    @Size(min = 2, max = 900)
    @Column(name = "key", unique = true, nullable = false, updatable = true)
    @NotEmpty(message = "key is required")
    private String key;

    @Column(name = "max_players", unique = false, nullable = false, updatable = true)
    private int maxPlayers = 64000;

    /**
     * width in cells
     */
    @Column(name = "width", unique = false, nullable = false, updatable = true)
    private int width;

    /**
     * height in cells
     */
    @Column(name = "height", unique = false, nullable = false, updatable = true)
    private int height;

    public String getKey() {
        return key;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
