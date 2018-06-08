package com.ensias.assistancemedicale;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Task.AsyncInscTask;

public class Inscription extends AppCompatActivity {


    Button btn;
    EditText nom, prenom, username, password,phone, adresse, dateNaissance;
    private Calendar myCalendar;
    private String myFormat;
    private SimpleDateFormat sdf;
    int type = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        dateNaissance = (EditText) findViewById(R.id.datenaissance);
        myCalendar = Calendar.getInstance();

        myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        //birthday=myCalendar.getTime();
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
        //dateNaissance.setText(sdf.format(myCalendar.getTime()));

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        dateNaissance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Inscription.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }



    private void updateLabel() {
        myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        //birthday=myCalendar.getTime();
        dateNaissance.setText(sdf.format(myCalendar.getTime()));
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


        String url = "nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&username=" + username.getText().toString()  + "&datenaissance=" + dateNaissance.getText().toString() + "&password=" + password.getText().toString() + "&adresse=" + adresse.getText().toString() + "&telephone=" + phone.getText().toString();
        new AsyncInscTask(this,url).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=signup_patient");
        Toast.makeText(this,"Inscription Done",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Inscription.this,MainActivity.class);
        startActivity(i);

    }

}
