package com.aoc2023.Q14;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Q14P2 {

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

        HashMap<String, Integer> mp = new HashMap<>();
        HashMap<Integer, Long> ansMap = new HashMap<>();
        for (int cycle = 1; cycle <= 1000; cycle++) {
            StringBuilder sb = new StringBuilder();
            long temp = 0;
            rotateNorth(grid, n, m);
            rotateWest(grid, n, m);
            rotateSouth(grid, n, m);
            rotateEast(grid, n, m);

            for (int i = 0; i < n; i++) {
                sb.append(grid[i]);
                long count = 0;
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 'O') {
                        count++;
                    }
                }

                temp += ((n - i) * count);
            }

            if (mp.containsKey(sb.toString())) {
                int oldCycle = mp.get(sb.toString());
                System.out.println("current Cycle: " + cycle);
                System.out.println("old Cycle: " + oldCycle);
                System.out.println("Cycle length : " + (cycle - oldCycle));
                int ansPosition = (1000000000 - cycle) % (cycle - oldCycle);
                System.out.println("ansPosition: " + ansPosition);
                ans = ansMap.get(mp.get(sb.toString()) + ansPosition);
                break;
            }
            ansMap.put(cycle, temp);
            mp.put(sb.toString(), cycle);
        }

        return ans;
    }

    private static void rotateNorth(char[][] grid, int n, int m) {
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
    }

    private static void rotateSouth(char[][] grid, int n, int m) {
        for (int j = 0; j < m; j++) {
            for (int i = n - 1; i >= 0; i--) {
                if (grid[i][j] == 'O') {
                    int k = i + 1;
                    while (k < n && grid[k][j] == '.') {
                        char temp = grid[k][j];
                        grid[k][j] = grid[k - 1][j];
                        grid[k - 1][j] = temp;
                        k++;
                    }
                }
            }
        }
    }

    private static void rotateEast(char[][] grid, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 'O') {
                    int k = j + 1;
                    while (k < m && grid[i][k] == '.') {
                        char temp = grid[i][k];
                        grid[i][k] = grid[i][k - 1];
                        grid[i][k - 1] = temp;
                        k++;
                    }
                }
            }
        }
    }

    private static void rotateWest(char[][] grid, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'O') {
                    int k = j - 1;
                    while (k >= 0 && grid[i][k] == '.') {
                        char temp = grid[i][k];
                        grid[i][k] = grid[i][k + 1];
                        grid[i][k + 1] = temp;
                        k--;
                    }
                }
            }
        }
    }


}