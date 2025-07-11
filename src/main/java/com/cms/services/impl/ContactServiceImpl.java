package com.cms.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cms.entities.Contact;
import com.cms.entities.User;
import com.cms.helper.ResourceNotFoundException;
import com.cms.repositories.ContactRepo;
import com.cms.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {


@Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }


      
    @Override
    public Contact update(Contact contact) {
       
       var contactOld = contactRepo.findById(contact.getId())
       .orElseThrow(()-> new ResourceNotFoundException("Contact Not Found"));
       contactOld.setName(contact.getName());
       contactOld.setEmail(contact.getEmail());
       contactOld.setPhoneNumber(contact.getPhoneNumber());
       contactOld.setAddress(contact.getAddress());
       contactOld.setDescription(contact.getDescription());
       contactOld.setPicture(contact.getPicture());
       contactOld.setFavourite(contact.isFavourite());
       contactOld.setCloudinaryImagePublicld(contact.getCloudinaryImagePublicld());
       contactOld.setWebsiteLink(contact.getWebsiteLink());
       contactOld.setLinkedInLink(contact.getLinkedInLink());
     

       return contactRepo.save(contactOld);


    


    }
       
    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
   
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with givrn id" + id));
               
              
    }
             
    @Override                     
    public void delete(String id) {
    
        var contact = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found with givrn id" + id));
       contactRepo.delete(contact);
    }
                          
    

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
        
    }



    


    @Override
    public Page<Contact> getByUser(User user, int page, int size , String sortBy , String direction) {
        Sort sort = direction.equals("desc")? Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size , sort);
    
      return contactRepo.findByUser(user , pageable);
    
    }



    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order ,User user) {
      var pageable = PageRequest.of(page, size ,Sort.by(sortBy));
      Sort sort = order.equals("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();   
       return  contactRepo.findByUserAndNameContaining(user,nameKeyword , pageable);
    }



    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order ,User user) {
       var pageable = PageRequest.of(page, size ,Sort.by(sortBy));
      Sort sort = order.equals("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();   
       return  contactRepo.findByUserAndEmailContaining(user,emailKeyword , pageable);
    }



    @Override
    public Page<Contact> SearchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order ,User user) {
        var pageable = PageRequest.of(page, size ,Sort.by(sortBy));
      Sort sort = order.equals("desc") ? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();   
       return  contactRepo.findByUserAndPhoneNumberContaining(user,phoneNumberKeyword , pageable);



  

   
}
}