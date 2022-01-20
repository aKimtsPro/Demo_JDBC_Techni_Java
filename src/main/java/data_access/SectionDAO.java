package data_access;

import model.Section;
import model.Student;

import java.sql.*;
import java.util.List;

// DEMO 3
// DAO : Data Access Object = Objet d'Accès aux Données
public class SectionDAO {

    // CREATE
    public boolean insert( Section toInsert ){

        String query = "INSERT INTO section(section_id, section_name) VALUES ( ? , ? )";

        try (
                Connection co = ConnectionFactory.getConnection();
                PreparedStatement stmt = co.prepareStatement(query);
        ){
            stmt.setLong(1, toInsert.getId() );
            stmt.setString(2, toInsert.getNom() );
            return 1 == stmt.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;

    }

    // READ
    public Section getOne(int id){

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
    public List<Section> getAll(){
        return null;
    }

    // UPDATE

    // DELETE
//    public ? delete(String nomSection){
//        // qqchose
//    }

}
