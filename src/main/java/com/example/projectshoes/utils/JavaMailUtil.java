package com.example.projectshoes.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

  public static void sendMail(String recepient) throws MessagingException {
    Properties properties = new Properties();

    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    String myAccountEmail = "19110262@student.hcmute.edu.vn";
    String password = "Hohoaiphong1999lh";
    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myAccountEmail, password);
      }
    });

    Message message = prepareMessage(session, myAccountEmail, recepient);

    Transport.send(message);
    System.out.println("Message sent successfully");
  }

  private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
    Message message = new MimeMessage(session);
    try {

      message.setFrom(new InternetAddress(myAccountEmail));
      message.setRecipient(RecipientType.TO, new InternetAddress(recepient));
      message.setSubject("Rest Password");
      message.setContent("\n"
              + "<!doctype html>\n"
              + "<html lang=\"en-US\">\n"
              + "\n"
              + "<head>\n"
              + "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
              + "    <title>Reset Password Email Template</title>\n"
              + "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n"
              + "    <style type=\"text/css\">\n"
              + "        a:hover {text-decoration: underline !important;}\n"
              + "    </style>\n"
              + "</head>\n"
              + "\n"
              + "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n"
              + "    <!--100% body table-->\n"
              + "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n"
              + "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n"
              + "        <tr>\n"
              + "            <td>\n"
              + "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n"
              + "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n"
              + "                    <tr>\n"
              + "                        <td style=\"height:80px;\">&nbsp;</td>\n"
              + "                    </tr>\n"
              + "                    <tr>\n"
              + "                        <td style=\"text-align:center;\">\n"
              + "                          <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\n"
              + "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\n"
              + "                          </a>\n"
              + "                        </td>\n"
              + "                    </tr>\n"
              + "                    <tr>\n"
              + "                        <td style=\"height:20px;\">&nbsp;</td>\n"
              + "                    </tr>\n"
              + "                    <tr>\n"
              + "                        <td>\n"
              + "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n"
              + "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n"
              + "                                <tr>\n"
              + "                                    <td style=\"height:40px;\">&nbsp;</td>\n"
              + "                                </tr>\n"
              + "                                <tr>\n"
              + "                                    <td style=\"padding:0 35px;\">\n"
              + "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n"
              + "                                            requested to reset your password</h1>\n"
              + "                                        <span\n"
              + "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n"
              + "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n"
              + "                                            We cannot simply send you your old password. A unique link to reset your\n"
              + "                                            password has been generated for you. To reset your password, click the\n"
              + "                                            following link and follow the instructions.\n"
              + "                                        </p>\n"
              + "                                        <a href=\"http://localhost:8080/forgot-password?message="
              + recepient + "\"\n"
              + "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\n"
              + "                                            Password</a>\n"
              + "                                    </td>\n"
              + "                                </tr>\n"
              + "                                <tr>\n"
              + "                                    <td style=\"height:40px;\">&nbsp;</td>\n"
              + "                                </tr>\n"
              + "                            </table>\n"
              + "                        </td>\n"
              + "                    <tr>\n"
              + "                        <td style=\"height:20px;\">&nbsp;</td>\n"
              + "                    </tr>\n"
              + "                    <tr>\n"
              + "                        <td style=\"text-align:center;\">\n"
              + "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.hoaiphong.com</strong></p>\n"
              + "                        </td>\n"
              + "                    </tr>\n"
              + "                    <tr>\n"
              + "                        <td style=\"height:80px;\">&nbsp;</td>\n"
              + "                    </tr>\n"
              + "                </table>\n"
              + "            </td>\n"
              + "        </tr>\n"
              + "    </table>\n"
              + "    <!--/100% body table-->\n"
              + "</body>\n"
              + "\n"
              + "</html>",
          "text/html");
      return message;
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }

}
