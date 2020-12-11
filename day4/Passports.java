package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Passports {

    public static boolean isValidYearField(String field, String passport, int lRange, int rRange) {
        // Skimmed the data and it looks like this value is always 4 digits long.
        int index = passport.indexOf(field);
        String year = passport.substring(index + 4, index + 8);
        int yearNum = Integer.parseInt(year);
        
        return (yearNum < lRange || yearNum > rRange);
    }

    public static boolean isValidHeightField(String passport, String system, int lRange, int rRange) {
        int heightExtractor = 0;
        if(system.equals("cm")) {
            heightExtractor = 7;
        }
        else {
            heightExtractor = 6;
        }

        int index = passport.indexOf("hgt:");
        String height = passport.substring(index + 4, index + heightExtractor);
        int heightNum = 0;

        try {
            heightNum = Integer.parseInt(height);
        } catch (NumberFormatException e) {
            return false;
        }

        return heightNum < lRange || heightNum > rRange;
    }

    public static ArrayList<String> readData() {
        ArrayList<String> passports = new ArrayList<String>();
        try {
            File input = new File("day4/input.txt");
            Scanner in = new Scanner(input);
            while(in.hasNextLine()) {
                StringBuilder sb = new StringBuilder();
                while(in.hasNextLine()) {
                    String data = in.nextLine();
                    if(data.length() == 0) {
                        passports.add(sb.toString());
                        break;
                    }
                    else {
                       sb.append(data + " ");
                    }
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file is borked.");
            e.printStackTrace();
        }

        return passports;
    }

    public static void main(String[] args) {
        ArrayList<String> passports = readData();
        // I'm going to invalidate passports as I go, rather than validate them.
        int validPassports = passports.size() + 1;
        String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        // Part 1:
        /**for(int i = 0; i < passports.size(); i++) {
            for(int j = 0; j < required.length; j++) {
                if(!passports.get(i).contains(required[j])) {
                    validPassports--;
                    break;
                }
            }
        }**/

        // Part 2:
        // Completely nonfunctional! I give up on getting this nightmare to work. One star is good enough for me :~)
        for(int i = 0; i < passports.size(); i++) {
            for(int j = 0; j < required.length; j++) {
                if(!passports.get(i).contains(required[j])) {
                    validPassports--;
                    break;
                }

                if(required[j].equals("byr") && !isValidYearField("byr", passports.get(i), 1920, 2002)
                    || (required[j].equals("iyr") && !isValidYearField("iyr", passports.get(i), 2010, 2020)
                    || (required[j].equals("eyr") && !isValidYearField("eyr", passports.get(i), 2020, 2030)
                    || (required[j].equals("hgt") && passports.get(i).contains("cm") && !isValidHeightField(passports.get(i), "cm", 150, 193)
                    || (required[j].equals("hgt") && passports.get(i).contains("in") && !isValidHeightField(passports.get(i), "in", 59, 76)))))) {
                        validPassports--;
                        break;
                }
                else if(required[j].equals("hcl")) {
                    int index = passports.get(i).indexOf("hcl:");
                    String color = passports.get(i).substring(index + 4, index + 10);
                    if(Pattern.matches("#\\p{XDigit}{6}", color)) {
                        validPassports--;
                        break;
                    }
                }
                else if(required[j].equals("ecl")) {
                    int index = passports.get(i).indexOf("ecl:");
                    String color = passports.get(i).substring(index + 4, index + 7);
                    if(!(color.equals("amb") || color.equals("blu") || color.equals("brn") || color.equals("gry") || color.equals("grn") 
                        || color.equals("hzl") || color.equals("oth"))) {
                        validPassports--;
                        break;
                    }
                }
                else if(required[j].equals("pid")) {
                    int index = passports.get(i).indexOf("pid:");
                    String number = passports.get(i).substring(index + 4, index + 13);
                    if(!number.matches("\\d+")) {
                        validPassports--;
                        break;
                    }

                }

            }
        }

        System.out.println(validPassports);

    }
}
