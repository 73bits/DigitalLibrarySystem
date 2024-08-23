package utilities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class NotifyReturn {

        private final String FILE = "application.properties";
        private String bookId, memberId, memberName, bookName, issueDate;
        private String contact, text, status, SID, TOKEN, CONTACT;
        private long diff, days;
        private Properties properties;
        private Message message;
        private Date dueDate;
        private Statement smt;
        private ResultSet rst;

        public void notifyReturn(String issuesId) {
                properties = new Properties();
                try (InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream(FILE)) {
                        if (inputStream != null) {
                                properties.load(inputStream);
                                SID = properties.getProperty("tw.sid");
                                TOKEN = properties.getProperty("tw.token");
                                CONTACT = properties.getProperty("tw.contact");
                        } else {
                                JOptionPane.showMessageDialog(null, "Error: couldn't find PID and TOKEN");
                                return;
                        }
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }

                try (Connection con = DBConnect.getConnection()) {
                        smt = con.createStatement();
                        rst = smt.executeQuery("SELECT * FROM issue_book_details WHERE id = " + issuesId);
                        while (rst.next()) {

                                bookId = rst.getString("book_id");
                                memberId = rst.getString("member_id");
                                memberName = rst.getString("member_name");
                                bookName = rst.getString("book_name");
                                issueDate = rst.getString("issue_date");
                                dueDate = rst.getDate("due_date");
                        }

                        rst = smt.executeQuery("SELECT * FROM members_details WHERE member_id = " + memberId);
                        if (rst.next()) {
                                contact = rst.getString("contact");
                        }

                        diff = new Date().getTime() - dueDate.getTime();
                        days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        if (days < 0) {
                                days = 0;
                        }
                        text = "Hey " + memberName + " (ID:" + memberId + "), " + "you have returned a book : " + bookName
                                        + " (ID:" + bookId + "), " + "Issue id : " + issuesId + ", Issue date " + issueDate
                                        + " and Due date " + dueDate + ". Late fine is Rs. " + days * 5 + ". Thank you.";

                        Twilio.init(SID, TOKEN);
                        message = Message.creator(new PhoneNumber("+91" + contact), new PhoneNumber(CONTACT), text).create();
                        status = Message.fetcher(message.getSid()).fetch().getStatus().toString();

                        switch (status) {
                                case "sent" ->
                                        JOptionPane.showMessageDialog(null, text, "Message Sent", 1);
                                case "failed" ->
                                        JOptionPane.showMessageDialog(null, "Message Failed", "Error", 2);
                                case "undelivered" ->
                                        JOptionPane.showMessageDialog(null, "Message Undelivered", "Error", 2);
                        }

                } catch (SQLException e) {
                        System.err.println(e);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error Sending Message :" + e);
                }
        }
}
