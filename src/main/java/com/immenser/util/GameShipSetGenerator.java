package com.immenser.util;

import com.immenser.model.GameShipSet;
import com.immenser.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class GameShipSetGenerator {
    public static GameShipSet generateShips(List<String> typesOfShipsAndTheirNumber) {
        int liveCellCounter = 0;
        List<Ship> ships = new ArrayList<>();
        for (String type : typesOfShipsAndTheirNumber) {
            String[] numberAndType = type.split(":");
            int num = Integer.parseInt(numberAndType[0]);
            String[] widthAndHeight = numberAndType[1].split("×");
            int width = Integer.parseInt(widthAndHeight[0]);
            int height = Integer.parseInt(widthAndHeight[1]);
            for (int i = 0; i < num; i++) {
                ships.add(new Ship(width, height));
                liveCellCounter += width * height;
            }
        }
        return new GameShipSet(ships, liveCellCounter);
    }
}
