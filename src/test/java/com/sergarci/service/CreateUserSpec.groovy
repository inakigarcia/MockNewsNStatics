package com.sergarci.service

import com.sergarci.dao.UserDao
import com.sergarci.model.User
import com.sergarci.service.UserService
import spock.lang.*

class CreateUserSpec extends Specification {
    def "collaborators must be invoked in order"() {

        given:
        String email = "test@email.com"
        String name = "John Doe"
        User user = Mock(User)

        def userDao = Mock(UserDao)
        def userService = Spy(UserService)
        userService.setUserDao(userDao)

        when:
        userService.createAdminUser(name, email)

        then:
        userService.createEmptyUser() >> user
        1 * user.setName(name)
        1 * user.setEmail(email)
        1 * userDao.saveUser(user)

    }
}