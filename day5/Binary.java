package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Binary {
    public static ArrayList<String> readData() {
        ArrayList<String> passes = new ArrayList<String>();
        try {
            File input = new File("day5/input.txt");
            Scanner in = new Scanner(input);
            while(in.hasNextLine()) {
                String data = in.nextLine();
                passes.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file is borked.");
            e.printStackTrace();
        }

        return passes;
    }

    public static void main(String[] args) {
        ArrayList<String> passes = readData();
        ArrayList<Integer> seats = new ArrayList<Integer>();
        int max = 0;

        for(int i = 0; i < passes.size(); i++) {
            String toBinary = passes.get(i).replaceAll("[FL]", "0").replaceAll("[BR]", "1");
            int binaryNum = Integer.parseInt(toBinary, 2);
            seats.add(binaryNum);
        }

        for(int j = 0; j < seats.size(); j++) {
            max = Math.max(max, seats.get(j));
        }

        System.out.println(max);

        // Part 2:
        while(seats.contains(max)) {
            max--;
        }

        System.out.println(max);
    }
}
