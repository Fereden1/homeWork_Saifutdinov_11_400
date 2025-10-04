package com.saifutdinov.service.impl;

import com.saifutdinov.dao.UserDao;
import com.saifutdinov.dao.impl.UserDaoImpl;
import com.saifutdinov.dto.UserDto;
import com.saifutdinov.entity.User;
import com.saifutdinov.service.UserService;
import com.saifutdinov.util.PasswordUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService, Serializable {

    private static final UserDao USER_DAO = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return USER_DAO.getAll()
                .stream()
                .map(usr -> new UserDto(usr.getName(), usr.getLogin()))
                .collect(Collectors.toList());
    }

    public static boolean signUp(String userLogin, String plainPassword, String firstName, String lastName) {
        if (USER_DAO.getByLogin(userLogin) != null) {
            return false;
        }
        User newUser = new User(
                0,
                userLogin,
                PasswordUtil.encrypt(plainPassword),
                firstName,
                lastName
        );
        USER_DAO.save(newUser);
        return true;
    }

    public static boolean userExists(String userLogin) {
        return USER_DAO.getByLogin(userLogin) != null;
    }

    public static boolean checkPassword(String userLogin, String plainPassword) {
        User foundUser = USER_DAO.getByLogin(userLogin);
        return foundUser != null &&
                foundUser.getPassword().equals(PasswordUtil.encrypt(plainPassword));
    }
}
