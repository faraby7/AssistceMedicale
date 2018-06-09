package com.ensias.assistancemedicale.FragmentMedecin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.RecyclerViewAdapter;
import com.ensias.assistancemedicale.HomeMedecin;
import com.ensias.assistancemedicale.HomePatient;
import com.ensias.assistancemedicale.Inscription;
import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Model.Medicament;
import Task.AsyncGetTask;
import Task.AsyncInscTask;

public class RendezVousFragment extends Fragment {

    EditText rendezDate;
    EditText userName;
    EditText description;
    Button addRendezVous;

    private Calendar myCalendar;
    private String myFormat;
    private SimpleDateFormat sdf;
    int type = 1;

    Context mContext;

    public RendezVousFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_rendez_vous, null);

        userName =(EditText) result.findViewById(R.id.usernamepatient);
        description=(EditText)result.findViewById(R.id.description);
        addRendezVous = (Button) result.findViewById(R.id.RendezVousButton) ;

        addRendezVous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String result1 = new AsyncGetTask(mContext).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindPatient&username=" + userName.getText().toString()).get();
                    JSONArray jArray1 = new JSONArray(result1);

                    if (jArray1.length() == 0) {

                        Toast.makeText(mContext, "Patient Not Found", Toast.LENGTH_LONG).show();
                        Toast.makeText(mContext, "Try again", Toast.LENGTH_LONG).show();

                    } else {
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Medecin", Context.MODE_PRIVATE);
                        JSONObject json_data = jArray1.getJSONObject(0);

                        if(json_data.getInt("id_medecin")!=sharedPreferences.getInt("idMedecin",0)){

                            Toast.makeText(mContext, "Patient Have Other Doctor", Toast.LENGTH_LONG).show();

                        }else {


                            //String url1 = "idPatient="+json_data.getInt("id")+"&description=" + description.getText().toString() +"&date="+rendezDate.getText().toString();
                           // new AsyncInscTask(mContext,url1).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=AddRDV");
                            String result2 = new AsyncGetTask(mContext).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=AddRDV&idPatient="+json_data.getInt("id")+"&description=" + description.getText().toString() +"&date="+rendezDate.getText().toString()).get();
                            Toast.makeText(mContext,"Add Done",Toast.LENGTH_LONG).show();

                            Log.d("TTG","Listner Called");
                            Intent AjouterPatient = new Intent(mContext, HomeMedecin.class);
                            startActivity(AjouterPatient);

                        }





                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });



        rendezDate= (EditText)result.findViewById(R.id.rendezDate);
        myCalendar = Calendar.getInstance();

        myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        //birthday=myCalendar.getTime();
        rendezDate.setText(sdf.format(myCalendar.getTime()));
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


        rendezDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RendezVousFragment.this.mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return result;
    }



    private void updateLabel() {
        myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        //birthday=myCalendar.getTime();
        rendezDate.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


    }
}