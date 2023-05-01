package Model;

import java.util.ArrayList;

public class Account {
    private static int incrementer =1;
    private int accountNumber =0;
    private final String ifsc = "SBI06134";
    private final String name;
    private final String mobileNumber;
    private long balance;
    private final String password;
    private ArrayList<String> transaction;

    public Account(String username, String password, String mobileNumber, long balance){
        this.accountNumber = incrementer++;
        this.name = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        transaction = new ArrayList<>();
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }
    public ArrayList<String> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<String> transaction) {
        this.transaction = transaction;
    }
}
