package utilities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

public class NotifyDefaulter {
    private final String FILE = "application.properties";
    private Properties properties;
    private String contact, text, status, CON, SID, TOKEN;
    private Message message;
    
    private boolean getAuthentication() {
        try(InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream(FILE)) {
            properties = new Properties();
            if(inputStream != null) {
                properties.load(inputStream);
                CON = properties.getProperty("tw.contact");
                SID = properties.getProperty("tw.sid");
                TOKEN = properties.getProperty("tw.token");
                return true;
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public void notifyDefaulter(String memberId) {
        if(getAuthentication() == false) {
            return;
        }
        
        try {
            Connection con = DBConnect.getConnection();
            Statement smt = con.createStatement();
            ResultSet rst = smt.executeQuery("SELECT * FROM members_details where member_id =" + memberId);
            if(rst.next()) {
                contact = rst.getString("contact");
            }
            
            text = "REMINDER: you have some books to return, please return them before due date. You will be fined afterwards. Thank you.";
            Twilio.init(SID, TOKEN);
            message = Message.creator(new PhoneNumber("+91" + contact), new PhoneNumber(CON), text).create();
            status = Message.fetcher(message.getSid()).fetch().getStatus().toString();
            
            switch(status) {
                    case "sent" -> JOptionPane.showMessageDialog(null, "Message sent to " + contact);
                    case "failed" -> JOptionPane.showMessageDialog(null, "Message Failed", "Error", 2);
                    case "undelivered" -> JOptionPane.showMessageDialog(null, "Message Undelivered", "Error", 2);
            }
            
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}