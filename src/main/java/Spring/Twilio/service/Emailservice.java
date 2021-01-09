package Spring.Twilio.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import Spring.Twilio.EmailRequest;

@Service
public class Emailservice {
	
	
	@Autowired
	SendGrid sendGrid;
	
	public Response sendemail(EmailRequest emailrequest) 
	{
		
		Mail mail = new Mail(new Email("chbharath517@gmail.com"), emailrequest.getSubject(), new Email(emailrequest.getTo()),new Content("text/plain", emailrequest.getBody()));
		mail.setReplyTo(new Email("abc@gmail.com"));
		Request request = new Request();

		Response response = null;

		try {

			request.setMethod(Method.POST);

			request.setEndpoint("mail/send");

			request.setBody(mail.build());

			response=this.sendGrid.api(request);

		} catch (IOException ex) {

			System.out.println(ex.getMessage());

		}

		return response;
		
		
	}

	

}
