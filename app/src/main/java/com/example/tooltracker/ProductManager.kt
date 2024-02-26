package com.example.tooltracker

class ProductManager {
    private val productList = mutableListOf<Product>()
    init {
        // Cargar la lista de productos de ejemplo en el bloque de inicialización
        productList.add(Product(1, "Martillo", 100, 10F, 50F,20))
        productList.add(Product(2, "Clavo", 200, 20F, 60F,40))
        // Agregar más productos de ejemplo según sea necesario
    }
    // Método para agregar un producto a la lista
    fun addProduct(product: Product) {
        productList.add(product)
    }

    // Método para obtener la lista de productos
    fun getProductList(): List<Product> {
        return productList
    }
}
