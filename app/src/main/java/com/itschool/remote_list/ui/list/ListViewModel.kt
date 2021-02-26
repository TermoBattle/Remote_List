package com.itschool.remote_list.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.itschool.remote_list.model.Model
import com.itschool.remote_list.model.repository.ShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val model: Model
    ): ViewModel(){
    val maxCount = mutableStateOf<Int>(0)
    val name = mutableStateOf<String>("")
    val description = mutableStateOf<String>("")

    fun findShopItem(id: Int): ShopItem = model.findItem(id = id).first()
    fun delete(shopItem: ShopItem) = model.delete(item = shopItem)
    fun create() {

        if (maxCount.value > 0) {
            model.create(
                item = ShopItem(
                    id = 0,
                    currentCount = 0,
                    maxCount = maxCount.value,
                    name = name.value,
                    description = description.value
                )
            )
        }
    }

    fun increaseCount(shopItem: ShopItem) {
        if (shopItem.currentCount + 1 <= shopItem.maxCount)
            model.update(shopItem.copy(currentCount = shopItem.currentCount + 1))
    }

    val shoppingItems:List<ShopItem> get() = model.items
}