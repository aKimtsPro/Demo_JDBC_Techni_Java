import data_access.ConnectionFactory;
import model.Section;
import model.Student;

import java.sql.*;

public class Program {

    public static void main(String[] args) {

        // DEMO 1
//        printSections();

        // EXO 1
//        Scanner sc = new Scanner(System.in);
//        try {
//            System.out.println("Veuillez s'il vous est gré entrer un identifiant: ");
//            int id = Integer.parseInt( sc.nextLine() );
//            printStudentWithSectionId(id);
//        }
//        catch (NumberFormatException exception) {
//            System.out.println("id invalide");
//        }

        // DEMO 2

        Section s = getSection(1110);

        System.out.println("----- SECTION -----");
        System.out.println(s.getNom());
        System.out.println(s.getDelegue().getFirstname() + " " + s.getDelegue().getLastname());

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

    public static Section getSection(int id){

        String query =
                " SELECT se.section_id as id, section_name, first_name, last_name, birth_date, student_id " +
                " FROM section se " +
                "     JOIN student st " +
                "        ON se.delegate_id = st.student_id " +
                " WHERE se.section_id = " + id;

        try(
                Connection co = ConnectionFactory.getConnection();
                Statement statement = co.createStatement();
                ResultSet rs = statement.executeQuery( query );
        ){

            if( rs.next() ){

                Section s = new Section();

                s.setId( rs.getInt("id") );
                s.setNom( rs.getString("section_name"));

                Student st = Student.builder()
                        .responsableDe( s )
                        .birthdate( rs.getDate("birth_date") )
                        .id( rs.getInt("student_id") )
                        .build(
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        );

                s.setDelegue( st );


                return s;

            }

        }
        catch ( SQLException ex ){
            ex.printStackTrace();
        }
        return null;

    }

}
