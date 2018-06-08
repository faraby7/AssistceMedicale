package com.ensias.assistancemedicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Task.AsyncInscTask;

public class InscriptionMedecin extends AppCompatActivity {



    Button btn;
    EditText nom, prenom, username, password,phone, adresse, specialite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_medecin);
    }


    public void GoToSignIn(View view){

        Intent SignIn = new Intent(view.getContext(),MainActivity.class);
        startActivity(SignIn);
    }




    public void SignUp(View view){

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText)findViewById(R.id.prenom);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.Password);
        phone =(EditText)findViewById(R.id.phone);
        adresse =(EditText)findViewById(R.id.adresse);
        specialite = (EditText)findViewById(R.id.specialite);


        String url = "nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&username=" + username.getText().toString()  + "&specialite=" + specialite.getText().toString() + "&password=" + password.getText().toString() + "&adresse=" + adresse.getText().toString() + "&telephone=" + phone.getText().toString();
        new AsyncInscTask(this,url).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=signup_doctor");
        Toast.makeText(this,"Inscription Done",Toast.LENGTH_LONG).show();
        Intent i = new Intent(InscriptionMedecin.this,MainActivity.class);
        startActivity(i);

    }
}
