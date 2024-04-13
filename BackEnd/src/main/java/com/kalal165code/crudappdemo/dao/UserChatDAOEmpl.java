package com.kalal165code.crudappdemo.dao;

import com.kalal165code.crudappdemo.entity.UserChat;
import com.kalal165code.crudappdemo.entity.UserFriend;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserChatDAOEmpl implements UserChatDAO{
    private EntityManager entityManager;

    // Injecting entitymanager created by spring boot
    @Autowired
    public UserChatDAOEmpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<UserChat> getChat(String fromUser, String toUser) {
        TypedQuery<UserChat> theQuery = entityManager.createQuery("FROM UserChat where " +
                "(fromUser=:theFromUser and toUser=:theToUser) or " +
                "(fromUser=:theToUser and toUser=:theFromUser)",UserChat.class);

        theQuery.setParameter("theFromUser",fromUser);
        theQuery.setParameter("theToUser",toUser);

        List<UserChat> chats = theQuery.getResultList();

        return chats;
    }

    @Override
    public void save(UserChat theUserChat) {
        entityManager.persist(theUserChat);
    }

    @Override
    public UserChat getUserChat(UserChat theUserChat) {
        TypedQuery<UserChat> theQuery = entityManager.createQuery("FROM UserChat where " +
                "fromUser=:theFromUser and toUser=:theToUser and chatTime=:theChatTime" ,UserChat.class);

        theQuery.setParameter("theFromUser",theUserChat.getFromUser());
        theQuery.setParameter("theToUser",theUserChat.getToUser());
        theQuery.setParameter("theChatTime",theUserChat.getChatTime());

        List<UserChat> chats = theQuery.getResultList();

        return chats.get(0);
    }
}
