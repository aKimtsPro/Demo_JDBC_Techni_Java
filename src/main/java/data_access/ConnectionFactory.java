package data_access;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/dbslide";
    private static final String USER = "root";
    private static final String PSWD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PSWD);
    }



}
