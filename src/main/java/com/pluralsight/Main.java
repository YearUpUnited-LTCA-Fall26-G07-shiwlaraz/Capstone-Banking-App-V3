package com.pluralsight;

import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        showHomeScreen();

/*        ArrayList<Transaction> transactions = Transaction.readTransactions();
       for (Transaction t : transactions) {
            System.out.printf("  %s   |   %s   |   %s   |   %s   |  %.2f   |   %.2f%n   ",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount(),
                    t.getBalance());
        }*/
    }
    public static void showHomeScreen() {
        String mainMenuTexts = "=== Manu Options ===\n" +
                "D) Enter (D) To Add Deposit \n" +
                "P) Enter (P) Make Payment (Debit) \n" +
                "L) Enter (L) Ledger To Check Ledger \n" +
                "X) Enter (X) To Exit  \n" +
                "Enter Your Command: ";
        String menuChoice;
        do {
            menuChoice = Console.promptForString(mainMenuTexts).toUpperCase();
            switch (menuChoice) {
                case "D":
                    Transaction.addDeposit();
                    break;
                case "P":
                    Transaction.makePayment();
                    break;
                case "L" :
                    String ledgerChoice;
                    do{
                        ledgerChoice = Console.promptForString(
                                "=== Menu Options === \n" +
                                "A) All -- Get All Transactions \n" +
                                "D) Deposit -- Get all deposit transactions \n" +
                                "P) Payments -- Get all payment transactions\n" +
                                "R) Report -- Create a report \n" +
                                "H) To go back \n" +
                                "Enter Your Command: "
                        ).toUpperCase();
                        switch (ledgerChoice) {
                            case "A" :
                                Transaction.displayAllTransactions();
                                break;
                            case "D" :
                                Transaction.displayDeposits();
                                break;
                            case "P":
                                Transaction.displayPayments();
                                break;
                            case "R":
                                int reportChoice;
                                do {
                                    reportChoice = Console.promptForInt(
                                            "=== Reports Menu === \n" +
                                                    "1) To Generate report -- Month To Date -- Enter (1)  \n" +
                                                    "2) To Generate report -- Previous Month -- Enter (2)  \n" +
                                                    "3) To Generate report -- Year To Date -- Enter (3)  \n" +
                                                    "4) To Generate report -- Previous Year -- Enter (4) \n" +
                                                    "5) To Generate report -- Search By The Vendor -- Enter (5) \n" +
                                                    "0) Enter -- 0 -- Back \n" +
                                                    "Enter Your Command: ");
                                    switch (reportChoice) {
                                        case 1:
                                            Transaction.displayReportMonthToDate();
                                            break;
                                        case 2:
                                            Transaction.displayReportOfPreviousMonth();
                                            break;
                                        case 3:
                                            Transaction.displayReportYearToDate();
                                            break;
                                        case 4:
                                            Transaction.displayReportOfPreviousYear();
                                            break;
                                        case 5:
                                            Transaction.displayReportUsingVendorName();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid choice! Please try again.");
                                            break;
                                    }
                                } while (reportChoice != 0);
                                break;
                            case "H":
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while(!ledgerChoice.equals("H"));
                    break;
                case"X" :
                    break;
                default: System.out.println("Invalid choice! Please try again.");
                break;
            }
        } while (!menuChoice.equals("X"));
    }

}