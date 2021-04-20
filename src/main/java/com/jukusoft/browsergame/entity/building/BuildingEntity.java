package com.jukusoft.browsergame.entity.building;

import com.jukusoft.browsergame.entity.general.AbstractEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "buildings", indexes = {
        //@Index(columnList = "email", name = "email_idx"),
}, uniqueConstraints = {
        //@UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BuildingEntity extends AbstractEntity {

    @Size(min = 2, max = 900)
    @Column(name = "name", unique = true, nullable = false, updatable = true)
    @NotEmpty(message = "unique name is required")
    private String uniqueName;

    @Size(min = 2, max = 255)
    @Column(name = "title", unique = false, nullable = false, updatable = true)
    @NotEmpty(message = "title is required")
    private String title;

    @Column(name = "min_level", unique = false, nullable = false, updatable = true)
    private int minLevel;

    @Column(name = "max_level", unique = false, nullable = false, updatable = true)
    private int maxLevel;

}
