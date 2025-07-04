package com.cms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cms.entities.User;
import com.cms.helper.Helper;
import com.cms.services.UserService;

@ControllerAdvice
public class RootController {

private Logger logger = LoggerFactory.getLogger(this.getClass());

@Autowired
private UserService userService;

@ModelAttribute
    public void addLoggedinUserInformation(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in  user informatation to the model");
        String username =Helper.getEmailOfLoggedInUser( authentication);
        logger.info("User Logged in: {}", username);
     // database se data ko fetch : get user from db
       User user= userService.getUserByEmail(username);

        System.out.println(user);
         System.out.println(user.getName()) ;
        System.out.println(user.getEmail());
        model.addAttribute("loggedinUser", user); 

       
       
    }
}
