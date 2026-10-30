package com.hospital.management.hospitalManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    public final DoctorService doctorService;

    @Autowired
    DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    HashMap<Integer, Doctor> mp = new HashMap<>();

    @GetMapping("/all")
    public String getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public String addDoctor(@RequestBody Doctor doctor) {
        mp.put(doctor.getId(), doctor);
        return "Doctor added successfully";
    }

    @PostMapping("/{id}")
    public String addPatient(@PathVariable int id, @RequestBody Patient patient) {
        if(mp.containsKey(id)) {
            mp.get(id).getPatients().add(patient);
            return "Patient added successfully";
        }
        return "No doctor with given ID exists";
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        if(mp.containsKey(id)) {
            mp.remove(id);
            return "Doctor removed successfully";
        }
        return "No doctor with given ID exists";
    }

    @DeleteMapping("/{id}/{pid}")
    public String deletePatient(@PathVariable int id, @PathVariable int pid) {
        if(mp.containsKey(id)) {
            ArrayList<Patient> patients = mp.get(id).getPatients();
            for(int i = 0; i < patients.size(); i++) {
                if(patients.get(i).getId() == pid) {
                    patients.remove(i);
                    return "Patient removed successfully";
                }
            }
            return "No patient with given ID exists";
        }
        return "No doctor with given ID exists";
    }
}
