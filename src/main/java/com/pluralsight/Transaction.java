package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;
    private double balance;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor,
                       double amount, double balance) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.balance = balance;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setTime(LocalTime Time){
        this.time = time;
    }
    public void setDescription(String Description) {
        this.description = description;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public void setAmount(double amount) {
        this.balance = balance;
    }
    public LocalDate setDate(){
        return date;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public String getDescription() {
        return description;
    }
    public String getVendor() {
        return vendor;
    }
    public double getAmount() {
        return amount;
    }
    public double getBalance() {
        return balance;
    }

    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fr1 = new FileReader("transactions.csv");
            BufferedReader br1 = new BufferedReader(fr1);

            String Line;
            br1.readLine();

            while((Line = br1.readLine()) != null) {
                if (Line.isBlank()) {
                    continue;
                }
                String[] split = Line.split("[|]");

                if (split.length < 6 || split[0].trim().isEmpty()) {
                    System.out.println("Skipping invalid row: " + Line);
                    continue;
                }

                LocalDate date = LocalDate.parse(split[0].trim());
                LocalTime time = LocalTime.parse(split[1].trim());
                String description = split[2].trim();
                String vendor = split[3].trim();
                double amount = Double.parseDouble(split[4].trim());
                double balance = Double.parseDouble(split[5].trim());

                Transaction transaction = new Transaction(date, time, description, vendor, amount, balance);
                transactions.add(transaction);
            }

        } catch (IOException e) {
            System.out.println("Error Finding the file" + e.getMessage());
        }
        return transactions;
    }
    public static double getLastBalance() {
        double balance = 0.0;
        try {
            FileReader fr2 = new FileReader("transactions.csv");
            BufferedReader br2 = new BufferedReader(fr2);

            String Line;
            br2.readLine();

            while ((Line = br2.readLine()) != null) {
                if (!Line.isBlank()) {
                    String[] split = Line.split("[|]");

                    if (split.length == 6) {
                        balance = Double.parseDouble(split[5]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading The File" + e.getMessage());
        }return balance;
    }

    public static void writeTransactions(Transaction transaction) {
        try (
            FileWriter fw1 = new FileWriter("transactions.csv", true)) {
            fw1.write(LocalDate.now() + " | " + LocalTime.now().withNano(0) + " | " + transaction.getDescription() +
                    " | " + transaction.getVendor() + " | " + transaction.getAmount() + " | " +
                    transaction.getBalance() + System.lineSeparator());
            System.out.println("Transaction saved successfully");

        } catch (IOException e) {
            System.out.println("Error Writing file" + e.getMessage());

        }
    }
    public static void addDeposit() {
        ArrayList<Transaction> transactions = readTransactions();

        String description = Console.promptForString("Enter The Description");
        String vendor = Console.promptForString("Enter the name of the vendor");
        double amount = Console.promptForDouble("enter the deposit amount");
        double balance = getLastBalance();
        if (amount < 0 ) {
            System.out.println("Amount is valid, Please enter the amount again");
        } else {
            Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount, balance + amount);
            writeTransactions(transaction);
        }
    }public static void makePayment() {
        String description = Console.promptForString("Enter the Description");
        String vendor = Console.promptForString("Enter the name of vendor");
        double amount = Console.promptForDouble("Enter the amount in decimal value");
        double balance = getLastBalance();
        if (amount < 0) {
            System.out.println("Enter a valid amount, amount should not be less than 0");
        } else if ( amount > balance) {
            System.out.println("Amount Exceeded the Balance, Transaction cannot be completed");
        } else {
            Transaction transaction = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount, balance - amount);
            writeTransactions(transaction);
        }

    }
    public static void displayAllTransactions () {
        ArrayList<Transaction> transactions = readTransactions();
        for (Transaction t : transactions) {
            System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount(),
                    t.getBalance());
        }
    }
    public static void displayDeposits() {
        ArrayList<Transaction> transactions = readTransactions();

        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }
    public static void displayPayments() {
        ArrayList<Transaction> transactions = readTransactions();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }
    public static void displayReportMonthToDate() {
        ArrayList<Transaction> transactions = readTransactions();
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }
    public static void displayReportOfPreviousMonth() {
        ArrayList<Transaction> transactions = readTransactions();
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withDayOfMonth(1).minusDays(1);



        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }
    public static void displayReportYearToDate() {
        ArrayList<Transaction> transactions = readTransactions();


        LocalDate startDate = LocalDate.now().withMonth(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }

    public static void displayReportOfPreviousYear() {
        ArrayList<Transaction> transactions = readTransactions();

        LocalDate startDate = LocalDate.now().minusYears(1).withMonth(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withMonth(1).minusDays(1);

        for (Transaction t : transactions) {
            LocalDate date = t.getDate();
            if(!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }

    }
    public static void displayReportUsingVendorName() {
        ArrayList<Transaction> transactions = readTransactions();
        String vendorName = Console.promptForString("Enter the name of the vendor");


        for (Transaction t : transactions) {
            String name = t.getVendor();
            if (t.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.printf( "%-12s | %-10s | %-20s | %-18s | %12.2f | %12.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount(),
                        t.getBalance());
            }
        }
    }
}

