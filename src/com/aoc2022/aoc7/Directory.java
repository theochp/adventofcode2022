package com.aoc2022.aoc7;

import java.util.HashMap;
import java.util.Map;

public class Directory extends FsNode {

    private final Map<String, FsNode> nodes;

    public Directory(String name) {
        super(name);
        this.nodes = new HashMap<>();
    }

    @Override
    public boolean isLeaf() {
        return nodes.isEmpty();
    }

    @Override
    public long size() {
        return nodes.values().stream().mapToLong(FsNode::size).sum();
    }

    public void add(FsNode node) {
        node.parent = this;
        this.nodes.put(node.name, node);
    }

    public Map<String, FsNode> nodes() {
        return nodes;
    }
}
