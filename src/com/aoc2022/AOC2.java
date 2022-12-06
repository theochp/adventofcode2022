package com.aoc2022;

class AOC2 {
    enum ResultsPart1 {
        // for part one, i just changed the score of each pair in the enum

        AX(1 + 3), // rock/rock
        AY(2 + 6), // rock/paper
        AZ(3 + 0), // rock/scissors
        BX(1 + 0), // paper/rock
        BY(2 + 3), // paper/paper
        BZ(3 + 6), // paper/scissors
        CX(1 + 6), // scissors/rock
        CY(2 + 0), // scissors/paper
        CZ(3 + 3); // scissors/scissors

        final int score;
        ResultsPart1(int score) {
            this.score = score;
        }
    }

    enum ResultsPart2 {
        AX(3 + 0), // rock/scissors
        AY(1 + 3), // rock/rock
        AZ(2 + 6), // rock/paper
        BX(1 + 0), // paper/rock
        BY(2 + 3), // paper/paper
        BZ(3 + 6), // paper/scissors
        CX(2 + 0), // scissors/paper
        CY(3 + 3), // scissors/scissors
        CZ(1 + 6); // scissors/rock

        final int score;
        ResultsPart2(int score) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        final var lines = Utils.getLines("data/2");

        long total1 = 0;
        long total2 = 0;

        for (var line : lines) {
            total1 += ResultsPart1.valueOf(line.replace(" ", "")).score;
            total2 += ResultsPart2.valueOf(line.replace(" ", "")).score;
        }

        System.out.println(total1);
        System.out.println(total2);
    }

}