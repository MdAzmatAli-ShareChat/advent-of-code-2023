package com.aoc2023.Q2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Q2P2 {
    private final int MAX_RED = 12;
    private final int MAX_GREEN = 13;
    private final int MAX_BLUE = 14;

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/org/example/Q2/input.txt";
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
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            List<Game> games = parseInput(line);
            long red = 0, blue = 0, green = 0;
            for (Game game : games) {
                red = Math.max(red, game.getRed());
                blue = Math.max(blue, game.getBlue());
                green = Math.max(green, game.getGreen());
            }
            ans += (red * blue * green);

        }
        return ans;
    }

    private static List<Game> parseInput(String input) {
        String[] split1 = input.split(":");
        String[] split2 = split1[1].split(";");

        // Use a List to store the parsed Game objects
        List<Game> games = new ArrayList<>();

        for (String gameString : split2) {
            Game game = parseGame(gameString);
            games.add(game);
        }

        return games;
    }

    private static Game parseGame(String gameString) {

        String[] stats = gameString.trim().split("\\s*,\\s*");

        Game game = new Game();
        for (String stat : stats) {
            String[] parts = stat.split("\\s+");
            int value = Integer.parseInt(parts[0]);
            String color = parts[1].toLowerCase();

            switch (color) {
                case "green":
                    game.setGreen(value);
                    break;
                case "blue":
                    game.setBlue(value);
                    break;
                case "red":
                    game.setRed(value);
                    break;
            }
        }

        return game;
    }

}