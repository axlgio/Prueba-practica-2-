package com.proyect.pruebaprctica2.dao

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.proyect.pruebaprctica2.entities.Producto;
import kotlinx.coroutines.flow.Flow


@Dao
interface SuperDao {
    @Query("SELECT * FROM producto_table ORDER BY nombre ASC")
    fun getProductoAddList(): Flow<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Producto: Producto)

    @Query("DELETE FROM producto_table WHERE id= :id")
    suspend fun deleteId(id: Int)


}





