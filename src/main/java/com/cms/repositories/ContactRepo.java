package com.cms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.entities.Contact;
import com.cms.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {
    //FIND THE CONTACT BY USER
 
    //custom finder method
    List<Contact> findByUser(User user);

    //custom query method
    @Query(" SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);


}
