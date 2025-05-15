package com.cms.helper;



import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){
       

      // agar email is password se login kia hai to : email kaise nikalenge
      if(authentication instanceof OAuth2AuthenticationToken){

        var aOAuth2AuthenticationToken =(OAuth2AuthenticationToken)authentication;
        var clientid = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
         var oauth2User =(OAuth2User)authentication.getPrincipal();
         String username="";

        // sign with google
        if(clientid.equalsIgnoreCase("google")){
             System.out.println("Getting email from google");
            username= oauth2User.getAttribute("email").toString();
        }


         //sign with github
        else if(clientid.equalsIgnoreCase("github")){
            System.out.println("Getting email form github");
            
            username =oauth2User.getAttribute("email")!=null ?
            oauth2User.getAttribute("email").toString():oauth2User.getAttribute("login").toString()+"@gmail.com";
        }


      //sign in with facebook 
      return username;
      }else{
        System.out.println("Getting data from local database");

       
      
        return authentication.getName();
    }

}
}