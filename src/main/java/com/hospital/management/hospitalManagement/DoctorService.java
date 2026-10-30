package com.hospital.management.hospitalManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public String addDoctor(Doctor doctor) {
        System.out.println("=== STEP 2: DoctorService.addDoctor() called ===");
        System.out.println("=== STEP 2 CHECK: Does doctor ID " + doctor.getId() + " already exist? " + doctorRepository.containsDoctor(doctor.getId()) + " ===");

        if (doctorRepository.containsDoctor(doctor.getId())) {
            System.out.println("=== STEP 2 BLOCKED: Duplicate doctor ID found ===");
            return "Doctor with ID " + doctor.getId() + " already exists";  // BREAKPOINT 8 - hit if duplicate
        }

        doctorRepository.addDoctor(doctor);  // BREAKPOINT 9 - set here to confirm save happens
        System.out.println("=== STEP 2 SAVED: Doctor saved to repository ===");
        return "Doctor added successfully";
    }

    public String getAllDoctors() {
        System.out.println("=== STEP 2: DoctorService.getAllDoctors() called ===");
        Collection<Doctor> doctors = doctorRepository.getAllDoctors();
        System.out.println("=== STEP 2 COUNT: Total doctors = " + doctors.size() + " ===");  // BREAKPOINT 10

        if (doctors.isEmpty()) return "No doctors registered";

        StringBuilder sb = new StringBuilder("LIST OF DOCTORS:\n");
        for (Doctor d : doctors) {
            System.out.println("=== STEP 2 DOCTOR: " + d.getName() + " | " + d.getSpecialty() + " ===");
            sb.append("ID: ").append(d.getId())
              .append(", Name: ").append(d.getName())
              .append(", Specialty: ").append(d.getSpecialty())
              .append(", Patients: ").append(d.getPatients().size())
              .append("\n");
        }
        return sb.toString();
    }

    public String getDoctorById(int id) {
        System.out.println("=== STEP 2: DoctorService.getDoctorById(" + id + ") called ===");
        Doctor doctor = doctorRepository.getDoctor(id);  // BREAKPOINT 11

        if (doctor == null) {
            System.out.println("=== STEP 2 NOT FOUND: No doctor with ID " + id + " ===");
            return "No doctor with ID " + id + " exists";
        }

        System.out.println("=== STEP 2 FOUND: " + doctor.getName() + " with " + doctor.getPatients().size() + " patients ===");
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(doctor.getId())
          .append(", Name: ").append(doctor.getName())
          .append(", Specialty: ").append(doctor.getSpecialty())
          .append("\nPatients:\n");

        if (doctor.getPatients().isEmpty()) {
            sb.append("  No patients assigned");
        } else {
            for (Patient p : doctor.getPatients()) {
                sb.append("  - ID: ").append(p.getId())
                  .append(", Name: ").append(p.getName())
                  .append(", Age: ").append(p.getAge())
                  .append("\n");
            }
        }
        return sb.toString();
    }

    public String deleteDoctor(int id) {
        System.out.println("=== STEP 2: DoctorService.deleteDoctor(" + id + ") called ===");
        if (!doctorRepository.containsDoctor(id)) {
            System.out.println("=== STEP 2 NOT FOUND: Doctor ID " + id + " does not exist ===");
            return "No doctor with ID " + id + " exists";
        }
        doctorRepository.deleteDoctor(id);  // BREAKPOINT 12
        System.out.println("=== STEP 2 DELETED: Doctor ID " + id + " removed ===");
        return "Doctor removed successfully";
    }

    public String addPatient(int doctorId, Patient patient) {
        System.out.println("=== STEP 2: DoctorService.addPatient() called for doctor ID " + doctorId + " ===");
        Doctor doctor = doctorRepository.getDoctor(doctorId);

        if (doctor == null) {
            System.out.println("=== STEP 2 NOT FOUND: Doctor ID " + doctorId + " does not exist ===");
            return "No doctor with ID " + doctorId + " exists";
        }

        for (Patient p : doctor.getPatients()) {
            if (p.getId() == patient.getId()) {
                System.out.println("=== STEP 2 BLOCKED: Patient ID " + patient.getId() + " already assigned ===");
                return "Patient with ID " + patient.getId() + " already assigned";  // BREAKPOINT 13
            }
        }

        doctor.getPatients().add(patient);  // BREAKPOINT 14 - confirm patient is added
        System.out.println("=== STEP 2 SAVED: Patient " + patient.getName() + " added to doctor " + doctor.getName() + " ===");
        System.out.println("=== STEP 2 TOTAL PATIENTS: " + doctor.getPatients().size() + " ===");
        return "Patient added successfully";
    }

    public String deletePatient(int doctorId, int patientId) {
        System.out.println("=== STEP 2: DoctorService.deletePatient() called - doctorId=" + doctorId + ", patientId=" + patientId + " ===");
        Doctor doctor = doctorRepository.getDoctor(doctorId);

        if (doctor == null) {
            System.out.println("=== STEP 2 NOT FOUND: Doctor ID " + doctorId + " does not exist ===");
            return "No doctor with ID " + doctorId + " exists";
        }

        ArrayList<Patient> patients = doctor.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == patientId) {
                patients.remove(i);  // BREAKPOINT 15
                System.out.println("=== STEP 2 DELETED: Patient ID " + patientId + " removed ===");
                return "Patient removed successfully";
            }
        }

        System.out.println("=== STEP 2 NOT FOUND: Patient ID " + patientId + " not found ===");
        return "No patient with ID " + patientId + " exists";
    }

    public String updateDoctor(int id, Doctor updated) {
        System.out.println("=== STEP 2: DoctorService.updateDoctor(" + id + ") called ===");
        Doctor doctor = doctorRepository.getDoctor(id);

        if (doctor == null) {
            System.out.println("=== STEP 2 NOT FOUND: Doctor ID " + id + " does not exist ===");
            return "No doctor with ID " + id + " exists";
        }

        if (updated.getName() != null) {
            System.out.println("=== STEP 2 UPDATE NAME: " + doctor.getName() + " -> " + updated.getName() + " ===");
            doctor.setName(updated.getName());  // BREAKPOINT 16
        }
        if (updated.getSpecialty() != null) {
            System.out.println("=== STEP 2 UPDATE SPECIALTY: " + doctor.getSpecialty() + " -> " + updated.getSpecialty() + " ===");
            doctor.setSpecialty(updated.getSpecialty());
        }
        return "Doctor updated successfully";
    }
}
