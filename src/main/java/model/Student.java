package model;

import java.sql.Date;
import java.time.LocalDate;

public class Student {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Section section;

    private Student() {
    }

    private Student(long id, String firstname, String lastname, LocalDate birthdate, Section section) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.section = section;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private long id;
//    private String firstname;
//    private String lastname;
        private LocalDate birthdate;
        private Section section;

        public StudentBuilder id(long id){
            this.id = id;
            return this;
        }

        // Si on oblige l'utilisateur a fournir les données
        // dans la methode build ces methodes ne sont plus utiles
//    public StudentBuilder firstname(String firstname){
//        this.firstname = firstname;
//        return this;
//    }
//
//    public StudentBuilder lastname(String lastname){
//        this.lastname = lastname;
//        return this;
//    }

        public StudentBuilder birthdate(LocalDate birthdate){
            this.birthdate = birthdate;
            return this;
        }

        public StudentBuilder birthdate(Date birthdate){
            this.birthdate = birthdate.toLocalDate();
            return this;
        }

        public StudentBuilder section(Section section){
            this.section = section;
            return this;
        }

        public Student build(String nom, String prenom){
            return new Student(
                    id,
                    prenom,
                    nom,
                    birthdate,
                    section
            );
        }
    }
}
