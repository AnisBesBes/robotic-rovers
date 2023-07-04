package com.nasa.robotic.rovers.model;

import java.util.Arrays;

public enum Cardinal {
    N(new Node('N', 'W', 'E')),
    E(new Node('E', 'N', 'S')),
    S(new Node('S', 'E', 'W')),
    W(new Node('W', 'S', 'N'));

    private final Node node;

    Cardinal(Node node) {
        this.node = node;
    }

    public static char precedent(char cardinalValue) {
        Cardinal cardinal = Arrays.stream(values())
                .filter(e -> e.node.value == cardinalValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unrecognized cardinal"));

        return cardinal.node.precedent;
    }

    public static char next(char cardinalValue) {
        Cardinal cardinal = Arrays.stream(values())
                .filter(e -> e.node.value == cardinalValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unrecognized cardinal"));

        return cardinal.node.next;
    }

    private static class Node {
        char value;
        char precedent;
        char next;

        public Node(char value, char precedent, char next) {
            this.value = value;
            this.precedent = precedent;
            this.next = next;
        }
    }
}
