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
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.Patient;
import Task.AsyncGetTask;

public class MyDoctorFragment extends Fragment {

    Context mContext;
    TextView nomDoctor;
    TextView emailDoctor;
    TextView adresseDoctor;
    TextView phoneDoctor;
    TextView rendezVousDoctor;
    TextView sepecialiteDoctor;


    public MyDoctorFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View res = inflater.inflate(R.layout.fragment_my_doctor, null);

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
                Toast.makeText(res.getContext(), "Welcome Mr "+ json_data2.getString("date") , Toast.LENGTH_LONG).show();


        }catch (Exception e){

        }

        return res;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Detecter objet", Toast.LENGTH_LONG);
    }
}
