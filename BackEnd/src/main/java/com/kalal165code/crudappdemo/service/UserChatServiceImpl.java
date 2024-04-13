package com.kalal165code.crudappdemo.service;

import com.kalal165code.crudappdemo.dao.UserChatDAO;
import com.kalal165code.crudappdemo.dao.UserChatDAOEmpl;
import com.kalal165code.crudappdemo.entity.UserChat;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserChatServiceImpl implements UserChatService{

    private UserChatDAO theUserChatDAO;

    @Autowired
    public UserChatServiceImpl(UserChatDAO theUserChatDAO){
        this.theUserChatDAO = theUserChatDAO;
    }

    @Override
    public List<UserChat> getChat(String fromUser, String toUser) {
        return theUserChatDAO.getChat(fromUser, toUser);
    }


    @Override
    @Transactional
    public void save(UserChat theUserChat) {
        theUserChatDAO.save(theUserChat);
    }

    @Override
    public UserChat getUserChat(UserChat theUserChat) {
        return theUserChatDAO.getUserChat(theUserChat);
    }
}
