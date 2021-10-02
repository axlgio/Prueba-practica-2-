package com.proyect.pruebaprctica2

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.proyect.pruebaprctica2.databinding.ItemProductoBinding
import com.proyect.pruebaprctica2.entities.Producto
import com.proyect.pruebaprctica2.repository.superRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlin.random.Random


@Suppress("MemberVisibilityCanBePrivate")
class productoAdapter(private val list: List<Producto>, private val context: Context) :
    RecyclerView.Adapter<productoAdapter.productoViewHolder>() {


    class productoViewHolder(val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productoViewHolder {
        val binding =
            ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return productoViewHolder(binding)


    }

    override fun onBindViewHolder(holder: productoViewHolder, position: Int) {


        with(holder.binding) {
            tvProducto.text = list[position].name
            tvCantidad.text = "x"+list[position].cantidad.toString()

            val multi: Double = list[position].cantidad * list[position].precio

            tvPrecio.text = "$"+multi.toString()


            ImDelete.setOnClickListener {

                val repository = superRepository.getRepository(context)
                GlobalScope.launch {
                    repository.deteleId(list[position].id)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size


}