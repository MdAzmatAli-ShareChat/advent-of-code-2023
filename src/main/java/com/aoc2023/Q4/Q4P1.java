package com.aoc2023.Q4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;


public class Q4P1 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q4/input.txt";
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
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53

        int n = lines.size();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            String line = lines.get(i);
            String[] parts = line.split(":");
            String cardDetails = parts[1].trim(); // Extract the card details

            String[] detailsArray = cardDetails.split("\\|");
            String[] winningCards = detailsArray[0].trim().split(" ");
            String[] cardsIhave = detailsArray[1].trim().split(" ");

            HashSet<Integer> winningCardsSet = new HashSet<>();
            for (String card : winningCards) {
                if(!card.isEmpty()) {
                    winningCardsSet.add(Integer.parseInt(card));
                }
            }

            long mul = 1;
            boolean found = false;
            for (String card : cardsIhave) {
                if(!card.isEmpty()) {
                    int cardNum = Integer.parseInt(card);
                    if(winningCardsSet.contains(cardNum)) {
                        found = true;
                        mul *= 2;
                    }
                }
            }
            if( mul > 1){
                mul /= 2;
            }
            if(found) {
                ans += mul;
            }

        }

        return ans;
    }


}