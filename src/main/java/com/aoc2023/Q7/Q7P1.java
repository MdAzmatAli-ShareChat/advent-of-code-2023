package com.aoc2023.Q7;

import com.aoc2023.Q5.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Q7P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q7/input.txt";
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
        ArrayList<Pair<String, Long>> cards = new ArrayList<Pair<String, Long>>();
        for (String line : lines) {
            String lineWithoutExtras = line.replaceAll("\\s+", " ").trim();
            String[] parts = lineWithoutExtras.split(" ");
            String card = parts[0];
            long value = Long.parseLong(parts[1]);
            cards.add(new Pair<String, Long>(card, value));
        }

        Comparator<Pair<String, Long>> customSort = (first, second) -> {
            HashMap<Character, Long> mp1 = new HashMap<>();
            HashMap<Character, Long> mp2 = new HashMap<>();
            for (char c : first.getFirst().toCharArray()) {
                mp1.put(c, mp1.getOrDefault(c, 0L) + 1);
            }
            for (char c : second.getFirst().toCharArray()) {
                mp2.put(c, mp2.getOrDefault(c, 0L) + 1);
            }
            ArrayList<Long> list1 = new ArrayList<>();
            ArrayList<Long> list2 = new ArrayList<>();
            for (char c : mp1.keySet()) {
                list1.add(mp1.get(c));
            }
            for (char c : mp2.keySet()) {
                list2.add(mp2.get(c));
            }
            list1.sort(Collections.reverseOrder());
            list2.sort(Collections.reverseOrder());

            if (list1.size() > list2.size()) {
                return -1;
            }
            if (list1.size() < list2.size()) {
                return 1;
            }

            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) < list2.get(i)) {
                    return -1;
                }
                if (list1.get(i) > list2.get(i)) {
                    return 1;
                }
            }

            for (int i = 0; i < 5; i++) {
                char firstChar = first.getFirst().charAt(i);
                char secondChar = second.getFirst().charAt(i);
                int firstCharValue = getCharValue(firstChar);
                int secondCharValue = getCharValue(secondChar);
                if (firstCharValue < secondCharValue) {
                    return -1;
                }
                if (firstCharValue > secondCharValue) {
                    return 1;
                }
            }
            return 0;
        };
        cards.sort(customSort);
        for(int i=0;i<cards.size();i++){
            Pair<String, Long> card = cards.get(i);
            System.out.println(card.getFirst() + " " + card.getSecond());
            ans += (long) (i + 1) * (card.getSecond());
        }


        return ans;
    }


    private static int getCharValue(char c) {
        if (c >= '2' && c <= '9') {
            return c - '0';
        }
        if (c == 'T') {
            return 10;
        }
        if (c == 'J') {
            return 11;
        }
        if (c == 'Q') {
            return 12;
        }
        if (c == 'K') {
            return 13;
        }
        if (c == 'A') {
            return 14;
        }
        return -1;
    }


}