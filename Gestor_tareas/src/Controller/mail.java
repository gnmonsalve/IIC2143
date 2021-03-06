package Controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
comprobarMail(String mail):boolean 
	Retorna un bool que nos dice si el correo existe. 
	Si existe le envía un correo de bienvenida.
send(String mensaje, String asunto): boolean
	Retorna un bool si pudo enviar el correo al mail ingresado
	
*/


public class mail {

	public static String Username = "gestortareasiic2143@gmail.com";
    public static String PassWord = "iic2143iic2143";
    public String mail="";

    public boolean comprobarMail(String mail){
    	if (send("Bienvenido al gestor de tareas! Desde ahora podrás recibir notificaciones de la aplicación.\n We <3 u", mail, "Bienvenido al gestor de tareas"))
    	{
    		this.mail = mail;
    		return true;
    	}
    	return false;
    }

    /**
    public boolean send(String mensaje, String asunto){
    	return(send(mensaje, mail, asunto));
    }
     **/

	public boolean send(String mensaje, String destinatario, String asunto) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                  
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            return true;
       

        } catch (MessagingException e) {            
            return false;
        }
       
    }
}


