package com.aoc2023.Q12;

import com.aoc2023.Q5.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Q12P2 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/ShareChat/advent-of-code-2023/src/main/java/com/aoc2023/Q12/input.txt";
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

        for (String line : lines) {
            String[] parts = line.split(" ");
            String spring = parts[0].trim();
            ArrayList<Integer> list = new ArrayList<>();
            String[] confirmationsString = parts[1].split(",");
            for (String confirmationString : confirmationsString) {
                list.add(Integer.parseInt(confirmationString.trim()));
            }
            ArrayList<Integer> confirmations = new ArrayList<>();
            spring = spring + '?' + spring + '?' + spring + '?' + spring + '?' + spring;
            for (int i = 0; i < 5; i++) {
                for (int num : list) {
                    confirmations.add(num);
                }
            }
            // System.out.println(spring);
            // System.out.println(confirmations);
            long[][][] dp = new long[spring.length()+1][spring.length()+1][confirmations.size()+1];
            for(int i=0;i<spring.length()+1;i++){
                for(int j=0;j<spring.length()+1;j++){
                    for(int k=0;k<confirmations.size()+1;k++){
                        dp[i][j][k] = -1;
                    }
                }
            }
            long temp = calcAns(0, spring, 0, 0, confirmations, dp);
            System.out.println(temp);
            ans += temp;

        }
        return ans;
    }

    private static long calcAns(int springIndex, String Spring, int broken, int confirmationIndex,
            ArrayList<Integer> confirmations, long[][][] dp) {
        long ans = 0;
        // System.out.println(springIndex+ " " + broken + " " + confirmationIndex);
        if (springIndex == Spring.length()) {
            // System.out.println(broken + " " + confirmationIndex);
            if (broken == 0) {
                if (confirmationIndex == confirmations.size())
                    return 1;
                else
                    return 0;
            }

            if ((confirmationIndex == confirmations.size() - 1) && (broken == confirmations.get(confirmationIndex))) {
                return 1;
            }
            return 0;
        }
        if(dp[springIndex][broken][confirmationIndex]!=-1){
            return dp[springIndex][broken][confirmationIndex];
        }

        if (Spring.charAt(springIndex) == '#') {
            ans += calcAns(springIndex + 1, Spring, broken + 1, confirmationIndex, confirmations,dp);
        } else if (Spring.charAt(springIndex) == '.') {
            if (broken == 0) {
                ans += calcAns(springIndex + 1, Spring, 0, confirmationIndex, confirmations, dp);
            } else {
                if (confirmationIndex < confirmations.size() && broken == confirmations.get(confirmationIndex)) {
                    ans += calcAns(springIndex + 1, Spring, 0, confirmationIndex + 1, confirmations, dp);
                } 
            }
        } else {
            ans += calcAns(springIndex + 1, Spring, broken + 1, confirmationIndex, confirmations, dp);
            if (broken == 0) {
                ans += calcAns(springIndex + 1, Spring, 0, confirmationIndex, confirmations, dp);
            } else {
                if (confirmationIndex < confirmations.size() && broken == confirmations.get(confirmationIndex)) {
                    ans += calcAns(springIndex + 1, Spring, 0, confirmationIndex + 1, confirmations, dp);
                } 
            }
        }
        dp[springIndex][broken][confirmationIndex] = ans;
        return ans;
    }

}