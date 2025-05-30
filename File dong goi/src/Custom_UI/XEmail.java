package Custom_UI;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import javax.swing.JOptionPane;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.swing.JOptionPane;


/**
 *
 * @author tungt
 */
public class XEmail {

    public void sendEmail(String emailToAddress, String textMessage) {
         try {

                Properties p = new Properties();
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                p.put("mail.smtp.port", "587");

                String user = "hethongpacepro@gmail.com";
                String pas = "qanmaewrsefzgabm";

                Session s = Session.getInstance(p,
                        new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pas);
                    }
                });
                Message msg = new MimeMessage(s);
                msg.setFrom(new InternetAddress(user));
                
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));

                msg.setSubject("Thông tin mật khẩu");

                msg.setContent("Mã xác nhận của bạn Là : " + textMessage, "text/html; charset=utf-8");
              
                Transport.send(msg);
                //MsgBox.alert(null, "Gửi Thành Công Đến Email " + emailToAddress);
                JOptionPane.showMessageDialog(null, "Gửi Thành Công Đến Email " + emailToAddress);
            } catch (Exception e) {
                //MsgBox.alert(null, String.valueOf(e));
                JOptionPane.showMessageDialog(null, String.valueOf(e));
                System.out.println(e);
            }

    }
}
