package com.aoc2023.Q5;

public class Pair<Ta,Tb> {

    private Ta first;
    private Tb second;

    public Pair(Ta first, Tb second) {
        this.first = first;
        this.second = second;
    }

    public Ta getFirst() {
        return first;
    }

    public Tb getSecond() {
        return second;
    }
}
