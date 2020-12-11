package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Got a lot of help for this solution from: https://www.reddit.com/r/adventofcode/comments/k5qsrk/2020_day_03_solutions/gepawrn

public class TobboganPartTwo {

    public static int findTrees(int right, int down, List<String> lines) {
        int foundTrees = 0;
        int position = 0;

        for(int i = down; i < lines.size(); i += down) {
            // Right 3 -- down 1 handled by loop
            position += right;

            if(position > lines.get(i).length() - 1) {
                position = position - lines.get(i).length();
            }

            if(lines.get(i).charAt(position) == '#') {
                foundTrees++;
            }

        }

        return foundTrees;
    }
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        try {
            File input = new File("day3/input.txt");
            Scanner in = new Scanner(input);
            while(in.hasNextLine()) {
                String data = in.nextLine();
                lines.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file is borked.");
            e.printStackTrace();
        }

        int[] rights = {1, 3, 5, 7, 1};
        int[] downs = {1, 1, 1, 1, 2};
        int[] counts = new int[5];

        for(int i = 0; i < rights.length; i++) {
            int trees = findTrees(rights[i], downs[i], lines);
            counts[i] = trees;
        }

        int product = 1;

        for(int j = 0; j < counts.length; j++) {
            System.out.println(counts[j]);
            product *= counts[j];
        }

        System.out.println(product);

    }
}
