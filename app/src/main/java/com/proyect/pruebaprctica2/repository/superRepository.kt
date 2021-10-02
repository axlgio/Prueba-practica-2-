package com.proyect.pruebaprctica2.repository

import android.content.Context
import com.proyect.pruebaprctica2.dao.SuperDao
import com.proyect.pruebaprctica2.database.superDatabase
import com.proyect.pruebaprctica2.entities.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class superRepository(private val SuperDao: SuperDao) {
    companion object {
        private var INSTANCE: superRepository? = null
        fun getRepository(context: Context): superRepository {
            return INSTANCE ?: synchronized(this) {
                val database = superDatabase.getDatabase(context)
                val instance = superRepository(database.SuperDao())
                INSTANCE = instance
                instance
            }
        }
    }

    // Room executes all queries on a separate thread.
// Observed Flow will notify the observer when the data has changed.
    val allProducto: Flow<List<Producto>> = SuperDao.getProductoAddList()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
// implement anything else to ensure we're not doing long running database work
// off the main thread.
    suspend fun insert(producto: Producto) {
        SuperDao.insert(producto)
    }

    suspend fun deteleId(id: Int){
        SuperDao.deleteId(id)
    }




}