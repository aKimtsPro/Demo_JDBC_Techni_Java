import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        try( Connection co = ConnectionFactory.getConnection() ) {
            System.out.println("Connection success");
        }catch (SQLException ex){
            System.out.println("Connection failed");
        }

    }

}
