package com.itschool.remote_list.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.itschool.remote_list.model.repository.ShopItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel,
) {
    var dialogExist: Boolean by remember { mutableStateOf(false) }

    //Функции для более читаемого кода
    fun hideDialog() {
        dialogExist = false
    }

    fun showDialog() {
        dialogExist = true
    }

    if (dialogExist)
        //Диалог создания покупки
        Dialog(onDismissRequest = ::hideDialog) {
            Text(text = "Новая покупка")
            PropertyField(
                name = "Название",
                property = viewModel.name,
            )
            PropertyField(
                name = "Количество",
                property = viewModel.maxCount,
            )
            PropertyField(
                name = "Описание",
                property = viewModel.description,
            )
            //Кнопка открытия диалога создания покупки
            OutlinedButton(
                onClick = viewModel::create,
                content = {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "")
                    Text(text = "Создать")
                }
            )
        }

    //Экран с названием и списком
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Список покупок")
        Divider(modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(
                items = viewModel.shoppingItems,
                itemContent = { shopItem: ShopItem ->
                    ShopItemComposable(
                        shopItem = shopItem,
                        onDelete = viewModel::delete,
                        onIncrease = viewModel::increaseCount
                    )
                }
            )
        }
        Divider(modifier = Modifier.padding(16.dp))

        //Кнопка добавления новой покупки
        ExtendedFloatingActionButton(
            text = { Text(text = "Добавить") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Создать новую покупку"
                )
            },
            onClick = ::showDialog
        )
    }
}

@Composable
private fun <T> PropertyField(
    name: String,
    property: MutableState<T>,
    convert: String.() -> T
) = OutlinedTextField(
    value = property.value.toString(),
    onValueChange = { value:String ->
        property.value = value.convert()
    },
    placeholder = { Text(text = name) },
)
@JvmName("MyPropertyTextFieldString")
@Composable
private fun PropertyField(name:String, property: MutableState<String>) = OutlinedTextField(
    value = property.value ?: "",
    onValueChange = { value:String ->
        property.value = value
    },
    placeholder = { Text(text = name) },
)
@JvmName("MyPropertyTextFieldInt")
@Composable
private fun PropertyField(name:String, property: MutableState<Int>) = OutlinedTextField(
    value = property.value.toString(),
    onValueChange = { value:String ->
        property.value = value.toInt()
    },
    placeholder = { Text(text = name) },
)




@Composable
private fun ShopItemComposable(
    shopItem: ShopItem,
    onDelete: (ShopItem) -> Unit,
    onIncrease:(ShopItem) -> Unit
) = Row {

    //Текстовые поля для отображения всей информации
    shopItem.apply {
        Text(text = "$currentCount/$maxCount")
        Divider(modifier = Modifier.padding(16.dp))
        Text(text = name)
        Divider(modifier = Modifier.padding(16.dp))
        Text(text = description)
    }

    //Кнопка увеличения количества купленного предмета
    IconButton(onClick = { onIncrease(shopItem) }) {
        Icon(
            imageVector = Icons.Default.PlusOne,
            contentDescription = ""
        )
    }

    //Кнопка удаления
    IconButton(onClick = { onDelete(shopItem) }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = ""
        )
    }
}