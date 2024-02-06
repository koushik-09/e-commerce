package com.resotechsolutions.ecommerce.dao;

import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;

public interface UserDao {
    User findUserByEmail(String email);

    void saveUser(User user);

    UserDetails findUserDetailByEmail(String email);


}
