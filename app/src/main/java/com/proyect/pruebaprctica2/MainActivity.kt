package com.proyect.pruebaprctica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.proyect.pruebaprctica2.databinding.ActivityMainBinding
import com.proyect.pruebaprctica2.repository.superRepository
import com.proyect.pruebaprctica2.repository.superRepository.Companion.getRepository
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()
    }

    private fun buildList() {

        val repository = superRepository.getRepository(this)

        val layoutManager = GridLayoutManager(this, 1)




        lifecycleScope.launch {
            repository.allProducto.collect { productos ->

                var suma = 0.0

                productos.forEach {

                    var s = it.precio * it.cantidad

                    suma += s
                }




                binding.tvTotal.setText("$"+suma.toString())


                binding.rvProducto.apply {
                    adapter = productoAdapter(productos, this@MainActivity)
                    setLayoutManager(layoutManager)
                }

            }
        }

    }

    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, addSuperActivity::class.java))
        }
    }
}

