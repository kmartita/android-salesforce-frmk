package io.omni.example.models;

import io.qameta.allure.Step;

import static java.lang.String.format;

public class SfUserModel {

    private final String username, password;

    public SfUserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Step
    public String toString(){
        return format("User model: [name=%s, password=%s]", username, password);
    }
}
