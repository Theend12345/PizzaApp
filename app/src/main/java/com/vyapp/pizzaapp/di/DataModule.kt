package com.vyapp.pizzaapp.di

import android.content.Context
import androidx.room.Room
import com.vyapp.pizzaapp.data.api.BASE_URL
import com.vyapp.pizzaapp.data.api.DishService
import com.vyapp.pizzaapp.data.db.DB_NAME
import com.vyapp.pizzaapp.data.db.DishDatabase
import com.vyapp.pizzaapp.data.repository.DishRepositoryImp
import com.vyapp.pizzaapp.domain.repository.DishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDishService(): DishService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DishService::class.java)

    @Provides
    @Singleton
    fun provideDishDataBase(@ApplicationContext context: Context): DishDatabase =
        Room.databaseBuilder(context, DishDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideDishRepository(
        dishService: DishService,
        dishDatabase: DishDatabase
    ): DishRepository = DishRepositoryImp(dishService, dishDatabase)
}