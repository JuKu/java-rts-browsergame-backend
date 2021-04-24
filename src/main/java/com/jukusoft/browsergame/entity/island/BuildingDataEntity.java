package com.jukusoft.browsergame.entity.island;

import com.jukusoft.browsergame.entity.user.RolePermissionID;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "buildings", indexes = {
        @Index(columnList = "island_id", name = "island_idx"),
}, uniqueConstraints = {
        //@UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@IdClass(BuildingDataID.class)
public class BuildingDataEntity {

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "island_id", nullable = false, updatable = false)
    private IslandEntity island;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_type_id", nullable = false, updatable = false)
    private BuildingTypeEntity type;

    @Column(name = "current_level", unique = false, nullable = false, updatable = true)
    private int level;

}
