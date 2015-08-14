package com.gdut.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends Thread {
	private EmailUtil emailUtil;
	private String title;
	//收件人地址
	private String address;
	//信件内容
	private String content;
	
	public EmailUtil getEmailUtil() {
		return emailUtil;
	}
	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SendEmail(EmailUtil emailUtil){
		this.emailUtil = emailUtil;
	}
	public void run() {
		System.out.println(emailUtil.getHost());
		System.out.println(emailUtil.getPort());
		System.out.println(emailUtil.getHost());
		Properties props=new Properties();
		props.setProperty("mail.host",emailUtil.getHost());
		props.setProperty("mail.smtp.auth","true");
		props.setProperty("mail.smtp.port",emailUtil.getPort());
		Authenticator auth=new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pwd=new PasswordAuthentication(emailUtil.getAccount().substring(0,emailUtil.getAccount().indexOf("@")), emailUtil.getPassword());
				return pwd;
			}
			
			
		};
		System.out.println(emailUtil.getAccount().substring(0,emailUtil.getAccount().indexOf("@")));
		Session session=Session.getDefaultInstance(props, auth);
		session.setDebug(true);
		MimeMessage mes=new MimeMessage(session); 
		try {
			Address from=new InternetAddress(emailUtil.getAccount());
			mes.setFrom(from);
			Address to=new InternetAddress(address);
			mes.setRecipient(RecipientType.TO, to);
			mes.setSubject(title);
			mes.setContent(content, "text/html;charset=UTF-8");
			Transport.send(mes);
			System.out.println(content);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
