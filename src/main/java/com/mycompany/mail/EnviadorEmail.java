package com.mycompany.mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author luan
 */
public class EnviadorEmail {

    public static void main(String[] args) {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor
         */
        props.put("mail.smtp.host", "x.x.x.x");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //email e senha de quem vai enviar o email ou usuario e senha do servico de email?
                return new PasswordAuthentication("xxx", "xxx");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("xx@xx.com")); //Remetente

            Address[] toUser = InternetAddress.parse("xx@xx.com"); //Destinatário(s)

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setContent(
                    "<!doctype html>\n"
                    + "<html>\n"
                    + "	<body style=\"background-color: coral;height: 500px\">\n"
                    + "		OLA\n"
                    + "	</body>\n"
                    + "</html>", "text/html");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
            System.out.println("Feito!!!");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
