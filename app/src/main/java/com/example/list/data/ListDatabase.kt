package com.example.list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.list.userdata.UserDao

@Database(entities = [ListEntity::class, User::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun listDao(): ListDao
    abstract fun userDao(): UserDao

    companion object{
        val DATABASE = "database"
    }
}