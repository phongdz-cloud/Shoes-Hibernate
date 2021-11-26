package com.example.projectshoes.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

public class SendGridEmailer {


  public static void main(String[] args) throws IOException {

    Email from = new Email("xxxxx");
    Email to = new Email("xxxxxx"); // use your own email address here

    String subject = "Sending with Twilio SendGrid is Fun";
    Content content = new Content("text/html",
        "and <em>easy</em> to do anywhere with <strong>Java</strong>");

    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("xxxxx");
    Request request = new Request();

    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(mail.build());

    Response response = sg.api(request);

    System.out.println(response.getStatusCode());
    System.out.println(response.getHeaders());
    System.out.println(response.getBody());
  }
}
