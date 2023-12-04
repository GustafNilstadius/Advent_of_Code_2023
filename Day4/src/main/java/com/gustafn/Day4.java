package com.gustafn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day4 {

    private static final String CARD = "Card ";

    public Day4() {
    }

    public long part1(List<String> input) {
        List<Integer> points = new LinkedList<>();
        for (String card : input) {
            int cardNumber = getCardNumber(card);
            String numbers = card.substring(card.indexOf(":")+1);
            String winningNumbersString = numbers.substring(0, numbers.indexOf("|"));
            String myNumbersString = numbers.substring(numbers.indexOf("|")+1);
            System.out.printf("Card %d: %s | %s%n", cardNumber, winningNumbersString, myNumbersString);
            Set<Integer> winningNumbers = getNumbers(winningNumbersString);
            Set<Integer> myNumbers = getNumbers(myNumbersString);
            int myNumbersCount = myNumbers.size();
            myNumbers.removeAll(winningNumbers);
            int diff = myNumbersCount - myNumbers.size();
            if (diff != 0) {
                int point = 1 << (diff-1);
                System.out.printf("Winning numbers: %d Points for card: %d%n", diff, point);
                points.add(point);
            }
        }
        return sum(points);
    }

    private HashSet<Integer> getNumbers(String s) {
        String[] numbers = s.split(" ");
        HashSet<Integer> set = new HashSet<>();
        for (String number : numbers) {
            if (number.isBlank()) {
                continue;
            }
            set.add(Integer.parseInt(number.trim()));
        }
        return set;
    }

    public long sum(List<Integer> data) {
        long sum = 0;
        for (Integer integer : data) {
            sum += integer;
        }
        return sum;
    }

    private int getCardNumber(String s) {
        String gameNumber = s.substring(CARD.length());
        return Integer.parseInt(gameNumber.substring(0, gameNumber.indexOf(":")).trim());
    }
}
