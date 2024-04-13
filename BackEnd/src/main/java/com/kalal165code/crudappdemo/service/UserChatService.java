package com.kalal165code.crudappdemo.service;

import com.kalal165code.crudappdemo.entity.UserChat;

import java.util.List;

public interface UserChatService {
    public List<UserChat> getChat(String fromUser, String toUser);

    public void save(UserChat theUserChat);

    public UserChat getUserChat(UserChat theUserChat);
}
