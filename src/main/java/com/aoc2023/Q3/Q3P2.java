package com.aoc2023.Q3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Q3P2 {

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
        long ans = 0;

        for (int i = 0; i < n; i++) {
            String line = lines.get(i);
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == '*') {
                    int[][] grid = new int[n][m];
                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < m; b++) {
                            grid[a][b] = 0;
                        }
                    }
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < n && y >= 0 && y < m) {
                            grid[x][y] = 1;
                        }
                    }

                    ans += calcAns(grid, i, lines);

                }
            }
        }

        return ans;
    }

    private static long calcAns(int[][] grid, int row, List<String> lines) {
        long ans = 0;
        ArrayList<Long> list = new ArrayList<>();
        if (row - 1 >= 0) {
            fetchNumber(row - 1, lines, grid, list);
        }
        if (row + 1 < lines.size()) {
            fetchNumber(row + 1, lines, grid, list);
        }
        fetchNumber(row, lines, grid, list);

        if(list.size()==2){
            ans = list.get(0) * list.get(1);
        }

        return ans;
    }

    private static void fetchNumber(int i, List<String> lines, int[][] grid, ArrayList<Long> list) {
        String line = lines.get(i);
        int j = 0;
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        while (j < line.length()) {
            if ((line.charAt(j) >= '0' && line.charAt(j) <= '9')) {
                if (grid[i][j] == 1) {
                    found = true;
                }
                sb.append(line.charAt(j));
            } else {
                String s = sb.toString();
                if ((!s.isEmpty()) && found) {
                    list.add(Long.parseLong(s));
                }
                sb = new StringBuilder();
                found = false;
            }
            j++;
        }
        String s = sb.toString();
        if (!s.isEmpty() && found) {
            list.add(Long.parseLong(s));
        }

    }

}