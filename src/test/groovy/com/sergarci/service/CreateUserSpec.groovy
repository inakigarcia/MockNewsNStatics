package com.sergarci.service

import com.sergarci.dao.UserDao
import com.sergarci.model.User
import spock.lang.*

class CreateUserSpec extends Specification {

    final String EMAIL = "test@mail.com"
    final String NAME = "Ser Garci"

    @Shared UserDao userDao
    @Shared UserService userService

    def setup() {
        userDao = Mock(UserDao)

        userService = Spy(UserService)
        userService.setUserDao(userDao)
    }

    def "we create an empty user"() {

        given:

        when:
            userService.createAdminUser(NAME, EMAIL)

        then:
            1 * userService.createEmptyUser()

    }

    def "we set the given name to this new empty user"() {

        given:
            User user = Mock(User)

        when:
            userService.createAdminUser(NAME, EMAIL)

        then:
            userService.createEmptyUser() >> user
            1 * user.setName(NAME)

    }

    def "we set the given email to this new empty user"() {

        given:
            User user = Mock(User)

        when:
            userService.createAdminUser(NAME, EMAIL)

        then:
            userService.createEmptyUser() >> user
            1 * user.setEmail(EMAIL)

    }

    def "we save this new user"() {

        given:
            User user = Mock(User)

        when:
            userService.createAdminUser(NAME, EMAIL)

        then:
            userService.createEmptyUser() >> user
            1 * userDao.saveUser(user)

    }

    def "we set users properties before saving it"() {

        given:
            User user = Mock(User)

        when:
            userService.createAdminUser(NAME, EMAIL)

        then:
            userService.createEmptyUser() >> user
        then:
            1 * user.setName(NAME)
            1 * user.setEmail(EMAIL)
        then:
            1 * userDao.saveUser(user)

    }
}