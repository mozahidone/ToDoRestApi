package com.mozahidone.backend;

import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    private Bank mockedBank;
    private User testUser;
    private ATM atm;

    @BeforeEach
    void setUp() {
        mockedBank = Mockito.mock(Bank.class);
        testUser = new User("1234567890", "1234", 1000.0);
        atm = new ATM(mockedBank);
    }

    @Test
    void testInsertCard() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        assertEquals(testUser, atm.getCurrentUser());
    }

    @Test
    void testEnterCorrectPin() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        assertTrue(atm.enterPin(testUser.getPin()));
        assertEquals(0, testUser.getWrongAttempts());
    }

    @Test
    void testEnterWrongPinLessThan3Times() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        assertFalse(atm.enterPin("0000"));
        assertEquals(1, testUser.getWrongAttempts());
        verify(mockedBank).updateUser(testUser);
    }

    @Test
    void testEnterWrongPin3Times() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        assertFalse(atm.enterPin("0000"));
        assertFalse(atm.enterPin("0000"));
        assertFalse(atm.enterPin("0000"));
        assertTrue(testUser.isLocked());
        verify(mockedBank).updateUser(testUser);
    }

    @Test
    void testCheckCardLocked() {
        testUser.setLocked(true);
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        assertFalse(atm.insertCard(testUser.getCardId()));
        assertNull(atm.getCurrentUser());
    }

    @Test
    void testCheckBalance() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        double balance = atm.checkBalance();
        assertEquals(testUser.getBalance(), balance);
        verify(mockedBank).getUser(testUser.getCardId());
    }

    @Test
    void testDeposit() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        double amount = 500.0;
        atm.deposit(amount);
        assertEquals(testUser.getBalance(), 1500.0);
        verify(mockedBank).deposit(testUser, amount);
    }

    @Test
    void testWithdrawEnoughMoney() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        double amount = 500.0;
        assertTrue(atm.withdraw(amount));
        assertEquals(testUser.getBalance(), 500.0);
        verify(mockedBank).withdraw(testUser, amount);
    }

    @Test
    void testWithdrawNotEnoughMoney() {
        when(mockedBank.getUser(testUser.getCardId())).thenReturn(testUser);
        atm.insertCard(testUser.getCardId());
        double amount = 2000.0;
        assertFalse(atm.withdraw(amount));
        assertEquals(testUser.getBalance(), 1000.0);
        verify(mockedBank, never()).withdraw(testUser, amount);
    }

    @Test
    void testEndProcess() {
        atm.endProcess();
        assertNull(atm.getCurrentUser());
    }

    @Test
    void testGetBankName() {
        String bankName = "Mocked Bank";
        when(mockedBank.getBankName()).thenReturn(bankName);
        assertEquals(bankName, ATM.getBankName());
        verifyStatic(Bank.class);
        Bank.getBankName();
    }
}
