package com.hospital.management.hospitalManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public String getAllDoctors() {
        System.out.println("=== STEP 1: GET /doctor/all called ===");
        String result = doctorService.getAllDoctors();
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;  // BREAKPOINT 1 - set here to see result before returning
    }

    @GetMapping("/{id}")
    public String getDoctorById(@PathVariable int id) {
        System.out.println("=== STEP 1: GET /doctor/" + id + " called ===");
        String result = doctorService.getDoctorById(id);
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;  // BREAKPOINT 2 - set here to inspect result
    }

    @PostMapping
    public String addDoctor(@RequestBody Doctor doctor) {
        System.out.println("=== STEP 1: POST /doctor called ===");
        System.out.println("=== STEP 1 DATA: name=" + doctor.getName() + ", id=" + doctor.getId() + ", specialty=" + doctor.getSpecialty() + " ===");
        String result = doctorService.addDoctor(doctor);  // BREAKPOINT 3 - set here to see doctor object received
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;
    }

    @PutMapping("/{id}")
    public String updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        System.out.println("=== STEP 1: PUT /doctor/" + id + " called ===");
        System.out.println("=== STEP 1 DATA: name=" + doctor.getName() + ", specialty=" + doctor.getSpecialty() + " ===");
        String result = doctorService.updateDoctor(id, doctor);  // BREAKPOINT 4
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        System.out.println("=== STEP 1: DELETE /doctor/" + id + " called ===");
        String result = doctorService.deleteDoctor(id);  // BREAKPOINT 5
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;
    }

    @PostMapping("/{id}/patient")
    public String addPatient(@PathVariable int id, @RequestBody Patient patient) {
        System.out.println("=== STEP 1: POST /doctor/" + id + "/patient called ===");
        System.out.println("=== STEP 1 DATA: name=" + patient.getName() + ", id=" + patient.getId() + ", age=" + patient.getAge() + " ===");
        String result = doctorService.addPatient(id, patient);  // BREAKPOINT 6
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;
    }

    @DeleteMapping("/{id}/patient/{pid}")
    public String deletePatient(@PathVariable int id, @PathVariable int pid) {
        System.out.println("=== STEP 1: DELETE /doctor/" + id + "/patient/" + pid + " called ===");
        String result = doctorService.deletePatient(id, pid);  // BREAKPOINT 7
        System.out.println("=== STEP 1 RESULT: " + result + " ===");
        return result;
    }
}
