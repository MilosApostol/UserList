package com.example.list.di

import android.content.Context
import androidx.room.Room
import com.example.list.data.AppDatabase
import com.example.list.data.ListDao
import com.example.list.data.ListRepository
import com.example.list.use_case.AddList
import com.example.list.use_case.DeleteList
import com.example.list.use_case.GetList
import com.example.list.use_case.GetLists
import com.example.list.use_case.ListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/*
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
    @Singleton
    @Provides
    fun providesListDao(database: AppDatabase) = database.listDao()


    @Singleton
    @Provides
    fun providesRepository(dao: ListDao) = ListRepository(dao)


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: ListRepository): ListUseCase {
        return ListUseCase(
            getLists = GetLists(repository),
            deleteList = DeleteList(repository),
            addList = AddList(repository),
            getList = GetList(repository)
        )
    }
}


 */

