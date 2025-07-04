package com.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.entities.User;
import com.cms.forms.UserForm;
import com.cms.helper.Message;
import com.cms.helper.MessageType;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("This is Home Page");
    

    //Sending data to view
    
       model.addAttribute("name","Document Page");
       model.addAttribute("InstaGramId","I am Prakhar12");
       model.addAttribute("GithubId","https://github.com/Prakharjstar/CODSOFT");
        return "home";
    }
     
    @RequestMapping("/base")
    public String base(){
        System.out.println("This is Base page");
        return "base";
    }
 @RequestMapping("/about")
    public String about(){
        System.out.println("This is about page");
        return "about";
    }
    @RequestMapping("/services")
    public String service(){
        System.out.println("This is service page");
        return "services";
    }
    @RequestMapping("/contact")
    public String contact(){
        
        return "contact";
    }



    // this is showing login page

   @RequestMapping("/login")
    public String login(){
        System.out.println("This is service page");
        return new String( "login");
    }

    
    //  registration page
    @RequestMapping("/register")
    public String register(Model model){
        UserForm userform= new UserForm();
      
        model.addAttribute("userForm",userform);
        
        return "register";
    }



     // for processing registration
    @RequestMapping(value="/do-register",method=RequestMethod.POST)
    public String processRegister( @Valid @ModelAttribute UserForm userForm , BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing registration");

        //UserForm
        System.out.println(userForm);

        //Validate Form data
        if(rBindingResult.hasErrors()){
            return "register";
        }





        //userservice


         User user=new User();
         user.setName(userForm.getName());
         user.setEmail(userForm.getEmail());
         user.setAbout(userForm.getAbout());
         user.setPassword(userForm.getPassword());
         user.setPhoneNumber(userForm.getPhoneNumber());
         user.setProfilePic(null);
        


        User savedUser=userService.saveUser(user);
        System.out.println("user Saved");
     
        // add a message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";



    }

}
