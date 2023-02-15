package com.avtukhov.trial_project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String userName;
    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Quote> userQuotes;
    @OneToMany(mappedBy = "user1")
    @JsonManagedReference
    private List<Vote> userVotes;

    public User1(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User1() {
    }
}
