package com.hospital.management.hospitalManagement;

import java.util.ArrayList;

public class Doctor {
    private String name;
    private int id;
    private String specialty;
    private ArrayList<Patient> patients;

    public Doctor(String name, int id, String specialty) {
        this.name = name;
        this.id = id;
        this.specialty = specialty;
        this.patients = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public ArrayList<Patient> getPatients() { return patients; }
    public void setPatients(ArrayList<Patient> patients) { this.patients = patients; }
}
