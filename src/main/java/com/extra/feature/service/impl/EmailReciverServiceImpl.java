package com.extra.feature.service.impl;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.extra.feature.dto.EmailReciver;
import com.extra.feature.service.EmailReciverService;

@Service
public class EmailReciverServiceImpl implements EmailReciverService {

	private JavaMailSender javaMailSender;

	@Autowired EmailReciverServiceImpl(JavaMailSender javaMailSender){
		this.javaMailSender=javaMailSender;
	}
	
	//normal basic text send via mail
	@Override
	public void sendTextMail(EmailReciver emailReciver) throws MailException {
		Random random = new Random(); 
		int randomCode=random.nextInt(6000);
		//send mail
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("nazifa.blog14@gmail.com");
		mail.setTo(emailReciver.getReciverEmail());		
		mail.setSubject("Do Not Reply");
		mail.setText("Copy This Code "+randomCode);
		System.out.println(mail);
		javaMailSender.send(mail);
		
		
		
	}
	
	//clickable link send via mail
	@Override
	public void sendLinkViaMail(EmailReciver emailReciver) {
		 String From="nazifa.blog14@gmail.com";//change accordingly  
	      String to= emailReciver.getReciverEmail();//change accordingly  
	      final String user="nazifa.blog14@gmail.com";//change accordingly  
	      final String password="kcbsbusxkqwqyidi";//change accordingly  
	  
	      Properties properties = System.getProperties();  
	      properties.put("mail.smtp.host","localhost");
	      properties.setProperty("mail.smtp.host","smtp.gmail.com" ); 
	      properties.put("mail.smtp.port", "465");
	      properties.put("mail.smtp.auth", "true");  
	      properties.put("mail.smtp.starttls.enable","true");
	      properties.put("mail.debug", "false");
	      properties.put("mail.smtp.ssl.enable", "true");
		  Session session = Session.getDefaultInstance(properties, new
		  javax.mail.Authenticator() { protected PasswordAuthentication
		  getPasswordAuthentication() { return new
		  PasswordAuthentication(user,password); } });
		  
	        
		try{
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(From));  
	         message.addRecipient(Message.RecipientType.TO,  
	                                  new InternetAddress(to));  
	  
	        message.setSubject("HTML Message");  
	        message.setContent("<h1>sending html mail check</h1>"+"<a href=\"https://www.linkedin.com\">\n" +
                    "<h4>Checkout my linked in profile...</h4></a>\n","text/html" );  
	       
	       Transport.send(message);  
	         System.out.println("message sent....");  
	      }catch (MessagingException ex) {ex.printStackTrace();}  
	   }
	// File sending via mail
	@Override
	public void sendFileViaMail(EmailReciver emailReciver) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	
		
		try {
			MimeMessageHelper msgHelper=new MimeMessageHelper(mimeMessage,true);
			msgHelper.setTo(emailReciver.getReciverEmail());
			msgHelper.setFrom("nazifa.blog14@gmail.com");
			msgHelper.setSubject("Attached File");
			msgHelper.setText("Find The Attached File..................");
			
			ClassPathResource classPathResource=new ClassPathResource("static/document/Evening_Class_Schedule_FALL_2020_Updated.xlsx");
			msgHelper.addAttachment(classPathResource.getFilename(), classPathResource);

			javaMailSender.send(mimeMessage);
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}  
		
}
	
	

