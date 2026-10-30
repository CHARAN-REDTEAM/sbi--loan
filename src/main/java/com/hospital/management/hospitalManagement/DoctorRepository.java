package com.hospital.management.hospitalManagement;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Set;

@Repository
public class DoctorRepository {
    HashMap<Integer, Doctor> mp;

    DoctorRepository() {
        mp = new HashMap<>();
    }

    void addDoctor(Doctor doctor) {
        mp.put(doctor.getId(), doctor);
    }

    Doctor getDoctor(int id) {
        if(!mp.containsKey(id)) {
            return null;
        }
        return mp.get(id);
    }

    Set<Integer> getAllDoctorIds() {
        return mp.keySet();
    }
}
