package com.jukusoft.browsergame.entity.island;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "resource_types", indexes = {
        //@Index(columnList = "gameworld_id", name = "gameworld_idx"),
}, uniqueConstraints = {
        //@UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResourceTypeEntity {

    @Id
    @Size(max = 50)
    @Column(name = "key", nullable = false, updatable = false)
    private String key;

    @Size(min = 2, max = 90)
    @Column(name = "title", nullable = false, updatable = true, unique = false)
    private String title;

    @Size(min = 2, max = 255)
    @Column(name = "alt", nullable = false, updatable = true, unique = false)
    private String alt;

    @Size(min = 2, max = 255)
    @Column(name = "icon", nullable = false, updatable = true, unique = false)
    private String icon;

    public ResourceTypeEntity(String key, String title, String alt, String icon) {
        this.key = key;
        this.title = title;
        this.alt = alt;
        this.icon = icon;
    }

    protected ResourceTypeEntity() {
        //
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
