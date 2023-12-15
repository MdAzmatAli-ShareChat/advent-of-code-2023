package com.aoc2023.Q15;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.aoc2023.Q5.Pair;

public class Q15P2 {
    private static final int LIMIT = 256;

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

    private static int getLabelIndex(ArrayList<Pair<String, Integer>> list, String label) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirst().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    private static long solve(List<String> lines) throws IOException {
        long ans = 0;
        String[] parts = lines.get(0).split(",");
        ArrayList[] arr = new ArrayList[LIMIT];
        for (int i = 0; i < LIMIT; i++) {
            arr[i] = new ArrayList<Pair<String, Integer>>();
        }

        for (String part : parts) {
            int hash = 0;
            for (int i = 0; i < part.length(); i++) {
                if (part.charAt(i) == '=' || part.charAt(i) == '-') {
                    String label = part.substring(0, i);
                    ArrayList<Pair<String, Integer>> list = arr[hash];
                    int index = getLabelIndex(list, label);
                    if (part.charAt(i) == '-') {
                        if (index != -1) {
                            list.remove(index);
                        }
                    } else {
                        int num = Integer.parseInt(part.substring(i + 1));
                        if (index == -1) {
                            list.add(new Pair<String, Integer>(label, num));
                        } else {
                            list.get(index).setSecond(num);
                        }
                    }
                } else {
                    int num = part.charAt(i);
                    hash += num;
                    hash *= 17;
                    hash %= LIMIT;
                }
            }
        }
        for (int i = 0; i < LIMIT; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                Pair<String, Integer> p = (Pair<String, Integer>) arr[i].get(j);
                ans += ((i + 1) * ((long) p.getSecond() * (j + 1)));
            }
        }

        return ans;
    }


}