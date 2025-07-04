package com.cms.config;

import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.cms.entities.User;
import com.cms.services.UserService;





public class GlobalModelAttributeConfig {
    
    @Autowired
    private UserService userService;

    @ModelAttribute("loggedinUser")
    public User getLoggedInUser(Principal principal) {
        if (principal == null) return null;

        String email = principal.getName();
        return userService.getUserByEmail(email);
    }

}
