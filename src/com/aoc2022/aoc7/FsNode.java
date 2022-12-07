package com.aoc2022.aoc7;

public abstract class FsNode {
    protected String name;
    protected Directory parent = null;

    protected FsNode(String name) {
        this.name = name;
    }

    public abstract long size();

    public Directory parent() {
        return parent;
    }
}
