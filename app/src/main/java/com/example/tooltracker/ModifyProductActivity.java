
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
        productManager = MyApplication.getProductManager();

        editTextSearchId.setVisibility(View.GONE);
        editTextSearchName.setVisibility(View.GONE);
        editTextChangeProductCost.setVisibility(View.GONE);
        editTextChangeProductSale.setVisibility(View.GONE);
        editTextChangeProductQuantity.setVisibility(View.GONE);

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
            // Verificar si se seleccionó "Todos los productos"
            if (selectedOptionChange.equals("Precio costo")) {
                editTextChangeProductCost.setVisibility(View.VISIBLE);
            } else if (selectedOptionChange.equals("Precio venta")){
                editTextChangeProductSale.setVisibility(View.VISIBLE);
            }else{
                editTextChangeProductQuantity.setVisibility(View.VISIBLE);
            }
        });
    }
    public void onSearch(View view){
        String selectedOption = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        String idDeleteText = editTextSearchId.getText().toString();
        String nameDeleteText = editTextSearchName.getText().toString();


        if (selectedOption.equals("Buscar por nombre")) {
            // Verificar si el ID del producto es válido
            if (nameDeleteText.isEmpty()) {
                // Mostrar un mensaje de error si el campo de ID del producto está vacío
                Toast.makeText(this, "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Product product : allProducts) {
                if (product.getName().equals(nameDeleteText)){
                    idProductoEncontrado = product.getProductId();
                    System.out.println(idProductoEncontrado);
                    Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Toast.makeText(this, "Busqueda fallida el nombre no existe o no es correcto", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            if (idDeleteText.isEmpty()) {
                // Mostrar un mensaje de error si el campo de ID del producto está vacío
                Toast.makeText(this, "Ingrese el ID del producto", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Product product : allProducts) {
                if (product.getProductId() == Integer.parseInt(idDeleteText)){
                    idProductoEncontrado = product.getProductId();
                    System.out.println(idProductoEncontrado);
                    Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Toast.makeText(this, "Busqueda fallida el id no existe o no es correcto", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void onSaveChanges(View view){
        String selectedOptionChange = ((RadioButton) findViewById(radioGroupChange.getCheckedRadioButtonId())).getText().toString();
        EditText costPriceEditText = findViewById(R.id.editTextCostPrice);
        EditText salePriceEditText = findViewById(R.id.editTextSalePrice);
        EditText quantityEditText = findViewById(R.id.editTextQuantity);
        System.out.println("AQUI SE SUPONE ES EL SEGUNDO PRINT ID ");
        System.out.println(idProductoEncontrado);
        Product productoEncontrado = allProducts.get(idProductoEncontrado);

        switch (selectedOptionChange) {
            case "Precio costo":
                if (costPriceEditText.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese el nuevo precio costo del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                float priceCost = Float.parseFloat(costPriceEditText.getText().toString());
                productoEncontrado.setPriceCost(priceCost);
                Toast.makeText(this, "se cambió el precio costo", Toast.LENGTH_SHORT).show();
                break;
            case "Precio venta":
                if (salePriceEditText.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese el nuevo precio venta del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                float priceSale = Float.parseFloat(salePriceEditText.getText().toString());
                productoEncontrado.setPriceSale(priceSale);
                Toast.makeText(this, "se cambió el precio venta", Toast.LENGTH_SHORT).show();
                break;

            case "Cantidad":
                if (quantityEditText.getText().toString().isEmpty()) {
                    // Mostrar un mensaje de error si el campo de ID del producto está vacío
                    Toast.makeText(this, "Ingrese la nueva cantidad del producto", Toast.LENGTH_SHORT).show();
                    return;
                }
                int quantity = Integer.parseInt(quantityEditText.getText().toString());
                productoEncontrado.setQuantity(quantity);
                Toast.makeText(this, "se cambió la cantidad", Toast.LENGTH_SHORT).show();
                break;
            }

    }
    public void onExitButtonClick(View view) {
        // Lógica para salir
        finish(); // Cierra la actividad y vuelve a la actividad anterior
    }
}