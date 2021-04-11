package com.example.composediet

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "adfmp1h21-diet-db.sdb"
    ).build()

    @Singleton
    @Provides
    fun provideFoodItemDao(db: AppDatabase) = db.foodItemDao()

    @Singleton
    @Provides
    fun provideDishDao(db: AppDatabase) = db.dishDao()
}