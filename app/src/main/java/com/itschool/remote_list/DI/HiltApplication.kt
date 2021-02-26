package com.itschool.remote_list.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.itschool.remote_list.model.repository.ShoppingItemsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class HiltApplication: Application()

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule{

    @Provides
    @Singleton
    fun provideShoppingItemsDB(
        @ApplicationContext context:Context
    ) = Room
        .databaseBuilder(
            context.applicationContext,
            ShoppingItemsDB::class.java,
            "ShoppingItemsDB"
        )
        .build()

    @Provides
    fun provideShoppingItemsDao(
        shoppingItemsDB: ShoppingItemsDB
    ) = shoppingItemsDB
        .shopItemsDao()

}