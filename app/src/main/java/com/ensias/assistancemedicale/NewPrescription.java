package com.ensias.assistancemedicale;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.PatientsAdapter;
import com.ensias.assistancemedicale.FragmentMedecin.GestionOrdonnancesFragment;
import com.ensias.assistancemedicale.HomeMedecin;
import com.ensias.assistancemedicale.HomePatient;
import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import Model.Medecin;
import Model.Patient;
import Task.AsyncGetTask;
import Task.AsyncInscTask;

public class NewPrescription extends AppCompatActivity {



    SharedPreferences sharedPreferences;
    EditText duree;
    EditText nom_medicament;
    Button ajout;
    Button terminer;
    CheckBox matin;
    CheckBox midi;
    CheckBox soir;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_prescription);


        duree = (EditText) findViewById(R.id.ordonnance_nbjour);
        nom_medicament = (EditText) findViewById(R.id.ordonnance_nom_medicament);
        ajout = (Button) findViewById(R.id.ordonnance_next_prescription);
        terminer = (Button) findViewById(R.id.ordonnance_terminer);
        matin = (CheckBox) findViewById(R.id.heure_matin);
        midi = (CheckBox) findViewById(R.id.heure_midi);
        soir = (CheckBox) findViewById(R.id.heure_soir);

        //sharedPreferences = mContext.getSharedPreferences("Medecin", Context.MODE_PRIVATE);

        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String url = MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindMedicamentByName&nom=" + nom_medicament.getText().toString();
                    url.replaceAll(" " ,"%20");

                    String result_medicament = new AsyncGetTask(getApplicationContext()).execute(url).get();
                    JSONArray jsonArrayMedicament = new JSONArray(result_medicament);

                    if(jsonArrayMedicament.length()==0)
                    {
                        Toast.makeText(getApplicationContext(), "medicament n'existe pas", Toast.LENGTH_LONG).show();
                    }
                    else{

                        int h_matin=0;
                        int h_midi=0;
                        int h_soir=0;
                        if(matin.isChecked()) h_matin=1;
                        if(midi.isChecked()) h_midi=1;
                        if(soir.isChecked()) h_soir=1;


                        JSONObject json_medicament = jsonArrayMedicament.getJSONObject(0);
                        new AsyncGetTask(getApplicationContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=AjoutPrescription&id_ordonnance=" + GestionOrdonnancesFragment.id_ord +"&id_medicament="+json_medicament.getInt("id")+"&duree="+duree.getText().toString()+"&matin="+h_matin+"&midi="+h_midi+"&soir="+h_soir).get();

                        Intent newPrescription = new Intent(getApplicationContext(),NewPrescription.class);
                        startActivity(newPrescription);

                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }});

        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(),HomeMedecin.class);
                startActivity(home);
            }
        });

    }





    /*private void loadRecyclerViewData() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        try {
            sharedPreferences = getContext().getSharedPreferences("Medecin", getContext().MODE_PRIVATE);
            // Toast.makeText(getContext(), sharedPreferences.getInt("idMedecin",0), Toast.LENGTH_LONG ).show();
            String result = new AsyncGetTask(getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=medecin_patient&idmedecin=" + sharedPreferences.getInt("idMedecin", 0)).get();
            JSONArray jsonArray = new JSONArray(result);
            progressDialog.dismiss();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                Patient item = new Patient(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("email"), json_data.getString("telephone"), json_data.getString("username"), json_data.getString("password"), json_data.getString("adresse"), json_data.getString("datenaissance"), null);
                patients.add(item);
                if (i % 5 == 0) {
                    images.add(getResources().getDrawable(R.drawable.patient1));
                } else {
                    if (i % 5 == 1) {
                        images.add(getResources().getDrawable(R.drawable.patient2));
                    } else {
                        if (i % 5 == 2) {
                            images.add(getResources().getDrawable(R.drawable.patient3));
                        } else {
                            if (i % 5 == 3) {
                                images.add(getResources().getDrawable(R.drawable.patient4));
                            } else {
                                if (i % 5 == 4) {
                                    images.add(getResources().getDrawable(R.drawable.patient5));
                                }
                            }
                        }
                    }
                }
            }
            adapter = new PatientsAdapter(patients, images, getContext());
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}