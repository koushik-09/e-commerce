package com.resotechsolutions.ecommerce.service;

import com.resotechsolutions.ecommerce.dao.UserDaoImplementation;
import com.resotechsolutions.ecommerce.entity.User;
import com.resotechsolutions.ecommerce.entity.UserDetails;
import com.resotechsolutions.ecommerce.entity.UserHandler;
import com.resotechsolutions.ecommerce.response.BaseResponse;
import com.resotechsolutions.ecommerce.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService{

    private UserDaoImplementation userDaoImplementation;

    private ResponseHandler responseHandler;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserDaoImplementation theUserDaoImplementation,BCryptPasswordEncoder thePasswordEncoder,ResponseHandler theResponseHandler){
        this.userDaoImplementation = theUserDaoImplementation;
        this.passwordEncoder = thePasswordEncoder;
        this.responseHandler = theResponseHandler;
    }


    @Override
    public User findUserByEmail(String email) {
        return userDaoImplementation.findUserByEmail(email);
    }

    @Override
    public UserDetails findUserDetailByEmail(String email) {
        return userDaoImplementation.findUserDetailByEmail(email);
    }


    @Override
    @Transactional
    public BaseResponse saveUser(UserHandler userHandler) {

        User user = new User();
        user.setUser(userHandler);

        UserDetails userDetails = new UserDetails();
        userDetails.setUserDetails(userHandler);
        userDetails.setUser(true);
        userDetails.setActive(true);

        user.setUserDetails(userDetails);

        String email = user.getEmail();
        User tempUser = findUserByEmail(email);

        if(tempUser == null){

            String password = user.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);

            userDaoImplementation.saveUser(user);

            return responseHandler.setMessageResponse("Registration Successful", HttpStatus.CREATED.value(), user.getUserDetails());
        }

        String message = "User already exists with email "+email;
        return responseHandler.setMessageResponse(message,HttpStatus.CONFLICT.value(),null);
    }

    @Override
    public BaseResponse validateUser(User user) {

        User tempUser = findUserByEmail(user.getEmail());

        if(tempUser == null){

            String message = "User Does not exist with email "+user.getEmail();
            return responseHandler.setMessageResponse(message,HttpStatus.NOT_FOUND.value(),user);
        }

        if(passwordEncoder.matches(user.getPassword(),tempUser.getPassword())){
            return responseHandler.setMessageResponse("Login Successful",HttpStatus.OK.value(),tempUser.getUserDetails());
        }

        return responseHandler.setMessageResponse("Invalid Password",HttpStatus.UNAUTHORIZED.value(),user);
    }
}
