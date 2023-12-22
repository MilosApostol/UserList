package com.example.list.di

import android.content.Context
import androidx.room.Room
import com.example.list.data.additems.AddCustomViewModel
import com.example.list.data.additems.AddItemsCustom
import com.example.list.data.additems.AddItemsDao
import com.example.list.data.additems.AddItemsRepository
import com.example.list.data.list.AppDatabase
import com.example.list.data.list.ListDao
import com.example.list.data.list.ListRepository
import com.example.list.data.userdata.UserDao
import com.example.list.data.userdata.UserRepository
import com.example.list.data.userlists.UserListsDao
import com.example.list.data.userlists.UserListsRepository
import com.example.list.sessionmanager.UserSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
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
    fun providesUserRepository(dao: UserDao, userSessionManager: UserSessionManager) =
        UserRepository(dao, userSessionManager)


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

    @Provides
    @Singleton
    fun providesAddCustomRepository(dao: AddItemsDao) =
        AddItemsRepository(dao)

    @Provides
    @Singleton
    fun providesAddCustomDao(database: AppDatabase) = database.addItemCustomDao()

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}
