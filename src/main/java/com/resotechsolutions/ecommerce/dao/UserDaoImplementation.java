package com.resotechsolutions.ecommerce.dao;

import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImplementation implements UserDao{

    private @Qualifier("mysqljpa") EntityManager entityManager;

    @Autowired
    public UserDaoImplementation(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public User findUserByEmail(String email) {

        //entityManager.createNativeQuery("SQl statement").getSingleResult();
        TypedQuery<User> theQuery = entityManager.createQuery("from User where email =:theData",User.class);
        theQuery.setParameter("theData",email);
        List<User> list = theQuery.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public UserDetails findUserDetailByEmail(String email) {
        User user = findUserByEmail(email);
        return (user != null) ? user.getUserDetails() : null;
    }

}
