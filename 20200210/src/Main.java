import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String ur1 = "jdbc:mysql://127.0.0.1:3306/java20_0210?useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(
                ur1, user,password
        );

        //// 插入一些数据，供 SELECT 用
        {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO users (id, name) VALUES (1, '盖飞宇'), (2, '宋雨荷'), (3, '郭晨曦')";
            statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
            statement.close();
        }

        // 执行 SELECT 语句
        {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> nameList = new ArrayList<>();
            while (resultSet.next()) {
                /*
                需要不同的类型，调用不同的方法
                int r = resultSet.getInt();
                long r = resultSet.getLong();
                String r = resultSet.getString();
                */

                /*
                int id = resultSet.getInt(1);   // 下标是从 1 开始的，不是从 0 开始的
                int id = resultSet.getInt("id"); // 通过字段名称获取
                 */
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                // 结果集马上需要销毁，所以通过 List 保存结果
                nameList.add(name);

                System.out.println(id + ", " + name);
            }

            resultSet.close();

            statement.close();
        }

  /*
        // INSERT
        String sql = "INSERT INTO users (id, name) VALUES (1, '陈沛鑫')";
        statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
        */

        /*
        // UPDATE
        String sql = "UPDATE users SET name = '高博' WHERE id = 1";
        statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
        */

        /*
        // Delete
        String sql = "DELETE FROM users WHERE id = 1";
        statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
        */

        connection.close();
    }
}
