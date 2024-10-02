package com.immenser.service;

import com.immenser.model.GameField;
import com.immenser.model.Player;
import com.immenser.model.Ship;

import java.util.List;

public interface ShipPlacementer {
    void placeShips(Player player, List<Ship> ships);
}
