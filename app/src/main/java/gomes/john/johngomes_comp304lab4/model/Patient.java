package gomes.john.johngomes_comp304lab4.model;

/**
 * Created by John on 2017-12-07.
 */

public class Patient {

    //Private instance variables
    int patientId;
    String firstName;
    String lastName;
    String department;
    String doctorId;
    String room;

    //Empty Constructor
    public Patient()
    {}

    //Constructor
    public Patient(int _patientId, String _firstName, String _lastName, String _department, String _doctorId, String _room)
    {
        this.patientId = _patientId;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.department = _department;
        this.doctorId = _doctorId;
        this.room = _room;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


}
