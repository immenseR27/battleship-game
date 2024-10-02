package com.immenser.model;

public class Ship {
    private final int height;
    private final int width;

    public Ship(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int min = Math.min(height, width);
        int max = Math.max(height, width);
        for (int i = 0; i < min; i++) {
            sb.append("☐".repeat(max));
            sb.append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
