package com.jukusoft.browsergame.entity.gameworld;

import com.jukusoft.browsergame.entity.GameWorldEntity;
import com.jukusoft.browsergame.entity.general.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "islands", indexes = {
        @Index(columnList = "gameworld_id", name = "gameworld_idx"),
}, uniqueConstraints = {
        //@UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IslandEntity extends AbstractEntity {

    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "gameworld_id", nullable = false, updatable = false)//don't use an extra table, use join column instead
    @NotEmpty(message = "gameworld cannot be null")
    private GameWorldEntity gameworld;

}
