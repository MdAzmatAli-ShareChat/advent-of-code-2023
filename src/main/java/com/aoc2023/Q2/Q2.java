package com.aoc2023.Q2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//only 12 red cubes, 13 green cubes, and 14 blue cubes?


class Game {
    public long green;
    public long blue;
    public long red;

    // getters and setters (or you can make fields public if you prefer)

    @Override
    public String toString() {
        return "Game{" +
                "green=" + green +
                ", blue=" + blue +
                ", red=" + red +
                '}';
    }
}

public class Q2 {
    private int MAX_RED = 12;
    private int MAX_GREEN = 13;
    private int MAX_BLUE = 14;

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
            boolean possible = true;
            for (Game game : games) {
                red = Math.max(red, game.red);
                blue = Math.max(blue, game.blue);
                green = Math.max(green, game.green);
            }
            ans+= (red * blue * green);

        }
        return ans;
    }

    private static List<Game> parseInput(String input) {
        String[] gameStrings = input.split(":");
        String[] a = gameStrings[1].split(";");

        // Use a List to store the parsed Game objects
        List<Game> games = new ArrayList<>();

        for (String gameString : a) {
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
            String color = parts[1].toLowerCase(); // convert color to lowercase for case-insensitive matching

            switch (color) {
                case "green":
                    game.green = (value);
                    break;
                case "blue":
                    game.blue = (value);
                    break;
                case "red":
                    game.red = (value);
                    break;
                // handle other colors if needed
            }
        }

        return game;
    }

}