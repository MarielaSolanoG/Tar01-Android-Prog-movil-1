
package com.example.tooltracker;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteProductActivity extends Activity {

    private EditText editTextProductId;
    private EditText editTextProductName;
    private ProductManager productManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        editTextProductId = findViewById(R.id.editTextProductId);
        editTextProductName = findViewById(R.id.editTextProductName);
        productManager = MyApplication.getProductManager();
    }

    public void deleteProductById(View view) {
        String productIdText = editTextProductId.getText().toString();

        // Verificar si el ID del producto es válido
        if (productIdText.isEmpty()) {
            // Mostrar un mensaje de error si el campo de ID del producto está vacío
            Toast.makeText(this, "Ingrese el ID del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        int productId = Integer.parseInt(productIdText);
        // Verificar si el ID del producto ya existe en la lista de productos
        List<Product> productList = productManager.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == productId){
                productList.remove(i);
                Toast.makeText(this, "Se elimino el producto", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(this, "No se encontró producto con ese nombre", Toast.LENGTH_SHORT).show();
    }
    public void deleteProductByName(View view) {
        String nameDeleteText = editTextProductName.getText().toString();

        // Verificar si el ID del producto es válido
        if (nameDeleteText.isEmpty()) {
            // Mostrar un mensaje de error si el campo de ID del producto está vacío
            Toast.makeText(this, "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el ID del producto ya existe en la lista de productos
        List<Product> productList = productManager.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(nameDeleteText)){
                productList.remove(i);
                Toast.makeText(this, "Se elimino el producto", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(this, "No se encontró producto con ese nombre", Toast.LENGTH_SHORT).show();
    }


    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}