package com.keep.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideKpsDatabase(context: Context): KpsDatabase {
        return Room.databaseBuilder(
            context,
            KpsDatabase::class.java,
            "kps-database").build()
    }
}