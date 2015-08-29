package com.sergarci.service;

import com.sergarci.dao.UserDao;
import com.sergarci.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateAdinUserWithRealConstructorTest {

    private static final String NAME = "UserName";
    private static final String EMAIL = "UserMail";

    private UserService userService;

    private UserDao userDao;

    private User user;

    @Before
    public void setUp() {
        userService = new UserService();

        userDao = mock(UserDao.class);

        userService.setUserDao(userDao);

        user = mock(User.class);
    }

    @Test
    public void creating_admin_user_for_given_name_and_email_saves_a_new_user() {
        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        verify(userDao, times(1)).saveUser(any(User.class));
    }

    @Test
    public void creating_admin_user_returns_the_user_saved_by_userDao() {
        doReturn(user).when(userDao).saveUser(any(User.class));

        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        assertEquals(user, newAdminUser);
    }

    /**
     * A new User is created.
     * We can't verify which values are set to this new User
     * On the other hand we can return a mock User when calling UserDao to verify what is the result of UserService method
     */
}
