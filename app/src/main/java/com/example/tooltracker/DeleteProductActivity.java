
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

    private EditText editTextSearch;
    private ProductManager productManager;
    private ArrayList<Product> allProducts;
    private List<Product> filteredProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        editTextSearch = findViewById(R.id.editTextProductId);
        productManager = MyApplication.getProductManager();

        allProducts = new ArrayList<>(productManager.getProductList());
        filteredProducts = new ArrayList<>();

    }

    public void deleteProduct(View view) {
        String idDeleteText = editTextSearch.getText().toString();
        String productIdText = idDeleteText.toString();

        // Verificar si el ID del producto es válido
        if (productIdText.isEmpty()) {
            // Mostrar un mensaje de error si el campo de ID del producto está vacío
            Toast.makeText(this, "Ingrese el ID del producto", Toast.LENGTH_SHORT).show();
            return;
        }

        int productId = Integer.parseInt(productIdText);
        // Verificar si el ID del producto ya existe en la lista de productos
        ProductManager productManager = MyApplication.getProductManager();
        List<Product> productList = productManager.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == Integer.parseInt(productIdText)){
                productList.remove(i);
                Toast.makeText(this, "Se elimino el producto", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}