package com.resotechsolutions.ecommerce.dao;

import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;

public interface UserDao {
    User findUserById(long id);

    void saveUser(User user);

    UserDetails findUserDetailByEmail(String email);


}
