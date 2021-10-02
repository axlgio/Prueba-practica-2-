package com.proyect.pruebaprctica2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.proyect.pruebaprctica2.databinding.ActivityAddSuperBinding
import com.proyect.pruebaprctica2.entities.Producto
import com.proyect.pruebaprctica2.repository.superRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class addSuperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSuperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSuperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }
    private fun addListener() {
        val repository = superRepository.getRepository(this)
        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etNombre.text.isBlank() || etCantidad.text.isBlank() || etPrecio.text.isBlank()) {
                    Snackbar.make(this.root, "Some fields are empty", Snackbar.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                Producto(
                                    name = etNombre.text.toString(),
                                    cantidad = etCantidad.text.toString().toInt(),
                                    precio = etPrecio.text.toString().toDouble()
                                )
                            )
                        }
                        onBackPressed()
                    }

                }
            }

        }
    }

    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}
