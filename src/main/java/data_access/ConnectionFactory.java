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
        return getDataSource("dbslide_bis").getConnection();
    }

    public static DataSource getDataSource(String dbName){
       switch (dbName){
           case "dbslide":
               return getDBSlideDatasource();
           case "dbslide_bis":
               return getDBSlideBisDatasource();
           default:
               throw new IllegalArgumentException("wrong name");
       }
    }

    private static DataSource getDBSlideDatasource(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("dbslide");
        ds.setUser(USER);
        ds.setPassword(PSWD);
        return ds;
    }
    private static DataSource getDBSlideBisDatasource(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("dbslide_bis");
        ds.setUser(USER);
        ds.setPassword(PSWD);
        return ds;
    }




}
