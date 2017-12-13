package gomes.john.johngomes_comp304lab4.model;

/**
 * Created by 300695550 on 12/10/2017.
 */

public class Nurse
{
    //Private instance variables
    String nurseId;
    String firstName;
    String lastName;
    String department;
    String password;

    //Empty Constructor
    public Nurse()
    {}

    //Constructor
    public Nurse(String _nurseId, String _firstName, String _lastName, String _department, String _password)
    {
        this.nurseId = _nurseId;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.department = _department;
        this.password = _password;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
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
