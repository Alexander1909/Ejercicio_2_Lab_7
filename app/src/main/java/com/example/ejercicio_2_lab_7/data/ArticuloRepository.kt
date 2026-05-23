package com.example.ejercicio_2_lab_7.data

import kotlinx.coroutines.flow.Flow

class ArticuloRepository(private val articuloDao: ArticuloDao) {
    val todosLosArticulos: Flow<List<Articulo>> = articuloDao.listarTodos()

    suspend fun insertar(articulo: Articulo) {
        articuloDao.insertar(articulo)
    }

    suspend fun actualizar(articulo: Articulo) {
        articuloDao.actualizar(articulo)
    }

    suspend fun eliminar(articulo: Articulo) {
        articuloDao.eliminar(articulo)
    }

    suspend fun obtenerPorId(id: Int): Articulo? {
        return articuloDao.obtenerPorId(id)
    }
}
