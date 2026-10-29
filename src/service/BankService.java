package service;

import model.*;
import exception.*;
import util.Validator;
import util.StatementFormatter;
import persistence.TransactionLog;
import persistence.StatePersister;
import persistence.ReportGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BankService {
private final Repository<Account, String> repository = new Repository<>();
private final AtomicInteger counter = new AtomicInteger(1000);

public Account openAccount(String name, String email, String mobile,
                           String line, String city, String pin, long initial)
                           throws BankException {

    if (!Validator.isValidEmail(email) || !Validator.isValidMobile(mobile))
        throw new BankException("Invalid customer details");

    if (initial < 0)
        throw new InvalidAmountException("Invalid initial balance");

    String accNo = "ACC" + counter.incrementAndGet();

    Customer.Address address = new Customer.Address(line, city, pin);
    Customer customer = new Customer(name, email, mobile, address);

    Account account = new SavingsAccount(accNo, name, customer, initial, 4.0);

    repository.save(accNo, account);
    TransactionLog.log("Account opened: " + accNo);

    return account;
}

public Account getAccount(String accNo) throws AccountNotFoundException {
    return repository.findById(accNo)
            .orElseThrow(() -> new AccountNotFoundException("Account not found"));
}

public void deposit(String accNo, long amount) throws BankException {
    getAccount(accNo).deposit(amount);
    TransactionLog.log("Deposit: " + accNo + " Amount: " + amount);
}

public void withdraw(String accNo, long amount) throws BankException {
    getAccount(accNo).withdraw(amount);
    TransactionLog.log("Withdraw: " + accNo + " Amount: " + amount);
}

public void transfer(String from, String to, long amount) throws BankException {
    if (from.equals(to))
        throw new InvalidAmountException("Accounts must be different");

    getAccount(from).transferTo(getAccount(to), amount);
    TransactionLog.log("Transfer: " + from + " to " + to + " Amount: " + amount);
}

public String statement(String accNo) throws AccountNotFoundException {
    return StatementFormatter.format(getAccount(accNo));
}

public List<Account> listAccountsSortedByBalance() {
    List<Account> list = new ArrayList<>(repository.findAll());
    Collections.sort(list);
    return list;
}

public void saveState() {
    StatePersister.saveAccounts(new ArrayList<>(repository.findAll()));
}

public void loadState() {
    List<Account> accounts = StatePersister.loadAccounts();

    for (Account account : accounts) {
        repository.save(account.getAccountNumber(), account);
    }
}

public void generateDailyReport() {
    ReportGenerator.generateDailyReport(repository.findAll());
}
}
