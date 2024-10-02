package com.immenser.model;

import java.util.List;

public class GameShipSet {

    private final List<Ship> ships;
    private final int liveCellCounter;

    public GameShipSet(List<Ship> ships, int liveCellCounter) {
        this.ships = ships;
        this.liveCellCounter = liveCellCounter;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getLiveCellCounter() {
        return liveCellCounter;
    }
}
