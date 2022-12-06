package com.aoc2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AOC1 {
    public static void main(String[] args) {
        final var lines = Utils.getLines("data/1");

        // part 1
        long maxSum = 0;
        long sum = 0;

        // part 2
        List<Long> sums = new ArrayList<>();

        for (var line : lines) {
            if (line.matches("\\d+")) {
                sum += Long.parseLong(line);
            } else {
                // part 1
                if (sum > maxSum) {
                    maxSum = sum;
                }
                // part 2
                sums.add(sum);

                sum = 0;
            }
        }

        // part 2
        Collections.sort(sums);
        Collections.reverse(sums);

        System.out.println("part 1: " + maxSum);
        System.out.println("part 2: " + sums.stream().limit(3).reduce(0L, Long::sum));
    }

}