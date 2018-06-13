package com.ensias.assistancemedicale.FragmentPatient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.OrdonnanceAdapter;
import com.ensias.assistancemedicale.Adapters.PatientsAdapter;
import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.Medicament;
import Model.Ordonnance;
import Model.Patient;
import Model.prescription;
import Task.AsyncGetTask;

public class MyOrdonnanceFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Ordonnance> ordonnances;
    private List<prescription> prescriptions;
    private List<Medicament> medicaments;

    SharedPreferences sharedPreferences;
    EditText userNamepatient;
    EditText idMedecin;
    EditText PatientName;
    Button Add;
    Context mContext;


    public MyOrdonnanceFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View res = inflater.inflate(R.layout.fragment_my_ordonnance, null);
/*
        nomDoctor = (TextView) res.findViewById(R.id.nomDoctor);
        emailDoctor = (TextView) res.findViewById(R.id.emailDoctor);
        adresseDoctor = (TextView) res.findViewById(R.id.adresseDoctor);
        phoneDoctor = (TextView) res.findViewById(R.id.phoneDoctor);
        rendezVousDoctor = (TextView) res.findViewById(R.id.DateRendezVousWithDoctor);
        sepecialiteDoctor = (TextView) res.findViewById(R.id.specialite);
        SharedPreferences sharedPreferences = res.getContext().getSharedPreferences("Patient", res.getContext().MODE_PRIVATE);

        try {

            String result = new AsyncGetTask(res.getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindMedecinId&id="+ sharedPreferences.getInt("idMedecin", 0)).get();
            JSONArray jArray = new JSONArray(result);
            JSONObject json_data = jArray.getJSONObject(0);
            nomDoctor.setText(json_data.getString("nom")+ " " +  json_data.getString("prenom"));
            emailDoctor.setText(json_data.getString("email"));
            adresseDoctor.setText(json_data.getString("adresse"));
            phoneDoctor.setText(json_data.getString("telephone"));
            sepecialiteDoctor.setText(json_data.getString("specialite"));
            Toast.makeText(res.getContext(), "Welcome Mr "+sharedPreferences.getInt("idPatient", 0), Toast.LENGTH_LONG).show();
            String result2 = new AsyncGetTask(res.getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindRendezVousId&idPatient="+ sharedPreferences.getInt("idPatient", 0)).get();
            JSONArray jArray2 = new JSONArray(result2);
            JSONObject json_data2 = jArray2.getJSONObject(0);
            rendezVousDoctor.setText(json_data2.getString("date"));



        }catch (Exception e){

        }
*/
        return res;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ordonnances = new ArrayList<>();
        medicaments = new ArrayList<>();
        prescriptions = new ArrayList<>();


        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {

        try {
           // Log.d("AST1","hi 1");
            sharedPreferences = getContext().getSharedPreferences("Patient", getContext().MODE_PRIVATE);
            String result = new AsyncGetTask(getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindOrdonnancePatient&idPatient=" + sharedPreferences.getInt("idPatient", 0)).get();
            JSONArray jsonArray = new JSONArray(result);
            JSONObject json_data = jsonArray.getJSONObject(0);
            Ordonnance ordonnance1 = new Ordonnance(json_data.getInt("id"), json_data.getInt("id_patient"), json_data.getInt("id_medecin"), json_data.getString("date"));
           // Log.d("AST2","hi 2");

            String result1 = new AsyncGetTask(getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindAllPrescription&idOrdonnance=" + ordonnance1.getId()).get();
            JSONArray jsonArray1 = new JSONArray(result1);
            //Log.d("AST3",result);
            //Log.d("AST4",result1);
            for (int i = 0; i < jsonArray1.length(); i++) {
                Log.d("AST1","hi");
                JSONObject json_data1 = jsonArray1.getJSONObject(i);
                prescription prescription1 = new prescription(json_data1.getInt("id_medicament"),json_data1.getInt("id_ordonnance"),json_data1.getInt("duree"),json_data1.getInt("heure_matin"),json_data1.getInt("heure_midi"),json_data1.getInt("heure_soir"));
                Log.d("AST2","hi");

                String result2 = new AsyncGetTask(getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindMedicamentById&id=" + json_data1.getInt("id_medicament")).get();
                JSONArray jsonArray2 = new JSONArray(result2);
                Log.d("AST3",result2);
                JSONObject json_data2 = jsonArray2.getJSONObject(0);
                Medicament medicament1 = new Medicament(json_data2.getInt("id"), json_data2.getString("nom"), json_data2.getString("type"), json_data2.getString("description"));


                medicaments.add(medicament1);
                ordonnances.add(ordonnance1);
                prescriptions.add(prescription1);
                

            }

            adapter = new OrdonnanceAdapter(ordonnances,prescriptions,medicaments,getContext());
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}