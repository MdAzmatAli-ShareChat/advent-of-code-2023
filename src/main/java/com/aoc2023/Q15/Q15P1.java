package com.aoc2023.Q15;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Q15P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/ShareChat/advent-of-code-2023/src/main/java/com/aoc2023/Q15/input.txt";
        try {
            // Read all lines from the file into a List
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            long ans = solve(lines);
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long solve(List<String> lines) throws IOException {
        long ans = 0;
        String[] parts = lines.get(0).split(",");
        for(String part: parts) {
            long hash = 0;
            for(int i=0;i<part.length();i++) {
                int num = part.charAt(i);
                hash += num;
                hash *= 17;
                hash %= 256;
            }
            System.out.println(hash);
            ans += hash;
        }

        return ans;
    }


}