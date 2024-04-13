package com.kalal165code.crudappdemo.dao;

import com.kalal165code.crudappdemo.entity.UserFriend;

import java.util.List;

public interface UserFriendDAO {

    public List<UserFriend> getListByUserName(String username);

    public UserFriend getListByUserFriendName(String userFriendName);

    public void save(UserFriend theUserFriend);
}
