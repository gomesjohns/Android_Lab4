package gomes.john.johngomes_comp304lab4.model;

/**
 * Created by John on 2017-12-09.
 */

public class Doctor
{
    //Private instance variables
    String doctorId;
    String firstName;
    String lastName;
    String department;
    String password;

    //Empty Constructor
    public Doctor()
    {}

    //Constructor
    public Doctor(String _doctorId, String _firstName, String _lastName, String _department, String _password)
    {
        this.doctorId = _doctorId;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.department = _department;
        this.password = _password;
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
