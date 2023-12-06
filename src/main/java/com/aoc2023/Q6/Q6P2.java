package com.aoc2023.Q6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Q6P2 {

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
        String timesString = lines.get(0).replaceAll("\\s+", "").trim();
        String distanceString = lines.get(1).replaceAll("\\s+", "").trim();
        System.out.println(timesString);
        System.out.println(distanceString);
        String times = timesString.split(":")[1].trim();
        String distance = distanceString.split(":")[1].trim();

        ans = calcAns(Long.parseLong(times), Long.parseLong(distance));
        return ans;
    }

    // 46173808
    private static long calcAns(long time, long distance) {
        long lower = Long.MAX_VALUE;
        long higher = Long.MIN_VALUE;
        long low = 1, high = time - 1;
        while (low <= high) {
            long mid = (low + high) / 2;
            long distanceTravelled = (time - mid) * mid;
            if (distanceTravelled > distance) {
                high = mid - 1;
                lower = Math.min(lower, mid);
            } else {
                low = mid + 1;
            }
        }
        low = 1;
        high = time - 1;

        while (low <= high) {
            long mid = (low + high) / 2;
            long distanceTravelled = (time - mid) * mid;
            if (distanceTravelled > distance) {
                low = mid + 1;
                higher = Math.max(higher, mid);
            } else {
                high = mid - 1;
            }
        }
        return higher - lower + 1;
    }


}