package meabh.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import meabh.Exceptions.DaoException;

public class MySqlDao {
    public Connection getConnection() throws DaoException{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/music";
        String user = "root";
        String password = "";
        Connection connection = null;

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find driver class " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Connection failed " + e.getMessage());
            System.exit(2);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
