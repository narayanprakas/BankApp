package controllers;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMail extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String toEmail = request.getParameter("email");
		HttpSession s = request.getSession(true);
		s.setAttribute("email", toEmail);

		String fromEmail="joypurkait.abc@gmail.com"; //sender's mail id.
		String pwd="";		//sender's mail pwd.

		//Creating Session Object
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail, pwd);
			}
		});

		//Composing the Mail
		MimeMessage mesg = new MimeMessage(session);
		try {
			mesg.setFrom(new InternetAddress(fromEmail));

			mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			mesg.setSubject("DO NOT REPLY: Mail from Java Program");  
			mesg.setText("Click on the below link to reset your password http://localhost:9090/BankAppp/resetPwd.jsp");  

			//Sending the Mail
			Transport.send(mesg);
			System.out.println("Mail Sent!!");
			response.sendRedirect("/BankAppp/mailSent.jsp");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
}