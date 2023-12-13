package com.aoc2023.Q13;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Q13P2 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/ShareChat/advent-of-code-2023/src/main/java/com/aoc2023/Q13/input.txt";
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
        ArrayList<String> list = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                ans += calcAns(list);
                list = new ArrayList<>();
            } else {
                list.add(line);
            }
        }


        return ans;
    }

    private static long calcAns(ArrayList<String> list) {
//        System.out.println(list);
        int n = list.size();
        int m = list.get(0).length();
        long ans = 0;
        // traverse on column and split on column
        for (int split = 1; split < m; split++) {

            int left = split - 1, right = split;

            int mismatch = 0;
            while (left >= 0 && right < m ) {

                for (int i = 0; i < n; i++) {
                    if (list.get(i).charAt(left) != list.get(i).charAt(right)) {
                        mismatch++;
                    }
                }
                left--;
                right++;
            }

            if (mismatch == 1) {
                ans += split;
            }
        }

        // traverse on row and split on row
        for (int split = 1; split < n; split++) {

            int up = split - 1, down = split;

            int mismatch = 0;
            while (up >= 0 && down < n) {

                for (int i = 0; i < m; i++) {
                    if (list.get(up).charAt(i) != list.get(down).charAt(i)) {
                        mismatch++;
                    }
                }
                up--;
                down++;
            }

            if (mismatch==1) {
                ans += (100L * split);
            }
        }

        return ans;
    }


}