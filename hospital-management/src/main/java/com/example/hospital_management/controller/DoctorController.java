package com.example.hospital_management.controller;

import com.example.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/doctor/apis")

public class DoctorController {

    HashMap<Integer, Doctor> doctorHashMapDb= new HashMap<>();

    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctorRequest){
        doctorHashMapDb.put(doctorRequest.getId(), doctorRequest);
        return "Doctor saved successfully";
    }

    @GetMapping("/getDoctor")
    public  HashMap<Integer, Doctor> getAllDoctor(){
        return doctorHashMapDb;
    }
}




/*
{
    "id":1,
    "name":"Dr. Vishesh Shukla",
    "age":24,
    "email":"vishes.musk4528@gmail.com",
    "specialization":"pulmonologist"
}

{
	"id": 2,
	"name": "Dr. Prince Yadav",
	"age": 27,
	"email": "prince.md7@gmail.com",
	"specialization": "Medicine"
}
 */