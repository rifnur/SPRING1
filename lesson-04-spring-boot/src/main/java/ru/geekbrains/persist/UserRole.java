package ru.geekbrains.persist;

public class UserRole {

    private final String username;

    private final String roleName;

    public UserRole(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public String getRoleName() {
        return roleName;
    }
}
