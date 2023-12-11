package com.aoc2023.Q11;


import com.aoc2023.Q5.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Q11P1 {


    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q11/input.txt";
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
        long ans = 0;
        int n = lines.size();
        int m = lines.get(0).length();
        long[] row = new long[n + 1];
        long[] col = new long[m + 1];
        row[0] = 0;
        col[0] = 0;
        long expansionFactor = 1000000; // for part 1 factor -> 2
        for (int i = 0; i < n; i++) {
            boolean pos = true;
            for (int j = 0; j < m; j++) {
                if (lines.get(i).charAt(j) == '#') {
                    pos = false;
                    break;
                }
            }
            row[i + 1] = row[i] + (pos ? (expansionFactor - 1) : 0);
        }

        for (int i = 0; i < m; i++) {
            boolean pos = true;
            for (int j = 0; j < n; j++) {
                if (lines.get(j).charAt(i) == '#') {
                    pos = false;
                    break;
                }
            }
            col[i + 1] = col[i] + (pos ? (expansionFactor - 1) : 0);
        }

        ArrayList<Pair<Integer, Integer>> galaxies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lines.get(i).charAt(j) == '#') {
                    galaxies.add(new Pair<>(i, j));
                }
            }
        }
        for (int i = 0; i < galaxies.size(); i++) {
            int x = galaxies.get(i).getFirst();
            int y = galaxies.get(i).getSecond();
            for (int j = i + 1; j < galaxies.size(); j++) {
                if (i == j) continue;
                int x1 = galaxies.get(j).getFirst();
                int y1 = galaxies.get(j).getSecond();

                long x_diff = Math.abs(x - x1);
                long y_diff = Math.abs(y - y1);
                long additional_x = row[Math.max(x, x1) + 1] - row[Math.min(x, x1)];
                long additional_y = col[Math.max(y, y1) + 1] - col[Math.min(y, y1)];
                ans += x_diff + y_diff + additional_x + additional_y;

            }
        }

        return ans;
    }


}