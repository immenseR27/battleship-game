package com.immenser.service;

import com.immenser.exception.UserInputException;
import com.immenser.model.GameField;
import com.immenser.model.Player;
import com.immenser.model.Position;
import com.immenser.model.Ship;
import com.immenser.util.StringToPositionParser;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManualShipPlacementer implements ShipPlacementer {

    private final int[] shifts = {-1, 0, 1};

    @Override
    public void placeShips(Player player, List<Ship> ships) {
        showBeforeShipPlacement(player);
        for (Ship ship : ships) {
            placeShip(player.getOwnGameField(), ship);
        }
        showAfterShipPlacement();
    }

    private void showBeforeShipPlacement(Player player) {
        System.out.printf("""
                Приветствую тебя в игре "Морской бой", %s!
                Для начала давай расставим корабли.
                \s
                         Это твое игровое поле%n""", player.getName());
        System.out.println(player.getOwnGameField());
    }

    private void showAfterShipPlacement() {
        System.out.print("""
                \s
                Отлично, ты разместил все корабли!
                Нажми enter, чтобы продолжить.""");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private void placeShip(GameField gameField, Ship ship) {
        showBeforeOrientationRequest(ship);
        int width = ship.getWidth();
        int height = ship.getHeight();
        int orientation = requestOrientationFromPlayer();
        if (orientation == 2) {
            int temp = width;
            width = height;
            height = temp;
        }
        Position position = requestPositionFromPlayer();
        try {
            checkPlaceSuitability(gameField, ship, position);
            for (int i = position.getRow(); i < position.getRow() + height; i++) {
                for (int j = position.getColumn(); j < position.getColumn() + width; j++) {
                    gameField.getBody()[i][j] = GameField.SHIP;
                }
            }
            System.out.println(gameField);
        } catch (UserInputException e) {
            System.out.println(e.getMessage());
            placeShip(gameField, ship);
        }
    }

    private void showBeforeOrientationRequest(Ship ship) {
        System.out.printf("\nКорабль %s. ", ship);
        System.out.println("""
                Как будем размещать?
                1. Как есть
                2. Поверни на 90°""");
    }

    private int requestOrientationFromPlayer() {
        int orientation;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                orientation = scanner.nextInt();
                if (orientation == 1 || orientation == 2) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение, попробуй еще раз.");
            }
            scanner.nextLine();
        }
        return orientation;
    }

    private Position requestPositionFromPlayer() {
        System.out.println("Где расположим? (Пример: А7).");
        Scanner scanner = new Scanner(System.in);
        String playerInputPosition = scanner.next();
         try {
             return StringToPositionParser.getPosition(playerInputPosition);
         } catch (UserInputException e) {
             System.out.println(e.getMessage());
             return requestPositionFromPlayer();
         }
    }

    private void checkPlaceSuitability(GameField gameField, Ship ship, Position position) throws UserInputException {
        for (int i = position.getRow(); i < position.getRow() + ship.getHeight(); i++) {
            for (int j = position.getColumn(); j < position.getColumn() + ship.getWidth(); j++) {
                checkCellSuitability(gameField, new Position(i, j));
            }
        }
    }

    private void checkCellSuitability(GameField gameField, Position position) throws UserInputException {
        if (position.getRow() < 1 || position.getRow() > gameField.getHeight()
                || position.getColumn() < 1 || position.getColumn() > gameField.getWidth()) {
            throw new UserInputException("Не удалось разместить корабль: выход за пределы игрового поля. Попробуй еще раз.");
        }
        for (int i : shifts) {
            for (int j : shifts) {
                if (gameField.getBody()[position.getRow() + i][position.getColumn() + j] == GameField.SHIP) {
                    throw new UserInputException("Не удалось разместить корабль: пересечение с другим кораблем. Попробуй еще раз.");
                }
            }
        }
    }
}
