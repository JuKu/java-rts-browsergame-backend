package com.jukusoft.browsergame.entity.gameworld;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coord {

    @Column(name = "x_pos", unique = false, nullable = false, updatable = true)
    private long xPos;

    @Column(name = "y_pos", unique = false, nullable = false, updatable = true)
    private long yPos;

    public Coord(long xPos, long yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public CompassDirection getCompassDirection() {
        if (xPos <= 0) {
            if (yPos <= 0) {
                return CompassDirection.SOUTH_WEST;
            } else {
                return CompassDirection.NORTH_WEST;
            }
        } else {
            if (yPos <= 0) {
                return CompassDirection.SOUTH_EAST;
            } else {
                return CompassDirection.NORTH_EAST;
            }
        }
    }

}
