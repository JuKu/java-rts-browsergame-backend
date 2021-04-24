package com.jukusoft.browsergame.entity.island;

import java.io.Serializable;

public class BuildingDataID implements Serializable {

    private long island;
    private long type;

    public BuildingDataID(long island, long type) {
        this.island = island;
        this.type = type;
    }

    public BuildingDataID() {
        //
    }

    public long getIsland() {
        return island;
    }

    public void setIsland(long island) {
        this.island = island;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

}
