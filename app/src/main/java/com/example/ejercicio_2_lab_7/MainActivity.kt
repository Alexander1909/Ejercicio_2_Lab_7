package com.example.ejercicio_2_lab_7

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_2_lab_7.data.Articulo
import com.example.ejercicio_2_lab_7.data.ArticuloViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var articuloViewModel: ArticuloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Manejo de Insets para diseño Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Configurar RecyclerView con el nuevo ArticuloAdapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ArticuloAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Inicializar ViewModel (que ya usa el patrón Repository)
        articuloViewModel = ViewModelProvider(this).get(ArticuloViewModel::class.java)

        // 3. Observar cambios usando repeatOnLifecycle (Punto 31)
        lifecycleScope.launch {
            // repeatOnLifecycle ejecuta el bloque cuando la Activity está en el estado indicado (STARTED)
            // y cancela la corrutina automáticamente cuando sale de ese estado.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                articuloViewModel.todosLosArticulos.collect { articulos ->
                    adapter.submitList(articulos)
                }
            }
        }

        // 4. Botón para agregar un artículo de prueba
        findViewById<Button>(R.id.btnAddUser).setOnClickListener {
            val nuevoArticulo = Articulo(
                nombre = "Artículo ${adapter.itemCount + 1}",
                descripcion = "Descripción del artículo ${adapter.itemCount + 1}"
            )
            articuloViewModel.insertar(nuevoArticulo)
        }
    }
}
