package com.aoc2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// I didn't keep part one
class AOC3 {

    public static void main(String[] args) {
        final var bags = Utils.getLines("data/3");

        // Liste des groupes. CHaque groupe est représenté par la liste de ses sacs, un sac étant représenté par le compte
        // des éléments qu'il contient, par priorité (map priorité => count)
        List<List<Map<Integer, Integer>>> groups = new ArrayList<>();

        final int GROUP_SIZE = 3;
        int idx = 0;

        // Création de la liste pour chaque groupe
        List<Map<Integer, Integer>> tempList = new ArrayList<>();
        for (var bag : bags) {
            if (idx != 0 && idx % GROUP_SIZE == 0) {
                groups.add(tempList);
                tempList = new ArrayList<>();
            }

            var map = initMap();
            computeOccurences(bag.toCharArray(), map);
            tempList.add(map);

            idx++;
        }
        groups.add(tempList);

        int sum = 0;
        for (var group : groups) {
            for (int currentPriority = 1; currentPriority <= group.get(0).size(); currentPriority++) {
                int allHas = 1;
                // on utilise la multiplication pour vérifier que tous les éléments de la liste contiennent l'item
                for (var countPerPriority : group) {
                    allHas *= countPerPriority.get(currentPriority);
                }
                if (allHas > 0) {
                    sum += currentPriority;
                }
            }
        }

        System.out.println(sum);
    }

    private static int priority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        } else if (item >= 'A' && item <= 'Z') {
            return item - 'A' + 27;
        }
        throw new IllegalArgumentException();
    }

    private static Map<Integer, Integer> initMap() {
        var map = new HashMap<Integer, Integer>();

        for (int i = 1; i <= 52; i++) {
            map.put(i, 0);
        }

        return map;
    }

    private static void computeOccurences(char[] compartment, Map<Integer, Integer> map) {
        for (char item : compartment) {
            map.put(priority(item), map.get(priority(item)) + 1);
        }
    }

}