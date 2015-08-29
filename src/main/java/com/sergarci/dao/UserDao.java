package com.sergarci.dao;

import com.sergarci.model.User;

public class UserDao {

    public User saveUser(User user) {
        System.out.println(String.format("saved user: %s with %s email", user.getName(), user.getEmail()));
        return user;
    }
}
