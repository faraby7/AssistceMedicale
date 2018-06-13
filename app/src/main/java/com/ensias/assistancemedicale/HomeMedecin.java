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
import com.ensias.assistancemedicale.FragmentMedecin.GestionMedicamentFragment;
import com.ensias.assistancemedicale.FragmentMedecin.GestionOrdonnancesFragment;
import com.ensias.assistancemedicale.FragmentMedecin.GestionPatientFragment;
import com.ensias.assistancemedicale.FragmentMedecin.RendezVousFragment;


public class HomeMedecin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView nomDoctor;
    TextView emailDoctor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_medecin);
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




        //Fragment mfragment=null;
        //int id = item.getItemId();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DashboardMedecinFragment mfragment = new DashboardMedecinFragment();
        mfragment.setmContext(this);
        fragmentTransaction.replace(R.id.screen_area2,mfragment);

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

        try {


            sharedPreferences= getSharedPreferences("Medecin", Context.MODE_PRIVATE);

            emailDoctor = (TextView)findViewById(R.id.emailDoctor);
            nomDoctor = (TextView)findViewById(R.id.nomDoctor);
            nomDoctor.setText(sharedPreferences.getString("nomMedecin",null)+sharedPreferences.getString(
                    "prenomMedecin",null));
            emailDoctor.setText(sharedPreferences.getString("usernameMedecin", null));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_medecin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            sharedPreferences = getSharedPreferences("Medecin", Context.MODE_PRIVATE);
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
        // Handle navigation view item clicks here.

        Fragment fragment=null;
        int id = item.getItemId();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.Patients) {

            GestionPatientFragment mfragment = new GestionPatientFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area2,mfragment);

         }else if(id == R.id.medicament){

            GestionMedicamentFragment mfragment = new GestionMedicamentFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area2,mfragment);


        }else if(id == R.id.RendezVous){

            RendezVousFragment mfragment = new RendezVousFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area2,mfragment);

        }else if(id == R.id.Detecter){

            Intent H = new Intent(this, RendezVousPatient.class);
            startActivity(H);

        }
        else if(id == R.id.ordonnances_menu){
            GestionOrdonnancesFragment mfragment = new GestionOrdonnancesFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area2,mfragment);
        }
/*
        /*else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/




        if(fragment!= null){

            DashboardMedecinFragment mfragment = new DashboardMedecinFragment();
            mfragment.setmContext(this);
            fragmentTransaction.replace(R.id.screen_area2,mfragment);
        }

        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
