package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ExpenseReport {
    public static void main(String[] args) {
        // Read everything in from the file, and stick it into an arraylist.
        ArrayList<Integer> expenses = new ArrayList<Integer>();
        try {
            File input = new File("day1/input.txt");
            Scanner in = new Scanner(input);
            while(in.hasNextLine()) {
                String data = in.nextLine();
                int castData = Integer.parseInt(data);
                expenses.add(castData);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file is borked.");
            e.printStackTrace();
        }
        // Part 1
        // Search for those two special numbers.
       /** 
        for(int i = 0; i < expenses.size(); i++) {
            for(int j = 1; j < expenses.size(); j++) {
                if(expenses.get(i) + expenses.get(j) == 2020) {
                    int result = expenses.get(i) * expenses.get(j);
                    System.out.println(result);
                    break;
                }
            }
        }
        **/

        // Part 2
        // Search for those three special numbers.
        for(int i = 0; i < expenses.size(); i++) {
            for(int j = 1; j < expenses.size(); j++) {
                for(int k = 2; k < expenses.size(); k++) {
                    // Never seen such an ugly triple loop before!
                    if(expenses.get(i) + expenses.get(j) + expenses.get(k) == 2020) {
                        long result = expenses.get(i) * expenses.get(j) * expenses.get(k);
                        System.out.println(result);
                        break;
                    }
                }
            }
        }


    }
}