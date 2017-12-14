package gomes.john.johngomes_comp304lab4.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.activities.PatientActivity;
import gomes.john.johngomes_comp304lab4.activities.PatientTestList;
import gomes.john.johngomes_comp304lab4.activities.TestDataActivity;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.sql.DatabaseManager;

import static android.content.ContentValues.TAG;

/**
 * Created by John on 2017-12-12.
 */

public class DisplayAdapterPatients extends RecyclerView.Adapter<DisplayAdapterPatients.PatientViewHolder>
{
    private Context context;
    private ArrayList<Patient> listPatients;
    private DatabaseManager databaseManager;
    private String selctedPatientID;

    public DisplayAdapterPatients(Context _context, ArrayList<Patient> _listPatients)
    {
        this.listPatients = _listPatients;
        this.context = _context;
        databaseManager = new DatabaseManager(context);
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Inflating recycler item view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_fields, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position)
    {
        holder.patientId.setText(Integer.toString(listPatients.get(position).getPatientId()));
        holder.firstName.setText(listPatients.get(position).getFirstName());
        holder.lastName.setText(listPatients.get(position).getLastName());
        holder.department.setText(listPatients.get(position).getDepartment());
        holder.doctorId.setText(listPatients.get(position).getDoctorId());
        holder.room.setText(listPatients.get(position).getRoom());
    }

    @Override
    public int getItemCount() {
        return listPatients.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView patientId, firstName, lastName, department, doctorId, room;

        public PatientViewHolder(View itemView)
        {
            super(itemView);
            patientId = itemView.findViewById(R.id.field_patientId);
            firstName = itemView.findViewById(R.id.field_firstName);
            lastName = itemView.findViewById(R.id.field_lastName);
            department = itemView.findViewById(R.id.field_department);
            doctorId = itemView.findViewById(R.id.field_doctorId);
            room = itemView.findViewById(R.id.field_room);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();
            Log.v(TAG, "onClick " + this.getAdapterPosition());
            listPatients.get(position);
            selctedPatientID= patientId.getText().toString().trim();
            verifyFromSQL();
        }

        private void verifyFromSQL()
        {
            //Data Query
            if (databaseManager.checkTestForPatient(selctedPatientID))
            {
                //Start Patient Tests Activities
                Intent intentTestList = new Intent(itemView.getContext(), PatientTestList.class);
                intentTestList.putExtra("patient_Id", selctedPatientID);
                itemView.getContext().startActivity(intentTestList);
            }
            else
            {
                Toast.makeText(context, "No test exists for this patient", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
