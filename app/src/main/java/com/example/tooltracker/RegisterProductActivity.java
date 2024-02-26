package com.example.tooltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;
import java.util.List;

public class RegisterProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
    }
    public void onAddProductButtonClick(View view) {
        // Obtener referencias a los campos de texto
        EditText idEditText = findViewById(R.id.editTextProductId);
        EditText nameEditText = findViewById(R.id.editTextProductName);
        EditText priceCostEditText = findViewById(R.id.editTextProductPriceCost);
        EditText priceSaleEditText = findViewById(R.id.editTextProductPriceSale);
        EditText quantityEditText = findViewById(R.id.editTextProductQuantity);
        EditText quantitySoldEditText = findViewById(R.id.editTextProductQuantitySold);

        // Obtener texto ingresado por el usuario
        String productIdText = idEditText.getText().toString();
        String name = nameEditText.getText().toString();
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        int quantitySold = Integer.parseInt(quantitySoldEditText.getText().toString());
        float priceCost = Float.parseFloat(priceCostEditText.getText().toString());
        float priceSale = Float.parseFloat(priceSaleEditText.getText().toString());

        // Verificar si el ID del producto es válido
        if (productIdText.isEmpty()) {
            // Mostrar un mensaje de error si el campo de ID del producto está vacío
            Toast.makeText(this, "Ingrese el ID del producto", Toast.LENGTH_SHORT).show();
            return;
        }
        // Convertir el ID del producto a un número entero
        int productId = Integer.parseInt(productIdText);
        // Crear un nuevo producto
        Product newProduct = new Product(productId, name, quantity, priceCost, priceSale, quantitySold);
        System.out.println(newProduct);
        ProductManager productManager = MyApplication.getProductManager();
        productManager.addProduct(newProduct);
        List<Product> productList = productManager.getProductList();
        for (Product product : productList) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Quantity: " + product.getQuantity());
        }
        Toast.makeText(this, "Producto agregado...", Toast.LENGTH_SHORT).show();

        idEditText.setText("");
        nameEditText.setText("");
        priceCostEditText.setText("");
        priceSaleEditText.setText("");
        quantityEditText.setText("");
        quantitySoldEditText.setText("");

    }

    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}
