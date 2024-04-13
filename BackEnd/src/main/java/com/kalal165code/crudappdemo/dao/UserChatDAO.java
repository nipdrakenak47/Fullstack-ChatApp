package com.kalal165code.crudappdemo.dao;

import com.kalal165code.crudappdemo.entity.UserChat;

import java.util.List;

public interface UserChatDAO {
    public List<UserChat> getChat(String fromUser, String toUser);

    public void save(UserChat theUserChat);
    public UserChat getUserChat(UserChat theUserChat);
}
