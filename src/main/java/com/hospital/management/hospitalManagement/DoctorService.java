package com.hospital.management.hospitalManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    public final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        System.out.println("Doctor Service Object created");
        this.doctorRepository = doctorRepository;
    }

    public String getAllDoctors() {
        String response = "LIST OF DOCTORS: \n";
        for(int key : doctorRepository.getAllDoctorIds()) {
            String name = doctorRepository.getDoctor(key).getName();
            int id = doctorRepository.getDoctor(key).getId();
            response += "[name=" + name + ",id=" + id + "]\n";
        }
        return response;
    }

    public String getDoctorById(int id) {
        Doctor doctor  = doctorRepository.getDoctor(id);
        if(doctor != null) {
            String response = "";
            response += "[name=" + doctor.getName() + ",id=" + doctor.getId() + "]\n";
            for(int i = 0; i < doctor.getPatients().size(); i++) {
                response += doctor.getPatients().get(i).getName() + " ";
            }
            return response;
        }
        return "No doctor with given ID exists";
    }
}
