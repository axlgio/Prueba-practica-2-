package com.proyect.pruebaprctica2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyect.pruebaprctica2.dao.SuperDao
import com.proyect.pruebaprctica2.entities.Producto


@Database(entities = [Producto::class], version = 1, exportSchema = false)

abstract class superDatabase : RoomDatabase() {
    abstract fun SuperDao(): SuperDao

    companion object {

        @Volatile
        private var INSTANCE: superDatabase? = null
        fun getDatabase(context: Context): superDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    superDatabase::class.java,
                    "producto_database"
                ).build()
                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
}