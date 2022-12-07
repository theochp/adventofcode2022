package com.aoc2022;

import com.aoc2022.aoc7.Directory;
import com.aoc2022.aoc7.File;

import java.util.ArrayList;
import java.util.List;

class AOC7 {

    public static void main(String[] args) {
        var lines = Utils.getLines("data/input7");

        Directory root = new Directory("/");
        Directory current = null;

        boolean readEntries = false;
        for (var line : lines) {
            if (line.startsWith("$")) {
                readEntries = false;
                if (line.startsWith("$ cd")) {
                    var folder = line.substring(5);
                    if (folder.equals("/")) {
                        current = root;
                    } else if (current != null) {
                        if (folder.equals("..")) {
                            if (current.parent() != null) {
                                current = current.parent();
                            } else {
                                throw new IllegalArgumentException("Current directory has no parent");
                            }
                        } else if (current.nodes().containsKey(folder)) {
                            var checkedNode = current.nodes().get(folder);
                            if (checkedNode instanceof Directory) {
                                current = (Directory) checkedNode;
                            } else {
                                throw new IllegalArgumentException("Only directories can be checked");
                            }
                        } else {
                            throw new IllegalArgumentException("Not found");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot check directory from nowhere");
                    }
                } else if (line.startsWith("$ ls")) {
                    readEntries = true;
                }
            } else if (readEntries) {
                if (current == null) {
                    throw new IllegalArgumentException("Cannot list files from nowhere");
                } else {
                    if (line.startsWith("dir")) {
                        var dirName = line.substring(4);
                        current.add(new Directory(dirName));
                    } else if (line.matches("\\d+ [a-zA-Z.]+")) {
                        var parts = line.split(" ");
                        var size = Long.parseLong(parts[0]);
                        var fileName = parts[1];
                        current.add(new File(fileName, size));
                    }
                }
            }
        }

        // part 1
        var dirs = smallDirectories(root);
        System.out.println("part 1 " + dirs.stream().mapToLong(Directory::size).sum());

        // part 2
        long mustFreeUp = (root.size() + 30_000_000) - 70_000_000;
        var candidates = dirsAboveLimit(root, mustFreeUp);
        var min = candidates.stream().mapToLong(Directory::size).min().orElse(0);
        System.out.println("part 2 " + min);
    }

    // part 1
    public static List<Directory> smallDirectories(Directory root) {
        final long maxSize = 100_000;
        List<Directory> dirs = new ArrayList<>();
        for (var node : root.nodes().values()) {
            if (node instanceof Directory directory) {
                dirs.addAll(smallDirectories(directory));
                if (node.size() <= maxSize) {
                    dirs.add(directory);
                }
            }
        }
        return dirs;
    }

    // part 2
    public static List<Directory> dirsAboveLimit(Directory root, long limit) {
        List<Directory> dirs = new ArrayList<>();
        for (var node : root.nodes().values()) {
            if (node instanceof Directory directory) {
                if (node.size() >= limit) {
                    dirs.addAll(dirsAboveLimit(directory, limit));
                    dirs.add(directory);
                }
            }
        }
        return dirs;
    }
}

