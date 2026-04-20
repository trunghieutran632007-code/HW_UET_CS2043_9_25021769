package Bai8_10.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    // Yeu cau 2: Cai dat so du ban dau la 500 truoc moi test
    @BeforeEach
    public void setUp() {
        // Khoi tao tai khoan voi so du 500
        account = new BankAccount("123456789", "Nguyen Van A", 500.0);
    }

    // ==========================================
    // TEST CASES CHO HÀM deposit(double amount)
    // ==========================================

    @Test
    public void testDeposit_BVA_ZeroAmount() {
        // Kiem tra bien amount = 0
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0.0);
        }, "Nap 0 dong phai nem ra ngoai le");
    }

    @Test
    public void testDeposit_EP_NegativeAmount() {
        // Kiem tra gia tri am
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-1.0);
        }, "Nap so tien am phai nem ra ngoai le");
    }

    @Test
    public void testDeposit_BVA_MinimumPositiveAmount() {
        // Kiem tra bien sat tren amount = 0.01
        account.deposit(0.01);
        assertEquals(500.01, account.getBalance(), "So du phai tang them 0.01");
    }

    @Test
    public void testDeposit_EP_NormalValidAmount() {
        // Kiem tra gia tri hop le binh thuong
        account.deposit(100.0);
        assertEquals(600.0, account.getBalance(), "So du phai la 600.0 sau khi nap 100.0");
    }

    // ==========================================
    // TEST CASES CHO HÀM withdraw(double amount)
    // ==========================================

    @Test
    public void testWithdraw_BVA_ZeroAmount() {
        // Kiem tra bien amount = 0
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.0);
        }, "Rut 0 dong phai nem ra ngoai le");
    }

    @Test
    public void testWithdraw_EP_NegativeAmount() {
        // Kiem tra gia tri am
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        }, "Rut so tien am phai nem ra ngoai le");
    }

    @Test
    public void testWithdraw_BVA_MinimumValidAmount() {
        // Kiem tra rut so tien nho nhat hop le
        boolean result = account.withdraw(0.01);
        assertTrue(result, "Rut tien phai thanh cong");
        assertEquals(499.99, account.getBalance(), "So du phai con lai 499.99");
    }

    @Test
    public void testWithdraw_BVA_ExactBalanceAmount() {
        // Kiem tra rut toan bo so du hien tai (bien 500)
        boolean result = account.withdraw(500.0);
        assertTrue(result, "Rut toan bo tien phai thanh cong");
        assertEquals(0.0, account.getBalance(), "So du phai ve 0.0");
    }

    @Test
    public void testWithdraw_BVA_ExceedBalanceAmount() {
        // Kiem tra rut vuot so du mot chut (bien 500.01)
        boolean result = account.withdraw(500.01);
        assertFalse(result, "Rut qua so du phai tra ve false");
        assertEquals(500.0, account.getBalance(), "So du phai duoc giu nguyen la 500.0");
    }

    @Test
    public void testWithdraw_EP_LargeExceedAmount() {
        // Kiem tra rut vuot qua xa so voi so du
        boolean result = account.withdraw(1000.0);
        assertFalse(result, "Rut qua nhieu tien phai tra ve false");
        assertEquals(500.0, account.getBalance(), "So du khong bi thay doi");
    }

    // ==========================================
    // TEST YÊU CẦU 3: KIỂM TRA TÍNH NHẤT QUÁN
    // ==========================================

    @Test
    public void testSequentialConsistency() {
        // So du ban dau la 0
        BankAccount seqAccount = new BankAccount("987654321", "Nguyen Van B", 0.0);
        assertEquals(0.0, seqAccount.getBalance(), "So du ban dau phai la 0");

        // Nap 500
        seqAccount.deposit(500.0);
        assertEquals(500.0, seqAccount.getBalance(), "So du sau khi nap phai la 500");

        // Rut 200 (thanh cong)
        boolean w1 = seqAccount.withdraw(200.0);
        assertTrue(w1, "Giao dich rut 200 phai thanh cong");
        assertEquals(300.0, seqAccount.getBalance(), "So du sau khi rut 200 phai la 300");

        // Rut 400 (that bai)
        boolean w2 = seqAccount.withdraw(400.0);
        assertFalse(w2, "Giao dich rut 400 phai that bai do thieu so du");

        // Kiem tra so du cuoi cung dung bang 300
        assertEquals(300.0, seqAccount.getBalance(), "So du cuoi cung phai duoc bao toan la 300");
    }
}