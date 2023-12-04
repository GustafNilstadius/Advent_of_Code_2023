package com.gustafn;

import java.util.LinkedList;
import java.util.List;

public class Day3 {
    public Day3() {
    }

    public long part1(List<String> input) {
        List<Integer> numbers = new LinkedList<>();
        for (int row = 0; row < input.size(); row++) {
            String rowString = input.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                boolean isSymbol = isSymbol(rowString.charAt(col));
                if (isSymbol) {
                    numbers.addAll(partNumber(input, 1, row, col));
                }
            }
        }
        return sum(numbers);
    }
    public long part2(List<String> input) {
        List<Integer> numbers = new LinkedList<>();
        for (int row = 0; row < input.size(); row++) {
            String rowString = input.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                boolean isSymbol = isSymbol(rowString.charAt(col));
                if (isSymbol && rowString.charAt(col) == '*') {
                    List<Integer> gearRatios = partNumber(input, 1, row, col);
                    if (gearRatios.size() == 2) {
                        numbers.add(gearRatios.get(0) * gearRatios.get(1));
                    }
                }
            }
        }
        return sum(numbers);
    }

    private List<Integer> partNumber(List<String> data, int lookAround, int rowStart, int colStart) {
        List<Integer> partNumbers = new LinkedList<>();
        int top, bottom, left, right;
        top = (rowStart - lookAround);
        bottom = (rowStart + lookAround);
        left = (colStart - lookAround);
        right = (colStart + lookAround);
        for (int row = top; row <= bottom; row++) {
            if (row < 0 || row > data.size() - 1) {
                continue;
            }
            String line = data.get(row);
            for (int col = left; col <= right; col++) {
                if (Character.isDigit(line.charAt(col))) {
                    int moveRight = extractPartNumberPartial(partNumbers, line, col);
                    col += moveRight;
                }
            }
        }
        return partNumbers;
    }

    private boolean isSymbol(char c) {
        if (!Character.isDigit(c) && c != '.') {
            return true;
        }
        return false;
    }

    private int extractPartNumberPartial(List<Integer> partNumberPartials, String line, int offset) {
        int moveRight = 0;
        if (Character.isDigit(line.charAt(offset))) {
            int start = offset;
            int end = offset;

            while (0 < start && Character.isDigit(line.charAt(start - 1))) {
                start--;
            }
            while (end < line.length() && Character.isDigit(line.charAt(end))) {
                end++;
            }
            moveRight = (end - offset);
            partNumberPartials.add(Integer.valueOf(line.substring(start, end)));
        }
        return moveRight;
    }

    public long sum(List<Integer> data) {
        long sum = 0;
        for (Integer integer : data) {
            sum += integer;
        }
        return sum;
    }

}
