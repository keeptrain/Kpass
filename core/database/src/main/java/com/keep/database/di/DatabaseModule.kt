package com.keep.database.di

import android.content.Context
import androidx.room.Room
import com.keep.database.KpsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesKpsDatabase(
        @ApplicationContext context: Context
    ): KpsDatabase = Room.databaseBuilder(
        context,
        KpsDatabase::class.java,
        "kps-database"
    ).build()
}