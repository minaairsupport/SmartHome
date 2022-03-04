package com.massive.smarthome.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [] , version = 1 , exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{
        @Volatile
        private var INSTANCE : AppDataBase?= null

        fun getDatabase(context: Context): AppDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDataBase(context).also {
                INSTANCE = it
            }
        }
        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context , AppDataBase::class.java, "SmartHomeDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }




}