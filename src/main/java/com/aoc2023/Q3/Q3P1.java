package com.aoc2023.Q3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Q3P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q3/input.txt";
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
        int n = lines.size();
        int m = lines.get(0).length();


        int[] dx = new int[]{1, 1, 1, -1, -1, -1, 0, 0};
        int[] dy = new int[]{0, -1, 1, 0, -1, 1, 1, -1};

        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            String line = lines.get(i);
            for (int j = 0; j < m; j++) {
                if ((!(line.charAt(j) >= '0' && line.charAt(j) <= '9')) && (!(line.charAt(j) == '.'))) {
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < n && y >= 0 && y < m) {
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            String line = lines.get(i);
            StringBuilder sb = new StringBuilder();
            boolean found = false;


            while (j < m) {
                if ((line.charAt(j) >= '0' && line.charAt(j) <= '9')) {
                    if(grid[i][j] == 1) {
                        found = true;
                    }
                    sb.append(line.charAt(j));
                } else {
                    String s = sb.toString();
                    if ((!s.isEmpty()) && found) {
                        ans += Long.parseLong(s);
                    }
                    sb = new StringBuilder();
                    found = false;
                }
                j++;
            }
            String s = sb.toString();
            if (!s.isEmpty() && found) {
                ans += Long.parseLong(s);
            }
        }

        return ans;
    }


}