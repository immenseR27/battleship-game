package com.immenser.model;

public class Player {
    private final String name;
    private final GameField ownGameField;
    private final GameField enemyGameField;
    private int liveCellCounter;

    public Player(String name, int liveCellCounter) {
        this.name = name;
        this.liveCellCounter = liveCellCounter;
        ownGameField = new GameField();
        enemyGameField = new GameField();
    }

    public String getName() {
        return name;
    }

    public GameField getOwnGameField() {
        return ownGameField;
    }

    public GameField getEnemyGameField() {
        return enemyGameField;
    }

    public int getLiveCellCounter() {
        return liveCellCounter;
    }

    public void decreaseLiveCellCounter() {
        liveCellCounter--;
    }
}
