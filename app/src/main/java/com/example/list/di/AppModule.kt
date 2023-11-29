package com.example.list.di

import android.content.Context
import androidx.room.Room
import com.example.list.data.AppDatabase
import com.example.list.data.ListDao
import com.example.list.data.ListRepository
import com.example.list.loginout.UserSessionManager
import com.example.list.userdata.UserDao
import com.example.list.userdata.UserRepository
import com.example.list.userlists.UserListsDao
import com.example.list.userlists.UserListsRepository
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
    @Singleton
    fun provideListRepository(dao: ListDao) = ListRepository(dao)

    @Provides
    @Singleton
    fun providesUserDao(database: AppDatabase) = database.userDao()

    @Provides
    @Singleton
    fun providesUserRepository(dao: UserDao) =
        UserRepository(dao)


    @Provides
    @Singleton
    fun providesUserListsData(database: AppDatabase) = database.userWithListsDao()

    @Provides
    @Singleton
    fun providesUserListRepository(
        userWithLists: UserListsDao
    ) =
        UserListsRepository(userWithLists)

    @Provides
    @Singleton
    fun providesUserSession(): UserSessionManager {
        return UserSessionManager()
    }
    
}
