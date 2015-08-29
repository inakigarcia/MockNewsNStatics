package com.sergarci.service;

import com.sergarci.dao.UserDao;
import com.sergarci.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateAdminUserWithoutUsingRealConstructorTest {

    private static final String NAME = "UserName";
    private static final String EMAIL = "UserMail";

    private UserService userService;

    private UserDao userDao;

    private User user;
    private User savedUser;

    @Before
    public void setUp() {
        userService = spy(new UserService());

        userDao = mock(UserDao.class);

        userService.setUserDao(userDao);

        user = mock(User.class);
        savedUser = mock(User.class);

        doReturn(user).when(userService).createEmptyUser();
    }

    @Test
    public void creating_admin_user_for_given_name_and_email_first_creates_new_user() {

        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        verify(userService, times(1)).createEmptyUser();
    }

    @Test
    public void creating_admin_user_for_given_name_and_email_sets_name_and_email_to_this_new_user() {

        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        verify(user, times(1)).setName(NAME);
        verify(user, times(1)).setEmail(EMAIL);
    }

    @Test
    public void creating_admin_user_for_given_name_and_email_saves_created_new_user() {

        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        verify(userDao, times(1)).saveUser(user);
    }

    @Test
    public void creating_admin_user_returns_the_user_saved_by_userDao() {
        doReturn(savedUser).when(userDao).saveUser(user);

        User newAdminUser = userService.createAdminUser(NAME, EMAIL);

        assertEquals(savedUser, newAdminUser);
    }

    /**
     * A new User is created.
     * We CAN verify which values are set to this new User (it is now a Mock not a real object)
     * We can verify each property set
     * We can verify the call to the protected method
     * We can verify other collavorator method call
     * We can verify result object
     */
}
