package View;

import Model.Account;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewClass {
    Scanner input = new Scanner(System.in);
    Map<Integer,Account> accounts = new HashMap<>();
    public void user(){
        while(true) {
            System.out.println("\n******user page ******");
            System.out.println("\n\t1. New User\n\t2.Existing user\n\t3.exit");
            switch (Validation.inputInt()) {
                case 1: {
                    createAccount();
                    break;
                }

                case 2: {
                    login();
                    break;
                }

                case 3: {
                    System.out.println("Thank you !!!");
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid Option");
            }
        }

    }

    public void createAccount(){
        System.out.print("Enter username : ");
        String username = input.next();
        String password;
        System.out.print("\nEnter password : ");
        password = input.next();
        while(true) {
            System.out.print("Confirm password : ");
            String rePassword = input.next();

            if(password.equals(rePassword))
                break;
            System.out.println("Invalid password : ");
        }
        password = encryptPassword(password);
        String mobileNumber = Validation.isValidMobileNumber();
        long amount =10000 ;

        Account account = new Account(username,password,mobileNumber,amount);
        accounts.put(account.getAccountNumber(),account);
        System.out.println("\nAccount created Successfully");
        account.getTransaction().add(String.format("|%-15s|%-10d|%-10d|","Opening", 10000,10000));
        System.out.println("Account number : "+ account.getAccountNumber());
        System.out.println("Balance : "+account.getBalance());

    }

    public String encryptPassword(String password){
        String encrypted = "";

        for(char character:password.toCharArray()){
            if(Character.isAlphabetic(character)){
                if(character == 'Z')
                    encrypted =encrypted+"A";
                else if (character == 'z')
                    encrypted =encrypted+"a";
                else
                    encrypted = encrypted+""+(char)(character+1);
            }
            else {
                if(character == '9')
                    encrypted =encrypted+"0";
                else
                    encrypted=encrypted+""+(char)(character+1);
            }
        }
        System.out.println(encrypted);
        return encrypted;
    }

    public void login(){
        System.out.println("\n***** login *****\n");
        int accountNumber = Validation.validInt("Account Number");
        boolean flag = false;
        if(accounts.containsKey(accountNumber)){
            Account account = accounts.get(accountNumber);
            System.out.print("Enter Password : ");
            String password = input.next();
            password = encryptPassword(password);
            if(password.equals(account.getPassword())){
                System.out.println("\nLogin Successfully");
                options(account);
            }else
                System.out.println("Invalid password");
        }
        else
            System.out.println("Invalid Account");
    }

    public void options(Account account){

        while (true) {
            System.out.println("****** OPTIONS ******");
            System.out.println("\n\n1.ATM WITHDRAW\n2.Cash Deposit\n3.Account Transfer\n" +
                    "4.Transaction History\n5.Back\n6.Exit");

            switch (Validation.inputInt()) {
                case 1: {
                    withdraw(account);
                    break;
                }

                case 2: {
                    deposit(account);
                    break;
                }

                case 3: {
                    transfer(account);
                    break;
                }

                case 4: {
                    transactionHistory(account);
                    break;
                }

                case 5:return;

                case 6: {
                    System.out.println("Thank you!!!");
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid option : ");
            }
        }
    }

    public void withdraw(Account account){
        long amount;
        while (true){
            amount = Validation.validLong("Amount");
            long balance = account.getBalance();
            if(balance-amount>=1000){
                balance = balance-amount;
                System.out.println("Available Balance "+balance);
                account.setBalance(balance);
                System.out.println("Withdraw successfully : ");
                String transaction = String.format("|%-15s|%-10d|%-10d|","ATM Withdraw ",amount,(balance));
                account.getTransaction().add(transaction);
                break;
            }
            System.out.println("Insufficient balance : ");
        }
    }

    public void deposit(Account account){
        long amount=Validation.validLong("Amount");
        long balance = account.getBalance();
        account.setBalance(balance+amount);
        String transaction = String.format("|%-15s|%-10d|%-10d|","Cash Deposit ",amount,(balance+amount));
        account.getTransaction().add(transaction);
        System.out.println("Available Balance "+(balance+amount));
    }

    public void transfer(Account account){
        int accountNUmber = Validation.validInt("Account Number to Transfer ");
        if(account.getAccountNumber() == accountNUmber || !accounts.containsKey(accountNUmber)){
            System.out.println("Invalid Account Number !!!");
            return;
        }
        long amount = Validation.validLong("Amount");
        long balance = account.getBalance();
        if(balance-amount<=1000){
            System.out.println("Insufficient balance !!!");
            return;
        }
        Account account1 = accounts.get(accountNUmber);
        account1.setBalance(account1.getBalance()+amount);
        account.setBalance(account.getBalance()-amount);
        String trans1 = String.format("|%-15s|%-10d|%-10d|","TransferTo-("+ accountNUmber+")",amount,account.getBalance());
        String trans2 = String.format("|%-15s|%-10d|%-10d|","TransferFrom-("+ account.getAccountNumber()+")",amount,account1.getBalance());
        account.getTransaction().add(trans1);
        account1.getTransaction().add(trans2);
        System.out.println("Transaction successfully ");
        System.out.println("Available Balance : "+account.getBalance());
    }

    public void transactionHistory(Account account){
        System.out.println("+---------------+----------+----------+");
        System.out.printf("|%-15s|%-10s|%-10s|\n","Type","Amount","Balance");
        System.out.println("+---------------+----------+----------+");
        for(String transaction:account.getTransaction()){
            System.out.println(transaction);
        }
        System.out.println("+---------------+----------+----------+");
    }
}
