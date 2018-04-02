package com.fundoonotes.utility;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**Purpose: This class is to send Email using java mail API
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public class SendEmail
{

   private static Logger logger = Logger.getLogger(SendEmail.class.getName());

   public static void sendEmail(String to, String subject, String message)
   {
      String user = "bridgelabzsolutions@gmail.com";
      String password = "bridgelabz";

      Properties properties = new Properties();
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.from", "bridgelabzsolutions@gmail.com");
      properties.put("mail.smtp.port", "465");
      properties.put("mail.debug", "true");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.socketFactory.fallback", "false");

      Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication()
         {

            return new PasswordAuthentication(user, password);
         }
      });
      try {
         MimeMessage message1 = new MimeMessage(session);
         message1.setFrom(new InternetAddress(user));
         message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message1.setSubject(subject);
         message1.setText(message);

         Transport.send(message1);
         logger.info("Email sent successfully...");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}


























/*
 * @Autowired private static MailSender mailSender;
 * 
 * public static MailSender sendEmail(String to, String subject, String from) {
 * try { SimpleMailMessage message = new SimpleMailMessage(); Date date=new
 * Date(); date.getTime();
 * 
 * message.setTo(to); message.setFrom(from); message.setText(
 * "Confirm Registration"); message.setSubject(subject);
 * 
 * System.out.println("SEnding Email..." + message); mailSender.send(message);
 * 
 * System.out.println("MAil sent successfully..");
 * 
 * } catch (Exception e) { System.err.println("Error sending a mail..");
 * e.printStackTrace(); } return mailSender; }
 */
