package com.immenser;

import com.immenser.model.*;
import com.immenser.service.ManualMoveMaker;
import com.immenser.service.ManualShipPlacementer;
import com.immenser.service.MoveMaker;
import com.immenser.util.GameShipSetGenerator;
import com.immenser.service.ShipPlacementer;

import java.util.List;

public class Game
{
    public static void main( String[] args ) {

        List<String> typesOfShipsAndTheirNumber = List.of("1:4×1", "2:3×1", "3:2×1", "4:1×1");
//        List<String> typesOfShipsAndTheirNumber = List.of("1:4×1");
        GameShipSet gameShipSet = GameShipSetGenerator.generateShips(typesOfShipsAndTheirNumber);

        Player player1 = new Player("Игрок 1", gameShipSet.getLiveCellCounter());
        Player player2 = new Player("Игрок 2", gameShipSet.getLiveCellCounter());

        ShipPlacementer shipPlacementer = new ManualShipPlacementer();

        shipPlacementer.placeShips(player1, gameShipSet.getShips());
        shipPlacementer.placeShips(player2, gameShipSet.getShips());

        MoveMaker moveMaker = new ManualMoveMaker();
        while (player1.getLiveCellCounter() > 0 && player2.getLiveCellCounter() > 0) {
            moveMaker.showBeforeMoveMaking(player1);
            moveMaker.makeMove(player1, player2);
            moveMaker.showAfterMoveMaking(player1);
            if (player2.getLiveCellCounter() > 0) {
                moveMaker.showBeforeMoveMaking(player2);
                moveMaker.makeMove(player2, player1);
                moveMaker.showAfterMoveMaking(player2);
            }
        }
        System.out.println("Конец игры. Поздравляю с победой!");
    }
}
