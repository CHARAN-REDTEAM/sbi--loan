package com.hospital.management.hospitalManagement;

import java.util.ArrayList;

public class Doctor {
    String name;
    int id;
    ArrayList<Patient> patients;

    public Doctor(String name, int id) {
        this.name = name;
        this.id = id;
        this.patients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
