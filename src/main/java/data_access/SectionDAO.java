package data_access;

import model.Section;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DEMO 3
// DAO : Data Access Object = Objet d'Accès aux Données
public class SectionDAO {

    // CREATE
    public boolean insert( Section toInsert ){

        String query = "INSERT INTO section(section_id, section_name) VALUES ( ? , ? )";

        try (
                Connection co = ConnectionFactory.getConnection();
                PreparedStatement stmt = co.prepareStatement( query );
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
                " SELECT se.section_id as section_id, section_name, first_name, last_name, birth_date, student_id " +
                " FROM section se " +
                "     JOIN student st " +
                "        ON se.delegate_id = st.student_id " +
                " WHERE se.section_id = " + id;

        try(
                Connection co = ConnectionFactory.getConnection();
                Statement statement = co.createStatement();
                ResultSet rs = statement.executeQuery( query );
        ){

            if( rs.next() )
                return extract( rs );

        }
        catch ( SQLException ex ){
            ex.printStackTrace();
        }
        return null;

    }
    public List<Section> getAll(){
        String query = """
                SELECT se.section_id as section_id, section_name, student_id, first_name, last_name, birth_date
                FROM section se
                    JOIN student st
                        ON se.delegate_id = student_id;
                """;

        try(
            Connection co = ConnectionFactory.getConnection();
            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery( query );
        ){

            List<Section> sections = new ArrayList<>();
            while( rs.next() )
                sections.add( extract(rs) );
            return sections;

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    // UPDATE

    public boolean update(long id, String newName, long newDelegateId ){

        String query =
                """
                UPDATE section
                SET section_name = ?, delegate_id = ?
                WHERE section_id = ?
                """;

        try(
                Connection co = ConnectionFactory.getConnection();
                PreparedStatement stmt = co.prepareStatement( query );
        ){
            stmt.setLong(2, newDelegateId);
            stmt.setString(1, newName);
            stmt.setLong(3, id);

            return 1 == stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean deleteById(long id){
        String query = "DELETE FROM section WHERE section_id = ?";

        try(
            Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement( query );
        ){
            stmt.setLong(1, id);
            return 1 == stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public int deleteByName(String nomSection){

        String query = "DELETE FROM section WHERE section_name = ?";

        try(
            Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(query);
        ){
            stmt.setString(1, nomSection);
            return stmt.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteStartsWith(char start){
        if( (start < 'a' || start > 'z') && (start < 'A' || start > 'Z' ) )
            throw new IllegalArgumentException("start doit être une lettre");

        String query = "DELETE FROM section WHERE section_name LIKE ?";
//        String query = "DELETE FROM section WHERE section_name LIKE ?%";

        try (
                Connection co = ConnectionFactory.getConnection();
                PreparedStatement stmt = co.prepareStatement(query);
        ){

            stmt.setString(1,start + "%");
//            stmt.setString(1,start + "");
            return stmt.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;

    }


    private Section extract(ResultSet rs) throws SQLException {

        Section s = new Section();

        s.setId(rs.getInt("section_id"));
        s.setNom(rs.getString("section_name"));

        Student st = Student.builder()
                .responsableDe(s)
                .birthdate(rs.getDate("birth_date"))
                .id(rs.getInt("student_id"))
                .build(
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );

        s.setDelegue(st);
        return s;
    }

}
