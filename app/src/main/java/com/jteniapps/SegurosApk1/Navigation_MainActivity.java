package com.jteniapps.SegurosApk1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import static android.content.Intent.ACTION_CALL;

/*import com.Fragments.FragmentMenuPrincipal;*/

public class Navigation_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton btnVehiculos;
    ImageButton btnHogar;
    String bb = "122";
    Uri callb = Uri.parse("tel:" + bb);
    String cr = "2381 6565";
    Uri callc = Uri.parse("tel:" + cr);
    String p = "110";
    Uri callp = Uri.parse("tel:" + p);
    String f = "5555-0000";
    Uri callf = Uri.parse("tel:" + f);
    String CallC = "00112233";
    Uri callcenter1 = Uri.parse("tel:" + CallC);
    String CallC2 = "11223344";
    Uri callcenter2 = Uri.parse("tel:" + CallC2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__main);

        btnVehiculos = findViewById(R.id.id_btn_menu_vehiculos);
        btnHogar = findViewById(R.id.id_btn_menu_hogar);

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
        navigationView.setItemIconTintList(null);//i

        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubMenuVehiculos.class);
                startActivity(intent);
            }
        });

        btnHogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubMenuHogar.class);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.navigation__main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent bb = new Intent(Intent.ACTION_CALL, callb);
            startActivity(bb);
        } else if (id == R.id.nav_gallery) {
            Intent cr = new Intent(Intent.ACTION_CALL, callc);
            startActivity(cr);
        } else if (id == R.id.nav_slideshow) {
            Intent p = new Intent(Intent.ACTION_CALL, callp);
            startActivity(p);
        } else if (id == R.id.nav_manage) {
            Intent fm = new Intent(Intent.ACTION_CALL, callf);
            startActivity(fm);
        } else if (id == R.id.nav_share) {
            Intent cc2 = new Intent(Intent.ACTION_CALL, callcenter1);
            startActivity(cc2);
        } else if (id == R.id.nav_send) {
            Intent cc = new Intent(Intent.ACTION_CALL, callcenter2);
            startActivity(cc);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
