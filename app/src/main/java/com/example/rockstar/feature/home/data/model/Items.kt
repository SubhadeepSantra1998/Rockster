package com.example.rockstar.feature.home.data.model



data class Product(
    val category: String,
    val item: Item
)

data class Item(
    val name: String,
    val imageUrl: String,
    val price: Double,
)

val itemList = listOf(
    Product(
        category = "Mobile",
        item = Item(
            name = "Apple 14 Pro",
            imageUrl = "https://cdn.pixabay.com/photo/2023/03/05/21/34/phone-7832257_1280.jpg",
            price = 80000.00,
        ),
    ),
    Product(
        category = "Mobile",
        item = Item(
            name = "Samsung Galaxy",
            imageUrl = "https://cdn.pixabay.com/photo/2016/11/10/16/03/android-1814556_1280.jpg",
            price = 80000.00,
        ),
    ),
    Product(
        category = "Laptop",
        item = Item(
                name = "Lenovo Yoga",
                imageUrl = "https://cdn.pixabay.com/photo/2016/04/17/17/43/lenovo-thinkpad-x61-tablet-1335138_640.jpg",
                price = 100000.00,
        ),
    ),
    Product(
        category = "Mobile",
        item = Item(
            name = "Xiomi 14 Pro",
            imageUrl = "https://cdn.pixabay.com/photo/2020/11/05/10/39/smartphone-5714763_1280.jpg",
            price = 80000.00,
        ),
    ),
)
