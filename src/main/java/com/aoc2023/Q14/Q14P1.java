package com.aoc2023.Q14;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Q14P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/ShareChat/advent-of-code-2023/src/main/java/com/aoc2023/Q14/input.txt";
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
        int n = lines.size();
        int m = lines.get(0).length();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = lines.get(i);
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 'O') {
                    int k = i - 1;
                    while (k >= 0 && grid[k][j] == '.') {
                        char temp = grid[k][j];
                        grid[k][j] = grid[k + 1][j];
                        grid[k + 1][j] = temp;
                        k--;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            long count = 0;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'O') {
                    count++;
                }
            }

            ans += ((n - i) * count);
        }

        return ans;
    }


}