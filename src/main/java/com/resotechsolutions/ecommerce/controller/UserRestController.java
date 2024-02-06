package com.resotechsolutions.ecommerce.controller;

import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;
import com.resotechsolutions.ecommerce.entity.UserHandler;
import com.resotechsolutions.ecommerce.response.BaseResponse;
import com.resotechsolutions.ecommerce.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserRestController(UserServiceImplementation theUserServiceImplementation){
        this.userServiceImplementation = theUserServiceImplementation;
    }
    @PostMapping("/register")
    public BaseResponse saveUser(@RequestBody UserHandler userHandler){
        return userServiceImplementation.saveUser(userHandler);
    }

    @PostMapping("/login")
    public BaseResponse validateUser(@RequestBody User user){
        return userServiceImplementation.validateUser(user);
    }
}
