package com.aoc2023.Q1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1P2 {
    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/org/example/Q1/input.txt";
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

    private static long getAns(List<String> lines) {
        long ans = 0;
        HashMap<String, Integer> numbers = new HashMap<String, Integer>() {
            {
                put("one", 1);
                put("two", 2);
                put("three", 3);
                put("four", 4);
                put("five", 5);
                put("six", 6);
                put("seven", 7);
                put("eight", 8);
                put("nine", 9);
            }
        };
        for (String line : lines) {
            long first = -1;
            long sec = -1;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    if (first == -1) {
                        first = line.charAt(i) - '0';
                    } else {
                        sec = line.charAt(i) - '0';
                    }
                } else {
                    for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
                        String number = entry.getKey();
                        int j = i;
                        int k = 0;
                        while (j < line.length() && k < number.length() && line.charAt(j) == number.charAt(k)) {
                            j++;
                            k++;
                        }
                        if (k == number.length()) {
                            if (first == -1) {
                                first = entry.getValue();
                            } else {
                                sec = entry.getValue();
                            }
                            break;
                        }
                    }
                }
            }

            if (sec == -1) {
                sec = first;
            }
            ans += (first * 10L + sec);
        }
        return ans;
    }
}