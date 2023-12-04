package com.gustafn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 {

    private List<Draw> rulesPart1 = List.of(new Draw(12, "red"), new Draw(13, "green"), new Draw(14, "blue"));

    private static final String GAME = "Game ";

    public Day2() {
    }

    public long part1(List<String> data) {
        List<Integer> gamesAdded = new LinkedList<>();
        for (String gameString : data) {
            int gameNumber = getGame(gameString);
            List<List<Draw>> gameSetsDraws = Draw.fromGame(gameString.substring(GAME.length() + (int)Math.log10(gameNumber)+2));
            if (!exceedsRules(gameSetsDraws)) {
                gamesAdded.add(gameNumber);
            }
        }
        return sum(gamesAdded);
    }
    public long part2(List<String> data) {
        List<Integer> gamesAdded = new LinkedList<>();
        for (String gameString : data) {
            int gameNumber = getGame(gameString);
            List<List<Draw>> gameSetsDraws = Draw.fromGame(gameString.substring(GAME.length() + (int)Math.log10(gameNumber)+2));
            List<Draw> minimumNeeded = getMaxOfEachColour(gameSetsDraws);
            int power = 1;
            for (Draw draw : minimumNeeded) {
                power = power * draw.getPoints();
            }
            gamesAdded.add(power);
        }
        return sum(gamesAdded);
    }

    private List<Draw> getMaxOfEachColour(List<List<Draw>> gameSetsDraws) {
        Map<String, Draw> max = new HashMap<>();
        for (List<Draw> setDraws : gameSetsDraws) {
            for (Draw draw : setDraws) {
                if (max.containsKey(draw.getColour())) {
                    if (max.get(draw.getColour()).compareTo(draw) < 0) {
                        max.put(draw.getColour(), draw);
                    }
                } else {
                    max.put(draw.getColour(), draw);
                }
            }
        }

        return max.keySet().stream().map(max::get).collect(Collectors.toList());
    }

    private boolean exceedsRules (List<List<Draw>> gameSetsDraws) {
        for (List<Draw> setDraws : gameSetsDraws) {
            for (Draw draw : setDraws) {
                for (Draw rule : rulesPart1) {
                    if (draw.equals(rule) && draw.compareTo(rule) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getGame(String s) {
        String gameNumber = s.substring(GAME.length());
        return Integer.parseInt(gameNumber.substring(0, gameNumber.indexOf(":")));
    }

    public long sum(List<Integer> data) {
        long sum = 0;
        for (Integer integer : data) {
            sum += integer;
        }
        return sum;
    }
}
