package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// Got a lot of help for this solution from: https://www.reddit.com/r/adventofcode/comments/k5qsrk/2020_day_03_solutions/gepawrn

public class Tobbogan {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
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

        int foundTrees = 0;
        int position = 0;

        for(int i = 1; i < lines.size(); i++) {
            // Right 3 -- down 1 handled by loop
            position += 3;

            if(position > lines.get(i).length() - 1) {
                position = position - lines.get(i).length();
            }

            if(lines.get(i).charAt(position) == '#') {
                foundTrees++;
            }

        }

        System.out.println(foundTrees);
    }
}
