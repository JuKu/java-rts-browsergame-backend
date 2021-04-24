package com.jukusoft.browsergame.entity.island;

import com.jukusoft.browsergame.entity.gameworld.Coord;
import com.jukusoft.browsergame.entity.gameworld.GameWorldEntity;
import com.jukusoft.browsergame.entity.general.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "islands", indexes = {
        @Index(columnList = "gameworld_id", name = "gameworld_idx"),
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gameworld_id", "x_pos", "y_pos"}, name = "coord_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IslandEntity extends AbstractEntity {

    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "gameworld_id", nullable = false, updatable = false)//don't use an extra table, use join column instead
    @NotEmpty(message = "gameworld cannot be null")
    private GameWorldEntity gameworld;

    @Size(min = 2, max = 90)
    @Column(name = "name", nullable = false, updatable = true, unique = true)
    private String islandName;

    @Embedded
    private Coord pos;

}
