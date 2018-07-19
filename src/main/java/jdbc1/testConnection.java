package jdbc1;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class testConnection {
    @Test
    public void test1() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://58.87.80.22:38094/demo","coredb","6D031KJTHPon");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        conn.close();
        System.out.println(conn);
    }

    public static    Connection getConnection() throws IOException {
        String driverClass = null;
        String dbUrl = null;
        String dbUser = null;
        String dbPwd = null;
        Connection conn = null;
        InputStream is = testConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        try {
            Class.forName(pros.getProperty("jdbcDriver"));
            conn = DriverManager.getConnection(pros.getProperty("dbUrl"),pros.getProperty("dbUser"),pros.getProperty("dbPwd"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(conn);
        return conn;
    }

    @Test
    public void test2() throws IOException, SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
        conn.close();
        System.out.println(conn);
    }
}
