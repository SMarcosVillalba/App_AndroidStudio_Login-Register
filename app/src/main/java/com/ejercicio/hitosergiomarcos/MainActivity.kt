package com.ejercicio.hitosergiomarcos

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ejercicio.hitosergiomarcos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  dbHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DataBaseHelper(this)

        binding.btnLogin.setOnClickListener(){
            binding.etUsuarioLogin.text.toString()
            binding.etPswLogin.text.toString()

            try{
                val db:SQLiteDatabase = dbHelper.readableDatabase
                val cursor = db.rawQuery("SELECT * FROM usuario",null)

                if (cursor.moveToFirst()){
                    do {
                        if(cursor.getString(1).toString().equals(binding.etUsuarioLogin.text.toString()) && cursor.getString(2).toString().equals(binding.etPswLogin.text.toString())){
                            val intent: Intent = Intent(this, AltaProducto::class.java)
                            val nombre = cursor.getString(1)
                            val id= cursor.getInt(0)
                            intent.putExtra("id", id)
                            intent.putExtra("nombre", nombre)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                        }
                    }while (cursor.moveToNext())
                }
            }catch (e: Exception){
                println(e)
            }

        }

        binding.btnRegistrarActivity.setOnClickListener(){

            val intent:Intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }

    }
}