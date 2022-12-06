package com.aoc2022;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class AOC4 {

    public static void main(String[] args) {
        final var pairs = Utils.getLines("data/4");
        final List<Section[]> sectionPairs = new ArrayList<>();

        int count1 = 0;
        int count2 = 0;
        for (var pair : pairs) {
            var sections = pair.split(",");
            var sectionPair = new Section[]{new Section(sections[0]), new Section(sections[1])};
            sectionPairs.add(sectionPair);

            if (sectionPair[0].contains(sectionPair[1])) {
                count1++;
            }
            if (sectionPair[0].overlaps(sectionPair[1])) {
                count2++;
            }
        }

        System.out.println("contained pairs: " + count1);
        System.out.println("overlap pairs: " + count2);
    }

    public static class Section {
        int from;
        int to;

        public Section(final String sectionString) {
            var sectionPattern = Pattern.compile("(\\d+)-(\\d+)");
            var sectionMatcher = sectionPattern.matcher(sectionString);
            if (sectionMatcher.matches()) {
                this.from = Integer.parseInt(sectionMatcher.group(1));
                this.to = Integer.parseInt(sectionMatcher.group(2));
            } else {
                throw new IllegalArgumentException();
            }
        }

        // part one
        public boolean contains(final Section section) {
            return this.from >= section.from && this.to <= section.to
                || section.from >= this.from && section.to <= this.to;
        }


        // part two
        public boolean overlaps(final Section section) {
            boolean overlaps;
            // 1st case: |----| section
            //              |----| this
            overlaps = section.to >= this.from && section.to <= this.to;

            // 2nd case:      |----| section
            //              |----| this
            overlaps |= section.from >= this.from && section.from <= this.to;

            // 3rd case:   |------| section or |----| section
            //              |----| this or   |-------| this
            overlaps |= this.contains(section);

            return overlaps;
        }
    }

}