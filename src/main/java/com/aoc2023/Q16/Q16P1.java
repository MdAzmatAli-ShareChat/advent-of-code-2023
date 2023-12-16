package com.aoc2023.Q16;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.aoc2023.Q5.Pair;

public class Q16P1 {
    private static int n, m;
    private static char[][] grid;
    private static boolean[][][] visited;

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/ShareChat/advent-of-code-2023/src/main/java/com/aoc2023/Q16/input.txt";
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
        n = lines.size();
        m = lines.get(0).length();
        grid = new char[n][m];
        visited = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            String line = lines.get(i);
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        dfs(0,0, 0);
        for (int i = 0; i < n; i++) {
            for(int j=0;j<m;j++){
                if(visited[i][j][0] || visited[i][j][1] || visited[i][j][2] || visited[i][j][3]){
                    ans++;
                }
            }
        }

        return ans;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    /*
        0 - right
        1 - down
        2 - left
        3 - up
     */


    private static void dfs(int x, int y, int dir) {
        if (!isValid(x, y) || visited[x][y][dir]) {
            return;
        }
        visited[x][y][dir] = true;

        if (grid[x][y] == '.') {
            if (dir == 0) {
                dfs(x, y + 1, dir);
            } else if (dir == 1) {
                dfs(x + 1, y, dir);
            } else if (dir == 2) {
                dfs(x, y - 1, dir);
            } else {
                dfs(x - 1, y, dir);
            }
        } else if (grid[x][y] == '-') {
            if (dir == 0) {
                dfs(x, y + 1, dir);
            } else if (dir == 1 || dir == 3) {
                dfs(x, y - 1, 2);
                dfs(x, y + 1, 0);
            } else if (dir == 2) {
                dfs(x, y - 1, dir);
            } else {
                dfs(x, y + 1, 0);
                dfs(x, y - 1, 2);
            }
        } else if (grid[x][y] == '|') {
            if (dir == 0) {
                dfs(x + 1, y, 1);
                dfs(x - 1, y, 3);
            } else if (dir == 1) {
                dfs(x + 1, y, dir);
            } else if (dir == 2) {
                dfs(x - 1, y, 3);
                dfs(x + 1, y, 1);
            } else {
                dfs(x - 1, y, dir);
            }
        } else if (grid[x][y] == '/') {
            if (dir == 0) {
                dfs(x - 1, y, 3);
            } else if (dir == 1) {
                dfs(x, y - 1, 2);
            } else if (dir == 2) {
                dfs(x + 1, y, 1);
            } else {
                dfs(x, y + 1, 0);
            }
        } else if (grid[x][y] == '\\') {
            if (dir == 0) {
                dfs(x + 1, y, 1);
            } else if (dir == 1) {
                dfs(x, y + 1, 0);
            } else if (dir == 2) {
                dfs(x - 1, y, 3);
            } else {
                dfs(x, y - 1, 2);
            }
        }
    }


}