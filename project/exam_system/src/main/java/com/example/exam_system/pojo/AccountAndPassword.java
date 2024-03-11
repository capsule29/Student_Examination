package com.example.exam_system.pojo;

public class AccountAndPassword {
    private String account;
    private String password;

    @Override
    public String toString() {
        return "AccountAndPassword{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
