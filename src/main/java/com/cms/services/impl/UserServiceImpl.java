package com.cms.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.cms.entities.User;
import com.cms.helper.AppConstants;
import com.cms.helper.ResourceNotFoundException;
import com.cms.repositories.UserRepo;
import com.cms.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired 
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // user id have to generate
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        // password Encode
        //user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));



        //user role

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
       return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
      User user2= userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceAccessException("User not found"));
    
      user2.setName(user.getName());
      user2.setEmail(user2.getEmail());
      user2.setPassword(user2.getPassword());
      user2.setAbout(user2.getAbout());
      user2.setPhoneNumber(user.getPhoneNumber());
      user2.setProfilePic(user.getProfilePic());
      user2.setEnabled(user2.isEnabled());
      user2.setEmailVarified(user2.isEmailVarified());
      user2.setPhoneVarified((user2.isPhoneVarified()));
      user2.setProvider((user2.getProvider()));
      user2.setProviderUserId((user2.getProviderUserId()));


      User save=userRepo.save(user2);
      return Optional.ofNullable(save);
    }
     
    

    @Override
    public void deleteUser(String id) {
        User user2= userRepo.findById(id).orElseThrow(()-> new ResourceAccessException("User not found"));
    userRepo.delete(user2);
     
    }

    @Override
    public boolean isUserExist(String userId) {
      User user2=userRepo.findById(userId).orElse(null);
      return user2!=null?true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
     User user=  userRepo.findByEmail(email).orElse(null);
       return user!=null?true:false;
    }

    @Override
    public List<User> getAllUsers() {
      return userRepo.findAll();


}

    @Override
    public User getUserByEmail(String email) {
    return userRepo.findByEmail(email).orElse(null);
    }
}