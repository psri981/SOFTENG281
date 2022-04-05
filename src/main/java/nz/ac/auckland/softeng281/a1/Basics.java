package nz.ac.auckland.softeng281.a1;

import java.util.Iterator;

public class Basics {

    /**
     * deposit operation
     *
     * @param currentBalance
     * @param amount
     * @param numOfTransactions
     * @param transactionsLimit
     * @return the new balance
     */
    public static double deposit(double currentBalance, double amount, int numOfTransactions, int transactionsLimit) {
        //TODO implement TASK 1
        if (numOfTransactions >= transactionsLimit){
          System.out.println("transactions limit reached");
          return currentBalance;
        } else{
          currentBalance = currentBalance + amount;
          return currentBalance;
        }
    }

    /**
     * withdraw operation
     *
     * @param currentBalance
     * @param amount
     * @param numOfTransactions
     * @param transactionsLimit
     * @return the new balance
     */
    public static double withdraw(double currentBalance, double amount, int numOfTransactions, int transactionsLimit) {
        //TODO implement TASK 1
        if (numOfTransactions >= transactionsLimit){
          System.out.println("transactions limit reached");
          return currentBalance;
        } else if(currentBalance > amount){
          currentBalance = currentBalance - amount;
          return currentBalance;
        }else{
          System.out.println("amount exceeded");
          return currentBalance;
        }

    }

}
