package com.aoc2022.aoc7;

public class File extends FsNode {

    private final long size;

    public File(String name, long size) {
        super(name);
        this.size = size;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public long size() {
        return size;
    }
}
