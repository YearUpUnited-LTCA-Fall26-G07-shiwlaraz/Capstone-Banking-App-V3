package com.pluralsight;

import java.util.Scanner;

public class Console {
    public static final Scanner myScanner = new Scanner(System.in);

    public Console () {

    }
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        return myScanner.nextLine();
    }
    public static int promptForInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int result = myScanner.nextInt();
                myScanner.nextLine();
                return result;
            } catch (Exception var3) {
                System.out.print("Invalid input, Please enter a number");
                myScanner.nextLine();
            }
        }
    }public static double promptForDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                double result = myScanner.nextDouble();
                myScanner.nextLine();
                return result;
            } catch (Exception var3) {
                System.out.print("Invalid input, Please enter amount with decimal value");
                myScanner.nextLine();
            }
        }
    }

}
