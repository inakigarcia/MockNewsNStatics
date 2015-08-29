package com.sergarci.service;

import com.sergarci.dao.UserDao;
import com.sergarci.model.User;

public class UserService {

    private UserDao userDao;

    protected User createEmptyUser() {
        return new User();
    }

    public User createAdminUser(String name, String email) {
        User user = createEmptyUser();

        user.setName(name);
        user.setEmail(email);

        user = getUserDao().saveUser(user);

        return user;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
