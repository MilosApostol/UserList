package com.example.list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.list.userdata.UserDao

@Database(entities = [ListEntity::class, User::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

<<<<<<< HEAD
@Database(entities = [ListEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
=======
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
    abstract fun listDao(): ListDao
    abstract fun userDao(): UserDao

<<<<<<< HEAD
    companion object {
        const val DATABASE = "data_base"
=======
    companion object{
        val DATABASE = "database"
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
    }
}
