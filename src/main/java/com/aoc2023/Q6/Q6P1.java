package com.aoc2023.Q6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Q6P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q6/input.txt";
        try {
            // Read all lines from the file into a List
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            long ans = getAns(lines);
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static long getAns(List<String> lines) throws IOException {
        long ans = 1;
        String timesString = lines.get(0).replaceAll("\\s+", " ").trim();
        String distanceString = lines.get(1).replaceAll("\\s+", " ").trim();
        System.out.println(timesString);
        System.out.println(distanceString);
        String[] times = timesString.split(":")[1].trim().split(" ");
        String[] distance = distanceString.split(":")[1].trim().split(" ");

        for (int i = 0; i < times.length; i++) {
            long time = Long.parseLong(times[i]);
            long dist = Long.parseLong(distance[i]);
            ans *= calcAns(time, dist);
        }

        return ans;
    }

    private static long calcAns(long time, long distance) {
        long count = 0;
        for (int i = 1; i < time; i++) {
            long distanceTravelled = (time - i) * i;
            if (distanceTravelled > distance) {
                count++;
            }
        }

        return count;

    }


}