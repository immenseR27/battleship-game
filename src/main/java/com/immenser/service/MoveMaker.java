package com.immenser.service;

import com.immenser.model.Player;

public interface MoveMaker {
    void showBeforeMoveMaking(Player player);
    void makeMove(Player activePlayer, Player opponentPlayer);
    void showAfterMoveMaking(Player player);
}
