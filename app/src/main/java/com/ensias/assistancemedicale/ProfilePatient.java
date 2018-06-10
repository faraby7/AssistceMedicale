package com.ensias.assistancemedicale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Patient;
import Task.AsyncGetTask;

import static com.ensias.assistancemedicale.MainActivity.IP;

public class ProfilePatient extends AppCompatActivity {
    TextView nompatient;
    TextView emailPatient;
    TextView adressePatient;
    TextView phonePatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_patient);
        String id =getIntent().getExtras().getString("id");
        try {
        String result = new AsyncGetTask(this).execute(IP + "/AssistanceMedicale/web_services.php?action=FindPatientId&id=" + id.toString()).get();
        JSONArray jArray = new JSONArray(result);
        JSONObject json_data = jArray.getJSONObject(0);
        Patient patient = new Patient(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("email"), json_data.getString("telephone"), json_data.getString("username"), json_data.getString("password"), json_data.getString("adresse"), json_data.getString("datenaissance"), null);
        nompatient = (TextView) findViewById(R.id.nomPatient);
        emailPatient = (TextView) findViewById(R.id.emailPatient);
        adressePatient = (TextView) findViewById(R.id.adressePatient);
        phonePatient = (TextView) findViewById(R.id.phonePatient);

        nompatient.setText(patient.getNom()+" "+patient.getPrenom());
        emailPatient.setText(patient.getEmail());
        adressePatient.setText(patient.getAdresse());
        phonePatient.setText(patient.getTelephone());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
