package com.cms.controllers;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.entities.Contact;
import com.cms.entities.User;
import com.cms.forms.ContactForm;
import com.cms.helper.Helper;
import com.cms.helper.Message;
import com.cms.helper.MessageType;
import com.cms.services.ContactService;
import com.cms.services.ImageService;
import com.cms.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger =org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

   @Autowired
    private ImageService imageService;


    @Autowired
    private UserService userService;


    
    @RequestMapping("/add")
    // add contact page handler
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }


    @RequestMapping(value="/add" , method = RequestMethod.POST)
    public String saveContact( @Valid  @ModelAttribute ContactForm contactForm , BindingResult result ,Authentication authentication , HttpSession session){

        
    // view contacts
    

        if(result.hasErrors()){
            session.setAttribute("message", Message.builder().content("Please correct your errors").type(MessageType.red).build());
            return "user/add_contact";

        }

        String username=Helper.getEmailOfLoggedInUser(authentication);

       User user= userService.getUserByEmail(username);
       // to process the contact picture

       // image process
        
       //upload krne ka code

       String filename=UUID.randomUUID().toString();

       String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

       

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavourite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setUser(user);
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicld(filename);
        contactService.save(contact);
        System.out.println(contactForm);

        session.setAttribute("message", Message.builder().content("You have successfully Added a new contact").type(MessageType.green).build());
        return "redirect:/user/contacts/add";

    }


    // view contacts
    @RequestMapping()
    public String viewContacts(Model model, Authentication authentication){

        //load all the user
        String username= Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        List<Contact> contacts = contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        
        return "user/contacts";
    }
}
   



  
  
  

 

 




 
 
  
         
                                  
                           
                                                   
 














