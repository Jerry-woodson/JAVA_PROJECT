package org.example.domain;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
public class Student {
    private int id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;

}