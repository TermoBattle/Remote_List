package com.itschool.remote_list.model

import com.itschool.remote_list.model.repository.ShopItem
import com.itschool.remote_list.model.repository.ShoppingItemsDao
import javax.inject.Inject

class Model @Inject constructor(private val shoppingItemsDao: ShoppingItemsDao){
    val itemsCount:Int
        get() = shoppingItemsDao.getShoppingItemsCount()
    val items:List<ShopItem>
        get() = shoppingItemsDao.getAllShoppingItems()

    fun findItem(id:Int): List<ShopItem> = shoppingItemsDao.getShopItemById(id = id)
    fun delete(item: ShopItem) = shoppingItemsDao.deleteShopItem(item)
    fun update(item: ShopItem) = shoppingItemsDao.updateShopItem(item)
    fun create(item: ShopItem) = shoppingItemsDao.insertShopItem(item)

}