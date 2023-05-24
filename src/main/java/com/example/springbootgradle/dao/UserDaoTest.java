package com.example.springbootgradle.dao;

import com.example.springbootgradle.domain.User;
import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao(new DConnectionMaker());
        User user = new User();
        user.setId("3");
        user.setName("luna");
        user.setPassword("1234");
//        userDao.add(user);

        User selectedUser = userDao.get("3");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }

}
