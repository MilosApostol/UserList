package com.example.list.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.list.data.AppDatabase
import com.example.list.data.ListDao
import com.example.list.data.ListRepository
import com.example.list.data.ListViewModel
import com.example.list.usecase.DeleteList
import com.example.list.usecase.GetListsUseCase
import com.example.list.usecase.ListUsecase
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

    @Provides
    fun providesListModel (listDao: ListDao): ListViewModel{
        return ListViewModel(listDao)
    }

    @Provides
    @Singleton
    fun provideListsUseCases(repository: ListRepository): ListUsecase{
        return ListUsecase(
            getLists = GetListsUseCase(repository),
            deleteList = DeleteList(repository)
        )
    }

}