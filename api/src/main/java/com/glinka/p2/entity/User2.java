package com.glinka.p2.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@ToString
@EqualsAndHashCode
@Data
@Entity
@Table(name = "user")
public class User2 {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(name = "name", columnDefinition = "VARCHAR(128)")
    private String name;
    @NotNull
    @Column(name = "surname", columnDefinition = "VARCHAR(128)")
    private String surname;
    @Email @NotNull
    @Column(name = "email", columnDefinition = "VARCHAR(128)")
    private String email;
    @Size(min=3, max=20) @NotNull
    @Column(name = "login", columnDefinition = "VARCHAR(128)")
    private String login;
    @Size(min=8) @NotNull
    @Column(name = "password", columnDefinition = "VARCHAR(128)")
    private String password;
//    @Pattern("yyyy-MM-dd")
//    @Column(name = "birthday", columnDefinition = "VARCHAR(128)")
//    private String birthday;
//    @Column(name = "gender", columnDefinition = "BOOLEAN")
//    private boolean gender;

}
