package nz.ac.auckland.softeng281.a1;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

@RunWith(Suite.class) @SuiteClasses({
	BankAccountTestSuite.Task1Test.class,
  BankAccountTestSuite.Task2Test.class,
  BankAccountTestSuite.Task3Test.class,
  BankAccountTestSuite.Task4Test.class,
    //BankAccountTestSuite.YourTests.class
})

public class BankAccountTestSuite {

	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static class Task1Test {
        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator() + myOut.toString());
            }
        }


        @Test
        public void testWithdraw() {
            double newBalance = Basics.withdraw(100, 10, 1, 10);
            assertEquals(90.0, newBalance, 0.0000001);
            assertFalse("method withdraw should not print amount exceeded "
                    , myOut.toString().contains("amount exceeded"));
            assertFalse("method withdraw should not print transactions limit reached "
                    , myOut.toString().contains("transactions limit reached"));
        }

        @Test
        public void testDeposit() {
            double newBalance = Basics.deposit(100, 10, 1, 10);
            assertEquals(110.0, newBalance, 0.0000001);
            assertFalse("method deposit should not print amount exceeded "
                    , myOut.toString().contains("amount exceeded"));
            assertFalse("method deposit should not print transactions limit reached "
                    , myOut.toString().contains("transactions limit reached"));
        }

        @Test
        public void testWithdrawExceed() {
            double newBalance = Basics.withdraw(100, 1000000, 1, 10);
            assertEquals(100.0, newBalance, 0.0000001);
            assertTrue("method withdraw does not print \"amount exceeded\" when we attempt to withdraw more than the available balance"
                    , myOut.toString().contains("amount exceeded"));
        }


        @Test
        public void testWithdrawLimit() {
            double newBalance = Basics.withdraw(100, 1000000, 10, 10);
            assertTrue("method withdraw does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(100.0, newBalance, 0.0000001);
        }

        @Test
        public void tesDepositLimit() {
            double newBalance = Basics.deposit(100, 1000000, 10, 10);

            assertTrue("method deposit does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(100.0, newBalance, 0.0000001);
        }

    }

    public static class Task2Test {
        BankAccount account;

        @Before
        public void setUp() {
            account = new BankAccount(50.0, 10);
        }

        @Test
        public void testConstructor() {
            account = new BankAccount();
            assertEquals(0.0, account.getBalance(), 0.000001);
            assertEquals(10, account.getTransactionsLimit());
        }

        @Test
        public void testBalance() {
            assertEquals(50.0, account.getBalance(), 0.0000001);
        }

        @Test
        public void testTransactionLimit() {
            assertEquals(10, account.getTransactionsLimit());
        }

    }

    public static class Task3Test {
        BankAccount account;
        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
            account = new BankAccount(50.0, 3);
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator() + myOut.toString());
            }
        }


        @Test
        public void testWithdraw() {
            account.withdraw(10.5);
            assertEquals(39.5, account.getBalance(), 0.0000001);
            assertFalse("method withdraw should not print amount exceeded "
                    , myOut.toString().contains("amount exceeded"));
            assertFalse("method withdraw should not print transactions limit reached "
                    , myOut.toString().contains("transactions limit reached"));
        }

        @Test
        public void testDeposit() {
            account.deposit(7.1);
            assertEquals(57.1, account.getBalance(), 0.0000001);
            assertFalse("method deposit should not print amount exceeded "
                    , myOut.toString().contains("amount exceeded"));
            assertFalse("method deposit should not print transactions limit reached "
                    , myOut.toString().contains("transactions limit reached"));
        }

        @Test
        public void testWithdrawExceed() {
            account.withdraw(100000.0);
            assertEquals(50.0, account.getBalance(), 0.0000001);
            assertTrue("method withdraw does not print \"amount exceeded\" when we attempt to withdraw more than the available balance"
                    , myOut.toString().contains("amount exceeded"));
        }


        @Test
        public void testWithdrawLimit() {
            account.withdraw(10.5);
            account.withdraw(1);
            account.withdraw(1);
            account.withdraw(1);
            assertTrue("method withdraw does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(37.5, account.getBalance(), 0.0000001);
        }

        @Test
        public void tesDepositLimit() {
            account.withdraw(10.5);
            account.deposit(1);
            account.withdraw(1);
            account.deposit(1000000);
            assertTrue("method deposit does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(39.5, account.getBalance(), 0.0000001);
        }

    }


    public static class Task4Test {
        BankAccount account1;
        BankAccount account2;
        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
            account1 = new BankAccount(50.0, 1);
            account2 = new BankAccount(0.0, 3);
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator() + myOut.toString());
            }
        }

        @Test
        public void testTransferSuccess() {
            account1.transferTo(10.0, account2);
            assertEquals(40.0, account1.getBalance(), 0.0000001);
            assertEquals(10.0, account2.getBalance(), 0.0000001);
            assertFalse("method transferTo should not print amount exceeded "
                    , myOut.toString().contains("amount exceeded"));
            assertFalse("method transferTo should not print transactions limit reached "
                    , myOut.toString().contains("transactions limit reached"));
        }

        @Test
        public void testTransferExceed() {
            account1.transferTo(10000.0, account2);
            assertTrue("method transferTo does not print \"amount exceeded\" when we attempt to transfer more than the available balance"
                    , myOut.toString().contains("amount exceeded"));
            assertEquals(50.0, account1.getBalance(), 0.0000001);
            assertEquals(0.0, account2.getBalance(), 0.0000001);
        }

        @Test
        public void testTransferLimit() {
            account1.deposit(10);
            account1.transferTo(10.0, account2);
            assertTrue("method transferTo does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(60.0, account1.getBalance(), 0.0000001);
            assertEquals(0.0, account2.getBalance(), 0.0000001);
        }

        @Test
        public void testTransferLimitRecipient() {
            account2.deposit(10);
            account2.deposit(10);
            account2.deposit(10);
            account1.transferTo(10.0, account2);
            assertTrue("method transferTo does not print \"transactions limit reached\" when we exceed the transactions limit"
                    , myOut.toString().contains("transactions limit reached"));
            assertEquals(50.0, account1.getBalance(), 0.0000001);
            assertEquals(30.0, account2.getBalance(), 0.0000001);
        }

    }

    public static class YourTests {

        @Test
        public void test1() {
            assertTrue(true);
        }

    }


}

