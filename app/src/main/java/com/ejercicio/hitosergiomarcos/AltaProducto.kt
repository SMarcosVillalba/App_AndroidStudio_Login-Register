package com.ejercicio.hitosergiomarcos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ejercicio.hitosergiomarcos.databinding.ActivityAltaProductoBinding


class AltaProducto : AppCompatActivity() {

    lateinit var binding: ActivityAltaProductoBinding
    lateinit var  dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAltaProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DataBaseHelper(this)

        val bundle= intent.extras
        val nombre = bundle?.getString("nombre")
        val id = bundle?.getInt("id")
        val cantidadProducto= binding.etCantidadProducto.text
        val nombreProducto = binding.etNombreProducto.text

        binding.tvBienvenida.text = "Bienvenido/a "+nombre

        binding.btnEnviar.setOnClickListener(){
            try{
                if (cantidadProducto.isNotBlank()
                    &&
                    nombreProducto.isNotBlank()){
                    if (id != null) {
                        dbHelper.addProducto(nombreProducto.toString(),
                            cantidadProducto.toString().toInt(),
                            id)
                        Toast.makeText(this, "Producto solicitado con éxito : "+nombreProducto+" "+cantidadProducto+" unidades", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "No se ha podido solicitar el producto, faltan datos.", Toast.LENGTH_LONG).show()

                }
                binding.etNombreProducto.text= null
                binding.etCantidadProducto.text= null
            }catch (e: Exception){
                println(e)
            }
        }

        binding.btnSalir.setOnClickListener(){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}