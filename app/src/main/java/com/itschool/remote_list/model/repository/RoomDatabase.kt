package com.itschool.remote_list.model.repository

import androidx.room.*

@Entity(tableName = "ShoppingItemsDB")
data class ShopItem(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val currentCount: Int,
        val maxCount: Int,
        val name: String,
        val description: String
        )

@Dao
interface ShoppingItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShopItem(shopItem: ShopItem)

    @Update
    fun updateShopItem(shopItem:ShopItem)

    @Delete
    fun deleteShopItem(shopItem: ShopItem)

    @Query("SELECT * FROM ShoppingItemsDB WHERE id == :id")
    fun getShopItemById(id:Int):List<ShopItem>

    @Query("SELECT * FROM ShoppingItemsDB")
    fun getAllShoppingItems():List<ShopItem>

    @Query("SELECT COUNT(id) FROM ShoppingItemsDB")
    fun getShoppingItemsCount():Int
}

@Database(entities = [ShopItem::class], version = 1)
abstract class ShoppingItemsDB: RoomDatabase(){

    abstract fun shopItemsDao():ShoppingItemsDao

}