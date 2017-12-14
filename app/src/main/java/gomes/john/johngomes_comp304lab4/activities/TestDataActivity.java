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

        //nurseID.setEnabled(false);
    }

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

    private void initListeners()
    {
        enterTestDataBtn.setOnClickListener(this);
        viewPatientDetailsBtn.setOnClickListener(this);
    }

    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);
        test = new Test();
    }

    private void initSharedPref()
    {
        //Init SharedPref
        SharedPreferences myPref = getSharedPreferences("nursePref",0);
        sharedPrefId= myPref.getString("nurseIdPref", null);
        nurseID.setText(sharedPrefId);
        Toast.makeText(getApplicationContext(), "Welcome " + sharedPrefId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.buttonEnterData:
                postDataToSQL();
                break;
            case R.id.buttonPatients:
                Intent intentPat= new Intent(getApplicationContext(), PatientActivity.class);
                startActivity(intentPat);
                emptyEditText();
                break;
        }
    }

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
        nurseID.setText(null);
        BPL.setText(null);
        BPH.setText(null);
        temperature.setText(null);
    }

}
