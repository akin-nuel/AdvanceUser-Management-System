package com.example.AdvanceUser.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity(name = "advance_user")
public class AdvanceUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_Name")
    @Length(min = 6, message = "enter your first name first")
    private String fullName;
    @Email(message = "Please Enter Email")
    private String email;

    @Length(min = 3, message = "male or female")
    private String gender;

    public AdvanceUser(String fullName, String email, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "phone_number")
    @Pattern(regexp = "[0-9]{11}")
    private String phoneNumber;

    public AdvanceUser() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
