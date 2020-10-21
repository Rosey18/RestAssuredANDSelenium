package jdbc;

import java.sql.*;

public class jdbcConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost/employee";
        String username = "postgres";
        String password = "qaz123.123";
        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(url, username, password);
             //System.out.println("We're connected");
             Statement statement = conn.createStatement()) {
            //statement.executeUpdate("drop table public.purpose");
            //statement.executeUpdate("CREATE TABLE PUBLIC.PURPOSE(ID_HAR SERIAL PRIMARY KEY, NAZN CHAR(50), NAZN_KAZ CHAR(50)");
            //statement.executeUpdate("insert into purpose values(1, ENGLISH, RUSSIAN)");
            ResultSet resultSet = statement.executeQuery("select * from catalog.students");
            while (resultSet.next()) {
                System.out.println(resultSet);
                System.out.println("id: " + resultSet.getString(1));
                System.out.println("surname: " + resultSet.getString(2));
                System.out.println("gender: " + resultSet.getString(3));
                System.out.println("name: " + resultSet.getString(4));
                System.out.println("born: " + resultSet.getString(5));
                System.out.println("----------------");
            }
        }
    }
}
