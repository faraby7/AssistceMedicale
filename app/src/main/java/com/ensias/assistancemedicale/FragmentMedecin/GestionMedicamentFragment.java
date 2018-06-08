package com.ensias.assistancemedicale.FragmentMedecin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.PatientsAdapter;
import com.ensias.assistancemedicale.Adapters.RecyclerViewAdapter;
import com.ensias.assistancemedicale.HomeMedecin;
import com.ensias.assistancemedicale.HomePatient;
import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.Medecin;
import Model.Medicament;
import Model.Patient;
import Task.AsyncGetTask;
import Task.AsyncInscTask;

public class GestionMedicamentFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Medicament> medicaments;
    private List<Drawable> logo;
    SharedPreferences sharedPreferences;
    EditText userNamepatient;
    EditText idMedecin;
    EditText PatientName;
    Button Add;
    Context mContext;

    public GestionMedicamentFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_gestion_medicament, null);

        return result;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        medicaments= new ArrayList<>();
        logo = new ArrayList<>();


        try {

            sharedPreferences = getContext().getSharedPreferences("Medecin", getContext().MODE_PRIVATE);
            String result = new AsyncGetTask(getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=AllMedicament&id=10").get();
            JSONArray jsonArray = new JSONArray(result);
            //progressDialog.dismiss();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                Medicament item = new Medicament(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("type"), json_data.getString("description"));
                medicaments.add(item);
                switch (item.getNom().charAt(0)){
                    case 'A': logo.add(getResources().getDrawable(R.drawable.a));break;
                    case 'I': logo.add(getResources().getDrawable(R.drawable.i));break;
                    case 'E': logo.add(getResources().getDrawable(R.drawable.e));break;
                    case 'G': logo.add(getResources().getDrawable(R.drawable.g));break;
                    default: logo.add(getResources().getDrawable(R.drawable.a));break;
                }



            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        recyclerView= (RecyclerView) view.findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter  adapter  = new RecyclerViewAdapter(medicaments,logo,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

}