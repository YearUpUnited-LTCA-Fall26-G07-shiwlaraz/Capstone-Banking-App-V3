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
                                                    "1) Generate report of -- Month To Date -- \n" +
                                                    "2) Generate report of -- Previous Month -- \n" +
                                                    "3) Generate report of -- Year To Date -- \n" +
                                                    "4) Generate report of -- Previous Year --  \n" +
                                                    "5) Generate report by -- Search By The Vendor -- \n" +
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
                                        default:
                                            System.out.println("Invalid choice! Please try again.");
                                            break;
                                    }
                                } while (reportChoice != 0);

                            case "H":
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    } while(!ledgerChoice.equals("H"));
                case"X" :
                    break;
                default: System.out.println("Invalid choice! Please try again.");
                break;
            }
        } while (!menuChoice.equals("X"));
    }

}