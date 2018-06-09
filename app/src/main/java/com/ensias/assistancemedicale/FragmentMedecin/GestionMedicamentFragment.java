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
                    case 'B': logo.add(getResources().getDrawable(R.drawable.b));break;
                    case 'C': logo.add(getResources().getDrawable(R.drawable.c));break;
                    case 'D': logo.add(getResources().getDrawable(R.drawable.d));break;
                    case 'E': logo.add(getResources().getDrawable(R.drawable.e));break;
                    case 'F': logo.add(getResources().getDrawable(R.drawable.f));break;
                    case 'G': logo.add(getResources().getDrawable(R.drawable.g));break;
                    case 'H': logo.add(getResources().getDrawable(R.drawable.h));break;
                    case 'I': logo.add(getResources().getDrawable(R.drawable.i));break;
                    case 'J': logo.add(getResources().getDrawable(R.drawable.j));break;
                    case 'K': logo.add(getResources().getDrawable(R.drawable.k));break;
                    case 'L': logo.add(getResources().getDrawable(R.drawable.l));break;
                    case 'M': logo.add(getResources().getDrawable(R.drawable.m));break;
                    case 'N': logo.add(getResources().getDrawable(R.drawable.n));break;
                    case 'O': logo.add(getResources().getDrawable(R.drawable.o));break;
                    case 'P': logo.add(getResources().getDrawable(R.drawable.p));break;
                    case 'Q': logo.add(getResources().getDrawable(R.drawable.q));break;
                    case 'R': logo.add(getResources().getDrawable(R.drawable.r));break;
                    case 'S': logo.add(getResources().getDrawable(R.drawable.s));break;
                    case 'T': logo.add(getResources().getDrawable(R.drawable.t));break;
                    case 'U': logo.add(getResources().getDrawable(R.drawable.u));break;
                    case 'V': logo.add(getResources().getDrawable(R.drawable.v));break;
                    case 'W': logo.add(getResources().getDrawable(R.drawable.w));break;
                    case 'X': logo.add(getResources().getDrawable(R.drawable.x));break;
                    case 'Y': logo.add(getResources().getDrawable(R.drawable.y));break;
                    case 'Z': logo.add(getResources().getDrawable(R.drawable.z));break;
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