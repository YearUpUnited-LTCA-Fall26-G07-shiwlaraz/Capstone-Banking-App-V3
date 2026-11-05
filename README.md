# Capstone-Banking-App-V3

Capstone 1 | Java CLI Application

## About
This is a simple Java application for tracking deposits and payments. It saves transactions in a `transactions.csv` 
file and lets users view their ledger and reports. 

## Features
- Add a deposit.
- Make a payment.
- View all transactions, deposits, or payments.
- Display newest transactions first.
- View Month to Date, Previous Month, Year to Date, and Previous Year reports.
- Search transactions by vendor.
- Return to previous menus or exit the application.

## Project Files
- `Main.java` — Displays the menus and handles user choices.
- `Console.java` — Reads text and number inputs from the user.
- `Transaction.java` — Reads, saves, sorts, and displays transactions.
- `transactions.csv` — Stores transaction information.

## Main Methods
- `showHomeScreen()` — Shows the main menu.
- `addDeposit()` — Adds a deposit and updates the balance.
- `makePayment()` — Records a payment and updates the balance.
- `readTransactions()` — Loads transactions from the CSV file.
- `writeTransactions()` — Saves a new transaction to the CSV file.
- `getLastBalance()` — Reads the latest saved balance.
- `displayAllTransactions()` — Shows all transactions.
- `displayDeposits()` — Shows deposits only.
- `displayPayments()` — Shows payments only.
- `displayReportMonthToDate()` — Shows this month's transactions.
- `displayReportOfPreviousMonth()` — Shows last month's transactions.
- `displayReportYearToDate()` — Shows this year's transactions.
- `displayReportOfPreviousYear()` — Shows last year's transactions.
- `displayReportUsingVendorName()` — Finds transactions by vendor.
- `sortTransactions()` — Sorts transactions from newest to oldest.

## CSV Format
`date|time|description|vendor|amount|Balance`

The `Balance` column is an extra feature used to track the account balance after each transaction.

## How to Run
Open the project in IntelliJ IDEA, make sure `transactions.csv` is in the project's working directory, and run `Main.java`.