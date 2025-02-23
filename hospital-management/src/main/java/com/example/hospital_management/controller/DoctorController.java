package com.example.hospital_management.controller;

import com.example.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/doctor/apis")

public class DoctorController {

    HashMap<Integer, Doctor> doctorHashMapDb= new HashMap<>();

    @PostMapping("/saveDoctor")
    public String saveDoctorDetails(@RequestBody Doctor doctorRequest){
        doctorHashMapDb.put(doctorRequest.getId(), doctorRequest);
        return "Doctor saved successfully";
    }

    @GetMapping("/findAllDoctor")
    public  HashMap<Integer, Doctor> getAllDoctor(){
        return doctorHashMapDb;
    }

    @GetMapping("/findDoctorById/{doctorId}")
    public Doctor getDoctorById(@PathVariable("doctorId") int doctorId){
        Doctor doctor= doctorHashMapDb.get(doctorId);
        System.out.println(doctor);
        return doctor;
    }

    @GetMapping("/findDoctorByName")
    public Doctor getDoctorByName(@RequestParam("doctorname") String doctorName){

        for(Doctor doctor: doctorHashMapDb.values()){
            if(doctor.getName().equals(doctorName)){
                return doctor;
            }
        }
        return null;
    }


    @GetMapping("/findDoctorBySpecialization")
    public List<Doctor> getDoctorBySpecialization(@RequestParam String specialization){
        List<Doctor> doctorList =new ArrayList<>();
        for(Doctor doctor: doctorHashMapDb.values()){
            if(doctor.getSpecialization().equalsIgnoreCase(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    @GetMapping("/findByCombination")
    public Doctor getValuesByCombination(@RequestParam int id, @RequestParam String specialization){
        for(Doctor doctor: doctorHashMapDb.values()){
            if(doctor.getId()==id && doctor.getSpecialization().equalsIgnoreCase(specialization))
                return doctor;
        }

        return null;
    }

    @GetMapping("/findByCombinationNotMandatoryRequestParam")
    public List<Doctor> getValuesByCombinationOptional(@RequestParam (required = false)int age, @RequestParam(required = false) String specialization){
        List<Doctor> doctorList=new ArrayList<>();
        for(Doctor doctor:doctorHashMapDb.values()){
            if(age!=0 && specialization!=null){
                if (doctor.getAge()==age && doctor.getSpecialization().equalsIgnoreCase(specialization)){
                    doctorList.add(doctor);
                }
            } else if (age==0 && specialization!=null) {
                if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                    doctorList.add(doctor);
                }
            }
            else if(specialization==null){
                if(doctor.getAge()==age)
                {
                    doctorList.add(doctor);
                }
            }
        }
        return doctorList;
    }

    @DeleteMapping("/deleteDoctorDetails/{id}")
    public String deleteDoctor(@PathVariable int id){
        doctorHashMapDb.remove(id);
        return "Doctor details with this id "+id+" gets deleted";
    }

    @PutMapping("/updateDoctorDetails/{doctorid}")
    public String updateDoctor(@PathVariable int doctorid, @RequestBody Doctor doctorRequest){
        //check it doctor id is present
        //if present update
        //else not
        String msg;
        for(Doctor doctor: doctorHashMapDb.values()) {
            if (doctor != null) {
//                doctorHashMapDb.put(id, doctorRequest);
//                return "New doctor details gets updated";
                if (doctor.getId() == doctorid) {
                    doctorHashMapDb.put(doctorid, doctorRequest);
                    msg = "Previous Details of this id " + doctorid + " get replaced with new details "+doctorRequest;
                    return msg;
                } else {
                    doctorHashMapDb.put(doctorid, doctorRequest);
                    msg = "New Details of the id " + doctorid + " is added.";
                    return msg;
                }
           }
            else{
                    //return "Doctor not found with this id " + id;
                msg="Doctor details object or class is missing to get updated";
                return msg;
                }
            }
        return null;
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