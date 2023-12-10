package com.aoc2023.Q9;


import com.aoc2023.Q5.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Q9P2 {

    public static void main(String[] args) {
        String filePath = "/Users/mdazmat/advent-of-code/src/main/java/com/aoc2023/Q9/input.txt";
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
        for(String line: lines){
            String lineWithoutExtras = line.replaceAll("\\s+", " ").trim();
            String[] parts = lineWithoutExtras.split(" ");
            ArrayList<Long> list = new ArrayList<>();
            for(String part: parts){
                list.add(Long.parseLong(part));
            }

            ans += calcAns(list).getSecond();
        }

        return ans;
    }

    private static Pair<Long,Long> calcAns(ArrayList<Long> list){
        long ans = 0;
        boolean stop = true;
        for(Long i: list){
            if(i != 0){
                stop = false;
                break;

            }
        }
        if(stop){
            return new Pair<Long,Long>(0L,0L);
        }

        ArrayList<Long> reduced = new ArrayList<>();
        for(int i=0; i<list.size()-1; i++){
            reduced.add(list.get(i+1)-list.get(i));
        }
        Pair<Long,Long> resp = calcAns(reduced);
        long partAns = list.get(0) - resp.getSecond();
        return new Pair<Long,Long>(ans + resp.getFirst() + partAns, partAns);
    }

}