package com.kalal165code.crudappdemo.dao;

import com.kalal165code.crudappdemo.entity.User;
import com.kalal165code.crudappdemo.entity.UserFriend;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFriendDAOJpaEmpl implements UserFriendDAO{

    private EntityManager entityManager;

    // Injecting entitymanager created by spring boot
    @Autowired
    public UserFriendDAOJpaEmpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }


    @Override
    public List<UserFriend> getListByUserName(String username) {
        TypedQuery<UserFriend> theQuery = entityManager.createQuery("FROM UserFriend where userFriendName " +
                "like CONCAT(:theUserName,'%')",UserFriend.class);

        theQuery.setParameter("theUserName",username);

        List<UserFriend> allFriend = theQuery.getResultList();

        return allFriend;
    }

    @Override
    public UserFriend getListByUserFriendName(String userFriendName) {
        TypedQuery<UserFriend> theQuery = entityManager.createQuery("FROM UserFriend where userFriendName " +
                "= :theUserFriendName",UserFriend.class);

        theQuery.setParameter("theUserFriendName",userFriendName);

        List<UserFriend> arr = theQuery.getResultList();

        if(arr.size()==0){
            return null;
        }
        else{
            return arr.get(0);
        }
    }

    @Override
    public void save(UserFriend theUserFriend) {
        TypedQuery<UserFriend> theQuery = entityManager.createQuery("FROM UserFriend where userFriendName " +
                "= :theUserFriendName",UserFriend.class);

        theQuery.setParameter("theUserFriendName",theUserFriend.getUserFriendName());

        List<UserFriend> arr = theQuery.getResultList();

        if(arr.size()==0) {
            entityManager.persist(theUserFriend);
        }
    }
}
