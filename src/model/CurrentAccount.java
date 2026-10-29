package model;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.contract.Premium;

public class CurrentAccount extends Account implements Premium {

private static final long serialVersionUID = 1L;
private final long overdraftLimit;

public CurrentAccount(String accountNumber, String accountHolder,
                      Customer customer, long balance, long overdraftLimit) {
    super(accountNumber, accountHolder, customer, balance);
    this.overdraftLimit = overdraftLimit;
}

@Override
public long getOverdraftLimit() {
    return overdraftLimit;
}

@Override
public double interestRate() {
    return 0;
}

@Override
public boolean canWithdraw(long amount) {
    return amount > 0 && balance + overdraftLimit >= amount;
}

@Override
public synchronized void withdraw(long amount)
        throws InsufficientFundsException, InvalidAmountException {

    if (!canWithdraw(amount)) {
        throw new InsufficientFundsException("Overdraft limit exceeded.",
                amount - (balance + overdraftLimit));
    }

    balance -= amount;
    transactions.add("Withdrew: " + amount + " | Balance: " + balance);
}
}
