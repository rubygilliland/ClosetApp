package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private final String username;
    private final String passwordHash;
    private final String salt;
    private final ClosetManager closet;

    public User(String username, String passwordHash, String salt) {
        this.username = Objects.requireNonNull(username);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.salt = Objects.requireNonNull(salt);
        this.closet = new ClosetManager();
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public ClosetManager getCloset() {
        return closet;
    }
}

