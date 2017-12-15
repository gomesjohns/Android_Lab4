package gomes.john.johngomes_comp304lab4.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import gomes.john.johngomes_comp304lab4.helper.InputValidation;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;
import gomes.john.johngomes_comp304lab4.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    //Global variables
    private final AppCompatActivity activity= LoginActivity.this;
    private EditText inputEmployeeID;
    private EditText inputPassword;
    private TextView buttonLinkRegister;
    private Button loginButton;
    private DatabaseManager databaseManager;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();
    }

    //Init views
    private void initViews()
    {
        inputEmployeeID = (EditText) findViewById(R.id.loginEmployeeId);
        inputPassword = (EditText) findViewById(R.id.loginPassword);

        loginButton = (Button) findViewById(R.id.loginBtn);
        buttonLinkRegister =(TextView) findViewById(R.id.textviewLinkRegister);
    }

    //Init listeners
    private void initListeners()
    {
        loginButton.setOnClickListener(this);
        buttonLinkRegister.setOnClickListener(this);
    }

    //Init objects
    private void initObjects()
    {
        databaseManager = new DatabaseManager(activity);
        inputValidation = new InputValidation(activity);
    }

    //Validate input
    private void inputValidation()
    {
        if(!inputValidation.isInputEditTextFilled(inputEmployeeID ,inputPassword, getString(R.string.error_message_empty)))
        {
            verifyFromSQL();
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    //Verify data from SQLite
    private void verifyFromSQL()
    {
        //Data Query
        if (databaseManager.checkDoctor(inputEmployeeID.getText().toString().trim(), inputPassword.getText().toString().trim()))
        {
            //Put employeeId in shared pref
            SharedPreferences myPref = getSharedPreferences("empId",0);
            SharedPreferences.Editor editor = myPref.edit();
            editor.putString("empIdPref", inputEmployeeID.getText().toString().trim());
            editor.apply();

            //Start doctor activity
            Intent intentPatient= new Intent(getApplicationContext(), PatientActivity.class);
            startActivity(intentPatient);
            emptyTextview();
        }
        else if (databaseManager.checkNurse(inputEmployeeID.getText().toString().trim(), inputPassword.getText().toString().trim()))
        {
            //Put employeeId in shared pref
            SharedPreferences myPref = getSharedPreferences("empId",0);
            SharedPreferences.Editor editor = myPref.edit();
            editor.putString("empIdPref", inputEmployeeID.getText().toString().trim());
            editor.apply();

            //Start nurse activity
            Intent intentTest= new Intent(getApplicationContext(), TestDataActivity.class);
            startActivity(intentTest);
            emptyTextview();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid Employee ID or Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyTextview()
    {
        inputEmployeeID.setText(null);
        inputPassword.setText(null);
    }

    //OnClick of button or link
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.loginBtn:
                inputValidation();
                break;
            case R.id.textviewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
