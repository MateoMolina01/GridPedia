package com.maestre.gridpedia.model.persistencia

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.maestre.gridpedia.model.Piloto
import com.maestre.gridpedia.model.Team

class AdminSQLiteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {
    //Si hay algún cambio en la BBDD, se cambia el número de versión y así automáticamente
    companion object {
        val DATABASE_VERSION: Int = 4
        val DATABASE_NAME: String = "gridpedia.db3"
    }

    constructor(context: Context): this(context, DATABASE_NAME, null, DATABASE_VERSION){
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.e("SQLLite","Paso por onCreate del AdminSQLIteConexion")

        // Crear tabla usuario
        db.execSQL("""
        CREATE TABLE usuario (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            password TEXT NOT NULL
        )
    """)

        // Crear tabla equipo
        db.execSQL("""
        CREATE TABLE equipo (
            id INTEGER PRIMARY KEY,
            nombre TEXT NOT NULL,
            puntos INTEGER NOT NULL,
            color TEXT NOT NULL
        )
    """)

        // Crear tabla piloto
        db.execSQL("""
        CREATE TABLE piloto (
            id INTEGER PRIMARY KEY,
            nombre TEXT NOT NULL,
            puntos INTEGER NOT NULL,
            imagenEquipo TEXT NOT NULL,
            id_equipo INTEGER NOT NULL,
            FOREIGN KEY (id_equipo) REFERENCES equipo(id)
        )
    """)

        db.execSQL("INSERT INTO usuario (nombre, password) VALUES ('admin', 'admin')")

        db.execSQL("INSERT INTO equipo (id, nombre, puntos, color) VALUES (1, 'Ferrari', 400, 'ferrari')")
        db.execSQL("INSERT INTO equipo (id, nombre, puntos, color) VALUES (2, 'Red Bull', 600, 'redbull')")
        db.execSQL("INSERT INTO equipo (id, nombre, puntos, color) VALUES (3, 'Mercedes', 500, 'mercedes')")
        db.execSQL("INSERT INTO equipo (id, nombre, puntos, color) VALUES (4, 'McLaren', 350, 'mclaren')")
        db.execSQL("INSERT INTO equipo (id, nombre, puntos, color) VALUES (5, 'Alpine', 200, 'alpine')")

        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (1, 'Carlos Sainz', 150, 'ferrari', 1)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (2, 'Charles Leclerc', 250, 'ferrari', 1)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (3, 'Max Verstappen', 400, 'redbull', 2)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (4, 'Sergio Pérez', 350, 'redbull', 2)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (5, 'Lewis Hamilton', 300, 'mercedes', 3)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (6, 'George Russell', 200, 'mercedes', 3)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (7, 'Lando Norris', 180, 'mclaren', 4)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (8, 'Oscar Piastri', 170, 'mclaren', 4)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (9, 'Pierre Gasly', 150, 'alpine', 5)")
        db.execSQL("INSERT INTO piloto (id, nombre, puntos, imagenEquipo, id_equipo) VALUES (10, 'Esteban Ocon', 140, 'alpine', 5)")



        Log.e("SQLite", "Tablas creadas correctamente")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e("SQLLite","Paso por OnUpgrade del AdminSQLIteConexion")
        db.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS equipo")
        db.execSQL("DROP TABLE IF EXISTS piloto")
        onCreate(db)


    }

    // Registrar un nuevo usuario
    fun registrarUsuario(contexto: AppCompatActivity, nombre: String, password: String): Long {
        val admin = AdminSQLiteConexion(contexto)
        val db: SQLiteDatabase = admin.writableDatabase
        val registro = ContentValues()

        registro.put("nombre", nombre)
        registro.put("password", password)

        val returnData = db.insert("usuario", null, registro)

        db.close()
        return returnData
    }

    fun comprobarUsuario(contexto: AppCompatActivity, nombre: String, password: String): Boolean {
        val admin = AdminSQLiteConexion(contexto)
        val db: SQLiteDatabase = admin.readableDatabase

        val cursor = db.rawQuery(
            "SELECT id FROM usuario WHERE nombre = ? AND password = ?",
            arrayOf(nombre, password)
        )

        val existe = cursor.moveToFirst() // Devuelve true si encuentra al menos una fila
        cursor.close()
        db.close()
        return existe
    }

    fun obtenerUsuarios(contexto: AppCompatActivity): ArrayList<String> {
        val usuarios = ArrayList<String>()
        val admin = AdminSQLiteConexion(contexto)
        val db: SQLiteDatabase = admin.readableDatabase

        val cursor = db.rawQuery("SELECT nombre FROM usuario", null)
        while (cursor.moveToNext()) {
            usuarios.add(cursor.getString(0)) // Obtiene el nombre
        }

        cursor.close()
        db.close()
        return usuarios
    }

    fun eliminarUsuario(contexto: AppCompatActivity, nombre: String): Int {
        val admin = AdminSQLiteConexion(contexto)
        val db: SQLiteDatabase = admin.writableDatabase

        val filasEliminadas = db.delete("usuario", "nombre = ?", arrayOf(nombre))
        db.close()
        return filasEliminadas
    }

    fun obtenerPilotos(contexto: AppCompatActivity): List<Piloto> {
        val pilotos = ArrayList<Piloto>()// Lista de pares (nombre, puntos)
        val admin = AdminSQLiteConexion(contexto)
        val db: SQLiteDatabase = admin.readableDatabase

        val cursor = db.rawQuery("SELECT id, nombre, puntos, imagenEquipo, id_equipo FROM piloto", null) // Consulta solo las columnas necesarias
        while (cursor.moveToNext()) {
            pilotos.add(
                Piloto(
                    id = cursor.getInt(0),
                    nombre = cursor.getString(1),
                    puntos = cursor.getInt(2),
                    imagenEquipo = cursor.getString(3),
                    idEquipo = cursor.getInt(4)
                )
            )
        }

        cursor.close()
        db.close()
        return pilotos
    }

    fun obtenerEquipos(context: Context): List<Team> {
        val equipos = ArrayList<Team>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT id, nombre, puntos, color FROM equipo", null)

        while (cursor.moveToNext()) {
            equipos.add(
                Team(
                    id = cursor.getInt(0),
                    nombre = cursor.getString(1), // Nombre del equipo
                    puntos = cursor.getInt(2),    // Puntos
                    color = cursor.getString(3)   // Color en formato hexadecimal (#RRGGBB)
                )
            )
        }

        cursor.close()
        db.close()
        return equipos
    }
}