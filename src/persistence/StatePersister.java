package persistence;

import model.Account;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class StatePersister {
private static final String DATA_FILE = "data/accounts.ser";

private StatePersister() {}

public static void saveAccounts(List<Account> accounts) {
    try {
        Files.createDirectories(Paths.get("data"));

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            out.writeObject(new ArrayList<>(accounts));
        }
    } catch (IOException e) {
        System.err.println("Save Error: " + e.getMessage());
    }
}

@SuppressWarnings("unchecked")
public static List<Account> loadAccounts() {
    try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream(DATA_FILE))) {
        return (List<Account>) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
        return new ArrayList<>();
    }
}
}
