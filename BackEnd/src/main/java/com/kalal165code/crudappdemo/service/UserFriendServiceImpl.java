package com.kalal165code.crudappdemo.service;

import com.kalal165code.crudappdemo.dao.UserFriendDAO;
import com.kalal165code.crudappdemo.entity.UserFriend;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFriendServiceImpl implements UserFriendService{

    private UserFriendDAO theUserFriendDao;

    @Autowired
    public UserFriendServiceImpl(UserFriendDAO theUserFriendDao){
        this.theUserFriendDao = theUserFriendDao;
    }

    @Override
    public List<UserFriend> getListByUserName(String username) {
        return theUserFriendDao.getListByUserName(username);
    }

    @Override
    public UserFriend getListByUserFriendName(String userFriendName) {
        return theUserFriendDao.getListByUserFriendName(userFriendName);
    }

    @Transactional
    @Override
    public void save(UserFriend theUserFriend) {
        theUserFriendDao.save(theUserFriend);
    }
}
