package com.ensias.assistancemedicale.FragmentMedecin;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.assistancemedicale.Adapters.PatientsAdapter;
import com.ensias.assistancemedicale.HomeMedecin;
import com.ensias.assistancemedicale.HomePatient;
import com.ensias.assistancemedicale.MainActivity;
import com.ensias.assistancemedicale.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.Medecin;
import Model.Patient;
import Task.AsyncGetTask;
import Task.AsyncInscTask;

public class GestionPatientFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Patient> patients;
    private List<Drawable> images;
    SharedPreferences sharedPreferences;
    EditText userNamepatient;
    EditText idMedecin;
    EditText PatientName;
    Button Add;
    Context mContext;

    public GestionPatientFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_gestion_patient, null);
        Log.d("TTG","onCreateView Called");

        Add = (Button) result.findViewById(R.id.AjouterPatient);
        PatientName = (EditText) result.findViewById(R.id.PatientAdd);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                String result1 = new AsyncGetTask(mContext).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=FindPatient&username=" + PatientName.getText().toString()).get();
                JSONArray jArray1 = new JSONArray(result1);

                if (jArray1.length() == 0) {

                    Toast.makeText(mContext, "Patient Not Found", Toast.LENGTH_LONG).show();
                    Toast.makeText(mContext, "Try again", Toast.LENGTH_LONG).show();

                } else {

                        JSONObject json_data = jArray1.getJSONObject(0);

                        if(json_data.getInt("id_medecin")!=0){

                            Toast.makeText(mContext, "Patient Have Doctor", Toast.LENGTH_LONG).show();

                        }else {


                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("Medecin", Context.MODE_PRIVATE);

                            String url1 = "idmedecin=" + sharedPreferences.getInt("idMedecin",0)+"&id="+json_data.getInt("id");
                            new AsyncInscTask(mContext,url1).execute(MainActivity.IP + "/AssistanceMedicale/web_services.php?action=AddPatient");
                            Toast.makeText(mContext,"Add Done"+sharedPreferences.getInt("idMedecin",0)+"f"+json_data.getInt("id"),Toast.LENGTH_LONG).show();

                            Log.d("TTG","Listner Called");
                            Intent AjouterPatient = new Intent(mContext, HomeMedecin.class);
                            startActivity(AjouterPatient);

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
        Toast.makeText(getContext(), "Gestion de Patient", Toast.LENGTH_LONG);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_patient);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        patients = new ArrayList<>();
        images = new ArrayList<>();


        loadRecyclerViewData();


    }


    private void loadRecyclerViewData() {


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
    }

}


