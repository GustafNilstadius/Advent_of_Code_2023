package com.gustafn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Day 3 solution. Reading file: %s %n", args[0]);
        List<String> data = readFile(args[0]);

        Day4 day = new Day4();
        long part1 = day.part1(data);
        System.out.printf("Sum part1: %d%n", part1);
        long part2 = day.part2(data);
        System.out.printf("Sum part2: %d%n", part2);
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