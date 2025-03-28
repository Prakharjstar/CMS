package com.cms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    //User Dashboard page


     @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        System.out.println("User Dashboard");
        return "user/dashboard";

    }
 
    //user Profile page
    @RequestMapping(value = "/profile")
    public String userProfile(){
        System.out.println("User Dashboard");
        return "user/profile";

    }
    //user add contacts page

    //user view contacts

    //user edit contact

    //user delete contact

    //user search contact

}
