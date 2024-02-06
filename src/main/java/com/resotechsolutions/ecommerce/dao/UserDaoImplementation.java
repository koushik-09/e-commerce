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
    public User findUserById(long id) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where userId = :theData", User.class);
        theQuery.setParameter("theData",id);
        List<User> list = theQuery.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public UserDetails findUserDetailByEmail(String email) {
        TypedQuery<UserDetails> theQuery = entityManager.createQuery("from UserDetails where email = :theData", UserDetails.class);
        theQuery.setParameter("theData",email);
        List<UserDetails> list = theQuery.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

//    @Transactional
//    public Long getLastInsertedId() {
//        // Assuming your JPA entity is named "YourEntity" and has an auto-incrementing ID
//        User entity = new User("abc");
//        entityManager.persist(entity);
//
//        // The ID will be populated after the transaction is committed
//        entityManager.flush();
//
//        return entity.getUserId();
//    }

}
