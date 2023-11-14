package com.example.list.data

<<<<<<< HEAD
import android.content.Context
=======
>>>>>>> origin/to-be-renewed
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
<<<<<<< HEAD

=======
>>>>>>> origin/to-be-renewed
    abstract fun listDao(): ListDao

    companion object{
        const val DATABASE = "data_base"
<<<<<<< HEAD
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{
                    instance = it
                }
            }
        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, AppDatabase.DATABASE)
                .fallbackToDestructiveMigration()
                .build()
=======
>>>>>>> origin/to-be-renewed
    }
}