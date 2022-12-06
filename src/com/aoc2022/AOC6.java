package com.aoc2022;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class AOC6 {

    public static void main(String[] args) {
        final var lines = Utils.getLines("data/6");

        for (var line : lines) {
            final var signals = line.toCharArray();

            // for part one, just change buffer size
            var buffer = new CharBuffer(14);

            for (char signal : signals) {
                buffer.read(signal);

                if (buffer.data.size() >= 14 && buffer.areElementsUnique()) {
                    System.out.println(buffer.getRead() + " " + buffer);
                    break;
                }
            }
        }
    }

    public static class CharBuffer {
        private final LinkedList<Character> data = new LinkedList<>();
        private final int size;
        private int read = 0;

        public CharBuffer(int size) {
            this.size = size;
        }

        public void read(final char character) {
            data.add(character);

            if (data.size() > size) {
                data.removeFirst();
            }

            read++;
        }

        public boolean areElementsUnique() {
            // this could be improved by keeping an updated set after each read. But I got issues when a buffer range
            // starts and ends with the same character.
            return new HashSet<>(data).size() == data.size();
        }

        public int getRead() {
            return read;
        }

        public String toString() {
            return data.stream().map(Object::toString).reduce("", (acc, c) -> acc + c);
        }
    }

}