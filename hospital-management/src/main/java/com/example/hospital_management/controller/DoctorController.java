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
}
