package com.kalal165code.crudappdemo.dao;

import com.kalal165code.crudappdemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOJpaEmpl implements UserDAO {

    private EntityManager entityManager;

    // Injecting entitymanager created by spring boot
    @Autowired
    public UserDAOJpaEmpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<User> findAll() {

        TypedQuery<User> theQuery = entityManager.createQuery("FROM User",User.class);

        List<User> allUser = theQuery.getResultList();

        return allUser;
    }

    @Override
    public User findByUserName(String userName) {

        User theUser = entityManager.find(User.class,userName);
        return theUser;
    }

    @Override
    public void save(User theUser) {
        entityManager.persist(theUser);
    }

    @Override
    public User getUserByUserName(String username) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User where userName = :theUserName",User.class);

        theQuery.setParameter("theUserName",username);

        List<User> allUser = theQuery.getResultList();

        if(allUser.size()==0){
            return null;
        }
        else{
            return allUser.get(0);
        }
    }


}
