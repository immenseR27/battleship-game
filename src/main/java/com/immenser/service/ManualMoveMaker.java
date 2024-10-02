package com.immenser.service;

import com.immenser.exception.UserInputException;
import com.immenser.model.GameField;
import com.immenser.model.Player;
import com.immenser.model.Position;
import com.immenser.util.StringToPositionParser;

import java.util.Scanner;

public class ManualMoveMaker implements MoveMaker {

    @Override
    public void showBeforeMoveMaking(Player activePlayer) {
        System.out.printf("""
                Ходит %s.
                \s
                         Это поле противника%n""", activePlayer.getName());
        System.out.println(activePlayer.getEnemyGameField());
    }

    @Override
    public void makeMove(Player activePlayer, Player opponentPlayer) {
        System.out.println("\nВыбери цель (Пример: А7).");
        Scanner scanner = new Scanner(System.in);
        String playerPositionInput = scanner.nextLine();
        try {
            Position position = StringToPositionParser.getPosition(playerPositionInput);
            checkCellNotYetCalled(activePlayer, position);
            attackEnemyField(activePlayer, opponentPlayer, position);
        } catch (UserInputException e) {
            System.out.println(e.getMessage());
            makeMove(activePlayer, opponentPlayer);
        }
    }

    private void checkCellNotYetCalled(Player activePlayer, Position position) throws UserInputException {
        if (activePlayer.getEnemyGameField().getBody()[position.getRow()][position.getColumn()] == GameField.HIT) {
            throw new UserInputException("Эта цель уже поражена. Попробуй еще раз.");
        }
        if (activePlayer.getEnemyGameField().getBody()[position.getRow()][position.getColumn()] == GameField.MISS) {
            throw new UserInputException("Ты уже проверял эту клетку, там ничего нет. Попробуй еще раз.");
        }
    }

    private void attackEnemyField(Player activePlayer, Player opponentPlayer, Position position) throws UserInputException {
        if (opponentPlayer.getOwnGameField().getBody()[position.getRow()][position.getColumn()] == GameField.SHIP) {
            System.out.println("Успех!");
            opponentPlayer.decreaseLiveCellCounter();
            opponentPlayer.getOwnGameField().getBody()[position.getRow()][position.getColumn()] = GameField.HIT;
            activePlayer.getEnemyGameField().getBody()[position.getRow()][position.getColumn()] = GameField.HIT;
        } else {
            System.out.println("Возможно, цель где-то рядом...");
            opponentPlayer.getOwnGameField().getBody()[position.getRow()][position.getColumn()] = GameField.MISS;
            activePlayer.getEnemyGameField().getBody()[position.getRow()][position.getColumn()] = GameField.MISS;
        }
    }

    @Override
    public void showAfterMoveMaking(Player activePlayer) {
        System.out.println(activePlayer.getEnemyGameField());
        System.out.print("Нажми enter, чтобы продолжить.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("\n\n\n");
    }
}
