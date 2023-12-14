package com.example.list.data.list

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.list.data.additems.AddItemsCustom
import com.example.list.data.additems.AddItemsDao
import com.example.list.data.userdata.User
import com.example.list.data.userdata.UserDao
import com.example.list.data.userlists.UserListsDao

@Database(
    entities = [ListEntity::class, User::class, AddItemsCustom::class],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listDao(): ListDao
    abstract fun userDao(): UserDao
    abstract fun userWithListsDao(): UserListsDao
    abstract fun addItemCustomDao(): AddItemsDao

    companion object {
        val DATABASE = "database"
    }
}