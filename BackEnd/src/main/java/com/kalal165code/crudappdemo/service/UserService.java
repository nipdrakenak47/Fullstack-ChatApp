package com.kalal165code.crudappdemo.service;

import com.kalal165code.crudappdemo.entity.User;
import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findByUserName(String userName);

    public void save(User theUser);

    public User getUserByUserName(String username);
}
