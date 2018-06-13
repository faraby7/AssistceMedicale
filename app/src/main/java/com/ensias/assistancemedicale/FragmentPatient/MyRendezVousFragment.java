package com.ensias.assistancemedicale.FragmentPatient;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

import Task.AsyncGetTask;

public class MyRendezVousFragment extends Fragment {

    Context mContext;
    TextView nomDoctor;
    TextView emailDoctor;
    TextView adresseDoctor;
    TextView phoneDoctor;
    TextView rendezVousDoctor;
    TextView sepecialiteDoctor;

    DatePicker datePicker;
    TextView displayDate;
    Button changeDate;
    int month;

    public MyRendezVousFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View res = inflater.inflate(R.layout.fragment_my_rdv, null);
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


        SharedPreferences sharedPreferences = res.getContext().getSharedPreferences("Patient", res.getContext().MODE_PRIVATE);

        datePicker = (DatePicker) res.findViewById(R.id.datePicker);
        displayDate =(TextView) res.findViewById(R.id.dateRDVc);

        try {

           // Toast.makeText(res.getContext(), "Welcome Mr "+sharedPreferences.getInt("idPatient", 0), Toast.LENGTH_LONG).show();
            String result = new AsyncGetTask(res.getContext()).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindRendezVousId&idPatient="+ sharedPreferences.getInt("idPatient", 0)).get();
            JSONArray jArray = new JSONArray(result);
            JSONObject json_data = jArray.getJSONObject(0);
          //  Toast.makeText(res.getContext(), "Welcome Mr "+json_data.getString("date"), Toast.LENGTH_LONG).show();
            displayDate.setText(json_data.getString("date"));
            Calendar cal = Calendar.getInstance();

            Integer i=Integer.parseInt(json_data.getString("date").substring(0,4));
            Integer j=Integer.parseInt(json_data.getString("date").substring(5,7));
            Integer k=Integer.parseInt(json_data.getString("date").substring(8,10));

            cal.set(i,j,k);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            datePicker.updateDate(year,month-1,day);





        }catch (Exception e){

        }


        return res;
    }

    public String currentDate() {
        StringBuilder mcurrentDate = new StringBuilder();
        month = datePicker.getMonth() + 1;
        mcurrentDate.append("Date: " + month + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
        return mcurrentDate.toString();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}