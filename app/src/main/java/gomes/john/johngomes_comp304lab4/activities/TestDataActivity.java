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
import gomes.john.johngomes_comp304lab4.model.Test;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

public class TestDataActivity extends AppCompatActivity implements View.OnClickListener
{
    //Global variables
    private final AppCompatActivity activity= TestDataActivity.this;
    private EditText testID;
    private EditText patientID;
    private EditText nurseID;
    private EditText BPL;
    private EditText BPH;
    private EditText temperature;
    private Button enterTestDataBtn;
    private Button viewPatientDetailsBtn;
    private DatabaseManager databaseManager;
    private InputValidation inputValidation;
    private Test test;
    private String sharedPrefId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        initViews();
        initListeners();
        initObjects();
        initSharedPref();
    }

    //Init views
    private void initViews()
    {
        testID = (EditText) findViewById(R.id.editTextTestID);
        patientID = (EditText) findViewById(R.id.editTextPatientID);
        nurseID = (EditText) findViewById(R.id.editTextNurseID);
        BPL = (EditText) findViewById(R.id.editTextBPL);
        BPH= (EditText) findViewById(R.id.editTextBPH);
        temperature = (EditText) findViewById(R.id.editTextTemp);

        enterTestDataBtn = (Button) findViewById(R.id.buttonEnterData);
        viewPatientDetailsBtn = (Button) findViewById(R.id.buttonPatients);
    }

    //Init listeners
    private void initListeners()
    {
        enterTestDataBtn.setOnClickListener(this);
        viewPatientDetailsBtn.setOnClickListener(this);
    }

    //Init objects
    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);
        test = new Test();
    }

    //Get shared preference
    private void initSharedPref()
    {
        //Init SharedPref
        SharedPreferences myPref = getSharedPreferences("empId",0);
        sharedPrefId= myPref.getString("empIdPref", null);
        //Set nurseId to the employee id currently signed in (Only Nurses)
        nurseID.setText(sharedPrefId);
    }

    //Validate input
    private void inputTestValidationTest()
    {
        if((!inputValidation.isTestInputEditTextFilled(testID, patientID, nurseID, BPL, BPH, temperature, getString(R.string.error_message_empty))))
        {
            postDataToSQL();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //Post test data to SQLite database
    private void postDataToSQL()
    {
        if (!databaseManager.checkTestId(testID.getText().toString().trim()))
        {
            test.setTestId(Integer.parseInt(testID.getText().toString().trim()));
            test.setPatientId(patientID.getText().toString().trim());
            test.setNurseId(nurseID.getText().toString().trim());
            test.setBPL(BPL.getText().toString().trim());
            test.setBPH(BPH.getText().toString().trim());
            test.setTemperature(temperature.getText().toString().trim());

            databaseManager.addTest(test);

            Toast.makeText(getApplicationContext(), "Test data added successfully", Toast.LENGTH_SHORT).show();
            emptyEditText();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Test Exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyEditText()
    {
        testID.setText(null);
        patientID.setText(null);
        BPL.setText(null);
        BPH.setText(null);
        temperature.setText(null);
    }

    //OnClick of buttons
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.buttonEnterData:
                inputTestValidationTest();
                break;
            case R.id.buttonPatients:
                Intent intentPat= new Intent(getApplicationContext(), PatientActivity.class);
                startActivity(intentPat);
                emptyEditText();
                break;
        }
    }

}
