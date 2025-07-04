package com.cms.services;

import com.cms.entities.Contact;
import com.cms.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;

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
    Page<Contact> searchByName(String namekeyword, int size , int page , String sortBy, String order,User user );

   Page<Contact> searchByEmail(String emailKeyword , int size , int page , String sortBy , String order,User user);

    Page<Contact> SearchByPhoneNumber(String phoneNumberKeyword , int size , int page , String sortBy , String order,User user);
    //get contacts by userId

    List<Contact> getByUserId(String userId);
    
    Page<Contact> getByUser(User user , int page , int size ,  String sortField ,  String sortDirection);

}
