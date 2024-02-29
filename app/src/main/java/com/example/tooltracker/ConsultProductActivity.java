package com.example.tooltracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultProductActivity extends Activity {

    private RadioGroup radioGroup;
    private EditText editTextSearch;
    private ListView listViewResults;
    private ProductManager productManager;

    private ArrayList<Product> allProducts;

    private List<Product> filteredProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_product);

        radioGroup = findViewById(R.id.radioGroup);
        editTextSearch = findViewById(R.id.editTextSearch);
        listViewResults = findViewById(R.id.listViewResults);
        productManager = MyApplication.getProductManager();

        allProducts = new ArrayList<>(productManager.getProductList());
        filteredProducts = new ArrayList<>();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String selectedOption = radioButton.getText().toString();
            // Verificar si se seleccionó "Todos los productos"
            if (selectedOption.equals("Todos los productos")) {
                // Mostrar todos los productos sin filtrar
                displayAllProducts();
                // Ocultar el EditText
                editTextSearch.setVisibility(View.GONE);
            } else {
                // Mostrar el EditText
                editTextSearch.setVisibility(View.VISIBLE);
            }
        });
    }

    public void searchProductButtonClick(View view) {
        clearListView();
        String selectedOption = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        String quantityText = editTextSearch.getText().toString();

        if (selectedOption.equals("Todos los productos")) {
            // Mostrar todos los productos sin filtrar
            displayAllProducts();
            return;
        }

        if (quantityText.isEmpty()) {
            Toast.makeText(this, "Debes ingresar una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        filterProducts(selectedOption, quantity);

        if (filteredProducts.isEmpty()) {
            Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show();
            clearListView();
        } else {
            updateListView(filteredProducts);
        }
    }

    public void filterProducts(String selectedOption, int quantity) {


        for (Product product : allProducts) {
            switch (selectedOption) {
                case "Precio Costo":
                    if (product.getPriceCost() == quantity) {
                        filteredProducts.add(product);
                    }
                    break;
                case "Precio Venta":
                    if (product.getPriceSale() == quantity) {
                        filteredProducts.add(product);
                    }
                    break;
                case "Cantidad":
                    if (product.getQuantity() == quantity) {
                        filteredProducts.add(product);
                    }
                    break;
                case "Cantidad vendidos":
                    if (product.getQuantitySold() == quantity) {
                        filteredProducts.add(product);
                    }
                    break;
            }
        }
    }

    private void clearListView() {
        ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) listViewResults.getAdapter();
        if (adapter != null) {
            adapter.clear();
        }
        filteredProducts.clear(); // Limpiar la lista de productos filtrados
    }

    private void displayAllProducts() {
        updateListView(new ArrayList<>(productManager.getProductList()));
    }

    private void updateListView(List<Product> products) {
        ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) listViewResults.getAdapter();
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
            listViewResults.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(products);
        }
    }

    public void onExitButtonClick(View view) {
        finish();
    }
}
