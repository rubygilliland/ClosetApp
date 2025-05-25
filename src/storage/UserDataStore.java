package storage;

import model.User;

import java.io.*;
import java.util.Optional;

public class UserDataStore {
    private static final String DIR = "users/";

    public static void saveUser(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DIR + user.getUsername() + ".ser"))) {
            out.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<User> loadUser(String username) {
        File file = new File(DIR + username + ".ser");
        if (!file.exists()) return Optional.empty();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return Optional.of((User) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static boolean userExists(String username) {
        return new File(DIR + username + ".ser").exists();
    }
}

