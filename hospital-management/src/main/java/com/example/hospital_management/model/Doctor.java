package com.example.hospital_management.model;

import lombok.Data;  //dependency which will add getter setter internally

@Data
//This annotation will help in creating getter setter internally
public class Doctor {

    private int id;
    private String name;
    private  int age;
    private String email;
    private String specialization;


}
