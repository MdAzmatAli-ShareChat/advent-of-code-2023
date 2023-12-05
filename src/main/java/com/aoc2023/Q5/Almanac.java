package com.aoc2023.Q5;

public class Almanac {

    private long destinationStart;
    private long sourceStart;
    private long length;


    public Almanac(long destinationStart, long sourceStart, long length) {
        this.destinationStart = destinationStart;
        this.sourceStart = sourceStart;
        this.length = length;
    }

    public long getDestinationStart() {
        return destinationStart;
    }

    public void setDestinationStart(long destinationStart) {
        this.destinationStart = destinationStart;
    }

    public long getSourceStart() {
        return sourceStart;
    }

    public void setSourceStart(long sourceStart) {
        this.sourceStart = sourceStart;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }


}
