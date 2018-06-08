package com.ensias.assistancemedicale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;

import Model.Medecin;
import Model.Patient;
import Task.AsyncGetTask;

public class MainActivity extends AppCompatActivity {

    public static final String IP="http://192.168.1.11";
    SharedPreferences sharedPreferences;
    EditText UserName,Password;
    Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("Medecin", Context.MODE_PRIVATE);
        //sharedPreferences.edit().clear().commit();
        if(sharedPreferences.getString("nomMedecin", null) != null) {

            Intent Home = new Intent(this, HomeMedecin.class);
            startActivity(Home);
            finish();

        }

        sharedPreferences = getSharedPreferences("Patient", Context.MODE_PRIVATE);
     //   sharedPreferences.edit().clear().commit();
        if(sharedPreferences.getString("nomPatient", null) != null) {

            Intent Home2 = new Intent(this, HomePatient.class);
            startActivity(Home2);
            finish();

        }


    }

    public void GoToPatientInscription(View view){

        Intent InscriptionPatient = new Intent(view.getContext(),Inscription.class);
        startActivity(InscriptionPatient);
    }

    public void GoToMedecinInscription(View view){

        Intent InscriptionMedecin = new Intent(view.getContext(), InscriptionMedecin.class);
        startActivity(InscriptionMedecin);


    }

    public void SignIn(View view){

        UserName = (EditText)findViewById(R.id.UserName);
        Password = (EditText) findViewById(R.id.Password);
        Toast.makeText(this, "Sign in", Toast.LENGTH_LONG).show();

            try {

                String result = new AsyncGetTask(this).execute(IP + "/AssistanceMedicale/web_services.php?action=signin_patient&username=" + UserName.getText().toString() + "&password=" + Password.getText().toString()).get();
                JSONArray jArray = new JSONArray(result);

                if (jArray.length() == 0) {

                    String result2 = new AsyncGetTask(this).execute(IP + "/AssistanceMedicale/web_services.php?action=signin_medecin&username=" + UserName.getText().toString() + "&password=" + Password.getText().toString()).get();
                    JSONArray jArray2 = new JSONArray(result2);

                    if (jArray2.length() == 0) {

                        Toast.makeText(this, "LogIn Or Password Error", Toast.LENGTH_LONG).show();
                        Intent p = new Intent(this, MainActivity.class);
                        Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show();
                        startActivity(p);
                        finish();

                    } else {

                        for (int i = 0; i < jArray2.length(); i++) {


                            JSONObject json_data = jArray2.getJSONObject(i);
                            Medecin medecin = new Medecin(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("email"), json_data.getString("telephone"), json_data.getString("username"), json_data.getString("password"), json_data.getString("adresse"), json_data.getString("specialite"));
                            //create session
                            sharedPreferences = getSharedPreferences("Medecin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("idMedecin",medecin.getId());
                            editor.putString("nomMedecin",medecin.getNom());
                            editor.putString("prenomMedecin",medecin.getPrenom());
                            editor.putString("emailMedecin",medecin.getEmail());
                            editor.putString("passwordMedecin",medecin.getPassword());
                            editor.putString("specialiteMedecin",medecin.getSpecialite());
                            editor.putString("telMedecin",medecin.getTelephone());
                            editor.putString("usernameMedecin",medecin.getUserName());
                            editor.putString("adresseMedecin",medecin.getAdresse());
                            editor.commit();

                            Toast.makeText(this, "Welcome Doctor " + medecin.getNom(), Toast.LENGTH_LONG).show();
                            Intent Home2 = new Intent(this, HomeMedecin.class);
                            startActivity(Home2);
                            finish();

                        }
                    }

                } else {


                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject json_data = jArray.getJSONObject(i);
                        Patient patient = new Patient(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("email"), json_data.getString("telephone"), json_data.getString("username"), json_data.getString("password"), json_data.getString("adresse"), json_data.getString("datenaissance"), null);
                        Toast.makeText(this, "Welcome Mr " + patient.getNom(), Toast.LENGTH_LONG).show();

                        sharedPreferences = getSharedPreferences("Patient", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("idPatient",patient.getId());
                        editor.putString("nomPatient",patient.getNom());
                        editor.putString("prenomPatient",patient.getPrenom());
                        editor.putString("emailPatient",patient.getEmail());
                        editor.putString("passwordPatient",patient.getPassword());
                        editor.putString("dateNaissancePatient",patient.getDateNaissance().toString());
                        editor.putString("telPatient",patient.getTelephone());
                        editor.putString("usernamePatient",patient.getUserName());
                        editor.commit();
                        Intent Home = new Intent(this, HomePatient.class);
                        startActivity(Home);
                        finish();

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();

            }


        }

}
