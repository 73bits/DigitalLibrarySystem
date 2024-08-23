package utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class DBConnect {
    private static final String FILE = "application.properties";
    private static String URL, USER, PASS;
    private static Connection con;
    private static Properties properties;
    
    public static Connection getConnection(){
        properties = new Properties();
        try(InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream(FILE)) {
            if(inputStream != null) {
                properties.load(inputStream);
                URL = properties.getProperty("db.url");
                USER = properties.getProperty("db.username");
                PASS = properties.getProperty("db.password");
            } else {
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}