package com.gustafn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Day1 {

    private Map<String, Integer> numbersasTextMap = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);
    private List<Integer> dataPart1;
    private List<Integer> dataPart2;

    public Day1(List<String> data) {
        this.dataPart1 = parseDataPart1(data);
        this.dataPart2 = parseDataPart2(data);
    }

    public long sum(List<Integer> data) {
        long sum = 0;
        for (Integer integer : data) {
            sum += integer;
        }
        return sum;
    }

    public List<Integer> getDataPart1() {
        return dataPart1;
    }

    public List<Integer> getDataPart2() {
        return dataPart2;
    }

    private List<Integer> parseDataPart2(Collection<String> inData) {
        List<Integer> outData = new ArrayList<>(inData.size());

        for (String line : inData) {
            int[] numbers = new int[2];
            // Get first number
            int counter = 0;
            while (counter < line.length()) {
                numbers[0] = getNumberFomText(line, counter, true);
                if (numbers[0] == -1) {
                    counter++;
                } else {
                    break;
                }
            }
            // Get last number
            counter = line.length() - 1;
            while (counter >= 0) {
                if (Character.isDigit(line.charAt(counter))) {
                    numbers[1] = Character.getNumericValue(line.charAt(counter));
                    break;
                }
                numbers[1] = getNumberFomText(line, counter, true);
                if (numbers[1] == -1) {
                    counter--;
                } else {
                    break;
                }
            }
            outData.add((numbers[0] * 10) + numbers[1]);
        }

        return outData;
    }

    private List<Integer> parseDataPart1(Collection<String> inData) {
        List<Integer> outData = new ArrayList<>(inData.size());

        for (String s : inData) {
            int[] numbers = new int[2];
            boolean first = true;
            for (int i = 0; i < s.length(); i++) {
                int val = getNumberFomText(s, i, false);
                if (val >= 0) {
                    numbers[1] = val;
                    if (first) {
                        first = false;
                        numbers[0] = numbers[1];
                    }
                }
            }
            outData.add((numbers[0] * 10) + numbers[1]);
        }
        return outData;
    }

    private int getNumberFomText(String line, int offset, boolean searchText) {
        if (Character.isDigit(line.charAt(offset))) {
            return Character.getNumericValue(line.charAt(offset));
        }
        if (searchText) {
            for (String key : numbersasTextMap.keySet()) {
                if (line.startsWith(key, offset)) {
                    return numbersasTextMap.get(key);
                }
            }
        }
        return -1;
    }
}
