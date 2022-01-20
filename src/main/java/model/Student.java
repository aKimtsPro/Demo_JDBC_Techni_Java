package model;

import java.sql.Date;
import java.time.LocalDate;

public class Student {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Section responsableDe;

    private Student() {
    }

    private Student(long id, String firstname, String lastname, LocalDate birthdate, Section responsableDe) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.responsableDe = responsableDe;
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

    public Section getResponsableDe() {
        return responsableDe;
    }

    public void setResponsableDe(Section responsableDe) {
        this.responsableDe = responsableDe;
    }

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private long id;
//    private String firstname;
//    private String lastname;
        private LocalDate birthdate;
        private Section responsableDe;

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

        public StudentBuilder responsableDe(Section responsableDe){
            this.responsableDe = responsableDe;
            return this;
        }

        public Student build(String nom, String prenom){
            return new Student(
                    id,
                    prenom,
                    nom,
                    birthdate,
                    responsableDe
            );
        }
    }
}
