package com.example.tooltracker;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class PantallaPrincipal extends AppCompatActivity {
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

        // Establecer la Toolbar como ActionBar
        setSupportActionBar(toolbar);

        // Crear el ActionBarDrawerToggle y vincularlo con el DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        // Agregar el ActionBarDrawerToggle como listener al DrawerLayout
        drawerLayout.addDrawerListener(toggle);

        // Sincronizar el estado del ActionBarDrawerToggle con el DrawerLayout
        toggle.syncState();
    }
}
