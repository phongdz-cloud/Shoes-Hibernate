package com.example.projectshoes.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

public class SendGridEmailerUtil {

  public static void sendMail(String recepient, String template, String subject) {
    try {
      SendGrid sg = new SendGrid(
          System.getenv("APIKEY"));
      Email from = new Email(System.getenv("EMAIL"));
      Email to = new Email(recepient); // use your own email address here
      Content content = new Content("text/html",
          template);
      Mail mail = new Mail(from, subject, to, content);
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getHeaders());
      System.out.println(response.getBody());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
