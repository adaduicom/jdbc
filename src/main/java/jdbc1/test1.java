package jdbc1;

import org.junit.Test;

import java.sql.*;

public class test1 {
    @Test
    public void update(){


    }


    @Test
    public void test4() throws SQLException {
        Connection conn = dbUtils.getConnection();
        System.out.println(conn);
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement("select * from customers where id = ?");
        preparedStatement.setInt(1,2);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            System.out.println(rs.getObject(2));
        }
        else {
            System.out.println("没有记录");
        }

        dbUtils.release(conn, preparedStatement, rs);

    }
    @Test
    public void test3() throws SQLException {
        Connection conn = dbUtils.getConnection();
        System.out.println(conn);
        Statement sm  = conn.createStatement();
        ResultSet rs = sm.executeQuery("select * from customers ");
        System.out.println(rs);
        while(rs.next())
        {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getString(4));

        }
        System.out.println("-----");
        rs.close();
        rs = sm.executeQuery("select count(*) from customers");
        rs.next();
        System.out.println(rs.getObject(1));
        dbUtils.release(conn,sm,rs);
    }
    @Test
    public void test2() throws SQLException {
        Connection conn = dbUtils.getConnection();
        System.out.println(conn);
        Statement sm = conn.createStatement();
        sm.executeUpdate("insert into customers (name, age, birth) values ('李四',22,'2018-05-01')");
        sm.close();
        conn.close();
    }
    /*
    获取连接
     */
    @Test
    public void test1(){
        Connection conn = dbUtils.getConnection();
        System.out.println(conn);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
