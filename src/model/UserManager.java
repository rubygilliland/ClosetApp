package model;

import security.PasswordUtil;
import storage.UserDataStore;

import java.util.Optional;

public class UserManager {
    private User currentUser = null;

    public boolean register(String username, String password) {
        if (UserDataStore.userExists(username)) return false;

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);
        User user = new User(username, hash, salt);
        UserDataStore.saveUser(user);
        return true;
    }

    public boolean login(String username, String password) {
        Optional<User> user = UserDataStore.loadUser(username);
        if (user.isPresent()) {
            String salt = user.get().getSalt();
            String hash = user.get().getPasswordHash();
            if (PasswordUtil.verifyPassword(password, salt, hash)) {
                currentUser = user.get();
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

