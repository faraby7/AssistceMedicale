package com.ensias.assistancemedicale.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.R;

import java.util.List;

import Model.Medicament;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //LayoutInflater inflater;
    Context context;
    private List<Medicament> listItems;
    private List<Drawable> logos;



    public RecyclerViewAdapter(List<Medicament> listItems, List<Drawable> imageView, Context context) {
        this.context=context;
        this.logos=imageView;
        this.listItems =listItems;
      //  inflater=LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_medicament,parent,false);
        ViewHolder e = new ViewHolder(v);
        return e;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Medicament listItem = listItems.get(position);
        final Drawable listlogo =logos.get(position);

        holder.tv1.setText("Nom :" + listItem.getNom());
        holder.tv2.setText("Type :"+listItem.getType());
        holder.imageView.setImageDrawable(listlogo);
    }


    @Override
    public int getItemCount() {
        if(listItems==null){
            return 0;

        }else{
            return listItems.size();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1, tv2;
        ImageView imageView;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.nomMedicament);
            tv2 = (TextView) itemView.findViewById(R.id.typeMedicament);
            imageView = (ImageView) itemView.findViewById(R.id.logo);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}