package model;

import lombok.*;

//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Section {

    private long id;
    private String nom;
    private Student delegue;

}
