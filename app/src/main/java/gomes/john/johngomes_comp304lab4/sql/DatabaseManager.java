package gomes.john.johngomes_comp304lab4.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gomes.john.johngomes_comp304lab4.activities.LoginActivity;
import gomes.john.johngomes_comp304lab4.model.Doctor;
import gomes.john.johngomes_comp304lab4.model.Nurse;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.model.Test;

import static android.content.ContentValues.TAG;

/**
 * Created by 300695550 on 12/6/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper
{
    //List fields to be displayed
    private ArrayList<String> list_patientId = new ArrayList<>();
    private ArrayList<String> list_firstName = new ArrayList<>();
    private ArrayList<String> list_lastName = new ArrayList<>();
    private ArrayList<String> list_department = new ArrayList<>();
    private ArrayList<String> list_doctorId = new ArrayList<>();
    private ArrayList<String> list_room = new ArrayList<>();

    //Database Version and Name
    private static final String DATABASE_NAME = "Hospital.db";
    private static final int DATABASE_VERSION = 3;

    //Tables
    private static final String TABLE_DOCTOR = "doctor_table";
    private static final String TABLE_NURSE = "nurse_table";
    private static final String TABLE_TEST = "test_table";
    private static final String TABLE_PATIENT = "patient_table";

    //Doctor table columns
    private static final String COLUMN_DOCTOR_ID = "doctorId";
    private static final String COLUMN_DOCTOR_FIRSTNAME = "firstname";
    private static final String COLUMN_DOCTOR_LASTNAME = "lastname";
    private static final String COLUMN_DOCTOR_DEPARTMENT = "department";
    private static final String COLUMN_DOCTOR_PASSWORD = "password";

    //Nurse table columns
    private static final String COLUMN_NURSE_ID = "nurseId";
    private static final String COLUMN_NURSE_FIRSTNAME = "firstname";
    private static final String COLUMN_NURSE_LASTNAME = "lastname";
    private static final String COLUMN_NURSE_DEPARTMENT = "department";
    private static final String COLUMN_NURSE_PASSWORD= "password";

    //Test table columns
    private static final String COLUMN_TEST_ID = "testId";
    private static final String COLUMN_PATIENT_TEST_ID = "patientId";
    private static final String COLUMN_NURSE_TEST_ID = "nurseId";
    private static final String COLUMN_BPL = "BPL";
    private static final String COLUMN_BPH = "BPH";
    private static final String COLUMN_TEMPERATURE = "temperature";

    //Test table columns
    private static final String COLUMN_PATIENT_ID = "patientId";
    private static final String COLUMN_PATIENT_FIRSTNAME = "firstname";
    private static final String COLUMN_PATIENT_LASTNAME = "lastname";
    private static final String COLUMN_PATIENT_DEPARTMENT = "department";
    private static final String COLUMN_PATIENT_DOCTOR_ID = "doctorId";
    private static final String COLUMN_PATIENT_ROOM = "room";


    //Create doctor table
    private String CREATE_DOCTOR_TABLE = "CREATE TABLE "+ TABLE_DOCTOR + "("
            +   COLUMN_DOCTOR_ID + " TEXT PRIMARY KEY,"
            +   COLUMN_DOCTOR_FIRSTNAME+ " TEXT,"
            +   COLUMN_DOCTOR_LASTNAME+ " TEXT,"
            +   COLUMN_DOCTOR_DEPARTMENT+" TEXT,"
            +   COLUMN_DOCTOR_PASSWORD+" TEXT"+")";

    //Create nurse table
    private String CREATE_NURSE_TABLE = "CREATE TABLE "+ TABLE_NURSE + "("
            +   COLUMN_NURSE_ID + " TEXT PRIMARY KEY,"
            +   COLUMN_NURSE_FIRSTNAME+ " TEXT,"
            +   COLUMN_NURSE_LASTNAME+ " TEXT,"
            +   COLUMN_NURSE_DEPARTMENT+" TEXT,"
            +   COLUMN_NURSE_PASSWORD+" TEXT"+")";

    //Create nurse table
    private String CREATE_TEST_TABLE = "CREATE TABLE "+ TABLE_TEST + "("
            +   COLUMN_TEST_ID + " TEXT PRIMARY KEY,"
            +   COLUMN_PATIENT_TEST_ID+ " TEXT,"
            +   COLUMN_NURSE_TEST_ID+ " TEXT,"
            +   COLUMN_BPL+" TEXT,"
            +   COLUMN_BPH+" TEXT,"
            +   COLUMN_TEMPERATURE+ " TEXT"+ ")";

    //Create patient table
    private String CREATE_PATIENT_TABLE = "CREATE TABLE "+ TABLE_PATIENT + "("
            +   COLUMN_PATIENT_ID + " TEXT PRIMARY KEY,"
            +   COLUMN_PATIENT_FIRSTNAME+ " TEXT,"
            +   COLUMN_PATIENT_LASTNAME+ " TEXT,"
            +   COLUMN_PATIENT_DEPARTMENT+" TEXT,"
            +   COLUMN_PATIENT_DOCTOR_ID+" INTEGER,"
            +   COLUMN_PATIENT_ROOM+ " TEXT"+ ")";

    //class constructor
    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       //Log.w(TAG, "Doctor table created");
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        Log.w(TAG, "onCreate DB");
        database.execSQL(CREATE_DOCTOR_TABLE);
        database.execSQL(CREATE_NURSE_TABLE);
        database.execSQL(CREATE_TEST_TABLE);
        database.execSQL(CREATE_PATIENT_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        //Clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        //Recreate the tables
        onCreate(db);
    }

    //---------------------------------------------Add New Record-------------------------------------------

    //Add Doctor
    public void addDoctor(Doctor doctor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DOCTOR_ID, doctor.getDoctorId());
        values.put(COLUMN_DOCTOR_FIRSTNAME, doctor.getFirstName());
        values.put(COLUMN_DOCTOR_LASTNAME, doctor.getLastName());
        values.put(COLUMN_DOCTOR_DEPARTMENT, doctor.getDepartment());
        values.put(COLUMN_DOCTOR_PASSWORD, doctor.getPassword());

        db.insert(TABLE_DOCTOR, null, values);
        db.close();
    }

    //Add Nurse
    public void addNurse(Nurse nurse)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NURSE_ID, nurse.getNurseId());
        values.put(COLUMN_NURSE_FIRSTNAME, nurse.getFirstName());
        values.put(COLUMN_NURSE_LASTNAME, nurse.getLastName());
        values.put(COLUMN_NURSE_DEPARTMENT, nurse.getDepartment());
        values.put(COLUMN_NURSE_PASSWORD, nurse.getPassword());

        db.insert(TABLE_NURSE, null, values);
        db.close();
    }

    //Add Patient
    public void addPatient(Patient patient)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PATIENT_ID, patient.getPatientId());
        values.put(COLUMN_PATIENT_FIRSTNAME, patient.getFirstName());
        values.put(COLUMN_PATIENT_LASTNAME, patient.getLastName());
        values.put(COLUMN_PATIENT_DEPARTMENT, patient.getDepartment());
        values.put(COLUMN_PATIENT_DOCTOR_ID, patient.getDoctorId());
        values.put(COLUMN_PATIENT_ROOM, patient.getRoom());

        db.insert(TABLE_PATIENT, null, values);
        db.close();
    }

    //Add Test Info
    public void addTest(Test test)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TEST_ID, test.getTestId());
        values.put(COLUMN_PATIENT_TEST_ID, test.getPatientId());
        values.put(COLUMN_NURSE_TEST_ID, test.getNurseId());
        values.put(COLUMN_BPL, test.getBPL());
        values.put(COLUMN_BPH, test.getBPH());
        values.put(COLUMN_TEMPERATURE, test.getTemperature());

        db.insert(TABLE_TEST, null, values);
        db.close();
    }


    //----------------------------------------------Check DB for Login---------------------------------------------
    //Check Doctor
    public boolean checkDoctor(String doctorID, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_DOCTOR_ID}; //Column names for query
        String selection = COLUMN_DOCTOR_ID+ " = ?" + " AND "+ COLUMN_NURSE_PASSWORD+ " = ?"; //The row data to be returned from the column(WHERE clause)
        String [] selectionArgs = {doctorID, password}; //Substituted for the " = ?" in the selection

        Cursor cursor = db.query(TABLE_DOCTOR,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //Check Nurse
    public boolean checkNurse(String nurseID, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_NURSE_ID}; //Column names for query
        String selection = COLUMN_NURSE_ID+ " = ?" + " AND "+ COLUMN_NURSE_PASSWORD+ " = ?"; //The row data to be returned from the column(WHERE clause)
        String [] selectionArgs = {nurseID, password}; //Substituted for the " = ?" in the selection

        Cursor cursor = db.query(TABLE_NURSE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //------------------------------------------Check DB for Registration---------------------------------------------
    //Check Doctor
    public boolean checkDoctorReg(String doctorID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_DOCTOR_ID};
        String selection = COLUMN_DOCTOR_ID+ " = ?";
        String [] selectionArgs = {doctorID};

        Cursor cursor = db.query(TABLE_DOCTOR,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //Check Nurse
    public boolean checkNurseReg(String nurseID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_NURSE_ID};
        String selection = COLUMN_NURSE_ID+ " = ?";
        String [] selectionArgs = {nurseID};

        Cursor cursor = db.query(TABLE_NURSE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //--------------------------------------------Check DB for Patient-------------------------------------------------
    //Check Doctor
    public boolean checkPatient(String patientId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_PATIENT_ID};
        String selection = COLUMN_PATIENT_ID+ " = ?";
        String [] selectionArgs = {patientId};

        Cursor cursor = db.query(TABLE_PATIENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //----------------------------------------------Check DB for Test--------------------------------------------------
    //Check Doctor
    public boolean checkTestId(String testID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_TEST_ID};
        String selection = COLUMN_TEST_ID+ " = ?";
        String [] selectionArgs = {testID};

        Cursor cursor = db.query(TABLE_TEST,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //-------------------------------------------Check Test DB for Patient---------------------------------------------
    //Check Doctor
    public boolean checkTestForPatient(String patientId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = {COLUMN_PATIENT_TEST_ID};
        String selection = COLUMN_PATIENT_TEST_ID+ " = ?";
        String [] selectionArgs = {patientId};

        Cursor cursor = db.query(TABLE_TEST,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }

    //----------------------------------------------Fetch All Patients------------------------------------------------
    public List<Patient> getAllPatients()
    {
        //Array of columns to fetch
        String [] columns = {
                COLUMN_PATIENT_ID,
                COLUMN_PATIENT_FIRSTNAME,
                COLUMN_PATIENT_LASTNAME,
                COLUMN_PATIENT_DEPARTMENT,
                COLUMN_PATIENT_DOCTOR_ID,
                COLUMN_PATIENT_ROOM
        };

        //Sorting Order
        String sortOrder = COLUMN_PATIENT_FIRSTNAME + " ASC";

        List<Patient> patientList = new ArrayList<Patient>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT,
                columns,    //Columns to return
                null,       //Columns for WHERE clause
                null,       //The values for WHERE clause
                null,       //Group the rows
                null,       //Filter the row groups
                sortOrder); //The sort order

        //Traversing through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Patient patient = new Patient();
                patient.setPatientId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID))));
                patient.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_FIRSTNAME)));
                patient.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_LASTNAME)));
                patient.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_DEPARTMENT)));
                patient.setDoctorId(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_DOCTOR_ID)));
                patient.setRoom(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ROOM)));

                patientList.add(patient);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        //Return patient list
        return patientList;
    }

    //---------------------------------------------Fetch Patient Tests------------------------------------------------
    public List<Test> getPatientsTests(String patientId)
    {
        //Array of columns to fetch
        String [] columns = {
                COLUMN_TEST_ID,
                COLUMN_PATIENT_TEST_ID,
                COLUMN_NURSE_TEST_ID,
                COLUMN_BPL,
                COLUMN_BPH,
                COLUMN_TEMPERATURE
        };

        String selection = COLUMN_PATIENT_TEST_ID+ " = ?"; //The row data to be returned from the column(WHERE clause)
        String [] selectionArgs = {patientId};

        //Sorting Order
        String sortOrder = COLUMN_TEST_ID + " ASC";

        List<Test> patientTestList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_TEST,
                columns,    //Columns to return
                selection,       //Columns for WHERE clause
                selectionArgs,       //The values for WHERE clause
                null,       //Group the rows
                null,       //Filter the row groups
                sortOrder); //The sort order

        //Traversing through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Test test = new Test();
                test.setTestId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TEST_ID))));
                test.setPatientId(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_TEST_ID)));
                test.setNurseId(cursor.getString(cursor.getColumnIndex(COLUMN_NURSE_TEST_ID)));
                test.setBPL(cursor.getString(cursor.getColumnIndex(COLUMN_BPL)));
                test.setBPH(cursor.getString(cursor.getColumnIndex(COLUMN_BPH)));
                test.setTemperature(cursor.getString(cursor.getColumnIndex(COLUMN_TEMPERATURE)));

                patientTestList.add(test);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        //Return patient tests list
        return patientTestList;
    }
}
