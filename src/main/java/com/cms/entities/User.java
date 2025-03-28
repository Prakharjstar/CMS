package com.cms.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
     

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="user")
@Table(name="users")
public class User implements UserDetails {

@Id
    private String userId;

    @Column (name="user_name",nullable =false )
    private String name;
   @Column(unique = true,nullable = false)
    private String email;
    
    private String password;
    @Column(length=5000)
    private String about;
    @Column(length=5000)
    private String profilePic;
    private String phoneNumber;

    private boolean enabled=true;
    private boolean emailVarified=false;
    private boolean phoneVarified=false;

     @Enumerated(value=EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user" , cascade=CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Contact>contacts=new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // list of roles[USER,ADMIN]
        //collection of SimpGrantedAithority[roles{ADMIN,USER}]
       Collection<SimpleGrantedAuthority> roles= roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles ;
      
    }


    // User Id is out User Name

    @Override
    public String getUsername() {
        return this.email;
        
    }
     
    @Override
    public boolean isAccountNonLocked(){
        return
         true;
    }

     @Override
    public boolean isAccountNonExpired(){
        return true;
    }

     @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
 
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
    public String getPassword(){
        return this.password;
    }

  

}
