package com.proyect.pruebaprctica2.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "producto_table")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nombre")
    val name: String,
    @ColumnInfo(name = "precio")
    val precio: Double,
    @ColumnInfo(name = "cantidad")
    val cantidad: Int,

        )