package com.ensias.assistancemedicale.FragmentMedecin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.NewPrescription;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import Task.AsyncGetTask;

public class GestionOrdonnancesFragment extends Fragment {



    SharedPreferences sharedPreferences;
    EditText userNamepatient;
    Button Add;
    Context mContext;
    public static int id_ord;

    public GestionOrdonnancesFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_ajout_ordonnances, null);
        Log.d("TTG","onCreateView Called");

        userNamepatient = (EditText) result.findViewById(R.id.ordonnance_patient_username);
        Add = (Button) result.findViewById(R.id.ordonnance_patient_username_button);
        sharedPreferences = mContext.getSharedPreferences("Medecin", Context.MODE_PRIVATE);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String result_patient = new AsyncGetTask(mContext).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindPatientByMedecin&username=" + userNamepatient.getText().toString()+"&id_medecin="+sharedPreferences.getInt("idMedecin",0)).get();
                    JSONArray jsonArrayPatient = new JSONArray(result_patient);

                    if(jsonArrayPatient.length()==0)
                    {
                        Toast.makeText(mContext, "patient n'existe pas", Toast.LENGTH_LONG).show();
                    }
                    else{

                        JSONObject json_patient = jsonArrayPatient.getJSONObject(0);
                        String result1 = new AsyncGetTask(mContext).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=CreateOrdonnance&id_patient=" + json_patient.getInt("id") +"&id_medecin="+sharedPreferences.getInt("idMedecin",0)).get();
                        JSONArray jArray1 = new JSONArray(result1);

                        if (jArray1.length() == 0) {

                            Toast.makeText(mContext, "Ordonnnaces non crée", Toast.LENGTH_LONG).show();

                        } else {

                            JSONObject json_data = jArray1.getJSONObject(0);
                            id_ord = json_data.getInt("id");
                            Toast.makeText(mContext, ""+id_ord, Toast.LENGTH_LONG).show();

                            Log.d("TTG","Listner Called");

                            Intent newPrescription = new Intent(mContext,NewPrescription.class);
                            startActivity(newPrescription);

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }});

        return result;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Gestion ordonnances", Toast.LENGTH_LONG);


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