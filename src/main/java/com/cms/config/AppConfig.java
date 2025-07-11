package com.cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;



@Configuration
public class AppConfig {
    

    @Value("${cloudinary.cloud.name}")  
    private String cloudname;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @Value("${cloudinary.api.secret}")
    private  String apiSecret;




    @Bean
    public Cloudinary Cloudinary(){



        return new Cloudinary(
         ObjectUtils.asMap(
            "cloud_name" , cloudname,
            "api_key",apiKey,
            "api_secret", apiSecret

         )
       

        );
    }


}
