package com.immenser.model;

import java.util.StringJoiner;

public class GameField {
    public static final char EMPTY = '·';
    private static final char BORDER = '~';
    public static final char SHIP = '☐';
    public static final char HIT = '☒';
    public static final char MISS = '✕';

    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private final char[][] body;

    public GameField() {
        body = new char[HEIGHT + 2][WIDTH + 2];
        for (int i = 0; i < HEIGHT + 2; i++) {
            for (int j = 0; j < WIDTH + 2; j++) {
                if (i == 0 || i == HEIGHT + 1 || j == 0 || j == WIDTH + 1) {
                    body[i][j] = BORDER;
                } else {
                    body[i][j] = EMPTY;
                }
            }
        }
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public char[][] getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("    А  Б  В  Г  Д  Е  Ж  З  И  К");
        for (int i = 1; i < HEIGHT + 1; i++) {
            StringJoiner sj2 = new StringJoiner("  ");
            if (i < 10) {
                sj2.add(' ' + String.valueOf(i));
            } else {
                sj2.add(String.valueOf(i));
            }
            for (int j = 1; j < WIDTH + 1; j++) {
                sj2.add(String.valueOf(body[i][j]));
            }
            sj.add(sj2.toString());
        }
        return sj.toString();
    }
}