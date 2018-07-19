package jdbc1;

import com.sun.deploy.util.ReflectionUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Dao {

    public void updatte(String sql,Object ... args){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dbUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            for(int i = 0 ;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                dbUtils.release(conn,preparedStatement,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T get(Class<T> clazz,String sql,Object ... args){
        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();

                for(int i = 0;i< columnCount;i++){
                    String columnLabel = resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue = resultSet.getObject(i+1);
                    map.put(columnLabel,columnValue);
                }
                entity = clazz.newInstance();
                for(Map.Entry<String,Object> entry : map.entrySet()){
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();
                    ReflectionUtils.setFieldValue(entity,propertyName,value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtils.release(connection,preparedStatement,resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

}
