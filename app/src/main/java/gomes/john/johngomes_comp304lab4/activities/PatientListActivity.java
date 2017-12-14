package gomes.john.johngomes_comp304lab4.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.helper.DisplayAdapterPatients;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

public class PatientListActivity extends AppCompatActivity
{
    private final AppCompatActivity activity= PatientListActivity.this;
    Context context = PatientListActivity.this;
    private RecyclerView recyclerViewPatient;
    private ArrayList<Patient> listPatients;
    private DisplayAdapterPatients patientDisplayAdapterPatients;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        this.setTitle(R.string.toolbar_patientDetails);

        initViews();
        initObjects();
    }
    private void initViews()
    {
        recyclerViewPatient = (RecyclerView) findViewById(R.id.pateint_recyclerView);
    }

    private void initObjects()
    {
        listPatients = new ArrayList<>();
        patientDisplayAdapterPatients = new DisplayAdapterPatients(this, listPatients);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPatient.setLayoutManager(layoutManager);
        recyclerViewPatient.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPatient.setHasFixedSize(true);
        recyclerViewPatient.setAdapter(patientDisplayAdapterPatients);
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
                listPatients.clear();
                listPatients.addAll(databaseManager.getAllPatients());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                patientDisplayAdapterPatients.notifyDataSetChanged();
            }
        }.execute();
    }
}
