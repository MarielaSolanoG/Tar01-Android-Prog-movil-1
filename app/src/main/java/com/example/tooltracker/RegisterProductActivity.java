package com.example.tooltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tooltracker.R;

public class RegisterProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
    }
    public void onAddProductButtonClick(View view) {
        // Lógica para agregar un producto
        Toast.makeText(this, "Agregando producto...", Toast.LENGTH_SHORT).show();
    }

    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}
