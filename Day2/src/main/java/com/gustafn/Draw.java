package com.gustafn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Draw implements Comparable<Draw> {
    private int points;
    private String colour;

    public Draw(int points, String colour) {
        this.points = points;
        this.colour = colour;
    }


    public static List<List<Draw>> fromGame(String s) {
        List<List<Draw>> gameSets = new LinkedList<>();
        String[] sets = s.split(";");
        for (int i = 0; i < sets.length; i++) {
            gameSets.add(fromSet(sets[i]));
        }
        return gameSets;
    }

    public static List<Draw> fromSet(String s) {
        String[] draws = s.split(",");
        List<Draw> drawList = new ArrayList<>(draws.length);
        for (int i = 0; i < draws.length; i++) {
            drawList.add(fromString(draws[i].trim()));
        }
        return drawList;
    }

    public static Draw fromString(String s) {
        int i = 0;
        while (Character.isDigit(s.charAt(i))) {
            i++;
        }
        int points = Integer.valueOf(s.substring(0, i));
        String color = s.substring(i + 1).trim();
        return new Draw(points, color);
    }

    public int getPoints() {
        return points;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Draw draw = (Draw) o;
        return getColour().equals(draw.getColour());
    }

    @Override
    public int hashCode() {
        return getColour().hashCode();
    }

    @Override
    public int compareTo(Draw draw) {
        return points - draw.getPoints();
    }
}