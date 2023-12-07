package com.gustafn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Day 5 solution. Reading file: %s %n", args[0]);
        List<String> data = readFile(args[0]);

        Day5 Day5 = new Day5(data);
        long part1 = Day5.part1();
        System.out.printf("Location part1: %d%n", part1);
        long part2 = Day5.part2();
        System.out.printf("Location part2: %d%n", part2);
    }

    private static List<String> readFile(String file) {
        BufferedReader reader;
        List<String> lines = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.printf("Read %d lines.%n", lines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}