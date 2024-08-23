package utilities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;

public class NotifyOTP {
    private final String FILE = "application.properties";
    private String otp;
    private Message message;
    private String text, status, CON, SID, TOKEN;
    private Properties properties;
    
    public String generateOTP(String number) {
        int max = 999999, min = 100000;
        otp = String.valueOf(new Random().nextInt(max - min + 1) + min);
        properties = new Properties();
        
        try(InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream(FILE)) {
            if(inputStream != null) {
                properties.load(inputStream);
                CON = properties.getProperty("tw.contact");
                SID = properties.getProperty("tw.sid");
                TOKEN = properties.getProperty("tw.token");
            } else {
                JOptionPane.showMessageDialog(null, "Error: couldn't find PID and TOKEN");
                return "-1";
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        if (!number.matches("[6789]{1}[0-9]{9}")) {
            JOptionPane.showMessageDialog(null, " Please enter valid contact number");
            return "-1";
        }
        
        try {
            text = "Hey, your OTP is " + otp + " to verify into LibraryManagementSystem. Thanks you.";
            Twilio.init(SID, TOKEN);
            message = Message.creator(new PhoneNumber("+91" + number), new PhoneNumber(CON), text).create();
            status = Message.fetcher(message.getSid()).fetch().getStatus().toString();
            
            switch(status) {
                    case "sent" -> JOptionPane.showMessageDialog(null, "OTP Sent");
                    case "failed" -> JOptionPane.showMessageDialog(null, "Message Failed", "Error", 2);
                    case "undelivered" -> JOptionPane.showMessageDialog(null, "Message Undelivered", "Error", 2);
            }
            
            JOptionPane.showMessageDialog(null, "OTP sent to " + number);
        } catch(HeadlessException e) {
            System.err.println(e);
        }
        return otp;
    }
}