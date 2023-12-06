package com.aoc2023.Q5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Q5P2 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q5/input.txt";
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
        String[] seedsStrings = lines.get(0).split(":")[1].trim().split(" ");
        ArrayList<Long> seeds = new ArrayList<>();
        for (String seedString : seedsStrings) {
            seeds.add(Long.parseLong(seedString));
        }


        int i = 3;
        Pair<Integer, ArrayList<Almanac>> output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> seedToSoil = output.getSecond();

        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> soilToFertlizer = output.getSecond();

        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> fertilizerToWater = output.getSecond();

        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> waterToLight = output.getSecond();

        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> lightToTemp = output.getSecond();


        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> TempToHumidity = output.getSecond();

        output = convertInputToAlmanac(i, lines);
        i = output.getFirst();
        ArrayList<Almanac> humidityToLocation = output.getSecond();

        long ans = Long.MAX_VALUE;
        for (int j = 0; j < seeds.size(); j += 2) {
            long seedStart = seeds.get(j);
            long seedEnd = seedStart + seeds.get(j + 1);

            CalcAnsUsingThread thread = new CalcAnsUsingThread(j,
                    seedToSoil,
                    soilToFertlizer,
                    fertilizerToWater,
                    waterToLight,
                    lightToTemp,
                    TempToHumidity,
                    humidityToLocation,
                    seedStart,
                    seedEnd);

            thread.start();
        }


        return ans;

    }

    public static long getMapping(long source, ArrayList<Almanac> almanacs) {
        long destination = source;
        for (Almanac almanac : almanacs) {
            if (source >= almanac.getSourceStart() && source < almanac.getSourceStart() + almanac.getLength()) {
                destination = almanac.getDestinationStart() + (source - almanac.getSourceStart());
                break;
            }
        }
        return destination;
    }


    private static Pair<Integer, ArrayList<Almanac>> convertInputToAlmanac(int i, List<String> lines) {
        ArrayList<Almanac> output = new ArrayList<>();

        while (i < lines.size()) {
            String[] parts = lines.get(i).trim().split(" ");
            long destinationStart = Long.parseLong(parts[0]);
            long sourceStart = Long.parseLong(parts[1]);
            long length = Long.parseLong(parts[2]);
            Almanac almanac = new Almanac(destinationStart, sourceStart, length);
            output.add(almanac);
            i++;
            if (lines.get(i).isEmpty()) {
                i += 2;
                break;
            }
        }
        return new Pair<Integer, ArrayList<Almanac>>(i, output);
    }

    static class CalcAnsUsingThread extends Thread {
        int threadId;
        ArrayList<Almanac> seedToSoil;
        ArrayList<Almanac> soilToFertilizer;
        ArrayList<Almanac> fertilizerToWater;
        ArrayList<Almanac> waterToLight;
        ArrayList<Almanac> lightToTemperature;
        ArrayList<Almanac> temperatureToHumidity;
        ArrayList<Almanac> humidityToLocation;

        Long seedStart;

        Long seedEnd;

        public CalcAnsUsingThread(int threadId,
                                  ArrayList<Almanac> seedToSoil,
                                  ArrayList<Almanac> soilToFertilizer,
                                  ArrayList<Almanac> fertilizerToWater,
                                  ArrayList<Almanac> waterToLight,
                                  ArrayList<Almanac> lightToTemperature,
                                  ArrayList<Almanac> temperatureToHumidity,
                                  ArrayList<Almanac> humidityToLocation,
                                  Long seedStart,
                                  Long seedEnd) {
            this.threadId = threadId;
            this.seedToSoil = seedToSoil;
            this.soilToFertilizer = soilToFertilizer;
            this.fertilizerToWater = fertilizerToWater;
            this.waterToLight = waterToLight;
            this.lightToTemperature = lightToTemperature;
            this.temperatureToHumidity = temperatureToHumidity;
            this.humidityToLocation = humidityToLocation;
            this.seedStart = seedStart;
            this.seedEnd = seedEnd;

        }

        public void run() {
            long ans = Long.MAX_VALUE;

            for (long k = seedStart; k < seedEnd; k++) {
                long seedToSoilAns = getMapping(k, seedToSoil);
                long soilToFertlizerAns = getMapping(seedToSoilAns, soilToFertilizer);
                long fertilizerToWaterAns = getMapping(soilToFertlizerAns, fertilizerToWater);
                long waterToLightAns = getMapping(fertilizerToWaterAns, waterToLight);
                long lightToTempAns = getMapping(waterToLightAns, lightToTemperature);
                long TempToHumidityAns = getMapping(lightToTempAns, temperatureToHumidity);
                long humidityToLocationAns = getMapping(TempToHumidityAns, humidityToLocation);

                ans = Math.min(ans, humidityToLocationAns);
            }
            System.out.println("ans -> " + ans);
        }
    }


}