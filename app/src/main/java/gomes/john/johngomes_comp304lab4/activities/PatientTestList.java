package gomes.john.johngomes_comp304lab4.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.helper.DisplayAdapterPatientTests;
import gomes.john.johngomes_comp304lab4.helper.DisplayAdapterPatients;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.model.Test;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

public class PatientTestList extends AppCompatActivity {

    private final AppCompatActivity activity= PatientTestList.this;
    Context context = PatientTestList.this;
    private RecyclerView recyclerViewPatient;
    private ArrayList<Test> listPatientTests;
    private DisplayAdapterPatientTests patientTestDisplayAdapter;
    private DatabaseManager databaseManager;
    Intent intent;
    private String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_test_list);

        this.setTitle(R.string.toolbar_testDetails);

        initViews();
        initObjects();

        intent = getIntent();
        Bundle extras = intent.getExtras();
        patientId = (String)extras.get("patient_Id");
    }



    private void initViews()
    {
        recyclerViewPatient = (RecyclerView) findViewById(R.id.pateint_test_recyclerView);
    }

    private void initObjects()
    {
        listPatientTests = new ArrayList<>();
        patientTestDisplayAdapter = new DisplayAdapterPatientTests(this, listPatientTests);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPatient.setLayoutManager(layoutManager);
        recyclerViewPatient.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPatient.setHasFixedSize(true);
        recyclerViewPatient.setAdapter(patientTestDisplayAdapter);
        databaseManager = new DatabaseManager(activity);

        getDataFromSQLite();
    }

    private void getDataFromSQLite()
    {
        //AsyncTask is used so that SQLite operation doesn't block UI
        new AsyncTask<Void, Void, Void>()
        {

            @Override
            protected Void doInBackground(Void... voids)
            {
                listPatientTests.clear();
                listPatientTests.addAll(databaseManager.getPatientsTests(patientId));
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                patientTestDisplayAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
