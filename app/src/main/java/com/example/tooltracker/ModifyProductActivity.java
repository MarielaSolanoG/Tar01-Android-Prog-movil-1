
package com.example.tooltracker;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModifyProductActivity extends Activity {

    private RadioGroup radioGroup;
    private RadioGroup radioGroupChange;
    private EditText editTextSearchId;
    private EditText editTextSearchName;
    private EditText editTextChangeProductCost;
    private EditText editTextChangeProductSale;
    private EditText editTextChangeProductQuantity;
    private EditText editTextChangeProductQuantitySold;
    private ProductManager productManager;
    private ArrayList<Product> allProducts;
    public int idProductoEncontrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);
        editTextSearchId = findViewById(R.id.editTextProductId);
        editTextSearchName = findViewById(R.id.editTextProductName);
        editTextChangeProductCost = findViewById(R.id.editTextCostPrice);
        editTextChangeProductSale = findViewById(R.id.editTextSalePrice);
        editTextChangeProductQuantity = findViewById(R.id.editTextQuantity);
        editTextChangeProductQuantitySold = findViewById(R.id.editTextQuantitySold);

        productManager = MyApplication.getProductManager();

        editTextSearchId.setVisibility(View.GONE);
        editTextSearchName.setVisibility(View.GONE);
        editTextChangeProductCost.setVisibility(View.GONE);
        editTextChangeProductSale.setVisibility(View.GONE);
        editTextChangeProductQuantity.setVisibility(View.GONE);
        editTextChangeProductQuantitySold.setVisibility(View.GONE);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroupChange = findViewById(R.id.radioGroupCambiar);

        allProducts = new ArrayList<>(productManager.getProductList());
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String selectedOption = radioButton.getText().toString();
            // Verificar si se seleccionó "Todos los productos"
            if (selectedOption.equals("Buscar por nombre")) {
                editTextSearchName.setVisibility(View.VISIBLE);
                editTextSearchId.setVisibility(View.GONE);
            } else {
                editTextSearchId.setVisibility(View.VISIBLE);
                editTextSearchName.setVisibility(View.GONE);
            }
        });

        radioGroupChange.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String selectedOptionChange = radioButton.getText().toString();
            editTextChangeProductCost.setVisibility(View.GONE);
            editTextChangeProductSale.setVisibility(View.GONE);
            editTextChangeProductQuantity.setVisibility(View.GONE);
            editTextChangeProductQuantitySold.setVisibility(View.GONE);
            // Verificar si se seleccionó "Todos los productos"
            if (selectedOptionChange.equals("Precio costo")) {
                editTextChangeProductCost.setVisibility(View.VISIBLE);
            } else if (selectedOptionChange.equals("Precio venta")){
                editTextChangeProductSale.setVisibility(View.VISIBLE);
            }else if (selectedOptionChange.equals("Cantidad")){
                editTextChangeProductQuantity.setVisibility(View.VISIBLE);
            }else{
                editTextChangeProductQuantitySold.setVisibility(View.VISIBLE);
            }
        });
    }
    public void onSearch(View view){
        String selectedOption = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        String idText = editTextSearchId.getText().toString();
        String nameText = editTextSearchName.getText().toString();

        boolean productoEncontrado = false;

        if (selectedOption.equals("Buscar por nombre")) {
            // Verificar si el nombre del producto es válido
            if (nameText.isEmpty()) {
                // Mostrar un mensaje de error si el campo de nombre del producto está vacío
                Toast.makeText(this, "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Product product : allProducts) {
                if (product.getName().equals(nameText)){
                    idProductoEncontrado = product.getProductId();
                    productoEncontrado = true;
                    System.out.println(idProductoEncontrado);
                    Toast.makeText(this, "Búsqueda exitosa", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if (!productoEncontrado) {
                Toast.makeText(this, "Búsqueda fallida: el nombre no existe o no es correcto", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (idText.isEmpty()) {
                // Mostrar un mensaje de error si el campo de ID del producto está vacío
                Toast.makeText(this, "Ingrese el ID del producto", Toast.LENGTH_SHORT).show();
                return;
            }
            int idProduct = Integer.parseInt(idText);
            for (Product product : allProducts) {
                if (product.getProductId() == idProduct){
                    idProductoEncontrado = product.getProductId();
                    productoEncontrado = true;
                    System.out.println(idProductoEncontrado);
                    Toast.makeText(this, "Búsqueda exitosa", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if (!productoEncontrado) {
                Toast.makeText(this, "Búsqueda fallida: el ID no existe o no es correcto", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onSaveChanges(View view){
        String selectedOptionChange = ((RadioButton) findViewById(radioGroupChange.getCheckedRadioButtonId())).getText().toString();
        System.out.println("AQUI SE SUPONE ES EL SEGUNDO PRINT ID ");
        System.out.println(idProductoEncontrado);
        editTextChangeProductCost = findViewById(R.id.editTextCostPrice);
        editTextChangeProductSale = findViewById(R.id.editTextSalePrice);
        editTextChangeProductQuantity = findViewById(R.id.editTextQuantity);
        editTextChangeProductQuantitySold = findViewById(R.id.editTextQuantitySold);
        Product productoEncontrado = allProducts.get(idProductoEncontrado);

        switch (selectedOptionChange) {
            case "Precio costo":
                if (editTextChangeProductCost.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese el nuevo precio costo del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                float priceCost = Float.parseFloat(editTextChangeProductCost.getText().toString());
                productoEncontrado.setPriceCost(priceCost);
                Toast.makeText(this, "se cambió el precio costo", Toast.LENGTH_SHORT).show();
                break;
            case "Precio venta":
                if (editTextChangeProductSale.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese el nuevo precio venta del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                float priceSale = Float.parseFloat(editTextChangeProductSale.getText().toString());
                productoEncontrado.setPriceSale(priceSale);
                Toast.makeText(this, "se cambió el precio venta", Toast.LENGTH_SHORT).show();
                break;

            case "Cantidad":
                if (editTextChangeProductQuantity.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese la nueva cantidad del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                int quantity = Integer.parseInt(editTextChangeProductQuantity.getText().toString());
                productoEncontrado.setQuantity(quantity);
                Toast.makeText(this, "se cambió la cantidad", Toast.LENGTH_SHORT).show();
                break;
            case "Cantidad vendida":
                if (editTextChangeProductQuantitySold.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese la nueva cantidad del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                int quantitySold = Integer.parseInt(editTextChangeProductQuantitySold.getText().toString());
                productoEncontrado.setQuantitySold(quantitySold);
                Toast.makeText(this, "se cambió la cantidad", Toast.LENGTH_SHORT).show();
                break;
            }

    }
    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}