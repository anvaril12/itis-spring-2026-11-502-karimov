import org.example.Main;
import org.example.Transaction;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void Test_solve1() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setAmount(100.0);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setAmount(200.0);
        transactions.add(t2);

        assertEquals(200.0, Main.solve1(transactions));
    }

    @Test
    void Test_solve2() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setAmount(100.0);
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setAmount(50.0);
        t2.setSuccess(true);
        transactions.add(t2);

        assertEquals(50.0, Main.solve2(transactions));
    }

    @Test
    void Test_solve3() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setSuccess(false);
        transactions.add(t2);

        assertEquals(50.0, Main.solve3(transactions));
    }

    @Test
    void Test_solve4() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdFrom(101);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdFrom(102);
        transactions.add(t2);

        Main.solve4(transactions);
    }

    @Test
    void Test_solve5() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setDenyReason("Fraud detected");
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setDenyReason("Not enough money");
        transactions.add(t2);

        Main.solve5(transactions);
    }

    @Test
    void Test_solve6() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdFrom(101);
        t1.setAmount(100.0);
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdFrom(101);
        t2.setAmount(50.0);
        t2.setSuccess(true);
        transactions.add(t2);

        Main.solve6(transactions);
    }

    @Test
    void Test_solve7() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdTo(201);
        t1.setAmount(100.0);
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdTo(201);
        t2.setAmount(50.0);
        t2.setSuccess(true);
        transactions.add(t2);

        Main.solve7(transactions);
    }

    @Test
    void Test_solve8() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setAmount(100.0);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setAmount(200.0);
        transactions.add(t2);

        Main.solve8(transactions);
    }

    @Test
    void Test_solve9() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdFrom(101);
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdFrom(101);
        t2.setSuccess(true);
        transactions.add(t2);

        Main.solve9(transactions);
    }

    @Test
    void Test_solve10() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setSuccess(true);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setSuccess(false);
        transactions.add(t2);

        Main.solve10(transactions);
    }

    @Test
    void Test_solve11() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdFrom(101);
        t1.setUserIdTo(201);
        t1.setAmount(100.0);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdFrom(101);
        t2.setUserIdTo(202);
        t2.setAmount(50.0);
        transactions.add(t2);

        Main.solve11(transactions);
    }

    @Test
    void Test_solve12() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdFrom(101);
        t1.setUserIdTo(201);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdFrom(101);
        t2.setUserIdTo(201);
        transactions.add(t2);

        Main.solve12(transactions);
    }

    @Test
    void Test_solve13() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setAmount(100.0);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setAmount(200.0);
        transactions.add(t2);

        Main.solve13(transactions);
    }

    @Test
    void Test_solve14() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setDenyReason("Fraud detected");
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setDenyReason("Fraud detected");
        transactions.add(t2);

        Transaction t3 = new Transaction();
        t3.setDenyReason("Not enough money");
        transactions.add(t3);

        Main.solve14(transactions);
    }

    @Test
    void Test_solve15() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction();
        t1.setUserIdTo(201);
        t1.setAmount(100.0);
        transactions.add(t1);

        Transaction t2 = new Transaction();
        t2.setUserIdTo(201);
        t2.setAmount(200.0);
        transactions.add(t2);

        Main.solve15(transactions);
    }

}