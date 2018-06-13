package com.ensias.assistancemedicale.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.ensias.assistancemedicale.R;

import org.w3c.dom.Text;

import java.util.List;

import Model.Medicament;
import Model.Ordonnance;
import Model.Patient;
import Model.Rdv;
import Model.prescription;

public class OrdonnanceAdapter extends RecyclerView.Adapter<OrdonnanceAdapter.ViewHolder> implements NumberPicker.OnValueChangeListener{


    private Context context;
    private List<Ordonnance> ordonnances;
    private List<prescription> prescriptions;
    private List<Medicament> medicaments;

    public OrdonnanceAdapter(List<Ordonnance> listItems1, List<prescription> listItems2,List<Medicament> listItems3, Context contextt) {
        this.ordonnances = listItems1;
        this.prescriptions =listItems2;
        this.medicaments =listItems3;
        this.context =contextt;
    }

    @NonNull
    @Override
    public OrdonnanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ordonnance,parent,false);
        return new OrdonnanceAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdonnanceAdapter.ViewHolder holder, final int position) {

        final Ordonnance ord1 = ordonnances.get(position);
        final prescription pre1 = prescriptions.get(position);
        final Medicament med1 = medicaments.get(position);


        holder.nomMedicament.setText(med1.getNom());
        holder.matin.setText(""+pre1.getHeureMatin());
        holder.midi.setText(""+pre1.getHeureMidi());
        holder.soir.setText(""+pre1.getHeureSoir());
        holder.duree.setText(""+pre1.getDuree());
    }


    @Override
    public int getItemCount() {
        return prescriptions.size();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is", "" + newVal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nomMedicament;
        public TextView matin;
        public TextView midi;
        public TextView soir;
        public TextView duree;



        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            nomMedicament = (TextView) itemView.findViewById(R.id.nomMedicament);
            matin =(TextView)itemView.findViewById(R.id.matin);
            midi =(TextView)itemView.findViewById(R.id.midi);
            soir =(TextView)itemView.findViewById(R.id.soir);
            duree =(TextView)itemView.findViewById(R.id.duree);

        }

    }



}