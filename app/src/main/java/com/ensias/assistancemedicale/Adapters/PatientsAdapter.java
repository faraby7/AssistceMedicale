package com.ensias.assistancemedicale.Adapters;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ensias.assistancemedicale.ProfilePatient;
import com.ensias.assistancemedicale.R;

import java.util.List;

import Model.Patient;
import Model.User;

/**
 * Created by freenemya on 5/1/18.
 */

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.ViewHolder> implements NumberPicker.OnValueChangeListener{
    private List<Patient> listItems;
    private Context context;
    private int pos;
    private List<Drawable> images;
    private ViewHolder viewHolder;
    public PatientsAdapter(List<Patient> listItems, List<Drawable> imageView, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.images=imageView;
    }

    @NonNull
    @Override
    public PatientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient,parent,false);
        return new PatientsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Patient listItem = listItems.get(position);
        final Drawable listimage =images.get(position);
       // holder.id.setText(listItem.getId());
        holder.Nom.setText("Nom :" + listItem.getNom());
        holder.UserName.setText("Email :"+listItem.getUserName());
        holder.image.setImageDrawable(listimage);

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is", "" + newVal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Nom;
        public TextView UserName;
        public ImageView image;
       // public TextView id;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
           //    id =(TextView) itemView.findViewById(R.id.idpatient);
            Nom = (TextView) itemView.findViewById(R.id.nom);
            UserName = (TextView)itemView.findViewById(R.id.username);
            image =(ImageView)itemView.findViewById(R.id.imagepatient);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Integer idpatienti = listItems.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context,ProfilePatient.class);
                    i.putExtra("id",idpatienti.toString());
                    context.startActivity(i);
                }
            });

        }

    }



}