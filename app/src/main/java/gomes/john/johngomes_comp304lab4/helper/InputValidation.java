package gomes.john.johngomes_comp304lab4.helper;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

    public boolean isInputEditTextFilled(EditText employeeId, EditText password, String message)
    {
        String value1 = employeeId.getText().toString().trim();
        String value2 = password.getText().toString().trim();

        if(value1.isEmpty())
        {
            employeeId.setError(message);
            hideKeyboardFrom(employeeId);
            //return false;
        }
        if(value2.isEmpty())
        {
            password.setError(message);
            hideKeyboardFrom(password);
            //return false;
        }
        else
        {
            employeeId.setError(null);
            password.setError(null);
        }
        return false;
    }


    public boolean isInputTextMatches (TextView editText1, TextView editText2, String message)
    {
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();

        if(!value1.contentEquals(value2))
        {
            editText1.setError(message);
            editText2.setError(message);
            hideKeyboardFrom(editText2);
            return false;
        }
        else
        {
            editText1.setError(null);
            editText2.setError(message);
        }
        return true;
    }

    private void hideKeyboardFrom(View v)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
