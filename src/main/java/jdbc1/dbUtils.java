package jdbc1;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class dbUtils {
    private static  String dbDriver;
    private static  String dbUrl;
    private static  String dbUser;
    private static  String dbPwd;


    static{

        try {
            InputStream is = dbUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);
            dbDriver = pros.getProperty("jdbcDriver");
            dbUrl = pros.getProperty("dbUrl");
            dbUser = pros.getProperty("dbUser");
            dbPwd = pros.getProperty("dbPwd");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean release(Connection conn , Statement sm , ResultSet rs) throws SQLException {
        if(conn != null){
            conn.close();
        }
        if(sm != null){
            sm.close();
        }
        if(rs!= null){
            rs.close();
        }
        return true;
    }
}
