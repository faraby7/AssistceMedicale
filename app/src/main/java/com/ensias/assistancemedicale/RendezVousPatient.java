package com.ensias.assistancemedicale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.PatientsAdapter;
import com.ensias.assistancemedicale.Adapters.RdvAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Model.Patient;
import Model.Rdv;
import Task.AsyncGetTask;

public class RendezVousPatient extends AppCompatActivity {



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Patient> patients;
    private List<Rdv> rdvs;
    SharedPreferences sharedPreferences;
    EditText userNamepatient;
    EditText idMedecin;
    EditText PatientName;
    Button Add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous_patient);
        ///AssistanceMedicale/web_services.php?action=FindAllRendezVous
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_Rdv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        patients = new ArrayList<>();
        rdvs = new ArrayList<>();

        loadRecyclerViewData();

    }


    private void loadRecyclerViewData() {



        try {


            sharedPreferences = this.getSharedPreferences("Medecin", this.MODE_PRIVATE);
            String result = new AsyncGetTask(this).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindAllRendezVous").get();
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json_data = jsonArray.getJSONObject(i);
                Rdv rdv=new Rdv(json_data.getString("Description"), json_data.getString(    "date"),null);
                Log.d("TQGRD","RDV: "+rdv);

                String result2 = new AsyncGetTask(this).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindPatientId&id="+ json_data.getInt("id_patient")).get();

                JSONArray jsonArray2 = new JSONArray(result2);

                JSONObject json_data2 = jsonArray2.getJSONObject(0);

                Patient item = new Patient(json_data2.getInt("id"), json_data2.getString("nom"), json_data2.getString("prenom"), json_data2.getString("email"), json_data2.getString("telephone"), json_data2.getString("username"), json_data2.getString("password"), json_data2.getString("adresse"), json_data2.getString("datenaissance"), null);
               if(json_data2.getInt("id_medecin")==sharedPreferences.getInt("idMedecin", 0)){
                    patients.add(item);

                   rdvs.add(rdv);

                }




            }

            adapter = new RdvAdapter(patients, rdvs, this);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

