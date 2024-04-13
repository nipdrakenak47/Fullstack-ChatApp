package com.kalal165code.crudappdemo.service;

import com.kalal165code.crudappdemo.dao.UserDAO;
import com.kalal165code.crudappdemo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    // Defining fields
    private UserDAO userDAO;

    // Constructor injection for DAO
    @Autowired
    public UserServiceImpl(UserDAO theUserDAO){
        this.userDAO = theUserDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Transactional
    @Override
    public void save(User theUser) {
        userDAO.save(theUser);
    }

    @Override
    public User getUserByUserName(String username) {

        return userDAO.getUserByUserName(username);
    }

}
