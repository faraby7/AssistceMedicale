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
        import Model.Rdv;
        import Model.User;

/**
 * Created by freenemya on 5/1/18.
 */

public class RdvAdapter extends RecyclerView.Adapter<RdvAdapter.ViewHolder> implements NumberPicker.OnValueChangeListener{


    private Context context;
    private List<Patient> listItems;
    private List<Rdv> rdvs;

    public RdvAdapter(List<Patient> listItems, List<Rdv> rdvs, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.rdvs=rdvs;
    }

    @NonNull
    @Override
    public RdvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rdv,parent,false);
        return new RdvAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Patient listItem = listItems.get(position);
        final Rdv rdv =rdvs.get(position);
        holder.NomRdv.setText( listItem.getNom() +" "+listItem.getPrenom());
        holder.DescriptionRdv.setText(rdv.getDescription());
        holder.DateRdv.setText(rdv.getDate().toString());

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

        public TextView NomRdv;
        public TextView DescriptionRdv;
        public TextView DateRdv;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            NomRdv = (TextView) itemView.findViewById(R.id.nomRDV);
            DescriptionRdv = (TextView)itemView.findViewById(R.id.descriptionRDV);
            DateRdv =(TextView) itemView.findViewById(R.id.dateRDV);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Integer idpatienti = listItems.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context,ProfilePatient.class);
                    i.putExtra("id",idpatienti.toString());
                    context.startActivity(i);
                }
            });
            */
        }

    }



}