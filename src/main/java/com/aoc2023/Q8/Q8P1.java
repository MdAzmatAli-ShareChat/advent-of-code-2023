package com.aoc2023.Q8;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Q8P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q8/input.txt";
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
        String commands = lines.get(0);

        HashMap<String, ArrayList<String>> mp = new HashMap<>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String key = line.substring(0, 3);
            String left = line.substring(7, 10);
            String right = line.substring(12, 15);
            ArrayList<String> temp = new ArrayList<>();
            temp.add(left);
            temp.add(right);
            mp.put(key, temp);
        }
        String cur = "AAA";
        String dest = "ZZZ";
        int commandPos = 0;
        while (!cur.equals(dest)) {
            ans++;
            char command = commands.charAt(commandPos);
            if (command == 'L') {
                cur = mp.get(cur).get(0);
            } else {
                cur = mp.get(cur).get(1);
            }
            commandPos++;
            if(commandPos == commands.length()) {
                commandPos = 0;
            }
        }


        return ans;
    }

}