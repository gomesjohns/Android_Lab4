package gomes.john.johngomes_comp304lab4.helper;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by John on 2017-12-08.
 */

public class InputValidation {
    private Context context;

    public InputValidation(Context _context)
    {
        this.context = _context;
    }

    //Login validation
    public boolean isInputEditTextFilled(EditText employeeId, EditText password, String message)
    {
        String value1 = employeeId.getText().toString().trim();
        String value2 = password.getText().toString().trim();

        if(value1.isEmpty())
        {
            employeeId.setError(message);
            hideKeyboardFrom(employeeId);
            return true;
        }
        else if(value2.isEmpty())
        {
            password.setError(message);
            hideKeyboardFrom(password);
            return true;
        }
        else
        {
            employeeId.setError(null);
            password.setError(null);
            return false;
        }

    }

    //Registration validation
    public boolean isInputEditTextFilled(EditText regId, EditText regFirstname,EditText regLastname, EditText regDept, EditText regPassword, EditText regConfrimPass, String message)
    {
        String _regId = regId.getText().toString().trim();
        String _regFirstname = regFirstname.getText().toString().trim();
        String _regLastname = regLastname.getText().toString().trim();
        String _regDept = regDept.getText().toString().trim();
        String _regPass = regPassword.getText().toString().trim();
        String _regConfirmPass = regConfrimPass.getText().toString().trim();

        if(_regId.isEmpty())
        {
            regId.setError(message);
            hideKeyboardFrom(regId);
            return true;
        }
        else if(_regFirstname.isEmpty())
        {
            regFirstname.setError(message);
            hideKeyboardFrom(regFirstname);
            return true;
        }
        else if(_regLastname.isEmpty())
        {
            regLastname.setError(message);
            hideKeyboardFrom(regLastname);
            return true;
        }
        else if(_regDept.isEmpty())
        {
            regDept.setError(message);
            hideKeyboardFrom(regDept);
            return true;
        }
        else if(_regPass.isEmpty())
        {
            regPassword.setError(message);
            hideKeyboardFrom(regPassword);
            return true;
        }
        else if(_regConfirmPass.isEmpty())
        {
            regConfrimPass.setError(message);
            hideKeyboardFrom(regConfrimPass);
            return true;
        }
        else
        {
            regId.setError(null);
            regFirstname.setError(null);
            regLastname.setError(null);
            regDept.setError(null);
            regPassword.setError(null);
            regConfrimPass.setError(null);
            return false;
        }
    }

    public boolean isCheckboxChecked(CheckBox nurse, CheckBox doc, String message)
    {
        CheckBox _nurse = nurse;
        CheckBox _doctor = doc;

        if (!_nurse.isChecked() && !_doctor.isChecked())
        {
            nurse.setError(message);
            doc.setError(message);
            return true;
        }
        else
        {
            nurse.setError(null);
            doc.setError(null);
            return false;
        }
    }

    //Patient details validation
    public boolean isPatientInputEditTextFilled(EditText patId, EditText patFirstname,EditText patLastname, EditText patDept, EditText patDoctorId, EditText patRoom, String message)
    {
        String _patId = patId.getText().toString().trim();
        String _patFirstname = patFirstname.getText().toString().trim();
        String _patLastname = patLastname.getText().toString().trim();
        String _patDept = patDept.getText().toString().trim();
        String _patDoctorId = patDoctorId.getText().toString().trim();
        String _patRoom = patRoom.getText().toString().trim();

        if(_patId.isEmpty())
        {
            patId.setError(message);
            hideKeyboardFrom(patId);
            return true;
        }
        else if(_patFirstname.isEmpty())
        {
            patFirstname.setError(message);
            hideKeyboardFrom(patFirstname);
            return true;
        }
        else if(_patLastname.isEmpty())
        {
            patLastname.setError(message);
            hideKeyboardFrom(patLastname);
            return true;
        }
        else if(_patDept.isEmpty())
        {
            patDept.setError(message);
            hideKeyboardFrom(patDept);
            return true;
        }
        else if(_patDoctorId.isEmpty())
        {
            patDoctorId.setError(message);
            hideKeyboardFrom(patDoctorId);
            return true;
        }
        else if(_patRoom.isEmpty())
        {
            patRoom.setError(message);
            hideKeyboardFrom(patRoom);
            return true;
        }
        else
        {
            patId.setError(null);
            patFirstname.setError(null);
            patLastname.setError(null);
            patDept.setError(null);
            patDoctorId.setError(null);
            patRoom.setError(null);
            return false;
        }
    }

    //Test details validation
    public boolean isTestInputEditTextFilled(EditText testId, EditText patId ,EditText nurseId, EditText bpl, EditText bph, EditText temp, String message)
    {
        String _testid = testId.getText().toString().trim();
        String _patId = patId.getText().toString().trim();
        String _nurseId = nurseId.getText().toString().trim();
        String _bpl = bpl.getText().toString().trim();
        String _bph = bph.getText().toString().trim();
        String _temp = temp.getText().toString().trim();

        if(_testid.isEmpty())
        {
            testId.setError(message);
            hideKeyboardFrom(testId);
            return true;
        }
        else if(_patId.isEmpty())
        {
            patId.setError(message);
            hideKeyboardFrom(patId);
            return true;
        }
        else if(_nurseId.isEmpty())
        {
            nurseId.setError(message);
            hideKeyboardFrom(nurseId);
            return true;
        }
        else if(_bpl.isEmpty())
        {
            bpl.setError(message);
            hideKeyboardFrom(bpl);
            return true;
        }
        else if(_bph.isEmpty())
        {
            bph.setError(message);
            hideKeyboardFrom(bph);
            return true;
        }
        else if(_temp.isEmpty())
        {
            temp.setError(message);
            hideKeyboardFrom(temp);
            return true;
        }
        else
        {
            testId.setError(null);
            patId.setError(null);
            nurseId.setError(null);
            bpl.setError(null);
            bph.setError(null);
            temp.setError(null);
            return false;
        }
    }

    //If password and confirm password fields match
    public boolean isInputTextMatches (EditText password, EditText confirmPass, String message)
    {
        String pass = password.getText().toString().trim();
        String passConfirm = confirmPass.getText().toString().trim();

        if(!pass.contentEquals(passConfirm))
        {
            //password.setError(message);
            confirmPass.setError(message);
            hideKeyboardFrom(confirmPass);
            return false;
        }
        else
        {
            password.setError(null);
            confirmPass.setError(null);
        }
        return true;
    }

    private void hideKeyboardFrom(View v)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
