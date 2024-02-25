package com.example.tooltracker;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.tooltracker.R;

public class ConsultProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_product);
    }
    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}