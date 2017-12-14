package gomes.john.johngomes_comp304lab4.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.helper.InputValidation;
import gomes.john.johngomes_comp304lab4.model.Doctor;
import gomes.john.johngomes_comp304lab4.model.Nurse;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener
{
    private final AppCompatActivity activity= PatientActivity.this;

    private EditText patientID;
    private EditText patientFirstName;
    private EditText patientLastName;
    private EditText patientDepartment;
    private EditText patientDoctorID;
    private EditText patientRoom;

    private Button addPatient;
    private Button viewPatient;
    private Button createTests;


    private DatabaseManager databaseManager;
    private InputValidation inputValidation;

    private Patient patient;

    private String sharedPrefId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        initViews();
        initListeners();
        initObjects();
        initSharedPref();
    }

    private void initViews()
    {
        patientID = (EditText) findViewById(R.id.editTextPatientId);
        patientFirstName = (EditText) findViewById(R.id.editTextFirstName);
        patientLastName = (EditText) findViewById(R.id.editTextLastName);
        patientDepartment = (EditText) findViewById(R.id.editTextDepartment);
        patientDoctorID = (EditText) findViewById(R.id.editTextDoctorId);
        patientRoom = (EditText) findViewById(R.id.editTextRoom);

        addPatient = (Button) findViewById(R.id.buttonAddPatient);
        viewPatient = (Button) findViewById(R.id.buttonViewPatients);
        createTests = (Button) findViewById(R.id.buttonCreateTests);
    }

    private void initListeners()
    {
        addPatient.setOnClickListener(this);
        viewPatient.setOnClickListener(this);
        createTests.setOnClickListener(this);
    }

    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);

        patient = new Patient();
    }

    private void initSharedPref()
    {
        //Init SharedPref
        SharedPreferences myPref = getSharedPreferences("doctorPref",0);
        sharedPrefId= myPref.getString("doctorIdPref", null);
        Toast.makeText(getApplicationContext(), "Welcome " + sharedPrefId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonAddPatient:
                postDataToSQL();
                break;
            case R.id.buttonViewPatients:
                Intent intentPList = new Intent(getApplicationContext(), PatientListActivity.class);
                startActivity(intentPList);
                break;
            case R.id.buttonCreateTests:
                Intent intentTest = new Intent(getApplicationContext(), TestDataActivity.class);
                startActivity(intentTest);
                break;
        }
    }

    private void postDataToSQL()
    {
        if (!databaseManager.checkPatient(patientID.getText().toString().trim())) {
                patient.setPatientId(Integer.parseInt(patientID.getText().toString().trim()));
                patient.setFirstName(patientFirstName.getText().toString().trim());
                patient.setLastName(patientLastName.getText().toString().trim());
                patient.setDepartment(patientDepartment.getText().toString().trim());
                patient.setDoctorId(patientDoctorID.getText().toString().trim());
                patient.setRoom(patientRoom.getText().toString().trim());

                databaseManager.addPatient(patient);

                Toast.makeText(getApplicationContext(), "Patient added successfully", Toast.LENGTH_SHORT).show();
                emptyEditText();

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Patient exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyEditText()
    {
        patientID.setText(null);
        patientFirstName.setText(null);
        patientLastName.setText(null);
        patientDepartment.setText(null);
        patientDoctorID.setText(null);
        patientRoom.setText(null);
    }
}
