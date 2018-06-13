package com.ensias.assistancemedicale;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ensias.assistancemedicale.FragmentMedecin.DashboardMedecinFragment;
import com.ensias.assistancemedicale.FragmentPatient.DashboardPatientFragment;
import com.ensias.assistancemedicale.FragmentPatient.DetecterFragment;
import com.ensias.assistancemedicale.FragmentPatient.MyDoctorFragment;
import com.ensias.assistancemedicale.FragmentPatient.MyOrdonnanceFragment;
import com.ensias.assistancemedicale.FragmentPatient.MyRendezVousFragment;

public class HomePatient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DashboardPatientFragment mfragment = new DashboardPatientFragment();
        mfragment.setmContext(this);
        fragmentTransaction.replace(R.id.screen_area,mfragment);

        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        try {


           SharedPreferences sharedPreferences= getSharedPreferences("Patient", Context.MODE_PRIVATE);

            TextView emailPatient = (TextView)findViewById(R.id.emailPatient);
            TextView nomPatient = (TextView)findViewById(R.id.nomPatient);
            nomPatient.setText(sharedPreferences.getString("nomPatient",null)+sharedPreferences.getString(
                    "prenomPatient",null));
            emailPatient.setText(sharedPreferences.getString("usernamePatient", null));

        } catch (Exception e) {
            e.printStackTrace();
        }

        getMenuInflater().inflate(R.menu.home_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            SharedPreferences sharedPreferences = getSharedPreferences("Patient", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            Intent authentification = new Intent(this, MainActivity.class);
            startActivity(authentification);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        Fragment fragment=null;
        int id = item.getItemId();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.Detecter) {
            DetecterFragment mfragment = new DetecterFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area,mfragment);

        } else if (id == R.id.my_doctor) {


            MyDoctorFragment mfragment = new MyDoctorFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area,mfragment);
        }

        else if (id == R.id.RendezVous) {
            MyRendezVousFragment mfragment = new MyRendezVousFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area,mfragment);



        } else if (id == R.id.Ordonnance) {

            MyOrdonnanceFragment mfragment= new MyOrdonnanceFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area,mfragment);

        }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        if(fragment!= null){

            DashboardPatientFragment mfragment = new DashboardPatientFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area,mfragment);
        }

        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
