package com.example.lab_5_1;

public class User {

    String _name;
    String _email;

    public User(String _name, String _email) {
        this._name = _name;
        this._email = _email;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
