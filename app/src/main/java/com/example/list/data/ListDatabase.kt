package com.example.list.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.list.userdata.UserDao
import com.example.list.userlists.UserListsDao

@Database(entities = [ListEntity::class, User::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun listDao(): ListDao
    abstract fun userDao(): UserDao
    abstract fun userWithListsDao(): UserListsDao

    companion object{
        val DATABASE = "database"
    }
}