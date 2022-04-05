package nz.ac.auckland.softeng281.a1;

import java.util.function.IntConsumer;

/**
 * University of Auckland
 * SOFTENG 281 - SEMESTER 1 - 2021
 * ASSIGNMENT 1 (A1)
 * <p>
 * Bank Account Class
 */
public class BankAccount {
    //TODO implement TASK 2
    // Declare class fields here
    double initialBalance;
    int transactionsLimit;
    int limitCount;

    /**
     * Construct a BackAccount instance with given initial balance and transactions limit
     *
     * @param initialBalance
     * @param transactionsLimit
     */
    public BankAccount(double initialBalance, int transactionsLimit) {
    	//TODO implement TASK 2
      this.initialBalance = initialBalance;
      this.transactionsLimit = transactionsLimit;
      limitCount = 0;
    }

    /**
     * Default constructor
     */
    public BankAccount() {
    	//TODO implement TASK 2
      initialBalance = 0.0;
      transactionsLimit = 10;
      limitCount = 0;
    }

    /**
     * returns the current balance
     *
     * @return
     */
    public double getBalance() {
        //TODO implement TASK 2
        return initialBalance;
    }

    /**
     * return the transaction limit associated to the account
     *
     * @return
     */
    public int getTransactionsLimit() {
        //TODO implement TASK 2
        return transactionsLimit;
    }
    
    /**
     * Add the given amount to the balance
     *
     * @param amount
     */
    public void deposit(double amount) {
        //TODO implement TASK 3
        if (limitCount == transactionsLimit){
          System.out.println("transactions limit reached");
        }else{
          limitCount++; //counts number of transactions 
          initialBalance += amount;
        }
    }

    /**
     * Subtract the given amount from balance
     *
     * @param amount
     */
    public void withdraw(double amount) {
        //TODO implement TASK 3
        if (limitCount == transactionsLimit){
          System.out.println("transactions limit reached");
        }else if(initialBalance >= amount){
          initialBalance -= amount;
          limitCount++;
        }else{
          System.out.println("amount exceeded");
        }
    }

    /**
     * Transfer the given amount to Account recipient
     *
     * @param amount
     * @param recipient
     */
    public void transferTo(double amount, BankAccount recipient) {
        //TODO implement  TASK 4
        if (limitCount >= transactionsLimit || recipient.limitCount >= recipient.transactionsLimit){
          System.out.println("transactions limit reached");
        }else if(initialBalance >= amount){
          recipient.initialBalance += amount;
          initialBalance -= amount;
          recipient.limitCount++; //increments negatively
        }else{
          System.out.println("amount exceeded");
        }

    }

}
