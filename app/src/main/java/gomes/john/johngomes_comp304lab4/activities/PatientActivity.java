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
    //Global variables
    private final AppCompatActivity activity= PatientActivity.this;
    private EditText patientID;
    private EditText patientFirstName;
    private EditText patientLastName;
    private EditText patientDepartment;
    private EditText patientDoctorID;
    private EditText patientRoom;
    private Button addPatient;
    private Button viewPatient;
    private Button updatePatient;
    private Button createTests;
    private DatabaseManager databaseManager;
    private InputValidation inputValidation;
    private Patient patient;
    private String sharedPrefId;
    private Intent intent;
    private boolean isDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        initViews();
        initListeners();
        initObjects();
        initSharedPref();

        //Hide createTest button if the user is a doctor
        if(isDoctor)
        {
            createTests.setVisibility(View.GONE);
        }
    }

    //Init views
    private void initViews()
    {
        patientID = (EditText) findViewById(R.id.editTextPatientId);
        patientFirstName = (EditText) findViewById(R.id.editTextFirstName);
        patientLastName = (EditText) findViewById(R.id.editTextLastName);
        patientDepartment = (EditText) findViewById(R.id.editTextDepartment);
        patientDoctorID = (EditText) findViewById(R.id.editTextDoctorId);
        patientRoom = (EditText) findViewById(R.id.editTextRoom);

        addPatient = (Button) findViewById(R.id.buttonAddPatient);
        updatePatient = (Button) findViewById(R.id.buttonUpdatePatient);
        viewPatient = (Button) findViewById(R.id.buttonViewPatients);
        createTests = (Button) findViewById(R.id.buttonCreateTests);
    }

    //Init listeners
    private void initListeners()
    {
        addPatient.setOnClickListener(this);
        updatePatient.setOnClickListener(this);
        viewPatient.setOnClickListener(this);
        createTests.setOnClickListener(this);
    }

    //Init objects
    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);
        patient = new Patient();
    }

    //Get shared preference
    private void initSharedPref()
    {
        //Init SharedPref
        SharedPreferences myPref = getSharedPreferences("empId",0);
        sharedPrefId= myPref.getString("empIdPref", null);
        //Toast.makeText(getApplicationContext(), "Welcome " + sharedPrefId, Toast.LENGTH_SHORT).show();

        //If id is in doctor table, user is a doctor
        if (databaseManager.checkDoctor(sharedPrefId))
        {
            isDoctor=true;
            patientDoctorID.setText(sharedPrefId);
        }
    }

    //Validate input for adding patients
    private void inputValidationAdd()
    {
        if((!inputValidation.isPatientInputEditTextFilled(patientID , patientFirstName, patientLastName,
                patientDepartment, patientDoctorID, patientRoom, getString(R.string.error_message_empty))))
        {
            postDataToSQL();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //Validate input for updating patients
    private void inputValidationUpdate()
    {
        if((!inputValidation.isPatientInputEditTextFilled(patientID , patientFirstName, patientLastName,
                patientDepartment, patientDoctorID, patientRoom, getString(R.string.error_message_empty))))
        {
            updatePatient();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //Post patient data to SQLite database
    private void postDataToSQL()
    {
        if (!databaseManager.checkPatient(patientID.getText().toString().trim()))
        {
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

    //Update patient details
    private void updatePatient()
    {
        if (databaseManager.checkPatient(patientID.getText().toString().trim()))
        {
            patient.setPatientId(Integer.parseInt(patientID.getText().toString().trim()));
            patient.setFirstName(patientFirstName.getText().toString().trim());
            patient.setLastName(patientLastName.getText().toString().trim());
            patient.setDepartment(patientDepartment.getText().toString().trim());
            patient.setDoctorId(patientDoctorID.getText().toString().trim());
            patient.setRoom(patientRoom.getText().toString().trim());

            databaseManager.updatePatient(patient);

            Toast.makeText(getApplicationContext(), "Patient updated successfully", Toast.LENGTH_SHORT).show();
            emptyEditText();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Patient doesn't exist", Toast.LENGTH_SHORT).show();
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

    //OnClick of buttons
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonAddPatient:
                inputValidationAdd();
                break;
            case R.id.buttonUpdatePatient:
                inputValidationUpdate();
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
}
