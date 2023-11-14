package com.example.list.di

import android.content.Context
import androidx.room.Room
import com.example.list.data.AppDatabase
<<<<<<< HEAD
import com.example.list.data.ListDao
import com.example.list.data.ListRepository
=======
>>>>>>> origin/to-be-renewed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, AppDatabase.DATABASE
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesListDao(database: AppDatabase) = database.listDao()
<<<<<<< HEAD

    @Provides
    @Singleton
    fun provideRepository(dao: ListDao) = ListRepository(dao)
=======
>>>>>>> origin/to-be-renewed
}
