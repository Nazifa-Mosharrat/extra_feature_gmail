package com.extra.feature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.extra.feature.dto.EmailReciver;
import com.extra.feature.service.EmailReciverService;

@Controller
public class EmailReciverController {

	@Autowired EmailReciverService emailReciverService;
	@GetMapping("registration-form.html")
	public String showRegistrationPage(Model model) {
		EmailReciver emailReciver=new EmailReciver();
		model.addAttribute("registraions", emailReciver);
		return "registration-form";
	}
	
	@PostMapping("RegistrationConfirmation")
	public String showRegistrationConfirmPage(Model model,EmailReciver emailReciver) {

		
		//send mail
		try {
			System.out.println(emailReciver);
			//normal basic text email via mail
			//emailReciverService.sendTextMail(emailReciver);
			//link via mail
			emailReciverService.sendLinkViaMail(emailReciver);
			//file via mail
			//emailReciverService.sendFileViaMail(emailReciver);
			
		}catch(MailException e) {
			System.out.println("some error occured to send mail:"+e.getMessage());
		}
		return "registration-confirm";
		
	}
	
}
