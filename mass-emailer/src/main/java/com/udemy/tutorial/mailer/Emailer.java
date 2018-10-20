package com.udemy.tutorial.mailer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.udemy.tutorial.constants.EmailConstants;

public class Emailer implements Runnable {

	Properties emailProperties;
	Session mailSession;
	private Lock createMessageLock = new ReentrantLock();
	private Lock sendMessageLock = new ReentrantLock();
	private BlockingQueue<List<String>> emailQueue;
	private BlockingQueue<List<String>> sentEmailQueue;
	
	public Emailer(BlockingQueue<List<String>> emailQueue,BlockingQueue<List<String>> sentEmailQueue) {
		this.emailQueue = emailQueue;
		this.sentEmailQueue = sentEmailQueue;
	}

	private void setMailServerProperties() {
		
		System.out.println("Thread "+Thread.currentThread().getName() + " entered setMailServerProperties function");

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", EmailConstants.EMAIL_PORT);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		
		System.out.println("Thread "+Thread.currentThread().getName() + " exited setMailServerProperties function");
	}

	private MimeMessage createEmailMessage(List<String> emailRecord) throws AddressException, MessagingException, IOException {
		
		System.out.println("Thread "+Thread.currentThread().getName() + " entered createEmailMessage");
		
		String toEmails[] = {emailRecord.get(0)};
		mailSession = Session.getDefaultInstance(emailProperties, null);
		MimeMessage emailMessage = new MimeMessage(mailSession);
		
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(getEmailSubject(emailRecord.get(1)));
		emailMessage.setContent(getEmailBody(emailRecord.get(1)), "text/html");
		
		System.out.println("Thread "+Thread.currentThread().getName() + " exited createEmailMessage");
		return emailMessage;
	}
	
	
	private void sendEmail(MimeMessage  emailMessage, List<String> item) throws AddressException, MessagingException {
		
		System.out.println("Thread "+Thread.currentThread().getName() + " entered sendEmail function");
		
		Transport transport = mailSession.getTransport("smtp");

		transport.connect(EmailConstants.EMAIL_HOST, 
						  EmailConstants.EMAIL_FROM_USER_ID,
						  EmailConstants.EMAIL_FROM_USER_PASSWORD);
		
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		
		try {
			//sentEmailQueue.put(emailMessage.getAllRecipients()[0].toString());
			sentEmailQueue.put(item);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Email sent successfully on: "+emailMessage.getAllRecipients()[0].toString());
		System.out.println("Thread "+Thread.currentThread().getName() + " exited sendEmail function");
		
	}

	@Override
	public void run() {
		
		MimeMessage message = null;
		//String emailTo = null;
		List<String> emailRecord = null;
		
		setMailServerProperties();
		
		while(true)
		{
			createMessageLock.lock();
			try {
				System.out.println("Current Queue Size: "+ emailQueue.size());
				emailRecord =  emailQueue.take();
				//String emailTo = emailQueue.take().toString();
				String emailTo = emailRecord.get(0);
				try {
					message = createEmailMessage(emailRecord);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createMessageLock.unlock();
			
			sendMessageLock.lock();
			
			try {
				sendEmail(message,emailRecord);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sendMessageLock.unlock();
			try {
				doRestAfterWork();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private String getEmailSubject(String toEmail) 
	{
		
		return EmailConstants.EMAIL_SUBJECT;
	}
	
	private String getEmailBody(String customerName) throws IOException
	{
		System.out.println("Fetching Email Template");
		
		URL url = getClass().getResource(EmailConstants.EMAIL_TEMPLATE_FILE_PATH);
		File file = new File(url.getPath());
		FileInputStream fileInputStream;
		fileInputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStream.read(bytes);
		fileInputStream.close();
		String fileContent = new String(bytes, "UTF-8");
		
		return fileContent.replace("CUSTOMER_NAME_TEMPLATE", customerName);
	}
	
	private void doRestAfterWork() throws InterruptedException {
		
		System.out.println("Thread "+Thread.currentThread().getName() + " going to rest now.");
		Thread.sleep(EmailConstants.CONSECUTIVE_EMAIL_INTERVAL);		
	}
}
