package com.si.Models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "app_database"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "user"
        const val COLUMN_CODE = "code"
        const val COLUMN_NAME = "name"
        const val COLUMN_CPF = "cpf"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_CODE INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_CPF TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_PHONE TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }
}
