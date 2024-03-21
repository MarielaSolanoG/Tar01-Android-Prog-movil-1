package com.example.tooltracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.example.tooltracker.R;

public class PantallaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla);

        // Referenciar los elementos del layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Manejar el clic en el elemento del menú
            if (item.getItemId() == R.id.nav_home) {
                Intent intent = new Intent(this, PantallaPrincipal.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_register) {
                Intent intent = new Intent(this, RegisterProductActivity.class);
                startActivity(intent);

            } else if (item.getItemId() == R.id.nav_consult) {
                Intent intent = new Intent(this, ConsultProductActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_delete) {
                Intent intent = new Intent(this, DeleteProductActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_modify) {
                Intent intent = new Intent(this, ModifyProductActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.nav_map) {
                //Intent intent = new Intent(this, GoogleMapActivity.class);
                //startActivity(intent);
    }

        // Cerrar el DrawerLayout después de manejar el clic en el elemento del menú
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

