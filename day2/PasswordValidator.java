package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class PasswordValidator {

    public static int getParam(String password, String param) {
        int hyphen = password.indexOf("-");
        int colon = password.indexOf(":");
        String output = "";

        if(param.equals("min")) {
            output = password.substring(0, hyphen);
        }
        else if(param.equals("max")) {
            output = password.substring(hyphen + 1, colon - 2);
        }

        return Integer.parseInt(output);
    }

    public static char getTarget(String password) {
        int colon = password.indexOf(":");
        return password.charAt(colon - 1);
    }

    public static String getPasswordProper(String password) {
        int colon = password.indexOf(":");
        return password.substring(colon + 2, password.length());
    }

    public static void main(String[] args) {
        // Read everything in from the file, and stick it into an arraylist.
        ArrayList<String> passwords = new ArrayList<String>();
        try {
            File input = new File("day2/input.txt");
            Scanner in = new Scanner(input);
            while(in.hasNextLine()) {
                String data = in.nextLine();
                passwords.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file is borked.");
            e.printStackTrace();
        }

        // Initialize global counter.
        int countValids = 0;
        for(String password : passwords) {
            // First, parse out all of the parameters that we need. 
            int maxVal = getParam(password, "max");
            int minVal = getParam(password, "min");
            char target = getTarget(password);
            String passwordP = getPasswordProper(password);

            // Convert the password into an array of characters.
            char[] passwordArr = passwordP.toCharArray();
            int counter = 0;
            for(char c : passwordArr) {
                if(c == target) {
                    counter++;
                }
            }

            // Check that it meets the minimum at least.
            if(counter >= minVal && counter <= maxVal) {
                countValids++;
            }

        }

        System.out.println(countValids);
    }
}
