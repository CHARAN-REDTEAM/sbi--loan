package com.hospital.management.hospitalManagement;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class DoctorRepository {
    private final HashMap<Integer, Doctor> store = new HashMap<>();

    public void addDoctor(Doctor doctor) {
        System.out.println("=== STEP 3: DoctorRepository.addDoctor() - Saving doctor ID " + doctor.getId() + " ===");
        store.put(doctor.getId(), doctor);
        System.out.println("=== STEP 3 DONE: Total doctors in store = " + store.size() + " ===");  // BREAKPOINT 17
    }

    public Doctor getDoctor(int id) {
        System.out.println("=== STEP 3: DoctorRepository.getDoctor(" + id + ") called ===");
        Doctor doctor = store.get(id);
        System.out.println("=== STEP 3 RESULT: " + (doctor != null ? "Found " + doctor.getName() : "NOT FOUND") + " ===");  // BREAKPOINT 18
        return doctor;
    }

    public Collection<Doctor> getAllDoctors() {
        System.out.println("=== STEP 3: DoctorRepository.getAllDoctors() - store has " + store.size() + " doctors ===");  // BREAKPOINT 19
        return store.values();
    }

    public boolean deleteDoctor(int id) {
        System.out.println("=== STEP 3: DoctorRepository.deleteDoctor(" + id + ") called ===");
        boolean removed = store.remove(id) != null;
        System.out.println("=== STEP 3 RESULT: Deleted=" + removed + ", Remaining doctors=" + store.size() + " ===");  // BREAKPOINT 20
        return removed;
    }

    public boolean containsDoctor(int id) {
        boolean exists = store.containsKey(id);
        System.out.println("=== STEP 3: DoctorRepository.containsDoctor(" + id + ") = " + exists + " ===");
        return exists;
    }
}
