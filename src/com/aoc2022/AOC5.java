package com.aoc2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AOC5 {

    public static void main(String[] args) {
        final var lines = Utils.getLines("data/5");

        Map<Integer, List<Character>> crates = new HashMap<>();
        List<Movement> movements = new ArrayList<>();
        for (var line : lines) {
            if (line.length() > 1 && line.charAt(1) != '1' && !line.startsWith("move")) {
                var crateEntries = parseSchemaLine(line);
                for (var crateEntry : crateEntries) {
                    crates.computeIfAbsent(crateEntry.index, k -> new ArrayList<>());
                    crates.get(crateEntry.index).add(crateEntry.crate);
                }
            } else if (line.startsWith("move")) {
                movements.add(parseMovementLine(line));
            }
        }

        // reverse crates
        for (var crateEntry : crates.entrySet()) {
            Collections.reverse(crateEntry.getValue());
        }

        // compute movements
        for (var movement : movements) {
            var from = crates.get(movement.from);
            var to = crates.get(movement.to);

            List<Character> buffer = new ArrayList<>();
            for (int i = 0; i < movement.count; i++) {
                var c = from.remove(from.size()-1);
                buffer.add(c);

                // for part one, just add c into the destination (forget the buffer)
                // to.add(c);

            }
            Collections.reverse(buffer);
            for (char c : buffer) {
                to.add(c);
            }
        }

        String result = "";
        for (var col : crates.values()) {
            result = result + col.get(col.size() - 1);
        }

        System.out.println(result);

    }

    private static List<CrateEntry> parseSchemaLine(final String line) {
        String tempLine = line + " ";
        int idx = 0;
        List<CrateEntry> entries = new ArrayList<>();
        for (int i = 0; i < tempLine.length(); i++) {
            if (i % 4 == 1) {
                var c = line.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    entries.add(new CrateEntry(idx, c));
                }
            }
            else if (i % 4 == 0) {
                idx ++;
            }
        }

        return entries;
    }

    private static Movement parseMovementLine(final String line) {
        Matcher lineMatcher = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)").matcher(line);

        if (lineMatcher.matches()) {
            return new Movement(Integer.parseInt(lineMatcher.group(2)), Integer.parseInt(lineMatcher.group(3)), Integer.parseInt(lineMatcher.group(1)));
        }

        throw new IllegalArgumentException();
    }

    public static class Movement {
        int from;
        int to;
        int count;

        public Movement(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }
    }

    public static class CrateEntry {
        int index;
        char crate;

        public CrateEntry(int index, char crate) {
            this.index = index;
            this.crate = crate;
        }
    }

}