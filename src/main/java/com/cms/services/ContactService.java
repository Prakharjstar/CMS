package com.cms.services;

import com.cms.entities.Contact;
import com.cms.entities.User;

import java.util.List;

public interface ContactService {
    //save Contacts
    Contact save (Contact contact);

    //update contact
    Contact update(Contact contact);

    //get contacts
    List<Contact> getAll();

    //get contact by id
    Contact getById(String id);

    //delete contact

    void delete(String id);

    //search Contact
    List<Contact> search(String name, String email , String phoneNumber );

    //get contacts by userId

    List<Contact> getByUserId(String userId);
    
    List<Contact> getByUser(User user);

}
