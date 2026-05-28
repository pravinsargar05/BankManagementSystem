package com.bank.main;

import com.bank.dto.*;
import com.bank.service.*;
import com.bank.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class BankDriver {

    private static final BankService bankService = new BankService();
    private static final BranchService branchService = new BranchService();
    private static final CustomerService customerService = new CustomerService();
    private static final AccountService accountService = new AccountService();
    private static final LoanService loanService = new LoanService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Initializing Banking Management System...");
        
        do {
            System.out.println("\n--------------------------------");
            System.out.println("MAIN MENU:");
            System.out.println("1. Add Data");
            System.out.println("2. View Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Exit");
            System.out.println("--------------------------------");
            System.out.print("Enter choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddData();
                    break;
                case 2:
                    handleViewData();
                    break;
                case 3:
                    handleUpdateData();
                    break;
                case 4:
                    handleDeleteData();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting System. Thank U...!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (!exit);

        JpaUtil.shutdown();
    }

    private static void handleAddData() {
        System.out.println("\nADD MENU:");
        System.out.println("1. Add Bank");
        System.out.println("2. Add Branch");
        System.out.println("3. Add Customer");
        System.out.println("4. Add Account");
        System.out.println("5. Add Loan");
        System.out.print("Enter choice: ");

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Bank Name: ");
                    String bankName = scanner.nextLine();
                    System.out.print("Enter IFSC Code: ");
                    String ifscCode = scanner.nextLine();
                    Bank bank = new Bank(bankName, ifscCode);
                    bankService.save(bank);
                    System.out.println("Data added successfully");
                    break;
                case 2:
                    System.out.print("Enter Branch Name: ");
                    String branchName = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter Bank ID to associate: ");
                    Long bankId = Long.parseLong(scanner.nextLine());
                    Bank existingBank = bankService.findById(bankId);
                    if (existingBank != null) {
                        Branch branch = new Branch(branchName, city, existingBank);
                        branchService.save(branch);
                        System.out.println("Data added successfully");
                    } else {
                        System.out.println("Bank not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Customer Name: ");
                    String custName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Bank ID to associate: ");
                    Long cBankId = Long.parseLong(scanner.nextLine());
                    Bank cBank = bankService.findById(cBankId);
                    if (cBank != null) {
                        Customer customer = new Customer(custName, email, phone, cBank);
                        customerService.save(customer);
                        System.out.println("Data added successfully");
                    } else {
                        System.out.println("Bank not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Enter Account Type (e.g., SAVINGS): ");
                    String accType = scanner.nextLine();
                    System.out.print("Enter Balance: ");
                    Double balance = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Customer ID: ");
                    Long custId = Long.parseLong(scanner.nextLine());
                    Customer existingCust = customerService.findById(custId);
                    if (existingCust != null) {
                        Account account = new Account(accNum, accType, balance, existingCust);
                        accountService.save(account);
                        System.out.println("Data added successfully");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Loan Type (e.g., HOME): ");
                    String loanType = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    Double amount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Interest Rate: ");
                    Double intRate = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Customer ID: ");
                    Long lCustId = Long.parseLong(scanner.nextLine());
                    System.out.print("Enter Branch ID: ");
                    Long lBranchId = Long.parseLong(scanner.nextLine());
                    Customer lCust = customerService.findById(lCustId);
                    Branch lBranch = branchService.findById(lBranchId);
                    if (lCust != null && lBranch != null) {
                        Loan loan = new Loan(loanType, amount, intRate, lCust, lBranch);
                        loanService.save(loan);
                        System.out.println("Data added successfully");
                    } else {
                        System.out.println("Customer or Branch not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error adding data: " + e.getMessage());
        }
    }

    private static void printEntityMenu() {
        System.out.println("\nENTITY MENU:");
        System.out.println("1. Bank");
        System.out.println("2. Branch");
        System.out.println("3. Customer");
        System.out.println("4. Account");
        System.out.println("5. Loan");
        System.out.print("Enter choice: ");
    }

    private static void handleViewData() {
        printEntityMenu();
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        switch (choice) {
            case 1:
                List<Bank> banks = bankService.findAll();
                System.out.println("\n--- Banks ---");
                banks.forEach(b -> System.out.println("ID: " + b.getId() + " | Name: " + b.getName() + " | IFSC: " + b.getIfscCode()));
                break;
            case 2:
                List<Branch> branches = branchService.findAll();
                System.out.println("\n--- Branches ---");
                branches.forEach(b -> System.out.println("ID: " + b.getId() + " | Name: " + b.getBranchName() + " | City: " + b.getCity() + " | Bank ID: " + b.getBank().getId()));
                break;
            case 3:
                List<Customer> customers = customerService.findAll();
                System.out.println("\n--- Customers ---");
                customers.forEach(c -> System.out.println("ID: " + c.getId() + " | Name: " + c.getName() + " | Email: " + c.getEmail() + " | Phone: " + c.getPhone() + " | Bank ID: " + c.getBank().getId()));
                break;
            case 4:
                List<Account> accounts = accountService.findAll();
                System.out.println("\n--- Accounts ---");
                accounts.forEach(a -> System.out.println("ID: " + a.getId() + " | Acc No: " + a.getAccountNumber() + " | Type: " + a.getAccountType() + " | Balance: $" + a.getBalance() + " | Customer ID: " + a.getCustomer().getId()));
                break;
            case 5:
                List<Loan> loans = loanService.findAll();
                System.out.println("\n--- Loans ---");
                loans.forEach(l -> System.out.println("ID: " + l.getId() + " | Type: " + l.getLoanType() + " | Amount: $" + l.getAmount() + " | Interest: " + l.getInterestRate() + "% | Customer ID: " + l.getCustomer().getId() + " | Branch ID: " + l.getBranch().getId()));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void handleUpdateData() {
        printEntityMenu();
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.print("Enter ID of record to update: ");
        Long id = -1L;
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        try {
            switch (choice) {
                case 1:
                    Bank bank = bankService.findById(id);
                    if (bank != null) {
                        System.out.print("Enter new Bank Name (leave blank to keep '" + bank.getName() + "'): ");
                        String newName = scanner.nextLine();
                        if (!newName.trim().isEmpty()) bank.setName(newName);
                        
                        System.out.print("Enter new IFSC Code (leave blank to keep '" + bank.getIfscCode() + "'): ");
                        String newIfsc = scanner.nextLine();
                        if (!newIfsc.trim().isEmpty()) bank.setIfscCode(newIfsc);
                        
                        bankService.update(bank);
                        System.out.println("Data updated successfully");
                    } else {
                        System.out.println("Bank not found.");
                    }
                    break;
                case 2:
                    Branch branch = branchService.findById(id);
                    if (branch != null) {
                        System.out.print("Enter new Branch Name (leave blank to keep '" + branch.getBranchName() + "'): ");
                        String newName = scanner.nextLine();
                        if (!newName.trim().isEmpty()) branch.setBranchName(newName);
                        
                        System.out.print("Enter new City (leave blank to keep '" + branch.getCity() + "'): ");
                        String newCity = scanner.nextLine();
                        if (!newCity.trim().isEmpty()) branch.setCity(newCity);
                        
                        branchService.update(branch);
                        System.out.println("Data updated successfully");
                    } else {
                        System.out.println("Branch not found.");
                    }
                    break;
                case 3:
                    Customer customer = customerService.findById(id);
                    if (customer != null) {
                        System.out.print("Enter new Name (leave blank to keep '" + customer.getName() + "'): ");
                        String newName = scanner.nextLine();
                        if (!newName.trim().isEmpty()) customer.setName(newName);
                        
                        System.out.print("Enter new Phone (leave blank to keep '" + customer.getPhone() + "'): ");
                        String newPhone = scanner.nextLine();
                        if (!newPhone.trim().isEmpty()) customer.setPhone(newPhone);
                        
                        customerService.update(customer);
                        System.out.println("Data updated successfully");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 4:
                    Account account = accountService.findById(id);
                    if (account != null) {
                        System.out.print("Enter new Balance (leave blank to keep '" + account.getBalance() + "'): ");
                        String balStr = scanner.nextLine();
                        if (!balStr.trim().isEmpty()) account.setBalance(Double.parseDouble(balStr));
                        
                        accountService.update(account);
                        System.out.println("Data updated successfully");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    Loan loan = loanService.findById(id);
                    if (loan != null) {
                        System.out.print("Enter new Amount (leave blank to keep '" + loan.getAmount() + "'): ");
                        String amtStr = scanner.nextLine();
                        if (!amtStr.trim().isEmpty()) loan.setAmount(Double.parseDouble(amtStr));
                        
                        System.out.print("Enter new Interest Rate (leave blank to keep '" + loan.getInterestRate() + "'): ");
                        String rateStr = scanner.nextLine();
                        if (!rateStr.trim().isEmpty()) loan.setInterestRate(Double.parseDouble(rateStr));
                        
                        loanService.update(loan);
                        System.out.println("Data updated successfully");
                    } else {
                        System.out.println("Loan not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }

    private static void handleDeleteData() {
        printEntityMenu();
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.print("Enter ID: ");
        Long id = -1L;
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        try {
            switch (choice) {
                case 1:
                    bankService.delete(id);
                    System.out.println("Data deleted successfully");
                    break;
                case 2:
                    branchService.delete(id);
                    System.out.println("Data deleted successfully");
                    break;
                case 3:
                    customerService.delete(id);
                    System.out.println("Data deleted successfully");
                    break;
                case 4:
                    accountService.delete(id);
                    System.out.println("Data deleted successfully");
                    break;
                case 5:
                    loanService.delete(id);
                    System.out.println("Data deleted successfully");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting data: " + e.getMessage());
        }
    }
}
