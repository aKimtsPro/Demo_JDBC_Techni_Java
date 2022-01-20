import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            int id = Integer.parseInt( sc.nextLine() );
            printStudentWithSectionId(id);
        }
        catch (NumberFormatException exception) {
            System.out.println("id invalide");
        }

    }

    public static void printSections(){
        String query = "SELECT * FROM section";
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){

            while( rs.next() ){
                int sectionId = rs.getInt("section_id") ;
                String sectionName = rs.getString("section_name");
                int delegateId = rs.getInt("delegate_id");

                System.out.println(sectionId + " - " + sectionName + " - " + delegateId);
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void printStudentWithSectionId(int sectionId){

        String query =
                " SELECT student_id, first_name, last_name, birth_date, section_name" +
                " FROM student st" +
                "    JOIN section se" +
                "        ON st.section_id = se.section_id" +
                " WHERE st.section_id = " + sectionId;

        try(
            Connection co = ConnectionFactory.getConnection();
            Statement statement = co.createStatement();
            ResultSet rs = statement.executeQuery( query );
        ){

            while( rs.next() ){
                int id = rs.getInt("student_id");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String sectionName = rs.getString("section_name");
                Date date = rs.getDate("birth_date");

                System.out.println("---------------------");
                System.out.println("- id : "+ id);
                System.out.println("- firstname : "+ firstname);
                System.out.println("- lastname : "+ lastname);
                System.out.println("- section name : "+ sectionName);
                System.out.println("- date : "+ date);

            }

            System.out.println("---------FIN---------");

        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}
