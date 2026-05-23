package com.example.ejercicio_2_lab_7.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ArticuloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ArticuloRepository
    val todosLosArticulos: Flow<List<Articulo>>

    init {
        val articuloDao = AppDatabase.getDatabase(application).articuloDao()
        repository = ArticuloRepository(articuloDao)
        todosLosArticulos = repository.todosLosArticulos
    }

    fun insertar(articulo: Articulo) = viewModelScope.launch {
        repository.insertar(articulo)
    }

    fun actualizar(articulo: Articulo) = viewModelScope.launch {
        repository.actualizar(articulo)
    }

    fun eliminar(articulo: Articulo) = viewModelScope.launch {
        repository.eliminar(articulo)
    }
}
