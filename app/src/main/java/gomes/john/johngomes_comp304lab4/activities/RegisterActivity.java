package gomes.john.johngomes_comp304lab4.activities;

import android.content.Intent;
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
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //Global variables
    private final AppCompatActivity activity= RegisterActivity.this;
    private EditText registerID;
    private EditText registerFirstName;
    private EditText registerLastName;
    private EditText registerDepartment;
    private EditText registerPassword;
    private EditText registerConfirmPass;
    private Button registerBtn;
    private TextView buttonLinkLogin;
    private CheckBox checkBoxNurse;
    private CheckBox checkBoxDoctor;
    private DatabaseManager databaseManager;
    private InputValidation inputValidation;
    private Doctor doctor;
    private Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();
    }

    //Init views
    private void initViews()
    {
        registerID = (EditText) findViewById(R.id.editTextEmpID);
        registerFirstName = (EditText) findViewById(R.id.editTextFirstName);
        registerLastName = (EditText) findViewById(R.id.editTextLastName);
        registerDepartment = (EditText) findViewById(R.id.editTextDepartment);
        registerPassword = (EditText) findViewById(R.id.editTextPassword);
        registerConfirmPass = (EditText) findViewById(R.id.editTextConfirmPass);

        registerBtn = (Button) findViewById(R.id.registerButton);
        buttonLinkLogin = (TextView) findViewById(R.id.textviewLinkLogin);

        checkBoxNurse = (CheckBox) findViewById(R.id.checkBoxNurse);
        checkBoxDoctor = (CheckBox) findViewById(R.id.checkBoxDoctor);
    }

    //Init listeners
    private void initListeners()
    {
        registerBtn.setOnClickListener(this);
        buttonLinkLogin.setOnClickListener(this);
        checkBoxNurse.setOnClickListener(this);
        checkBoxDoctor.setOnClickListener(this);
    }

    //Init objects
    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);
        doctor = new Doctor();
        nurse = new Nurse();
    }

    //Validate input
    private void inputValidation()
    {
        //Validate no field is empty, passwords match, at least one check box checked
        if((!inputValidation.isInputEditTextFilled(registerID , registerFirstName, registerLastName,
            registerDepartment, registerPassword, registerConfirmPass, getString(R.string.error_message_empty)))
            && (inputValidation.isInputTextMatches(registerPassword, registerConfirmPass, getString(R.string.error_message_noMatch)))
            && (!inputValidation.isCheckboxChecked(checkBoxNurse, checkBoxDoctor, getString(R.string.error_message_checkBox))))
        {
            postDataToSQL();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //Post data to SQLite database
    private void postDataToSQL()
    {
        //Nurse Checked
        if (checkBoxNurse.isChecked())
        {
            if (!databaseManager.checkNurseReg(registerID.getText().toString().trim())) {
                nurse.setNurseId(registerID.getText().toString().trim());
                nurse.setFirstName(registerFirstName.getText().toString().trim());
                nurse.setLastName(registerLastName.getText().toString().trim());
                nurse.setDepartment(registerDepartment.getText().toString().trim());
                nurse.setPassword(registerPassword.getText().toString().trim());

                databaseManager.addNurse(nurse);

                Toast.makeText(getApplicationContext(), "Nurse Signup Successful", Toast.LENGTH_SHORT).show();
                emptyEditText();
            } else {
                Toast.makeText(getApplicationContext(), "Nurse Exists", Toast.LENGTH_SHORT).show();
            }
        }
        //Doctor Checked
        else if (checkBoxDoctor.isChecked())
        {
            if (!databaseManager.checkDoctorReg(registerID.getText().toString().trim())) {
                doctor.setDoctorId(registerID.getText().toString().trim());
                doctor.setFirstName(registerFirstName.getText().toString().trim());
                doctor.setLastName(registerLastName.getText().toString().trim());
                doctor.setDepartment(registerDepartment.getText().toString().trim());
                doctor.setPassword(registerPassword.getText().toString().trim());

                databaseManager.addDoctor(doctor);

                Toast.makeText(getApplicationContext(), "Doctor Signup Successful", Toast.LENGTH_SHORT).show();
                emptyEditText();
            } else {
                Toast.makeText(getApplicationContext(), "Doctor Exists", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void emptyEditText()
    {
        registerID.setText(null);
        registerFirstName.setText(null);
        registerLastName.setText(null);
        registerDepartment.setText(null);
        registerPassword.setText(null);
        registerConfirmPass.setText(null);
    }

    //OnClick of buttons or links
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.registerButton:
                inputValidation();
                break;
            case R.id.textviewLinkLogin:
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                intentLogin.putExtra("checkBoxNurse", checkBoxNurse.isChecked());
                intentLogin.putExtra("checkBoxDoctor", checkBoxDoctor.isChecked());
                startActivity(intentLogin);
                break;
            case R.id.checkBoxDoctor:
                checkBoxNurse.setChecked(false);
                break;
            case R.id.checkBoxNurse:
                checkBoxDoctor.setChecked(false);
                break;
        }
    }
}
