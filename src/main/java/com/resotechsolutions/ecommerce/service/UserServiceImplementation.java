package com.resotechsolutions.ecommerce.service;

import com.resotechsolutions.ecommerce.config.TokenGenerator;
import com.resotechsolutions.ecommerce.dao.UserDaoImplementation;
import com.resotechsolutions.ecommerce.entity.Token;
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

    private TokenGenerator tokenGenerator;

    @Autowired
    public UserServiceImplementation(UserDaoImplementation theUserDaoImplementation,
                                     BCryptPasswordEncoder thePasswordEncoder,
                                     ResponseHandler theResponseHandler,
                                     TokenGenerator theTokenGenerator){
        this.userDaoImplementation = theUserDaoImplementation;
        this.passwordEncoder = thePasswordEncoder;
        this.responseHandler = theResponseHandler;
        this.tokenGenerator = theTokenGenerator;
    }


    @Override
    public User findUserById(long id) {
        return userDaoImplementation.findUserById(id);
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

        Token token = new Token();
        token.setToken(userHandler);
        user.setToken(token);

        String email = userDetails.getEmail();
        UserDetails tempUser = findUserDetailByEmail(email);

        if(tempUser == null){

            String password = user.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);

            String generatedToken = tokenGenerator.generateToken(email);
            token.setToken(generatedToken);
            System.out.println(user.toString());
            System.out.println(user.getUserDetails().toString());
            System.out.println(user.getToken().toString());

            tempUser = user.getUserDetails();
            tempUser.setToken(generatedToken);
            System.out.println(tempUser.toString());
            userDaoImplementation.saveUser(user);

            return responseHandler.setMessageResponse("Registration Successful", HttpStatus.CREATED.value(), tempUser);
        }

        String message = "User already exists with email "+email;
        return responseHandler.setMessageResponse(message,HttpStatus.CONFLICT.value(),null);
    }

    @Override
    public BaseResponse validateUser(User user) {

        UserDetails tempUserDetail = findUserDetailByEmail(user.getEmail());
        if(tempUserDetail == null){

            String message = "User Does not exist with email "+user.getEmail();
            return responseHandler.setMessageResponse(message,HttpStatus.NOT_FOUND.value(),null);
        }
        User tempUser = findUserById(tempUserDetail.getUserId());
        if(passwordEncoder.matches(user.getPassword(),tempUser.getPassword())){
            Token tempToken = tempUser.getToken();
            tempUserDetail = tempUser.getUserDetails();
            tempUserDetail.setToken(tempToken.getToken());
            System.out.println(tempUserDetail);
            return responseHandler.setMessageResponse("Login Successful",HttpStatus.OK.value(),tempUserDetail);
        }

        return responseHandler.setMessageResponse("Invalid Password",HttpStatus.UNAUTHORIZED.value(),null);
    }

}
