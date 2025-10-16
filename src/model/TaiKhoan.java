package main.model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String username;
    private String passwordHash;
    private String role;

    public TaiKhoan(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return username + " (" + role + ")";
    }
}
