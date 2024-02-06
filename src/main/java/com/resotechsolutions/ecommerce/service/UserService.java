package com.resotechsolutions.ecommerce.service;

import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;
import com.resotechsolutions.ecommerce.entity.UserHandler;
import com.resotechsolutions.ecommerce.response.BaseResponse;

public interface UserService {
    User findUserById(long id);

    BaseResponse saveUser(UserHandler userHandler);
    UserDetails findUserDetailByEmail(String email);

    BaseResponse validateUser(User user);
}
