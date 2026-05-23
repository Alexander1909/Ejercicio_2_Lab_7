package com.example.ejercicio_2_lab_7.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticuloDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(articulo: Articulo)

    @Update
    suspend fun actualizar(articulo: Articulo)

    @Delete
    suspend fun eliminar(articulo: Articulo)

    @Query("SELECT * FROM articulos ORDER BY id ASC")
    fun listarTodos(): Flow<List<Articulo>>

    @Query("SELECT * FROM articulos WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Articulo?
}
