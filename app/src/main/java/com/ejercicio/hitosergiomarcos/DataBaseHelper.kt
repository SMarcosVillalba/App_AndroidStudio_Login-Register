package com.ejercicio.hitosergiomarcos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "tienda.db", null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val crearTablaUsers = "CREATE TABLE usuario "+
                "(idusuario INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre VARCHAR, psw VARCHAR);"

        val crearTablaProductos = "CREATE TABLE producto "+
                "(idproducto INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, cantidad INTEGER, " +
                "idusuario INTEGER, FOREIGN KEY(idusuario) REFERENCES usuario(idusuario));"

        p0!!.execSQL(crearTablaUsers)
        p0!!.execSQL(crearTablaProductos)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val borrarTablaUsers = "DROP TABLE IF EXISTS usuario;"
        val borrarTablaProductos = "DROP TABLE IF EXISTS producto;"
        p0!!.execSQL(borrarTablaUsers)
        p0!!.execSQL(borrarTablaProductos)

        onCreate(p0)
    }

    fun addUsuario(nombre: String, psw: String){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("psw", psw)

        val db= this.writableDatabase
        db.insert("usuario", null, datos)
        db.close()
    }

    fun addProducto(nombre: String, cantidad: Int, iduser: Int){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("cantidad", cantidad)
        datos.put("idusuario", iduser)

        val db= this.writableDatabase
        db.insert("producto", null, datos)
        db.close()
    }


}