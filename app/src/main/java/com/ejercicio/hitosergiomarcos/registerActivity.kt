package com.ejercicio.hitosergiomarcos

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ejercicio.hitosergiomarcos.databinding.ActivityRegisterBinding

class registerActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var  dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DataBaseHelper(this)
        binding.btnRegistrar.setOnClickListener(){
            try {
                if(binding.etNombre.text.isNotBlank() && binding.etPsw.text.isNotBlank()){
                    val db: SQLiteDatabase = dbHelper.readableDatabase
                    val cursor = db.rawQuery("SELECT * FROM usuario",null)

                    if (cursor.moveToFirst()){
                        do {
                            if(!cursor.getString(1).toString().equals(binding.etNombre.text.toString()) && !cursor.getString(2).toString().equals(binding.etPsw.text.toString())){
                                dbHelper.addUsuario(binding.etNombre.text.toString(), binding.etPsw.text.toString())
                                val intent: Intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_LONG).show()
                            }
                        }while (cursor.moveToNext())
                    }else{
                        dbHelper.addUsuario(binding.etNombre.text.toString(), binding.etPsw.text.toString())
                        val intent: Intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }


                }else{
                    Toast.makeText(this, "Usuario no registrado con éxito", Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){
                println(e)
            }


        }
        binding.btnLoginActivity.setOnClickListener(){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}