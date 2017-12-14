package gomes.john.johngomes_comp304lab4.helper;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gomes.john.johngomes_comp304lab4.R;
import gomes.john.johngomes_comp304lab4.activities.TestDataActivity;
import gomes.john.johngomes_comp304lab4.model.Patient;
import gomes.john.johngomes_comp304lab4.model.Test;

import static android.content.ContentValues.TAG;

/**
 * Created by John on 2017-12-13.
 */

public class DisplayAdapterPatientTests extends RecyclerView.Adapter<DisplayAdapterPatientTests.PatientViewHolder>
{
    private Context context;
    private ArrayList<Test> listPatientTests;

    public DisplayAdapterPatientTests(Context _context, ArrayList<Test> _listPatientTests)
    {
        this.listPatientTests = _listPatientTests;
        this.context = _context;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Inflating recycler item view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_fields, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayAdapterPatientTests.PatientViewHolder holder, int position)
    {
        holder.testId.setText(Integer.toString(listPatientTests.get(position).getTestId()));
        holder.patientId.setText(listPatientTests.get(position).getPatientId());
        holder.nurseId.setText(listPatientTests.get(position).getNurseId());
        holder.BPL.setText(listPatientTests.get(position).getBPL());
        holder.BPH.setText(listPatientTests.get(position).getBPH());
        holder.temp.setText(listPatientTests.get(position).getTemperature());
    }

    @Override
    public int getItemCount() {
        return listPatientTests.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder
    {
        TextView testId, patientId, nurseId, BPL, BPH, temp;

        public PatientViewHolder(View itemView)
        {
            super(itemView);
            testId = itemView.findViewById(R.id.field_testId);
            patientId = itemView.findViewById(R.id.field_patientId);
            nurseId = itemView.findViewById(R.id.field_nurseId);
            BPL = itemView.findViewById(R.id.field_BPL);
            BPH = itemView.findViewById(R.id.field_BPH);
            temp = itemView.findViewById(R.id.field_temp);
        }

    }
}
