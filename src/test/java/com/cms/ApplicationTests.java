package com.cms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cms.services.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private EmailService service;
     @Test
	void sendEmailTest(){
		service.sendEmail("batchcwd@gmail.com", "just testing email service", "This is a scm project working on email service");

	}

}
