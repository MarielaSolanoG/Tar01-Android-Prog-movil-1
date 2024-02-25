package com.example.tooltracker

class ProductManager {
    private val productList = mutableListOf<Product>()

    // Método para agregar un producto a la lista
    fun addProduct(product: Product) {
        productList.add(product)
    }

    // Método para obtener la lista de productos
    fun getProductList(): List<Product> {
        return productList
    }
}
