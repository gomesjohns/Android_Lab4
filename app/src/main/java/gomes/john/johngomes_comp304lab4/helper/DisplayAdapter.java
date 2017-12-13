package gomes.john.johngomes_comp304lab4.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.model.Patient;

/**
 * Created by John on 2017-12-12.
 */

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.PatientViewHolder>
{
    private Context context;
    private ArrayList<Patient> listPatients;

    public DisplayAdapter(Context _context, ArrayList<Patient> _listPatients, View itemView)
    {
        this.listPatients = _listPatients;
        this.context = _context;

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
        Context context;
        ArrayList<Patient> patients = new ArrayList<>();

        public PatientViewHolder(View itemView, Context ctx, ArrayList<Patient> patients)
        {
            super(itemView);

            this.patients = patients;
            this.context = ctx;

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

        }
    }

}
